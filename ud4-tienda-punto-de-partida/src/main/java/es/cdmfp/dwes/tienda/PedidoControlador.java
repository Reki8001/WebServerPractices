package es.cdmfp.dwes.tienda;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PedidoControlador {

    private static final double ENVIO    = 6.95;
    private static final double SIN_GASTOS = 150.0;
    private static final int    MINIMO_DESCUENTO = 3;

    private final List<Pedido> historial = new ArrayList<>();

    @GetMapping("/pedido")
    public String formulario(@CookieValue(value = "idioma", defaultValue = "es") String idioma, Model modelo) {
        modelo.addAttribute("form", new PedidoForm());
        modelo.addAttribute("catalogo", Catalogo.todos());
        modelo.addAttribute("idioma", idioma);
        return "pedido-formulario";
    }

    @PostMapping("/pedido")
    public String procesar(@CookieValue(value = "idioma", defaultValue = "es") String idioma,
                           @ModelAttribute PedidoForm form,
                           HttpSession session,
                           Model modelo) {

        List<String> errores = new ArrayList<>();

        Articulo articulo = Catalogo.porNombre(form.getArticulo());
        if (articulo == null) {
            errores.add("Elige un articulo del catalogo.");
        }
        if (form.getUnidades() < 1 || form.getUnidades() > 20) {
            errores.add("Las unidades tienen que estar entre 1 y 20.");
        }

        if (!errores.isEmpty()) {
            modelo.addAttribute("errores", errores);
            modelo.addAttribute("catalogo", Catalogo.todos());
            modelo.addAttribute("idioma", idioma);
            return "pedido-formulario";
        }

        double total = calcularTotal(form.getUnidades(), articulo.getPrecio(), form.isSocio());

        historial.add(new Pedido(articulo.getNombre(), form.getUnidades(), total));

        @SuppressWarnings("unchecked")
        List<String> carrito = (List<String>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        carrito.add(articulo.getNombre() + " (x" + form.getUnidades() + ") - " + total + " EUR");

        modelo.addAttribute("articulo", articulo);
        modelo.addAttribute("unidades", form.getUnidades());
        modelo.addAttribute("total", total);
        modelo.addAttribute("historial", historial);
        modelo.addAttribute("idioma", idioma);

        return "pedido-resultado";
    }

    private double calcularTotal(int unidades, double precio, boolean socio) {
        double base = unidades * precio;
        if (unidades >= MINIMO_DESCUENTO) {
            base = base - base * 5 / 100;
        }
        if (base < SIN_GASTOS && !socio) {
            base = base + ENVIO;
        }
        return base + base * 21 / 100;
    }
}