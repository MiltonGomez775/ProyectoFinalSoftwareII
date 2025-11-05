package edu.co.uniquindio.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del proyecto Spring Boot.
 * Se encarga de inicializar el contexto de la aplicación.
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Punto de entrada de la aplicación
        SpringApplication.run(DemoApplication.class, args);

        // Log para verificar el inicio correcto del proyecto
        System.out.println("🚀 Aplicación iniciada correctamente desde DemoApplication");
    }

}
