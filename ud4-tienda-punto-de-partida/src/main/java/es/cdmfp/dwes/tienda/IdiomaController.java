package es.cdmfp.dwes.tienda;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IdiomaController {
    @GetMapping("/idioma")
    public String guardarIdioma(@RequestParam String codigo, HttpServletResponse response) {
        Cookie cookie = new Cookie("idioma", codigo);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/catalogo";
    }

}
