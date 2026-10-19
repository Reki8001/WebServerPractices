package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * El controlador con el que termina la UD2: lee parametros, calcula el desglose
 * con IVA y devuelve una plantilla. Hace SIEMPRE lo mismo.
 *
 * La UD3 empieza aqui: decidir, repetir y comprobar.
 */
@Controller
public class PresupuestoController {

    @GetMapping("/presupuesto_form")
    public String mostrarFormulario() {
        return "presupuesto_form";
    }

    @GetMapping("/presupuesto")
    public String presupuesto(@RequestParam(defaultValue = "sin nombre") String producto,
                              @RequestParam(defaultValue = "1") int unidades,
                              @RequestParam(defaultValue = "0") double precio,
                              @RequestParam(defaultValue= "false")boolean montaje,
                              @RequestParam(required=false) String cliente,
                              Model modelo) {

        if (unidades<-100){unidades=100;}else if (unidades<0){unidades=unidades*-1;}
        if (unidades>100){unidades=100;}
        double base = precio * unidades;
        if (montaje==true){base=base+15;}
        double iva  = base * 0.21;

        modelo.addAttribute("producto", producto);
        modelo.addAttribute("unidades", unidades);
        modelo.addAttribute("precio", precio);
        modelo.addAttribute("montaje", montaje);
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("base", base);
        modelo.addAttribute("iva", iva);
        modelo.addAttribute("total", base + iva);

        return "presupuesto";
    }
}
