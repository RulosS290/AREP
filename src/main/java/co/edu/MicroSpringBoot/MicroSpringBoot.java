// src/main/java/co/edu/microspringboot/MicroSpringBoot.java
package co.edu.microspringboot;

import co.edu.microspringboot.annotations.GetMapping;
import co.edu.microspringboot.annotations.RequestMapping;
import co.edu.microspringboot.annotations.RestController;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MicroSpringBoot {

    private Map<String, Method> services = new HashMap<>();
    private Object controllerInstance;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java -cp target/classes co.edu.microspringboot.MicroSpringBoot <controlador>");
            return;
        }

        MicroSpringBoot server = new MicroSpringBoot();
        try {
            server.init(args[0]);
            server.startServer(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(String controllerClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> controllerClass = Class.forName(controllerClassName);
        if (controllerClass.isAnnotationPresent(RestController.class)) {
            controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            Method[] methods = controllerClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    String path = method.getAnnotation(GetMapping.class).value();
                    services.put(path, method);
                }
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    String path = method.getAnnotation(RequestMapping.class).value();
                    services.put(path, method);
                }
            }
            System.out.println("Servicios cargados: " + services.keySet());
        } else {
            System.out.println("La clase especificada no está anotada con @RestController.");
        }
    }

    public void startServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor iniciado en el puerto " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
            clientSocket.close(); // Manejo no concurrente
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // Leer la línea de solicitud (e.g., GET /hello HTTP/1.1)
            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }

            System.out.println("Solicitud recibida: " + requestLine);
            String[] tokens = requestLine.split(" ");
            if (tokens.length < 2) {
                sendResponse(out, "400 Bad Request", "Bad Request");
                return;
            }

            String method = tokens[0];
            String path = tokens[1];

            if (!method.equals("GET")) {
                sendResponse(out, "405 Method Not Allowed", "Method Not Allowed");
                return;
            }

            // Ignorar los headers
            while (true) {
                String header = in.readLine();
                if (header == null || header.isEmpty()) {
                    break;
                }
            }

            // Obtener el método correspondiente al path
            Method serviceMethod = services.get(path);
            if (serviceMethod != null) {
                // Invocar el método y obtener la respuesta
                String responseBody = (String) serviceMethod.invoke(controllerInstance);
                sendResponse(out, "200 OK", responseBody);
            } else {
                sendResponse(out, "404 Not Found", "Not Found");
            }

        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(BufferedWriter out, String status, String body) throws IOException {
        out.write("HTTP/1.1 " + status + "\r\n");
        out.write("Content-Type: text/plain\r\n");
        out.write("Content-Length: " + body.length() + "\r\n");
        out.write("\r\n");
        out.write(body);
        out.flush();
    }
}


