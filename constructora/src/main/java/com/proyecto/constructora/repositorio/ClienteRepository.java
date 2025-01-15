package com.proyecto.constructora.repositorio;


import com.proyecto.constructora.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos personalizados si es necesario
}

