# HulkStore
Tienda de productos para empleados, aquí podrás encontrar camisetas, vasos, comics, juguetes y accesorios basados en los superhéroes de Marvel y DC comics, incluso algunos alternativos creados por la comunidad.
Este proyecto se desarrollo con un modelo Vista Controlador estructurado como proyecto [JAVA](https://www.java.com/en/download/) en MAVEN, para la parte de FrontEnd se utilizao Html, JavaScript, JQuery, bootsrap y para la parte de BackEnd Servicios Rest, 
Servidor [Apache Tomcat 8](https://tomcat.apache.org/download-80.cgi) y una BD relacional en [SQL Server EXPRESS](https://www.microsoft.com/en-us/download/details.aspx?id=42299).
# Instalacion
Se debe clonar y descargar el repositorio, corremos el comando seguido de la ruta del repositorio asi: git clone https://github.com/cehg1982/HulkStore.git
Creamos una base de datos SQL Server EXPRESS llamada bdHulkStore y corremos los Scripts sql que estan en el directorio https://github.com/cehg1982/HulkStore/tree/master/HulkStoreSQL/ScriptsBD para crear las tablas y SPs desarrollados.
En nuestro IDE favorito NetBean, Eclipse u otro importamos El proyecto y sus modulos Maven del repositorio git:

https://github.com/cehg1982/HulkStore/tree/master/HulkStoreData
https://github.com/cehg1982/HulkStore/tree/master/HulkStoreLogic
https://github.com/cehg1982/HulkStore/tree/master/HulkStoreServices 
https://github.com/cehg1982/HulkStore/tree/master/HulkStoreWeb
https://github.com/cehg1982/HulkStore/tree/master/HulkStoreEAR
https://github.com/cehg1982/HulkStore/tree/master/HulkStoreParent

# Despliegue en Servidor de Desarrollo
En nuestro IDE corremos el proyecto importado HulkStoreServices con click derecho sobre él, Run As - Run On Server y luego
corremos igualmente el proyecto importado HulkStoreWeb con click derecho sobre él, Run As - Run On Server y Listo.

Abrimos la aplicacion en nuestro explorador con la url: http://localhost:8080/HulkStoreWeb/view/hulkstore/aplicacion.html.

# Despliegue en otros ambientes
Se genera el Archivo de despliegue .EAR que se genera en el modulo HulkStoreEAR con click derecho sobre él, Run As - Maven install y se despliega en el servidor deseado.
