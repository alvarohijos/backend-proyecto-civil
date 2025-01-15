package com.proyecto.constructora;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ProyectosocialApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProyectosocialApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Intento de realizar una consulta sencilla
            String result = jdbcTemplate.queryForObject("SELECT 1", String.class);
            System.out.println("Conexión exitosa a la base de datos: " + result);
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }
}

