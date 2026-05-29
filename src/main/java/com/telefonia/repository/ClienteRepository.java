package com.telefonia.repository;

import com.telefonia.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByVendedorId(Long vendedorId);
}
