package com.proyecto.constructora.servicios;

import com.proyecto.constructora.modelo.TipoCliente;
import com.proyecto.constructora.repositorio.TipoClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoClienteService {

    private final TipoClienteRepository tipoClienteRepository;

    public TipoClienteService(TipoClienteRepository tipoClienteRepository) {
        this.tipoClienteRepository = tipoClienteRepository;
    }

    /**
     * Obtener todos los tipos de cliente
     */
    public List<TipoCliente> listarTodos() {
        try {
            return tipoClienteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los tipos de cliente", e);
        }
    }

    /**
     * Buscar un tipo de cliente por ID
     */
    public Optional<TipoCliente> buscarPorId(Long id) {
        try {
            return tipoClienteRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el tipo de cliente con ID " + id, e);
        }
    }

    /**
     * Guardar un nuevo tipo de cliente
     */
    public TipoCliente guardar(TipoCliente tipoCliente) {
        try {
            if (tipoCliente.getNombreCliente() == null || tipoCliente.getNombreCliente().isEmpty()) {
                throw new IllegalArgumentException("El nombre del tipo de cliente no puede estar vacío");
            }
            return tipoClienteRepository.save(tipoCliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el tipo de cliente", e);
        }
    }

    /**
     * Eliminar un tipo de cliente por ID
     */
    public void eliminar(Long id) {
        try {
            if (!tipoClienteRepository.existsById(id)) {
                throw new IllegalArgumentException("El tipo de cliente con ID " + id + " no existe");
            }
            tipoClienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tipo de cliente con ID " + id, e);
        }
    }

    /**
     * Actualizar completamente un tipo de cliente (PUT)
     */
    public TipoCliente actualizar(Long id, TipoCliente tipoClienteActualizado) {
        try {
            if (!tipoClienteRepository.existsById(id)) {
                throw new IllegalArgumentException("El tipo de cliente con ID " + id + " no existe");
            }
            tipoClienteActualizado.setTipoClienteId(id);
            return tipoClienteRepository.save(tipoClienteActualizado);
        } catch (Exception e)            {
            throw new RuntimeException("Error al actualizar el tipo de cliente con ID " + id, e);
        }
    }

    /**
     * Actualizar parcialmente un tipo de cliente (PATCH)
     */
    public TipoCliente actualizarParcial(Long id, TipoCliente tipoClienteParcial) {
        try {
            if (!tipoClienteRepository.existsById(id)) {
                throw new IllegalArgumentException("El tipo de cliente con ID " + id + " no existe");
            }
            return tipoClienteRepository.findById(id).map(tipoCliente -> {
                if (tipoClienteParcial.getNombreCliente() != null && !tipoClienteParcial.getNombreCliente().isEmpty()) {
                    tipoCliente.setNombreCliente(tipoClienteParcial.getNombreCliente()); // Corregido
                }
                return tipoClienteRepository.save(tipoCliente);
            }).orElseThrow(() -> new RuntimeException("TipoCliente no encontrado"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el tipo de cliente con ID " + id, e);
        }
    }

}
