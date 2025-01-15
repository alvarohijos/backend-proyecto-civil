package com.proyecto.constructora.controlador;

import com.proyecto.constructora.modelo.Ciudad;
import com.proyecto.constructora.servicios.CiudadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> listarTodas() {
        try {
            List<Ciudad> ciudades = ciudadService.listarTodas();
            return ResponseEntity.ok(ciudades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> buscarPorId(@PathVariable Long id) {
        try {
            return ciudadService.buscarPorId(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Ciudad> guardar(@RequestBody Ciudad ciudad) {
        try {
            Ciudad nuevaCiudad = ciudadService.guardar(ciudad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCiudad);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> actualizar(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        try {
            ciudad.setCiudadId(id);
            Ciudad ciudadActualizada = ciudadService.guardar(ciudad);
            return ResponseEntity.ok(ciudadActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ciudad> actualizarParcial(@PathVariable Long id, @RequestBody Ciudad ciudadParcial) {
        try {
            return ciudadService.buscarPorId(id)
                    .map(ciudadExistente -> {
                        if (ciudadParcial.getNombreCiudad() != null) {
                            ciudadExistente.setNombreCiudad(ciudadParcial.getNombreCiudad());
                        }
                        // Agregar otros campos si es necesario
                        Ciudad ciudadActualizada = ciudadService.guardar(ciudadExistente);
                        return ResponseEntity.ok(ciudadActualizada);
                    })
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            if (ciudadService.buscarPorId(id).isPresent()) {
                ciudadService.eliminar(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
