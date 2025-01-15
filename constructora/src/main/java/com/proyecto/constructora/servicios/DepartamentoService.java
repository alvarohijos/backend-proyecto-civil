package com.proyecto.constructora.servicios;

import com.proyecto.constructora.modelo.Departamento;
import com.proyecto.constructora.repositorio.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    /**
     * Obtener todos los departamentos
     */
    public List<Departamento> listarTodos() {
        try {
            return departamentoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los departamentos");
        }
    }

    /**
     * Buscar un departamento por ID
     */
    public Optional<Departamento> buscarPorId(Long id) {
        try {
            return departamentoRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el departamento con ID " + id);
        }
    }

    /**
     * Guardar un nuevo departamento
     */
    public Departamento guardar(Departamento departamento) {
        try {
            if (departamento.getNombreDepartamento() == null || departamento.getNombreDepartamento().isEmpty()) {
                throw new IllegalArgumentException("El nombre del departamento no puede estar vacío");
            }
            return departamentoRepository.save(departamento);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el departamento", e);
        }
    }

    /**
     * Eliminar un departamento por ID
     */
    public void eliminar(Long id) {
        try {
            if (!departamentoRepository.existsById(id)) {
                throw new IllegalArgumentException("El departamento con ID " + id + " no existe");
            }
            departamentoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el departamento con ID " + id, e);
        }
    }

    /**
     * Actualizar completamente un departamento (PUT)
     */
    public Departamento actualizar(Long id, Departamento departamentoActualizado) {
        try {
            if (!departamentoRepository.existsById(id)) {
                throw new IllegalArgumentException("El departamento con ID " + id + " no existe");
            }
            departamentoActualizado.setDepartamentoId(id);
            return departamentoRepository.save(departamentoActualizado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el departamento con ID " + id, e);
        }
    }

    /**
     * Actualizar parcialmente un departamento (PATCH)
     */
    public Departamento actualizarParcial(Long id, Departamento departamentoParcial) {
        try {
            if (!departamentoRepository.existsById(id)) {
                throw new IllegalArgumentException("El departamento con ID " + id + " no existe");
            }
            return departamentoRepository.findById(id).map(departamento -> {
                if (departamentoParcial.getNombreDepartamento() != null && !departamentoParcial.getNombreDepartamento().isEmpty()) {
                    departamento.setNombreDepartamento(departamentoParcial.getNombreDepartamento());
                }
                return departamentoRepository.save(departamento);
            }).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar parcialmente el departamento con ID " + id, e);
        }
    }
}
