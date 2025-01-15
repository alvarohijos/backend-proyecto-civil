package com.proyecto.constructora.servicios;

import com.proyecto.constructora.modelo.Ciudad;
import com.proyecto.constructora.repositorio.CiudadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> listarTodas() {
        return ciudadRepository.findAll();
    }

    public Optional<Ciudad> buscarPorId(Long id) {
        return ciudadRepository.findById(id);
    }

    public Ciudad guardar(Ciudad ciudad) {
        if (ciudad.getNombreCiudad() == null || ciudad.getNombreCiudad().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede estar vacío");
        }
        if (ciudad.getDepartamento() == null) {
            throw new IllegalArgumentException("El ID del departamento no puede estar vacío");
        }
        return ciudadRepository.save(ciudad);
    }

    public void eliminar(Long id) {
        if (!ciudadRepository.existsById(id)) {
            throw new IllegalArgumentException("La ciudad con ID " + id + " no existe");
        }
        ciudadRepository.deleteById(id);
    }

    /**
     * Actualizar completamente una ciudad (PUT)
     */
    public Ciudad actualizar(Long id, Ciudad ciudadActualizada) {
        if (!ciudadRepository.existsById(id)) {
            throw new IllegalArgumentException("La ciudad con ID " + id + " no existe");
        }
        if (ciudadActualizada.getNombreCiudad() == null || ciudadActualizada.getNombreCiudad().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede estar vacío");
        }
        if (ciudadActualizada.getDepartamento() == null) {
            throw new IllegalArgumentException("El ID del departamento no puede estar vacío");
        }

        return ciudadRepository.findById(id).map(ciudad -> {
            // Sobreescribe todos los campos
            ciudad.setNombreCiudad(ciudadActualizada.getNombreCiudad());
            ciudad.setDepartamento(ciudadActualizada.getDepartamento());
            // Guardar la ciudad actualizada
            return ciudadRepository.save(ciudad);
        }).orElseThrow(() -> new IllegalArgumentException("Error al actualizar la ciudad con ID " + id));
    }

    /**
     * Actualizar parcialmente una ciudad (PATCH)
     */
    public Ciudad actualizarParcial(Long id, Ciudad ciudadParcial) {
        if (!ciudadRepository.existsById(id)) {
            throw new IllegalArgumentException("La ciudad con ID " + id + " no existe");
        }

        return ciudadRepository.findById(id).map(ciudad -> {
            // Actualiza solo los campos que no sean nulos en ciudadParcial
            if (ciudadParcial.getNombreCiudad() != null && !ciudadParcial.getNombreCiudad().isEmpty()) {
                ciudad.setNombreCiudad(ciudadParcial.getNombreCiudad());
            }
            if (ciudadParcial.getDepartamento() != null) {
                ciudad.setDepartamento(ciudadParcial.getDepartamento());
            }
            // Guardar la ciudad parcialmente actualizada
            return ciudadRepository.save(ciudad);
        }).orElseThrow(() -> new IllegalArgumentException("Error al actualizar parcialmente la ciudad con ID " + id));
    }
}
