package es.cdmfp.dwes.tienda;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * La pagina del catalogo, con su filtro por tipo.
 *
 * <p>El parametro {@code tipo} PUEDE NO VENIR: al entrar en /catalogo sin
 * filtrar salen todos los articulos.</p>
 */
@Controller
public class CatalogoControlador {

    @GetMapping({"/", "/catalogo"})
    public String catalogo(@CookieValue(value = "idioma", defaultValue = "es") String idioma, @RequestParam(required = false) String tipo, Model modelo) {

        List<Articulo> encontrados = new ArrayList<>();

        for (Articulo a : Catalogo.todos()) {
            if (tipo == null || tipo.isBlank() || a.getTipo().equals(tipo)) {
                encontrados.add(a);
            }
        }

        modelo.addAttribute("catalogo", encontrados);
        modelo.addAttribute("tipo", tipo);
        modelo.addAttribute("idioma", idioma);
        return "catalogo";
    }

}
