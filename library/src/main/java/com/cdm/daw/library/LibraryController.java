package com.cdm.daw.library;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryController {

    @GetMapping("/catalog")
    public String book_catalog(@RequestParam(defaultValue="")
                               String title, @RequestParam(defaultValue = "unknown")
                               String author, @RequestParam(defaultValue = "unknown")
                               String date, @RequestParam(defaultValue= "0", required = false)
                               int copies, Model model){

        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("date", date);
        model.addAttribute("copies", copies);


        return "catalog";
    }
    @PostMapping("/catalog/post")
    public String add_book(@ModelAttribute CatalogForm form, Model model){
        List<String> errors = new ArrayList<>();

        if (form.getCopies() < 0) {
            errors.add("No puede haber copias negativas");
        }
        if (form.getTitle.isBlank() || form.getDate.isBlank() || form.getCopies == null){
            errors.add("Faltan campos por rellenar");
        }
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "catalog";
        }

        return "catalog";
    }
}
