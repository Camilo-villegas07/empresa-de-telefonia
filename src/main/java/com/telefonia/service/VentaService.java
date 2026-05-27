package com.telefonia.service;

import com.telefonia.enums.EstadoVenta;
import com.telefonia.modelo.Venta;
import com.telefonia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    public Venta buscar(Long id) {
        Optional<Venta> optional = ventaRepository.findById(id);
        return optional.orElse(null);
    }

    public void guardar(Venta venta) {
        // Asigna fecha si viene nula
        if (venta.getFechaVenta() == null) {
            venta.setFechaVenta(LocalDate.now());
        }
        // Asigna estado inicial si viene nulo
        if (venta.getEstado() == null || venta.getEstado().isEmpty()) {
            venta.setEstado(EstadoVenta.PROSPECTO.name());
        }
        ventaRepository.save(venta);
    }

    public void actualizarEstado(Long id, String nuevoEstado, String observacion) {
        try {
            EstadoVenta.valueOf(nuevoEstado); // valida que exista en el enum
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado no válido: " + nuevoEstado);
        }
        Venta venta = buscar(id);
        if (venta != null) {
            venta.setEstado(nuevoEstado);
            if (observacion != null && !observacion.isEmpty()) {
                venta.setObservaciones(observacion);
            }
            ventaRepository.save(venta);
        }
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }
}
