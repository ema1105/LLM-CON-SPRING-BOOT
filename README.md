RESUMEN EJECUTIVO
Se llevó a cabo el desarrollo de una aplicación BACK-END con spring boot, permitiendo la integración con modelos LLM mediante API. Configurando el entorno de desarrollo, dependencias, variables, se integró un proveedor de inteligencia artificial y se realizaron pruebas mediante los endpoints REST.
Durante la actividad se logró una mejor compresión sobre las clases record en los DTOs, funcionamiento de servicios externos, manejo óptimo de las API-KEYs, su consumo y como pueden ser integradas posteriormente en proyectos personales, así también la solución de errores comunes en la integración de APIs.
CONTEXTO DEL PROYECTO BASE
El proyecto base proporcionado denota una arquitectura enfocada a servicios con spring, sus componentes principales son:
•	Controller (ChatController)
Expone los endpoints del REST como
-	/api/v1/chat/info
-	/api/v1/chat/directo

•	Services (GeminiDirectServices)
Contiene la lógica de negocio que consume la API

•	WebClient
Realiza las llamadas HTTP al servicio externo en este caso Gemini

•	application.properties
Maneja al proveedor LLM, su modelo, la API Key
AMBIENTE DE DESARROLLO
-	IDE: Intellij IDEA community 2024
-	Lenguaje: Java
-	JDK: Versión 21
-	Framework: Spirng Boot
-	Gestor de dependencias: Maven
-	Control de Versiones: Git
-	Cliente API: Postman
-	Proveedor LLM: Google Gemini






DESARROLLO DE LA ACTIVIDAD
1.	Configuración del proyecto
Se genero un proyecto desde spring initializr con las siguientes dependencias. Posteriormente se importo a Intellij y se verifico las dependías mediante Maven.
 
 






2.	Configuración de la API Key
Se ingreso a  https://ai.google.dev, para generar una key y se configuro como variable de entorno en Intellij.
 

 


3.	Codificación
Se realizó la codificación de las clases respectivas como DTOs, Services, Controllers, agregando también las modificaciones indicadas en la guía. 

 

4.	Prueba de endpoints
Se verifico el servicio mediante la url en el navegador y Postman
GET http://localhost:8080/api/v1/chat/info
 

 

Se realizo unas consultas al modelo
POST http://localhost:8080/api/v1/chat/directo
Body:
-	{
  "pregunta": "Explícame qué es Sprint Boot en dos oraciones"
	}

-	{
  "pregunta": "Explícame qué es .net en dos oraciones"
	}


 

 

 

PROBLEMAS Y SOLUCIONES

-	Error 500 — Internal Server Error
Causa: Fallo en llamada al servicio externo 
Solución: Revisar logs en consola
-	Error 429 — Too Many Requests
Causa: Límite de cuota en API
Solución: Esperar o generar nueva API key
-	Error 404 — Modelo no disponible
Causa: Uso de modelo obsoleto (gemini-2.0-flash)
Solución: Actualizar a modelo compatible (gemini-2.5-flash) o modelos posteriores como el 1.5
REFLEXION Y APRENDIZAJES
Para la realización correcta de la actividad se tuvo en cuenta criterios y conocimientos importantes como:
•	Integración de APIs externas
•	Creación de un servicio REST con spring boot 
•	Depuración mediante logs
•	Importancia en la compatibilidad entre versiones de herramientas, APIs y modelos
•	Uso correcto de las clases record para los DTOs
Importancia en la configuración del entorno e interpretación de errores para la solución eficiente de problemas.



