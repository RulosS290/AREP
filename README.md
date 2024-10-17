# AREP-Taller6: cEnterprise Architecture Workshop: Security Application Design

## Descripción del Taller

**Objetivo:** Diseñar y desplegar una aplicación segura, compuesta por un servidor frontend y un servidor backend, utilizando las mejores prácticas de seguridad para proteger la integridad y confidencialidad de los datos.

### Empezando

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos

Para ejecutarlo de manera local necesitamos Docker para facilitar la base de datos, ya se cuenta con el DockerFile
para los ajustes del contenerdor.


* [Git](https://git-scm.com/) - Control de versiones
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación
* [Docker](https://www.docker.com/) - Plataforma de contenedores
* [AWS (Amazon Web Services)](https://aws.amazon.com/es/) - Plataforma de servicios en la nube

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Instalación



Realice los siguientes pasos para clonar el proyecto en su máquina local.

1. Clonar repositorio.

```bash
git clone https://github.com/RulosS290/AREP.git
```

2. Cambiar de rama.

```bash
git checkout Taller6
```

### Ejecutando la aplicación - Backend

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
* Correr la aplicación
```bash
mvn spring-boot:run
```

### Ejecutando la aplicación - Frontend

* Construir el proyecto
```bash
npm install
```
* Compilar el proyecto
```bash
npm start
```

[Link](http://localhost:3000/)

### Funcionalidad

[Video](https://youtu.be/miQvnd2e_ko)

### Ejecutando Pruebas

```bash
mvn test
```

![imagen](https://github.com/user-attachments/assets/12f6355f-923e-41ab-810e-b0b2906f0c39)


Pruebas unitarias del controlador (CustomerController)

```bash
mvn test
```

### Arquitectura del Proyecto - Backend

**1. Capa de Controlador (Controller)**

  *  AuthController: Maneja las solicitudes relacionadas con la autenticación de usuarios, como el registro y el inicio de sesión.
  *  PropertyController: Gestiona las solicitudes relacionadas con la administración de propiedades, incluyendo la creación, consulta, actualización y eliminación de propiedades.

**2. Capa de Modelo (Model)**

  *  Customer: Representa a un cliente en el sistema con atributos como firstName y lastName.
  *  Property: Representa las propiedades que se manejan en el sistema, con atributos como address, price, size y description.
  *  User: Representa a los usuarios del sistema, con atributos como username y password.

**3. Capa de Repositorio (Repository)**

  *  PropertyRepository: Define los métodos para interactuar con la base de datos para la entidad Property. Incluye la búsqueda de propiedades por su dirección.
  *  UserRepository: Define los métodos para interactuar con la base de datos para la entidad User, incluyendo la búsqueda de usuarios por su nombre de usuario.

**4. Capa de Servicio (Service)**

  *  AuthService: Contiene la lógica de negocio para la autenticación y registro de usuarios. Se encarga de verificar las credenciales y de cifrar las contraseñas usando PasswordEncoder.

**5. Configuración de Seguridad (SecurityConfig)**

  *  Implementa la seguridad de la aplicación usando Spring Security. Configura un filtro de seguridad que permite el acceso público a las rutas de autenticación y propiedades (/auth/**, /properties/**), y asegura el resto de las rutas. También deshabilita CSRF y permite CORS.
  *  Usa BCrypt para el cifrado de contraseñas.

**6. Capa de Persistencia (JPA)**

  *  AccessingDataJpaApplication: Es el punto de entrada de la aplicación Spring Boot. Se utiliza JPA para interactuar con la base de datos, lo que permite la persistencia de datos.

### Arquitectura del Proyecto - Frontend

**1. Componentes Principales**

  *  Login.js: Este componente maneja el proceso de autenticación de usuarios. Contiene el formulario para ingresar el nombre de usuario y la contraseña, y realiza una solicitud POST al backend para verificar las credenciales. También gestiona la visibilidad de la contraseña y muestra errores si el inicio de sesión falla.

  *  Register.js: Este componente gestiona el registro de nuevos usuarios. Valida las contraseñas y envía una solicitud POST al backend con el nombre de usuario y la contraseña. También permite mostrar y ocultar las contraseñas para una mejor experiencia del usuario.

  *  Properties.js: Este componente se encarga de mostrar, crear, editar y eliminar propiedades. Utiliza el estado para almacenar los datos de las propiedades y se comunica con el backend a través de peticiones HTTP. Además, contiene la lógica para manejar el formulario de creación y edición de propiedades, y gestionar la autenticación con sesión.

  *  ProtectedRoute.js: Componente de protección de rutas. Si el usuario no está autenticado (es decir, no tiene una sesión válida en sessionStorage), redirige al inicio de sesión. Esto protege las rutas sensibles, como la de propiedades, de usuarios no autenticados.
    
**2. Estructura de Archivos**

  *  App.js: Define las rutas principales de la aplicación utilizando React Router. Incluye las rutas para Login, Register, y Properties, esta última protegida por ProtectedRoute.

  *  App.css: Archivos de estilo que aplican diseño y formato a los componentes visuales de la aplicación.

  *  index.js: Es el punto de entrada de la aplicación React. Renderiza el componente App en el DOM utilizando ReactDOM.createRoot.

  *  index.css: Archivo de estilos globales que afecta a toda la aplicación.

**3. Enrutamiento**

  *  El enrutamiento es manejado por React Router, donde se definen rutas para el inicio de sesión (/login), registro (/register), y las propiedades (/properties), esta última bajo una ruta protegida que requiere autenticación.

  *  ProtectedRoute.js se encarga de verificar si el usuario está autenticado antes de permitir el acceso a la página de propiedades.

**4. Estado y Efectos**

La gestión del estado en componentes como Login, Register, y Properties se realiza mediante los hooks useState y useEffect.
  *  useState: Almacena valores como el nombre de usuario, contraseña, y datos de las propiedades.
  *   useEffect: En Properties.js, se utiliza para realizar la verificación de autenticación y obtener los datos de las propiedades al cargar el componente.

**5. Interacción con el Backend**

Se utilizan peticiones HTTP a través de Axios para interactuar con el backend. Estas peticiones incluyen:

  *  POST para registrar usuarios (/auth/register) y autenticarlos (/auth/login).
  *  GET para obtener las propiedades (/properties).
  *  POST, PUT y DELETE para crear, actualizar, y eliminar propiedades.

**6. Seguridad y Autenticación**

  *  La autenticación de usuarios se maneja mediante la verificación de credenciales enviadas al backend. Si el usuario es autenticado, se almacena un indicador de autenticación en sessionStorage que permite el acceso a las rutas protegidas.

### Arquitectura del Proyecto - General

  *  Frontend: Un cliente que corre en localhost:3000, basado en HTML/JavaScript/React (según el contexto que has compartido previamente).
  *  Backend: Implementado con Spring Boot, gestionando rutas de autenticación y administración de propiedades.
  *  Base de Datos: Utiliza JPA para interactuar con la base de datos, donde se almacenan los usuarios y propiedades.
  *  Seguridad: Uso de Spring Security para proteger las rutas sensibles y cifrado de contraseñas con BCrypt.

### Versiones

**Java 17**

**Maven 3.9.9**

**Node.js 10.8.2**

## Autores

Daniel Santiago Torres Acosta [https://github.com/RulosS290](https://github.com/RulosS290)

## Agradecimientos

Daniel Benavides - Profesor AREP

Santiago Parra - Monitor











