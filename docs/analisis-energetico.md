# Documentación API - Análisis Energético

## Endpoint

**Método:** POST

**Rutas:**

- `/v1/analisis-energetico` (usa el modelo de Regresión Logística)
- `/v2/analisis-energetico` (usa el modelo de Random Forest)

La versión en la ruta determina qué modelo se usa para la predicción.

### Descripción

Este endpoint recibe información de consumo energético y devuelve una clasificación energética junto con recomendaciones.

## Parámetros de entrada

| Campo | Tipo | Obligatorio | Descripción |
|---------|---------|---------|---------|
| consumo_kwh | Number | Sí | Consumo mensual de energía |
| uso_horario_pico | Boolean | Sí | Indica si existe consumo en horario pico |
| horas_alto_consumo | Integer | Sí | Horas diarias de alto consumo |
| cantidad_equipos | Integer | Sí | Cantidad de equipos eléctricos |
| cantidad_personas | Integer | Sí | Personas que utilizan el inmueble |
| tipo_inmueble | String | Sí | Casa, Departamento o Comercio |
| mes | Integer | Sí | Mes analizado (1-12) |

## Ejemplos 


### Ejemplo 1 - Eficiente
### Request
```json
{
  "consumo_kwh": 130,
  "uso_horario_pico": false,
  "horas_alto_consumo": 2,
  "cantidad_equipos": 4,
  "cantidad_personas": 5,
  "tipo_inmueble": "Departamento",
  "mes": 5
}
```
### Response
```json
{
	"categoria": "Eficiente",
	"probabilidad": 0.97,
	"recomendaciones": [
		{
			"prioridad": "Información",
			"impacto": "Sin cambios",
			"mensaje": "El consumo energético es adecuado. Mantenga los hábitos actuales."
		}
	],
	"costo_estimado_mensual": 97.5,
	"indicadores": {
		"consumo_por_equipo": 32.5,
		"consumo_por_persona": 26.0,
		"consumo_por_hora": 65.0
	}
}
```
---

## Ejemplo 2 - Moderado

### Request

```json
{
  "consumo_kwh": 450,
  "uso_horario_pico": true,
  "horas_alto_consumo": 3,
  "cantidad_equipos": 9,
  "cantidad_personas": 6,
  "tipo_inmueble": "Casa",
  "mes": 7
}
```
### Response
```json
{
	"categoria": "Moderado",
	"probabilidad": 0.71,
	"recomendaciones": [
		{
			"prioridad": "Alta",
			"impacto": "Alto",
			"mensaje": "Evite utilizar varios equipos de alto consumo simultáneamente durante el horario pico."
		},
		{
			"prioridad": "Baja",
			"impacto": "Bajo",
			"mensaje": "Mejorar el aislamiento de puertas y ventanas ayuda a reducir el uso de calefacción."
		}
	],
	"costo_estimado_mensual": 337.5,
	"indicadores": {
		"consumo_por_equipo": 50.0,
		"consumo_por_persona": 75.0,
		"consumo_por_hora": 150.0
	}
}
```
---

## Ejemplo 3 - Ineficiente

### Request

```json
{
  "consumo_kwh": 850,
  "uso_horario_pico": true,
  "horas_alto_consumo": 8,
  "cantidad_equipos": 15,
  "cantidad_personas": 2,
  "tipo_inmueble": "Comercio",
  "mes": 1
}
```
### Response
```json
{
  "categoria": "Ineficiente",
  "probabilidad": 0.81,
  "recomendaciones": [
    {
      "prioridad": "Alta",
      "impacto": "Alto",
      "mensaje": "Evite utilizar varios equipos de alto consumo simultáneamente durante el horario pico."
    },
    {
      "prioridad": "Media",
      "impacto": "Medio",
      "mensaje": "El consumo por persona es elevado. Revise los hábitos de uso dentro del inmueble."
    },
    {
      "prioridad": "Media",
      "impacto": "Medio",
      "mensaje": "Una mejor distribución del consumo diario puede mejorar la eficiencia energética."
    },
    {
      "prioridad": "Baja",
      "impacto": "Bajo",
      "mensaje": "Configure el aire acondicionado entre 24°C y 26°C para reducir el consumo."
    },
    {
      "prioridad": "Alta",
      "impacto": "Alto",
      "mensaje": "Se detectó un consumo significativamente superior al habitual."
    }
  ],
  "costo_estimado_mensual": 637.5,
  "indicadores": {
    "consumo_por_equipo": 56.67,
    "consumo_por_persona": 425.0,
    "consumo_por_hora": 106.25
  }
}
```

## Ejemplo de error

### Request

```json
{
  "consumo_kwh": 420,
  "uso_horario_pico": true,
  "horas_alto_consumo": 8,
  "cantidad_equipos": 10,
  "cantidad_personas": 4,
  "tipo_inmueble": "Casa"
}
```
### Response
```json
{
"mes": "El mes es obligatorio"
}
400 Bad Request

```