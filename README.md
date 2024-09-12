# AREP- Taller4: Taller de de modularización con virtualización e Introducción a Docker

## Descripción del Taller

Este taller tiene como objetivo implementar una arquitectura distribuida y desplegarla en AWS utilizando EC2 y Docker. La aplicación consta de tres componentes principales: un servicio de base de datos MongoDB, un servicio REST llamado LogService, y una aplicación web denominada APP-LB-RoundRobin.

## Objetivos

1. **Desplegar y configurar una infraestructura en la nube con AWS EC2**:
   - Crear y gestionar instancias EC2 para ejecutar contenedores Docker.
   - Configurar los grupos de seguridad y las reglas de red necesarias para el funcionamiento de la aplicación.

2. **Implementar y desplegar contenedores Docker**:
   - Desplegar MongoDB en un contenedor Docker dentro de una instancia EC2.
   - Crear y desplegar múltiples instancias del servicio **LogService** en contenedores Docker.

3. **Desarrollar un servicio REST con balanceo de carga**:
   - Implementar el servicio **LogService** utilizando Java Spring Boot.
   - Configurar el servicio para que reciba mensajes, los almacene en MongoDB, y devuelva las últimas 10 entradas en formato JSON.
   - Implementar un balanceador de cargas con el algoritmo **Round Robin** para distribuir las solicitudes entre tres instancias del servicio **LogService**.

4. **Desarrollar una aplicación web para interactuar con el servicio REST**:
   - Desarrollar una aplicación web cliente que permita al usuario enviar mensajes a través de un campo de texto y un botón.
   - Configurar la aplicación para enviar mensajes al servicio **LogService** y recibir respuestas en formato JSON, mostrando las últimas 10 cadenas almacenadas.

5. **Practicar el uso de herramientas modernas para contenedores y la nube**:
   - Usar Docker para la contenerización de aplicaciones y servicios.
   - Gestionar aplicaciones distribuidas y balanceadas en la nube con AWS EC2.
   - Integrar aplicaciones web con servicios REST utilizando Java Spring Boot y MongoDB.

Estos objetivos permitirán adquirir habilidades en el despliegue de aplicaciones distribuidas, manejo de contenedores Docker, y la implementación de soluciones en la nube utilizando AWS.

### Empezando
Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba. Consulte la sección Implementación para obtener notas sobre cómo implementar el proyecto en un sistema en vivo.

### Requisitos

* [Git](https://git-scm.com/) - Control de versiones
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación
* [Docker](https://www.docker.com/) - Plataforma de contenedores

### Instalación

Realice los siguientes pasos para clonar el proyecto en su máquina local.

1. Clonar repositorio.

```bash
git clone https://github.com/RulosS290/AREP.git
```

2. Cambiar de rama.

```bash
git checkout Taller4
```
## Ejecutando la aplicación

Para ejecutar la aplicación, ejecute el siguiente comando:

* Para que funcione de forma local debes cambiar esto en el html en los archivos static

![imagen](https://github.com/user-attachments/assets/d5e18cea-f05e-4a30-9721-0ae2203c4229)

* Por esto

![imagen](https://github.com/user-attachments/assets/09ee0545-8c90-4d4f-9bb1-0059a79a0585)


* Construir el proyecto
```bash
mvn clean package
```
* Compilar el proyecto
```bash
mvn compile
```
* Ejecutar pruebas
```bash
mvn test
```
* Ejecutar el el docker-compose.yml
```bash
docker compose up
```
[http://localhost:35001](http://localhost:35001) Para probar el servicio

### Funcionalidad

[https://youtu.be/dbJANLFHvRI](https://youtu.be/dbJANLFHvRI)

### Componentes de la Arquitectura

1. LogService REST Service

   *  Responsabilidad: Recibe mensajes a través de una API REST, los almacena en la base de datos MongoDB y proporciona los últimos 10 mensajes almacenados.
    Endpoints:
   
    *  POST /log: Recibe un mensaje en el cuerpo de la solicitud y lo almacena en la base de datos.
    *  GET /messages: Devuelve una lista de los últimos 10 mensajes almacenados en formato JSON.
   
    *  Controlador:
        LogServiceController: Maneja las solicitudes HTTP y utiliza el servicio MessageService para interactuar con la base de datos.
    *  Modelo:
        Message: Representa un mensaje almacenado en la base de datos, con campos para el contenido del mensaje y la fecha de almacenamiento.

3. Servicio de Mensajes

    *  Responsabilidad: Proporciona operaciones para guardar mensajes en la base de datos y recuperar los últimos 10 mensajes.
    *  Implementación:
        MessageService: Contiene la lógica de negocio para manejar la persistencia de mensajes.
        MessageRepository: Interface de acceso a datos que extiende MongoRepository para operaciones CRUD sobre la colección de mensajes.

4. Base de Datos

   *  MongoDB: Base de datos NoSQL utilizada para almacenar los mensajes.
        Colección: messages
        Esquema:
            id: Identificador único del mensaje (generado automáticamente).
            content: Contenido del mensaje.
            date: Fecha en que el mensaje fue almacenado.

5. Configuración

    *  Puerto: El puerto en el que se ejecuta la aplicación se puede configurar mediante la variable de entorno PORT. Si no se establece, se usa el puerto 35001 por defecto.

6. Dependencias

   *  Spring Boot: Framework principal para el desarrollo de la aplicación.
   *  Spring Data MongoDB: Facilita la integración con MongoDB y proporciona el repositorio para la persistencia de datos.
  
## Autores

Daniel Santiago Torres Acosta [https://github.com/RulosS290](https://github.com/RulosS290)

## Agradecimientos
Daniel Benavides
