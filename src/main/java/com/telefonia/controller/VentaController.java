package com.telefonia.controller;

import com.telefonia.modelo.Venta;
import com.telefonia.service.VentaService;
import com.telefonia.service.ClienteService;
import com.telefonia.service.VendedorService;
import com.telefonia.service.PlanService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String listarVentas(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        if (isAdmin != null && isAdmin) {
            // Admin ve todas las ventas
            model.addAttribute("ventas", ventaService.listar());
        } else if (usuarioId != null) {
            // Vendedor solo ve sus ventas
            var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
            if (vendedor != null) {
                model.addAttribute("ventas", ventaService.listarPorVendedorId(vendedor.getId()));
            } else {
                model.addAttribute("ventas", List.of());
            }
        } else {
            model.addAttribute("ventas", List.of());
        }
        return "ventas";
    }

    @GetMapping("/nuevaVenta")
    public String nuevaVenta(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        model.addAttribute("venta", new Venta());
        model.addAttribute("planes", planService.listar());
        
        if (isAdmin != null && isAdmin) {
            // Admin ve todos los clientes y vendedores
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("vendedores", vendedorService.listar());
        } else if (usuarioId != null) {
            // Vendedor solo ve sus clientes y a sí mismo
            var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
            if (vendedor != null) {
                model.addAttribute("clientes", clienteService.listarPorVendedorId(vendedor.getId()));
                model.addAttribute("vendedores", List.of(vendedor));
            } else {
                model.addAttribute("clientes", List.of());
                model.addAttribute("vendedores", List.of());
            }
        } else {
            model.addAttribute("clientes", List.of());
            model.addAttribute("vendedores", List.of());
        }

        return "formVenta";
    }

    @PostMapping("/guardarVenta")
    public String guardarVenta(@ModelAttribute Venta venta, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        if (usuarioId != null) {
            var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
            if (vendedor != null) {
                venta.setVendedor(vendedor);
            }
        }
        ventaService.guardar(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editarVenta/{id}")
    public String editarVenta(@PathVariable Long id, Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        Venta venta = ventaService.buscar(id);
        if (venta == null) {
            return "redirect:/ventas";
        }
        
        // Verificar permisos: admin puede editar todas, vendedor solo las suyas
        if (isAdmin == null || !isAdmin) {
            if (usuarioId != null) {
                var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
                if (vendedor == null || !vendedor.getId().equals(venta.getVendedor().getId())) {
                    return "redirect:/ventas";
                }
            } else {
                return "redirect:/ventas";
            }
        }
        
        model.addAttribute("venta", venta);
        model.addAttribute("planes", planService.listar());
        
        if (isAdmin != null && isAdmin) {
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("vendedores", vendedorService.listar());
        } else if (usuarioId != null) {
            var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
            if (vendedor != null) {
                model.addAttribute("clientes", clienteService.listarPorVendedorId(vendedor.getId()));
                model.addAttribute("vendedores", List.of(vendedor));
            } else {
                model.addAttribute("clientes", List.of());
                model.addAttribute("vendedores", List.of());
            }
        } else {
            model.addAttribute("clientes", List.of());
            model.addAttribute("vendedores", List.of());
        }
        
        return "formVenta";
    }

    @GetMapping("/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable Long id, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        Venta venta = ventaService.buscar(id);
        if (venta == null) {
            return "redirect:/ventas";
        }
        
        // Verificar permisos: admin puede eliminar todas, vendedor solo las suyas
        if (isAdmin == null || !isAdmin) {
            if (usuarioId != null) {
                var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
                if (vendedor == null || !vendedor.getId().equals(venta.getVendedor().getId())) {
                    return "redirect:/ventas";
                }
            } else {
                return "redirect:/ventas";
            }
        }
        
        ventaService.eliminar(id);
        return "redirect:/ventas";
    }

    @GetMapping("/cambiarEstadoVenta/{id}")
    public String mostrarFormularioCambioEstado(@PathVariable Long id, Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        Venta venta = ventaService.buscar(id);
        if (venta == null) {
            return "redirect:/ventas";
        }
        
        // Verificar permisos: admin puede cambiar estado de todas, vendedor solo las suyas
        if (isAdmin == null || !isAdmin) {
            if (usuarioId != null) {
                var vendedor = vendedorService.buscarPorUsuarioId(usuarioId);
                if (vendedor == null || !vendedor.getId().equals(venta.getVendedor().getId())) {
                    return "redirect:/ventas";
                }
            } else {
                return "redirect:/ventas";
            }
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
