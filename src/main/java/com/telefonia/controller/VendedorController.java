package com.telefonia.controller;

import com.telefonia.modelo.Vendedor;
import com.telefonia.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VendedorController {

    @Autowired
    private VendedorService service;

    @GetMapping("/vendedores")
    public String listarVendedores(Model model) {
        model.addAttribute("vendedores", service.listar());
        return "vendedores";
    }

    @GetMapping("/nuevoVendedor")
    public String nuevoVendedor(Model model) {
        model.addAttribute("vendedor", new Vendedor());
        return "formVendedor";
    }

    @PostMapping("/guardarVendedor")
    public String guardarVendedor(@ModelAttribute Vendedor vendedor) {
        service.guardar(vendedor);
        return "redirect:/vendedores";
    }

    @GetMapping("/editarVendedor/{id}")
    public String editarVendedor(@PathVariable Long id, Model model) {
        model.addAttribute("vendedor", service.buscar(id));
        return "formVendedor";
    }

    @GetMapping("/eliminarVendedor/{id}")
    public String eliminarVendedor(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/vendedores";
    }
}
