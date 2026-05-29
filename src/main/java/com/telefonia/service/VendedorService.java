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

    public List<Vendedor> listarPorUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Vendedor buscarPorUsuarioId(Long usuarioId) {
        return repository.findVendedorByUsuarioId(usuarioId);
    }

    public void guardar(Vendedor vendedor) {
        repository.save(vendedor);
    }

    public Vendedor buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        // Verificar si el vendedor tiene ventas asociadas
        // Si tiene ventas, no permitir eliminar
        // Esto se maneja en el controlador con try-catch
        repository.deleteById(id);
    }
}
