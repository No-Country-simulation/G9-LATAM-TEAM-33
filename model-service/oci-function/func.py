import hashlib
import io
import json
import logging
import joblib
import pandas as pd
import sklearn
import oci

from fdk import response

MENSAJES = {
    "horario_pico": [
        "Reducir el uso de equipos durante los horarios pico puede disminuir significativamente el costo de la energía.",
        "Trasladar parte del consumo fuera del horario pico ayuda a mejorar la eficiencia energética.",
        "Evite utilizar varios equipos de alto consumo simultáneamente durante el horario pico."
    ],
    "consumo_equipo_alto": [
        "El consumo promedio por equipo es elevado. Revise electrodomésticos antiguos o de baja eficiencia energética.",
        "Cada equipo presenta un consumo superior al esperado. Considere reemplazar los de mayor antigüedad.",
        "Se detectó un consumo elevado por dispositivo. Una revisión de los equipos puede generar importantes ahorros."
    ],
    "consumo_equipo_bajo": [
        "El consumo promedio por equipo es bajo. Los dispositivos parecen utilizarse eficientemente.",
        "Se observa un buen aprovechamiento de los equipos instalados.",
        "El consumo por dispositivo es reducido, indicando un uso eficiente."
    ],
    "consumo_persona_alto": [
        "El consumo por persona es elevado. Revise los hábitos de uso dentro del inmueble.",
        "Cada ocupante presenta un consumo superior al promedio esperado.",
        "El consumo energético por persona puede optimizarse mediante pequeños cambios de hábito."
    ],
    "horas_alto_consumo": [
        "Distribuya las actividades de mayor consumo durante el día para reducir picos de demanda.",
        "Evite concentrar todas las actividades eléctricas en pocas horas.",
        "Una mejor distribución del consumo diario puede mejorar la eficiencia energética."
    ],
    "paneles": [
        "Por el nivel de consumo, podría evaluar la instalación de paneles solares.",
        "Una vivienda con este consumo puede beneficiarse de energía solar fotovoltaica.",
        "El consumo registrado justifica analizar alternativas de generación renovable."
    ],
    "verano": [
        "Configure el aire acondicionado entre 24°C y 26°C para reducir el consumo.",
        "Evite temperaturas demasiado bajas en el aire acondicionado durante el verano.",
        "Utilice ventilación natural cuando sea posible antes de recurrir al aire acondicionado."
    ],
    "invierno": [
        "Mejorar el aislamiento de puertas y ventanas ayuda a reducir el uso de calefacción.",
        "Revise posibles pérdidas de calor para disminuir el consumo energético.",
        "Un buen aislamiento térmico puede reducir considerablemente el consumo en invierno."
    ],
    "consumo_muy_alto": [
        "El consumo mensual es muy elevado. Se recomienda realizar una auditoría energética.",
        "Se detectó un consumo significativamente superior al habitual.",
        "Conviene identificar los equipos responsables del mayor consumo eléctrico."
    ],
    "todo_correcto": [
        "El consumo energético es adecuado. Mantenga los hábitos actuales.",
        "No se detectan oportunidades importantes de mejora.",
        "El perfil energético es saludable. Continúe con las buenas prácticas."
    ]
}

def elegir_mensaje(clave, opciones):
    indice = (
        int(hashlib.md5(clave.encode()).hexdigest(), 16)
        % len(opciones)
    )
    return opciones[indice]

def calcular_indicadores(datos):
    return {
        "consumo_equipo": datos["consumo_kwh"] / max(datos["cantidad_equipos"], 1),
        "consumo_persona": datos["consumo_kwh"] / max(datos["cantidad_personas"], 1),
        "consumo_hora": datos["consumo_kwh"] / max(datos["horas_alto_consumo"], 1)
    }

