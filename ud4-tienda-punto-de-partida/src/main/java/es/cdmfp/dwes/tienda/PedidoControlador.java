package es.cdmfp.dwes.tienda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * El pedido de la tienda: el GET ensena el formulario y el POST lo procesa.
 *
 * <p>Las reglas son las de la prueba del RA3: 5 % de descuento desde 3 unidades,
 * envio de 6,95 € salvo que el pedido llegue a 150 € sin IVA o el cliente sea
 * socio, y el IVA del 21 % al final, sobre todo lo demas.</p>
 */
@Controller
public class PedidoControlador {

    private static final double ENVIO    = 6.95;
    private static final double SIN_GASTOS = 150.0;
    private static final int    MINIMO_DESCUENTO = 3;

    /** El historial. OJO: esta en el controlador, asi que es de TODOS. Se arregla en la UD4. */
    private final List<Pedido> historial = new ArrayList<>();

    @GetMapping("/pedido")
    public String formulario(@CookieValue(value = "idioma", defaultValue = "es") String idioma, Model modelo) {
        modelo.addAttribute("form", new PedidoForm());
        modelo.addAttribute("catalogo", Catalogo.todos());
        modelo.addAttribute("idioma", idioma);
        return "pedido-formulario";
    }

    @PostMapping("/pedido")
    public String procesar(@CookieValue(value = "idioma", defaultValue = "es") String idioma, @ModelAttribute PedidoForm form, Model modelo) {

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
            return "pedido-formulario";
        }

        double total = calcularTotal(form.getUnidades(), articulo.getPrecio(), form.isSocio());
        historial.add(new Pedido(articulo.getNombre(), form.getUnidades(), total));

        modelo.addAttribute("articulo", articulo);
        modelo.addAttribute("unidades", form.getUnidades());
        modelo.addAttribute("total", total);
        modelo.addAttribute("historial", historial);
        modelo.addAttribute("idioma", idioma);
        return "pedido-resultado";
    }

    /**
     * Calcula el importe final de un pedido, con IVA.
     *
     * @param unidades cuantas unidades se compran
     * @param precio precio de una unidad, sin IVA
     * @param socio true si el cliente es socio
     * @return el importe final, en euros
     */
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
