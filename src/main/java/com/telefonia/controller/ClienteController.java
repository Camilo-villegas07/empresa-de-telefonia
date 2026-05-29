package com.telefonia.controller;

import com.telefonia.modelo.Cliente;
import com.telefonia.service.ClienteService;
import com.telefonia.service.VendedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/clientes")
    public String listarClientes(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        if (isAdmin != null && isAdmin) {
            // Admin ve todos los clientes
            model.addAttribute("clientes", service.listar());
        } else if (usuarioId != null) {
            // Vendedor solo ve sus clientes
            var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
            if (vendedor != null) {
                model.addAttribute("clientes", service.listarPorVendedorId(vendedor.getId()));
            } else {
                model.addAttribute("clientes", List.of());
            }
        } else {
            model.addAttribute("clientes", List.of());
        }
        return "clientes";
    }

    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formCliente";
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if (usuarioId != null) {
            var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
            if (vendedor != null) {
                cliente.setVendedorId(vendedor.getId());
            }
        }
        service.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editarCliente/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", service.buscar(id));
        return "formCliente";
    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "Cliente eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el cliente porque tiene ventas asociadas");
        }
        return "redirect:/clientes";
    }
}
