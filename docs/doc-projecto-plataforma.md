# **LeafWatt**

# Descripcion

Proyecto desarrollado para el **Hackathon ONE G9 de Alura Latam + Oracle Next Education**.

Una solución inteligente que utiliza Ciencia de Datos y Machine Learning para analizar patrones de consumo energético, clasificar perfiles de eficiencia y generar recomendaciones personalizadas que ayuden a reducir costos y fomentar un consumo responsable.

Busca responder la pregunta **'Estoy mal utilizando la electricidad ?'**, cargando tus datos en nuestra aplicacion recibiras rapidamente un analisis, categorizacion y recomendaciones.

**Pruebala ahora gratis !**
[Probar la app](https://leafwatt.ddns.net/)
[Video del projecto](https://youtube.com)

# Objetivos de la Hackathon

Desarrollar un MVP funcional capaz de:

- Analizar patrones de consumo energético.
- Clasificar perfiles de eficiencia energética.
- Generar recomendaciones de mejora.
- Estimar impactos financieros con base en una tarifa de referencia.
- Poner los resultados a disposición mediante una API REST.
- Utilizar al menos un servicio OCI como parte de la arquitectura de la solución.

# Tecnologias que ocupamos

#### Lenguajes

- Java 17
- Python 3.12
- JSON

#### Machine Learning

- Python
- Scikit-learn
- Pandas
- Numpy
- Joblib
- Regresión Logística
- Random Forest

### Cloud Computing

- Oracle Cloud Infrastructure (OCI)
- Object Storage (OCI)
- OCI Compute

### Front-End

- HTML5
- CSS3
- JavaScript

### Backend

- Spring Boot
- FastAPI
- nginx
- Uvicorn

### Database

- PostgreSQL
- Hibernate / Spring Data JPA

### DevOps

- Git + Github Actions

### Deployment

- Certbot / Let's Encrypt
- NoIP.com

# Logros

El equipo logro lo siguiente:

-Analisis de patrones de consumo
-Clasificacion de perfiles de eficiencia energetica
-2 Modelos entrenados
-Graficos para visualizar patrones
-API rest para hacer peticiones
-Integracion con OCI
-Front End para facilitar el uso de la API

# API Rest e Integracion OCI

Nuestra **API Rest** fue realizada con **Spring Boot** y vive en una Instancia **Compute** de **OCI**.
A traves de ella los clientes pueden hacer peticiones, ocupando el **Front End** o los end points publicos para realizar un analisis de gastos energetico.
Dentro de la misma Instancia vive una **FastAPI** que se preocupa de tomar los datos del cliente y ocuparlos para hacer una prediccion en Python. Esta segunda API es de acceso privado y no recibe peticiones desde el internet.
Ocupamos **Object Storage** para guardar y versionar los modelos serializados.

# Ejecucion

Para ocupar la aplicacion sigua los siguientes pasos:

### 1- Front End

Link a aplicacion - [LeafWatt](https://leafwatt.ddns.net/)
Dentro de la aplicacion rellene sus datos en el formulario y presione el boton **'Calcular'**

### 2- Peticion directa

En una aplicacion como **Postman** manda una peticion POST a https://leafwatt.ddns.net/{version}/analisis-energetico con un payload **JSON** como:

```
{
    "consumo_kwh": 30,
    "uso_horario_pico": true,
    "horas_alto_consumo": 10,
    "cantidad_equipos": 2,
    "cantidad_personas": 2,
    "tipo_inmueble": "Casa",
    "mes":1
}
```

{version} puede ser 'v1' o 'v2'

# Equipo

- Josue Marquez Sanchez - [Linkedin](https://www.linkedin.com/in/josuemarquez/)
- Matias Marquez
- Isaac Ruiz
- Angela Balta - [Linkedin](https://www.linkedin.com/in/angela-balta-412506140)
- Tomas Raggio - [Github](https://github.com/innit-tomi)
- Jhon Rodriguez
- Angie Alejandra Vega - [Linkedin](https://www.linkedin.com/in/angiealejandravegaromero)
- Jhon Fernando Gomez - [Linkedin](https://www.linkedin.com/in/jhon-fernando-gómez-villa-4a6bb6341)

# Licencia

Este proyecto fue desarrollado con fines educativos como parte del **Hackathon ONE G9 - Alura Latam + Oracle Next Education**.

No está destinado a uso comercial.
