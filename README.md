# tech-trove
InfiniteGrowth Challange 

Como parte del analisis en es Business Case, se llevo acabo la implementación de tres microservicios, los cuales implementan la arquitectura hexagonal o también conocida como Ports and Adapter. 

### Stack Tecnológico ###
- Openjdk17
- Maven 3.9.2
- Docker y docker compose

### Ejecutar los servicios ###

Toda la infraestructura se encuentra contenerizada, a nivel raíz del proyecto se encuentra el **docker-compose.yaml** el cual nos ayudara a levantar la base de datos, el inspector de la calidad del código Sonarqube y  nuestro IAM (Identity and Access Management) y todas las dependencias que estás pudieras tener. 

A continuación describire los pasos que necesitamos para poder leventar y validar nuestra infra.

- Iniciar los contenedores y observar los logs por si marca un error
```
docker compose up -d
docker compose logs -f
```

- Una vez arriba los contenedores, procedemos a validar la conexión a la BD (yo voy a utilizar MongoCompass)
```
mongodb://super-root:D7kJe7gr@127.0.01:27017/?authMechanism=DEFAULT
```
![img.png](img.png)

- Ahora continuamos con el sonarqube, el cual al inicio nos solicita las credenciales por default admin:admin (user:password), procedemos a cambiar el password
  http://127.0.0.1:8080/auth/

- Procedemos con Keycloak, las credenciales por default son user:bitnami (user:password)
- http://127.0.0.1:8080/auth/

> [!NOTE]
> Para ejecutar los microservicios utilizaré el IDE Intellij y Postman

> [!IMPORTANT]
> En el video que compartiré explico brevemente como configura Keycloak para empezar a Auteticarnos con la herramienta.