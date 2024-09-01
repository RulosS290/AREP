package co.edu.microSpringBoot;

import java.io.*;
import java.net.*;

public class SimpleWebServer {
    private static final String ROOT_DIRECTORY = "target/classes/webroot"; // Asegúrate de que esta ruta es correcta
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running on port " + PORT);

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                handleRequest(clientSocket);
            }
        }
    }

    public static void handleRequest(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            OutputStream dataOut = clientSocket.getOutputStream();
        ) {
            // Leer la solicitud del cliente
            String requestLine = in.readLine();
            if (requestLine == null) return;

            // Analizar la solicitud
            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String requestedFile = tokens[1];

            if (!method.equals("GET")) {
                sendResponse(out, "501 Not Implemented", "text/plain", "Method Not Implemented");
                return;
            }

            // Manejar la ruta específica
            if ("/hello".equals(requestedFile)) {
                sendResponse(out, "200 OK", "text/plain", "Greetings from Spring Boot!");
            } else {
                // Determinar el archivo a servir
                File file = new File(ROOT_DIRECTORY, requestedFile.equals("/") ? "index.html" : requestedFile);
                if (file.exists() && !file.isDirectory()) {
                    String contentType = getContentType(file.getName());
                    sendFileResponse(out, dataOut, file, contentType);
                } else {
                    sendResponse(out, "404 Not Found", "text/plain", "File Not Found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFileResponse(PrintWriter out, OutputStream dataOut, File file, String contentType) throws IOException {
        // Enviar cabecera de respuesta
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: " + contentType);
        out.println("Content-Length: " + file.length());
        out.println(); // Línea en blanco para separar cabeceras del cuerpo

        // Enviar cuerpo de respuesta
        try (FileInputStream fileIn = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                dataOut.write(buffer, 0, bytesRead);
            }
        }
    }

    private static void sendResponse(PrintWriter out, String status, String contentType, String message) {
        // Enviar cabecera de respuesta
        out.println("HTTP/1.1 " + status);
        out.println("Content-Type: " + contentType);
        out.println();
        out.println(message);
    }

    private static String getContentType(String fileName) {
        if (fileName.endsWith(".html")) {
            return "text/html";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".css")) {
            return "text/css";
        } else if (fileName.endsWith(".js")) {
            return "application/javascript";
        } else {
            return "application/octet-stream";
        }
    }
}



