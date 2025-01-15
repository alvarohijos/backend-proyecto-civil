package com.proyecto.constructora.controlador;

import com.proyecto.constructora.modelo.Departamento;
import com.proyecto.constructora.servicios.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    /**
     * Obtener todos los departamentos
     */
    @GetMapping
    public ResponseEntity<List<Departamento>> listarTodos() {
        try {
            List<Departamento> departamentos = departamentoService.listarTodos();
            return ResponseEntity.ok(departamentos);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Obtener un departamento por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        try {
            return departamentoService.buscarPorId(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(404).build());  // NOT_FOUND
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Crear un nuevo departamento
     */
    @PostMapping
    public ResponseEntity<Departamento> guardar(@RequestBody Departamento departamento) {
        try {
            if (departamento.getNombreDepartamento() == null || departamento.getNombreDepartamento().isEmpty()) {
                return ResponseEntity.status(400).build();  // BAD_REQUEST
            }
            Departamento nuevoDepartamento = departamentoService.guardar(departamento);
            return ResponseEntity.status(201).body(nuevoDepartamento);  // CREATED
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Actualizar completamente un departamento (PUT)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(@PathVariable Long id, @RequestBody Departamento departamentoActualizado) {
        try {
            if (departamentoActualizado.getNombreDepartamento() == null || departamentoActualizado.getNombreDepartamento().isEmpty()) {
                return ResponseEntity.status(400).build();  // BAD_REQUEST
            }
            Departamento departamento = departamentoService.actualizar(id, departamentoActualizado);
            return ResponseEntity.ok(departamento);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Actualizar parcialmente un departamento (PATCH)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Departamento> actualizarParcial(@PathVariable Long id, @RequestBody Departamento departamentoParcial) {
        try {
            Departamento departamento = departamentoService.actualizarParcial(id, departamentoParcial);
            return ResponseEntity.ok(departamento);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Eliminar un departamento por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            departamentoService.eliminar(id);
            return ResponseEntity.noContent().build();  // NO_CONTENT
        } catch (Exception e) {
            return ResponseEntity.status(404).build();  // NOT_FOUND
        }
    }
}
