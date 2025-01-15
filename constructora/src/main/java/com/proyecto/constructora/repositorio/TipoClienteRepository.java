package com.proyecto.constructora.repositorio;

import com.proyecto.constructora.modelo.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente, Long> {
}