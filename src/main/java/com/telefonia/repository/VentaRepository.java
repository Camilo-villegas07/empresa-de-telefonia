package com.telefonia.repository;

import com.telefonia.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByVendedorId(Long vendedorId);
}
