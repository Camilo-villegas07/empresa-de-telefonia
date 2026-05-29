package com.telefonia.repository;

import com.telefonia.modelo.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    List<Vendedor> findByUsuarioId(Long usuarioId);
    Vendedor findVendedorByUsuarioId(Long usuarioId);
}
