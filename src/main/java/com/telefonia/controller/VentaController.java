package com.telefonia.controller;

import com.telefonia.modelo.Venta;
import com.telefonia.service.VentaService;
import com.telefonia.service.ClienteService;
import com.telefonia.service.VendedorService;
import com.telefonia.service.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private PlanService planService;

    @GetMapping("/ventas")
    public String listarVentas(Model model) {

        model.addAttribute("ventas", ventaService.listar());

        return "ventas";
    }

    @GetMapping("/nuevaVenta")
    public String nuevaVenta(Model model) {

        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("vendedores", vendedorService.listar());
        model.addAttribute("planes", planService.listar());

        return "formVenta";
    }

    @PostMapping("/guardarVenta")
    public String guardarVenta(@ModelAttribute Venta venta) {

        ventaService.guardar(venta);

        return "redirect:/ventas";
    }

    @GetMapping("/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable Long id) {

        ventaService.eliminar(id);

        return "redirect:/ventas";
    }

    @GetMapping("/cambiarEstadoVenta/{id}")
    public String mostrarFormularioCambioEstado(@PathVariable Long id, Model model) {
        Venta venta = ventaService.buscar(id);
        if (venta == null) {
            return "redirect:/ventas";
        }
        model.addAttribute("venta", venta);
        return "cambiarEstadoVenta";
    }

    @PostMapping("/actualizarEstadoVenta")
    public String actualizarEstado(@RequestParam Long id,
                                   @RequestParam String estado,
                                   @RequestParam(required = false) String observaciones,
                                   RedirectAttributes redirectAttributes) {
        ventaService.actualizarEstado(id, estado, observaciones);
        redirectAttributes.addFlashAttribute("success", "Estado actualizado correctamente");
        return "redirect:/ventas";
    }
}
