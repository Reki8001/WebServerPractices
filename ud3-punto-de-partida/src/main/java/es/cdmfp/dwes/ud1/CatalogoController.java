package es.cdmfp.dwes.ud1;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class CatalogoController {
    public record Articulo(int numero_orden, String nombre, double precio, int stock){};

    List<Articulo> catalogo = List.of(
            new Articulo(1, "Gibson", 89.99, 17),
            new Articulo(2, "Les Paul", 65.00, 307),
            new Articulo(3, "SG", 69.99, 32),
            new Articulo(4, "ES-335", 59.99, 0),
            new Articulo(5, "Explorer", 59.99, 41),
            new Articulo(6, "Firebird", 89.99, 22),
            new Articulo(7, "Acústica", 35.00, 10),
            new Articulo(8, "Flying V", 107.00, 3)
    );

    @GetMapping("/catalogo")
    public String catalogo(Model modelo){

        modelo.addAttribute("catalogo", catalogo);
        return "catalogo";
    }
}
