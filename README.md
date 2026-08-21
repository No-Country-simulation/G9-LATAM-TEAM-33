![Logo](assets/Logos/Portada%20Leaf%20Watt%201.png)

![https://www.youtube.com/channel/UCgF66HaRWpx01yt8DTqbQzA](https://img.shields.io/badge/YouTube-%23FF0000.svg?style=for-the-badge&logo=YouTube&logoColor=white)
![https://trello.com/b/c3USs77B/g9-latam-team-33](https://img.shields.io/badge/Trello-%23026AA7.svg?style=for-the-badge&logo=Trello&logoColor=white)

# 🍃 Leaf Watt⚡

### Hackathon ONE G9 | Alura Latam + Oracle Next Education

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Python](https://img.shields.io/badge/Python-3.12-3776AB?logo=python&logoColor=white)
![Scikit-Learn](https://img.shields.io/badge/Scikit--Learn-Machine_Learning-F7931E?logo=scikitlearn&logoColor=white)
![Pandas](https://img.shields.io/badge/Pandas-Data_Analysis-150458?logo=pandas&logoColor=white)
![Oracle Cloud](https://img.shields.io/badge/Oracle_Cloud-OCI-F80000?logo=oracle&logoColor=white)
![REST API](https://img.shields.io/badge/API-REST-blue)
![JSON](https://img.shields.io/badge/JSON-Data-black?logo=json)
![Status](https://img.shields.io/badge/Status-En_Desarrollo-yellow)
![License](https://img.shields.io/badge/Licencia-Educacional-lightgrey)

---

**Proyecto desarrollado para el Hackathon ONE G9 de Alura Latam + Oracle Next Education.**

Una solución inteligente que utiliza Ciencia de Datos y Machine Learning
para analizar patrones de consumo energético, clasificar perfiles de eficiencia y
generar recomendaciones personalizadas que ayuden a reducir costos y fomentar un consumo responsable.

---

# 📑 Índice

- [📌 Descripción](#-descripción)
- [🎯 Objetivos](#-objetivos)
- [📋 Alcance del Proyecto](#-alcance-del-proyecto)
- [💻 Tareas a Realizar](#-tareas-a-realizar)
- [✅ Requisitos](#-requisitos)
- [🛠 Tecnologías que ocupamos](#-tecnologias-que-ocupamos)
- [💡 Soluciones](#-soluciones)
- [📈 Categorías de Consumo](#-categorías-de-consumo)
- [▶️ Ejecución](#️-ejecución)
- [📂 Estructura del Proyecto](#-estructura-del-proyecto)
- [👥 Equipo](#-equipo)
- [📄 Licencia](#-licencia)

---

# 📌 Descripción

El proyecto consiste en desarrollar una solución inteligente capaz de analizar el consumo de energía eléctrica de viviendas o pequeños establecimientos.

Mediante técnicas de **Ciencia de Datos** y **Machine Learning**, el sistema identificará patrones de consumo, clasificará el nivel de eficiencia energética y ofrecerá recomendaciones personalizadas para disminuir el desperdicio y optimizar el uso de la energía.

Además, la solución calculará una estimación del costo mensual del consumo y expondrá toda la información mediante una **API REST**, integrándose con servicios **Oracle Cloud Infrastructure (OCI)**.

---

# 🎯 Objetivos

## 🎯 Objetivo General

Desarrollar un MVP funcional que permita:

- Analizar patrones de consumo energético.
- Clasificar perfiles de eficiencia energética.
- Generar recomendaciones inteligentes.
- Estimar el costo mensual del consumo.
- Publicar los resultados mediante una API REST.
- Utilizar al menos un servicio de Oracle Cloud Infrastructure (OCI).

---

## 👤 Objetivos Individuales (Consumidores)

### 🏠 Usuario Final

La aplicación permitirá al usuario:

- Comprender su perfil de consumo energético.
- Detectar hábitos que generan desperdicio.
- Recibir recomendaciones personalizadas.
- Conocer el costo mensual estimado.
- Hacer seguimiento de la evolución de su eficiencia energética.

### 👨‍💻 Equipo Desarrollador

El equipo tendrá como objetivos:

- Construir el conjunto de datos.
- Entrenar un modelo de Machine Learning.
- Implementar una API REST.
- Integrar la solución con Oracle Cloud.
- Documentar la arquitectura del proyecto.

---

# 📋 Alcance del Proyecto

El sistema analizará información como:

- Consumo mensual (kWh)
- Horarios de mayor consumo
- Cantidad de equipos eléctricos
- Tipo de inmueble
- Horas de mayor utilización
- Otros indicadores definidos por el equipo

Con estos datos clasificará al usuario en un perfil energético y ofrecerá recomendaciones junto con una estimación financiera.

---

# 💻 Tareas a Realizar

## 📊 Ciencia de Datos

- Recolección de datos.
- Limpieza y preparación (EDA).
- Ingeniería de características.
- Entrenamiento del modelo.
- Evaluación del rendimiento.
- Serialización del modelo.
- Generación de recomendaciones.

## ⚙️ Back-End

Desarrollar una API REST que permita:

- Recibir datos de consumo.
- Analizar el perfil energético.
- Clasificar la eficiencia.
- Calcular el costo estimado.
- Generar recomendaciones.
- Validar entradas.
- Manejar errores.
- Documentar los endpoints.

---

# ✅ Requisitos

## 🚀 Requisitos del MVP

- [x] Modelo entrenado.
- [x] Clasificación funcional.
- [x] Recomendaciones automáticas.
- [x] Estimación financiera.
- [x] API documentada.
- [x] Integración con OCI.
- [x] Tres ejemplos de uso.

## ⭐ Funcionalidades Opcionales

- [ ] Dashboard interactivo.
- [x] Historial de consultas.
- [ ] Procesamiento mediante CSV.
- [ ] Docker.
- [ ] Pruebas automatizadas.
- [ ] Alertas de alto consumo.
- [ ] Visualizaciones gráficas.
- [ ] Comparación entre períodos.
- [ ] Ranking energético.
- [ ] Simulación de ahorro.

## ☁️ Oracle Cloud Infrastructure (OCI)

Implementar al menos uno de los siguientes servicios:

- [x] Object Storage
- [x] OCI Compute
- [ ] OCI Functions
- [ ] OCI Database

---

# 🛠 Tecnologias que ocupamos

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

---

# 💡 Soluciones

## 🌍 Solución General

Transformar datos de consumo eléctrico en información útil mediante algoritmos de Machine Learning para ayudar a mejorar la eficiencia energética.

La plataforma permitirá:

- Clasificar perfiles energéticos.
- Detectar desperdicios.
- Estimar el costo del consumo.
- Generar recomendaciones inteligentes.
- Integrarse con otros sistemas mediante API REST.

## 👤 Soluciones Individuales

### 🏠 Usuario Final

Obtendrá:

- Perfil energético.
- Nivel de eficiencia.
- Probabilidad de clasificación.
- Recomendaciones personalizadas.
- Estimación mensual del consumo.
- Historial de análisis (si se implementa).

### 👨‍💻 Equipo Técnico

Dispondrá de:

- Modelo de Machine Learning.
- API REST documentada.
- Arquitectura escalable.
- Integración con Oracle Cloud.
- Respuestas en formato JSON.

---

# 📈 Categorías de Consumo

El modelo clasificará a los usuarios en tres perfiles:

| Categoría      | Descripción                                   |
| -------------- | --------------------------------------------- |
| 🟢 Eficiente   | Consumo optimizado                            |
| 🟡 Moderado    | Consumo aceptable con oportunidades de mejora |
| 🔴 Ineficiente | Alto consumo y desperdicio energético         |

---

---

# ▶️ Ejecución

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

---

# 📂 Estructura del Proyecto

```text
📦 Leaf Watt
│
├── 📁 .github/
│   └── 📁 workflows/
│       └── upload-to-OCI-prod.yml
│
├── 📁 OCI/
│   └── 📁 Functions/
│       └──  📁 makeprediction_arm
│
├── 📁 assets/
│
├── 📁 backend/
│   └── 📁 source/
│
├── 📁 data-science/
│   └── 📁 notebooks/
│       └── EnergIA_Notebook.ipynb
│
├── 📁 demonstration-backend/
│   └── 📁 source/
│
├── 📁 docs/
│
├── 📁 fast-api/
│   └── app.py
│
├── 📁 model-service/
│   ├── 📁 local-test/
│   └── 📁 oci-function/
│
├── .gitignore
└── README.md

```

## Leyenda

- `.github/workflows/` — GitHub Actions workflows para el CI/CD y deployment.
- `OCI/Functions/` — Codigo legacy de OCI Functions.
- `assets/` — Imagenes, iconos y otros recursos estaticos para el proyecto.
- `backend/` — Codigo de produccion para el API Spring Boot.
- `data-science/` — Notebooks con analisis, preprocesamiento, entrenamiento, etc. de la Data.
- `demonstration-backend/` — Codigo de prueba para integracion con OCI.
- `docs/` — Documentacion de la API, Proyecto, etc.
- `fast-api` — Codigo Python/FastAPI de produccion.
- `model-service/` — Primer codigo Python/FastAPI de respaldo.

---

# 👥 Equipo

| Integrante                | Rol              | Responsabilidades                        | Links                                                                                                                |
| ------------------------- | ---------------- | ---------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| Jhon Fernando Gomez Villa | Backend          | Desarrollo de la API                     | [Linkedin](https://www.linkedin.com/in/jhon-fernando-gómez-villa-4a6bb6341) - [Github](https://github.com/JHFEGOVI)  |
| Angie Alejandra Vega      | Frontend         | Desarrollo del Front End                 | [Linkedin](https://www.linkedin.com/in/angiealejandravegaromero) - [Github](https://github.com/AngieVegaR)           |
| Jhon Rodríguez            | Ciencia de Datos | Modelado y entrenamiento                 |
| Tomas Raggio              | Ciencia de Datos | Modelado y entrenamiento                 | [Github](https://github.com/innit-tomi)                                                                              |
| Angela Balta              | Ciencia de Datos | Graficos                                 | [Linkedin](https://www.linkedin.com/in/angela-balta-412506140) - [Github](https://github.com/Anel-7)                 |
| Isaac Ruiz                | Ciencia de Datos | Modelado y entrenamiento                 |
| Matias Marquez            | Documentación    | README y presentación                    | [Linkedin](https://www.linkedin.com/in/matias-ivan-marquez-b05888378/) - [Github](https://github.com/MarquezIMatias) |
| Josue Marquez             | Project Manager  | Organizar proyecto y Integración con OCI | [Linkedin](https://www.linkedin.com/in/josuemarquez/) - [Github](https://github.com/owaruuu)                         |

---

# 📄 Licencia

Este proyecto fue desarrollado con fines educativos como parte del **Hackathon ONE G9 - Alura Latam + Oracle Next Education**.

No está destinado a uso comercial.
