package com.telefonia.service;

import com.telefonia.modelo.Cliente;
import com.telefonia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public List<Cliente> listarPorVendedorId(Long vendedorId) {
        return repository.findByVendedorId(vendedorId);
    }

    public void guardar(Cliente cliente) {
        repository.save(cliente);
    }

    public Cliente buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
