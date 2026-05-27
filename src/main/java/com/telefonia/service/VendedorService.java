package com.telefonia.service;

import com.telefonia.modelo.Vendedor;
import com.telefonia.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository repository;

    public List<Vendedor> listar() {
        return repository.findAll();
    }

    public void guardar(Vendedor vendedor) {
        repository.save(vendedor);
    }

    public Vendedor buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
