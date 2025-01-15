package com.proyecto.constructora.servicios;

import com.proyecto.constructora.modelo.Cliente;
import com.proyecto.constructora.repositorio.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardarCliente(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarParcial(Long id, Cliente clienteParcial) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (!clienteExistente.isPresent()) {
            throw new IllegalArgumentException("El cliente con ID " + id + " no existe.");
        }
        Cliente cliente = clienteExistente.get();

        if (clienteParcial.getNombres() != null) {
            cliente.setNombres(clienteParcial.getNombres());
        }
        if (clienteParcial.getApellidos() != null) {
            cliente.setApellidos(clienteParcial.getApellidos());
        }
        // Actualiza otros campos según sea necesario
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("El cliente con ID " + id + " no existe.");
        }
        clienteRepository.deleteById(id);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getCedula() == null || cliente.getCedula().isEmpty()) {
            throw new IllegalArgumentException("La cédula es obligatoria.");
        }
        if (cliente.getCorreoElectronico() == null || cliente.getCorreoElectronico().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio.");
        }
        if (cliente.getNumeroCelular() == null || cliente.getNumeroCelular().isEmpty()) {
            throw new IllegalArgumentException("El número de celular es obligatorio.");
        }
    }
}
