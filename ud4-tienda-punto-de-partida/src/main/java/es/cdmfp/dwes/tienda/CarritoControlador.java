package es.cdmfp.dwes.tienda;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/carrito")
public class CarritoControlador {

    @SuppressWarnings("unchecked")
    private List<String> carritoDe(HttpSession session) {
        List<String> carrito = (List<String>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }

    @PostMapping("/add")
    public String add(@RequestParam String articulo, HttpSession session) {
        carritoDe(session).add(articulo);
        return "redirect:/carrito";
    }

    @GetMapping
    public String ver(HttpSession session, Model model) {
        List<String> carrito = carritoDe(session);
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", carrito.size());
        return "carrito";
    }

    @PostMapping("/vaciar")
    public String vaciar(HttpSession session) {
        carritoDe(session).clear();
        return "redirect:/catalogo";
    }
}