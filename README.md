# AREP-Taller5: Taller de trabajo individual en patrones arquitecturales

## Descripción del Taller

Este proyecto consiste en un sistema CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar anuncios de propiedades inmobiliarias. Los usuarios pueden crear nuevos listados de propiedades, ver una lista de todas las propiedades y detalles individuales, actualizar la información de las propiedades existentes y eliminar listados.

### Objetivos del Proyecto

1. **Desarrollar un Sistema CRUD:** Crear una aplicación web que permita a los usuarios gestionar listados de propiedades mediante las operaciones de Crear, Leer, Actualizar y Eliminar.

2. **Interfaz de Usuario Intuitiva:** Diseñar una interfaz sencilla que permita a los usuarios ingresar información sobre propiedades, como dirección, precio, tamaño y descripción, y que muestre una lista de todas las propiedades disponibles.

3. **Implementar Validaciones:** Asegurar que la interfaz de usuario incluya validaciones en el lado del cliente para campos requeridos y tipos de datos válidos.

4. **Crear API RESTful:** Desarrollar endpoints RESTful en el backend para manejar las operaciones CRUD, asegurando que cada operación sea capaz de gestionar errores, como entradas no válidas o solicitudes para propiedades no existentes.

5. **Persistencia de Datos:** Utilizar JPA/Hibernate para mapear objetos de propiedad a la base de datos MySQL y garantizar que todas las operaciones CRUD se almacenen de manera persistente.

6. **Despliegue en AWS:** Implementar el backend y la base de datos en servidores separados en AWS, asegurando que el sistema sea accesible y escalable.

7. **Opciones de Mejora:** Añadir funcionalidades opcionales, como paginación, búsqueda avanzada y retroalimentación del usuario sobre las operaciones realizadas.

### Empezando

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.


### Requisitos

Para ejecutarlo de manera local necesitamos Docker para facilitar la base de datos, ya se cuenta con el DockerFile
para los ajustes del contenerdor.

Además se debe reemplazar la linea 3 de aplication.properties por:

spring.datasource.url=jdbc:mysql://localhost:3306/mysqldaniel

* [Git](https://git-scm.com/) - Control de versiones
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación
* [Docker](https://www.docker.com/) - Plataforma de contenedores
* [AWS (Amazon Web Services)](https://aws.amazon.com/es/) - Plataforma de servicios en la nube

### Instalación

Realice los siguientes pasos para clonar el proyecto en su máquina local.

1. Clonar repositorio.

```bash
git clone https://github.com/RulosS290/AREP.git
```

2. Cambiar de rama.

```bash
git checkout Taller5
```

### Ejecutando la aplicación

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


En caso de correrlo de forma local para no depender de AWS

* Construir imagen docker

```bash
docker build -t mi-mysql-imagen .
```

* Correr un contenedor basado en la imagen anterior

```bash
docker run -d --name mi-mysql-contenedor -p 3306:3306 mi-mysql-imagen
```

[Link](http://localhost:8080/Customers) Para probar el servicio de forma local.

### Funcionalidad

[Video de Despliegue](https://www.youtube.com/watch?v=9B3LDUpwqDM&ab_channel=Daniel)

### Ejecutando Pruebas

Pruebas unitarias del controlador (CustomerController)

```bash
mvn test
```

![imagen](https://github.com/user-attachments/assets/6c8eb2d9-8d8b-403d-ac65-e530ad638bb6)


![imagen](https://github.com/user-attachments/assets/cf3c87a3-c315-435f-a36a-0b4d2b76e197)

### Arquitectura del Proyecto

#### 1. Estructura General

El proyecto sigue una arquitectura típica de **Modelo-Vista-Controlador (MVC)**

- **Modelo**: Define la entidad `Customer`, que representa los datos de los clientes y las operaciones relacionadas con ellos.
- **Vista**: Se gestionan las vistas a través de Thymeleaf (o un motor de plantillas similar) para renderizar las páginas web.
- **Controlador**: La clase `CustomerController` maneja las solicitudes HTTP y coordina las interacciones entre la vista y el modelo.

#### 2. Componentes

##### 2.1. Entidad (Modelo)

```java
@Entity
public class Customer {
    // Atributos de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private int price;
    private int size;
    private String description;

    // Constructores, getters y setters
}
```

- **`Customer`**: Representa un cliente con atributos como nombre, apellido, dirección, precio, tamaño y descripción. Utiliza anotaciones JPA para la persistencia en la base de datos.

##### 2.2. Repositorio

```java
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
    Customer findById(long id);
    Page<Customer> findAll(Pageable pageable);
}
```

- **`CustomerRepository`**: Interfaz que extiende `JpaRepository`, proporcionando métodos para acceder a los datos de los clientes, como encontrar clientes por apellido y realizar consultas paginadas.

#### 2.3. Servicio

```java
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    // Métodos para obtener, crear, actualizar y eliminar clientes
}
```

- **`CustomerService`**: Contiene la lógica de negocio para la manipulación de clientes. Se encarga de interactuar con el repositorio y manejar excepciones.

##### 2.4. Controlador

```java
@Controller
@RequestMapping("/Customers")
public class CustomerController {
    private final CustomerService customerService;

    // Métodos para manejar las solicitudes HTTP
}
```

- **`CustomerController`**: Maneja las solicitudes HTTP relacionadas con los clientes, como listar, crear, actualizar y eliminar clientes. Se encarga de la paginación y redireccionamientos de la vista.

#### 3. Funcionalidades

- **Listar Clientes**: Permite visualizar la lista de clientes en una vista paginada.
- **Crear Cliente**: Proporciona un formulario para crear nuevos clientes.
- **Actualizar Cliente**: Permite editar la información de un cliente existente.
- **Eliminar Cliente**: Ofrece la opción de eliminar un cliente.

#### 4. Pruebas

```java
class CustomerControllerTest {
    // Métodos de prueba para el controlador
}
```

- **`CustomerControllerTest`**: Contiene pruebas unitarias para asegurar que los métodos del controlador funcionen correctamente, usando `MockMvc` para simular las solicitudes HTTP y verificar las respuestas.

#### 5. Configuración de Aplicación

```java
@SpringBootApplication
public class AccessingDataJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }
}
```

- **`AccessingDataJpaApplication`**: Clase principal que arranca la aplicación Spring Boot.

#### Resumen

La arquitectura del proyecto es clara y modular, siguiendo los principios de la arquitectura MVC. Los diferentes componentes están bien separados, facilitando la escalabilidad y el mantenimiento del sistema. El uso de Spring Boot y JPA permite una integración fluida con la base de datos y simplifica la gestión de la lógica empresarial.

### Autores

Daniel Santiago Torres Acosta [https://github.com/RulosS290](https://github.com/RulosS290)

## Agradecimientos

Daniel Benavides - Profesor AREP

Santiago Parra - Monitor









