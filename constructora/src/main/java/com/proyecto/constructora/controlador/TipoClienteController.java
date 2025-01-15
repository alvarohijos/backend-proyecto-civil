package com.proyecto.constructora.controlador;

import com.proyecto.constructora.modelo.TipoCliente;
import com.proyecto.constructora.servicios.TipoClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipocliente")
public class TipoClienteController {

    private final TipoClienteService tipoClienteService;

    public TipoClienteController(TipoClienteService tipoClienteService) {
        this.tipoClienteService = tipoClienteService;
    }

    /**
     * Obtener todos los tipos de cliente
     */
    @GetMapping
    public ResponseEntity<List<TipoCliente>> listarTodos() {
        try {
            List<TipoCliente> tiposClientes = tipoClienteService.listarTodos();
            return ResponseEntity.ok(tiposClientes);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Obtener un tipo de cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TipoCliente> buscarPorId(@PathVariable Long id) {
        try {
            return tipoClienteService.buscarPorId(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(404).build());  // NOT_FOUND
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Crear un nuevo tipo de cliente
     */
    @PostMapping
    public ResponseEntity<TipoCliente> guardar(@RequestBody TipoCliente tipoCliente) {
        try {
            if (tipoCliente.getNombreCliente() == null || tipoCliente.getNombreCliente().isEmpty()) {
                return ResponseEntity.status(400).build();  // BAD_REQUEST
            }
            TipoCliente nuevoTipoCliente = tipoClienteService.guardar(tipoCliente);
            return ResponseEntity.status(201).body(nuevoTipoCliente);  // CREATED
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Actualizar completamente un tipo de cliente (PUT)
     */
    @PutMapping("/{id}")
    public ResponseEntity<TipoCliente> actualizar(@PathVariable Long id, @RequestBody TipoCliente tipoClienteActualizado) {
        try {
            if (tipoClienteActualizado.getNombreCliente() == null || tipoClienteActualizado.getNombreCliente().isEmpty()) {
                return ResponseEntity.status(400).build();  // BAD_REQUEST
            }
            TipoCliente tipoCliente = tipoClienteService.actualizar(id, tipoClienteActualizado);
            return ResponseEntity.ok(tipoCliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Actualizar parcialmente un tipo de cliente (PATCH)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<TipoCliente> actualizarParcial(@PathVariable Long id, @RequestBody TipoCliente tipoClienteParcial) {
        try {
            TipoCliente tipoCliente = tipoClienteService.actualizarParcial(id, tipoClienteParcial);
            return ResponseEntity.ok(tipoCliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();  // INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Eliminar un tipo de cliente por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            tipoClienteService.eliminar(id);
            return ResponseEntity.noContent().build();  // NO_CONTENT
        } catch (Exception e) {
            return ResponseEntity.status(404).build();  // NOT_FOUND
        }
    }
}
