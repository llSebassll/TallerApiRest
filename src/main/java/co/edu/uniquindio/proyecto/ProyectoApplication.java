package co.edu.uniquindio.proyecto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ProyectoApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProyectoApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
        app.run(args);
    }
}