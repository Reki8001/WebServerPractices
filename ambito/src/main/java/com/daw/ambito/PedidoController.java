package com.daw.ambito;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {

    @GetMapping("/pedido")
    public String pedido(@RequestParam(defaultValue = "No Name") String producto, @RequestParam(defaultValue = "1") int cantidad, @RequestParam(defaultValue = "0.00") double precio, Model modelo){
        //double total=0.00;
        if(cantidad<0){
            cantidad=cantidad*-1;
        }

        modelo.addAttribute("producto", producto);
        modelo.addAttribute("precio", precio);
        modelo.addAttribute("cantidad", cantidad);
         double total=precio*cantidad+(precio*cantidad*(21.00/100));
        modelo.addAttribute("total", total);
        return "pedido";
    }
}