def generar_recomendaciones(datos, indicadores):
    recomendaciones = []
    if datos["uso_horario_pico"]:
        recomendaciones.append({"prioridad": "Alta", "impacto": "Alto",
            "mensaje": elegir_mensaje("horario_pico", MENSAJES["horario_pico"])})
    if indicadores["consumo_equipo"] > 60:
        recomendaciones.append({"prioridad": "Alta", "impacto": "Alto",
            "mensaje": elegir_mensaje("equipo_alto", MENSAJES["consumo_equipo_alto"])})
    elif indicadores["consumo_equipo"] < 30:
        recomendaciones.append({"prioridad": "Baja", "impacto": "Bajo",
            "mensaje": elegir_mensaje("equipo_bajo", MENSAJES["consumo_equipo_bajo"])})
    if indicadores["consumo_persona"] > 150:
        recomendaciones.append({"prioridad": "Media", "impacto": "Medio",
            "mensaje": elegir_mensaje("persona_alta", MENSAJES["consumo_persona_alto"])})
    if datos["horas_alto_consumo"] >= 8:
        recomendaciones.append({"prioridad": "Media", "impacto": "Medio",
            "mensaje": elegir_mensaje("horas", MENSAJES["horas_alto_consumo"])})
    if datos["tipo_inmueble"] == "Casa" and datos["consumo_kwh"] > 550:
        recomendaciones.append({"prioridad": "Media", "impacto": "Alto",
            "mensaje": elegir_mensaje("solar", MENSAJES["paneles"])})
    if datos["mes"] in [12, 1, 2]:
        recomendaciones.append({"prioridad": "Baja", "impacto": "Bajo",
            "mensaje": elegir_mensaje("verano", MENSAJES["verano"])})
    if datos["mes"] in [6, 7, 8]:
        recomendaciones.append({"prioridad": "Baja", "impacto": "Bajo",
            "mensaje": elegir_mensaje("invierno", MENSAJES["invierno"])})
    if datos["consumo_kwh"] > 650:
        recomendaciones.append({"prioridad": "Alta", "impacto": "Alto",
            "mensaje": elegir_mensaje("alto", MENSAJES["consumo_muy_alto"])})
    if len(recomendaciones) == 0:
        recomendaciones.append({"prioridad": "Información", "impacto": "Sin cambios",
            "mensaje": elegir_mensaje("ok", MENSAJES["todo_correcto"])})
    return recomendaciones

def calcular_costo(consumo):
    TARIFA = 0.75
    return round(consumo * TARIFA, 2)

def generar_respuesta(datos, categoria, probabilidad):
    indicadores = calcular_indicadores(datos)
    return {
        "categoria": categoria,
        "probabilidad": round(float(probabilidad), 2),
        "costo_estimado_mensual": calcular_costo(datos["consumo_kwh"]),
        "indicadores": {
            "consumo_por_equipo": round(indicadores["consumo_equipo"], 2),
            "consumo_por_persona": round(indicadores["consumo_persona"], 2),
            "consumo_por_hora": round(indicadores["consumo_hora"], 2)
        },
        "recomendaciones": generar_recomendaciones(datos, indicadores)
    }


def handler(ctx, data: io.BytesIO = None):
    logger = logging.getLogger()
    try:
        signer = oci.auth.signers.get_resource_principals_signer()
        object_storage = oci.object_storage.ObjectStorageClient(
            config={}, signer=signer)
        namespace = object_storage.get_namespace().data
    except Exception as ex:
        logger.info(f"error: {str(ex)}")
        raise

    try:
        bucket_response = object_storage.get_object(
            namespace_name=namespace, bucket_name="hackathon-model-bucket", 
            object_name="live/modelo_consumo.pkl")
        model_bytes = bucket_response.data.content
        model = joblib.load(io.BytesIO(model_bytes))
    except (Exception, ValueError) as ex:
        logger.info('error: ' + str(ex))
        return response.Response(
            ctx,
            response_data=json.dumps({"error": f"Error cargando el modelo: {str(ex)}"}, ensure_ascii=False),
            headers={"Content-Type": "application/json"},
            status=500
        )

    try:
        body = json.loads(data.getvalue())
        df = pd.DataFrame([body])
        
        categoria = model.predict(df)[0]
        probabilidades = model.predict_proba(df)[0]
        probabilidad = max(probabilidades)
        
        resultado = generar_respuesta(body, categoria, probabilidad)
        
        return response.Response(
            ctx,
            response_data=json.dumps(resultado, ensure_ascii=False),
            headers={"Content-Type": "application/json"}
        )
    except Exception as ex:
        logger.info('error: ' + str(ex))
        return response.Response(
            ctx,
            response_data=json.dumps({"error": f"Error procesando la predicción: {str(ex)}"}, ensure_ascii=False),
            headers={"Content-Type": "application/json"},
            status=400
        )
