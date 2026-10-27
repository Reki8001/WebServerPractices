package es.cdmfp.dwes.tienda;

import java.util.ArrayList;
import java.util.List;

/**
 * El catalogo de la tienda, escrito en el codigo.
 *
 * <p>En la UD6 esto dejara de ser una lista y pasara a ser una tabla de la base
 * de datos. Por ahora vive aqui para que todos trabajemos con los mismos datos.</p>
 */
public final class Catalogo {

    /** Nadie crea objetos de esta clase: solo se usan sus metodos estaticos. */
    private Catalogo() { }

    private static final List<Articulo> ARTICULOS = new ArrayList<>(List.of(
            new Articulo("Teclado mecanico",  "periferico", 69.90, 12),
            new Articulo("Raton inalambrico", "periferico", 24.50,  0),
            new Articulo("Monitor 27\"",      "pantalla",  189.00,  4),
            new Articulo("Monitor 24\"",      "pantalla",  119.00,  9),
            new Articulo("SSD 1 TB",          "almacen",    74.95,  7),
            new Articulo("Disco 4 TB",        "almacen",    89.90,  2)));

    /** @return todos los articulos del catalogo */
    public static List<Articulo> todos() {
        return ARTICULOS;
    }

    /**
     * Busca un articulo por su nombre.
     *
     * @param nombre el nombre exacto del articulo
     * @return el articulo, o null si no esta en el catalogo
     */
    public static Articulo porNombre(String nombre) {
        for (Articulo a : ARTICULOS) {
            if (a.getNombre().equals(nombre)) {
                return a;
            }
        }
        return null;
    }
}
