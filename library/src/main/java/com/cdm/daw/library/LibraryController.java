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

    private final List<CatalogForm> form = List.of(
            new CatalogForm("Don Quijote De La Mancha", "Miguel de Cervantes", "1605", 46, "Fantasía | Comedia"),
            new CatalogForm("La Celestina", "Fernando de Rojas", "1499", 23, "Drama"),
            new CatalogForm("El Alquimista", "Paulo Coelho", "1988", 0, "Aventura | Fantasía"),
            new CatalogForm("Blackwater Saga Completa", "Michael McDowell", "1983", 102, "Misterio | Drama | Terror"),
            new CatalogForm("Los Juegos Del Hambre", "Suzanne Collins", "2008", 10, "Ciencia Ficción | Aventura | Drama"),
            new CatalogForm("D&D Manual del Jugador (5ª edición)", "Mike Mearls | Jeremy Crawford", "2014", 57, "Manual"),
            new CatalogForm("Los Ojos Verdes", "Gustavo Adolfo Bécquer", "1861", 0, "Poesía | Misterio"),
            new CatalogForm("Tiempo De Silencio", "Luis Martín-Santos", "1961", 124, "Drama | Política | Contemporáneo"),
            new CatalogForm("Rimas De Un Poeta Desconocido", "Rafael Castillo", "", 1, "Poesía | Drama"),
            new CatalogForm("Juego De Tronos", "George R.R. Martin", "1996", 1250, "Fantasía"),
            new CatalogForm("El Señor De Los Anillos", "J.R.R. Tolkien", "1955", 460, "Fantasía | Aventura"),
            new CatalogForm("Harry Potter y la Piedra Filosofal", "J.K. Rowling", "1997", 500, "Fantasía | Aventura"),
            new CatalogForm("Harry Potter y la Cámara Secreta", "J.K. Rowling", "1998", 500, "Fantasía | Aventura"),
            new CatalogForm("Harry Potter y el Prisionero de Azkaban", "J.K. Rowling", "1999", 500, "Fantasía | Aventura"),
            new CatalogForm("Harry Potter y el Cáliz de Fuego", "J.K. Rowling", "2000", 500, "Fantasía | Aventura"),
            new CatalogForm("Harry Potter y la Orden del Fénix", "J.K. Rowling", "2003", 500, "Fantasía | Aventura"),
            new CatalogForm("Harry Potter y el Misterio del Príncipe", "J.K. Rowling", "2005", 500, "Fantasía | Aventura"),
            new CatalogForm("Harry Potter y las Reliquias de la Muerte", "J.K. Rowling", "2007", 500, "Fantasía | Aventura"),
            new CatalogForm("En llamas", "Suzanne Collins", "2009", 20, "Ciencia Ficción | Aventura"),
            new CatalogForm("Sinsajo", "Suzanne Collins", "2010", 20, "Ciencia Ficción | Aventura"),
            new CatalogForm("Balada de pájaros cantores y serpientes", "Suzanne Collins", "2020", 15, "Ciencia Ficción | Aventura"),
            new CatalogForm("Java: The Complete Reference (Manual Oficial)", "Herbert Schildt", "2022", 10, "Manual"),
            new CatalogForm("El Principito", "Antoine de Saint-Exupéry", "1943", 30, "Infantil | Fantasía"),
            new CatalogForm("Cinco horas con Mario", "Miguel Delibes", "1966", 12, "Drama"),
            new CatalogForm("El Arte de la Guerra", "Sun Tzu", "Siglo V a.C.", 25, "Histórico | Manual"),
            new CatalogForm("Bodas de Sangre", "Federico García Lorca", "1933", 15, "Drama"),
            new CatalogForm("Yerma", "Federico García Lorca", "1934", 15, "Drama"),
            new CatalogForm("La Casa de Bernarda Alba", "Federico García Lorca", "1936", 15, "Drama"),
            new CatalogForm("El Buscón", "Francisco de Quevedo", "1626", 8, "Novela Picaresca | Clásico"),
            new CatalogForm("Sueños y discursos", "Francisco de Quevedo", "1627", 7, "Histórico | Comedia"),
            new CatalogForm("Novelas Ejemplares", "Miguel de Cervantes", "1613", 10, "Comedia"),
            new CatalogForm("La Galatea", "Miguel de Cervantes", "1585", 6, "Histórico | Drama"),
            new CatalogForm("Los trabajos de Persiles y Sigismunda", "Miguel de Cervantes", "1617", 6, "Histórico | Drama"),
            new CatalogForm("Rimas y Leyendas", "Gustavo Adolfo Bécquer", "1871", 18, "Poesía | Romance"),
            new CatalogForm("Star Wars: Trilogía de Thrawn", "Timothy Zahn", "1991", 12, "Ciencia Ficción"),
            new CatalogForm("El Hobbit", "J.R.R. Tolkien", "1937", 400, "Fantasía | Aventura"),
            new CatalogForm("Dune", "Frank Herbert", "1965", 3, "Ciencia Ficción | Aventura"),
            new CatalogForm("La Regenta", "Leopoldo Alas Clarín", "1884", 3, "Ciencia Ficción"),
            new CatalogForm("Frankenstein", "Mary Shelley", "1818", 3, "Terror | Ciencia Ficción"),
            new CatalogForm("Drácula", "Bram Stoker", "1897", 3, "Terror"),
            new CatalogForm("Nacidos de la Bruma", "Brandon Sanderson", "2006", 3, "Fantasía | Aventura"),
            new CatalogForm("Fortunata y Jacinta", "Benito Pérez Galdós", "1887", 3, "Drama"),
            new CatalogForm("El Nombre de la Rosa", "Umberto Eco", "1980", 3, "Misterio | Histórico | Romance")
    );


    @GetMapping("/catalog")
    public String book_catalog(@RequestParam(defaultValue="")
                               String title, @RequestParam(defaultValue = "unknown")
                               String author, @RequestParam(defaultValue = "unknown")
                               String date, @RequestParam(defaultValue= "0", required = false)
                               int copies, @RequestParam(defaultValue="Desconocido", required = false) String genres, Model model){

        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("date", date);
        model.addAttribute("copies", copies);
        model.addAttribute("genres", genres);
        model.addAttribute("form", this.form);

        return "catalog";
    }
    @PostMapping("/catalog/post")
    public String add_book(@ModelAttribute CatalogForm form, Model model){
        List<String> errors = new ArrayList<>();

        if (form.getCopies() < 0) {
            errors.add("No puede haber copias negativas");
        }
        if (form.getTitle().isBlank() || form.getTitle() ==  null || form.getDate().isBlank() || form.getDate() == null){
            errors.add("Faltan campos por rellenar");
        }
        if (form.getTitle().length()>256 || form.getDate().length()>256 || form.getAuthor().length()>256) {
            errors.add("Uno de los datos introducidos es demasiado largo");
        }
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("form", form);
            return "new_book";
        }

        return "catalog";
    }
}
