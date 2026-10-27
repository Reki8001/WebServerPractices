package es.cdmfp.dwes.tienda;

/**
 * Un articulo del catalogo de la tienda.
 *
 * <p>Es una clase normal, con sus dos constructores y sus get/set, que es como
 * se trabaja en el modulo: el constructor vacio hace falta para poder crear el
 * objeto e ir rellenandolo, y el de todos los campos para crearlo de una vez.</p>
 */
public class Articulo {

    private String nombre;
    private String tipo;
    private double precio;
    private int stock;

    public Articulo() { }

    public Articulo(String nombre, String tipo, double precio, int stock) {
        this.nombre = nombre;
        this.tipo   = tipo;
        this.precio = precio;
        this.stock  = stock;
    }

    public String getNombre()            { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo()          { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getPrecio()             { return precio; }
    public void setPrecio(double precio)  { this.precio = precio; }

    public int getStock()           { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
