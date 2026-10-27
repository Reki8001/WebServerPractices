package es.cdmfp.dwes.tienda;

/**
 * Un pedido ya terminado: el dato que se guarda, con el total ya calculado.
 *
 * <p>No recoge nada del formulario. Se crea al final, cuando ya estan hechas
 * las cuentas, y a partir de ahi no cambia.</p>
 */
public class Pedido {

    private String articulo;
    private int unidades;
    private double total;

    public Pedido() { }

    public Pedido(String articulo, int unidades, double total) {
        this.articulo = articulo;
        this.unidades = unidades;
        this.total    = total;
    }

    public String getArticulo()              { return articulo; }
    public void setArticulo(String articulo) { this.articulo = articulo; }

    public int getUnidades()              { return unidades; }
    public void setUnidades(int unidades) { this.unidades = unidades; }

    public double getTotal()            { return total; }
    public void setTotal(double total)  { this.total = total; }
}
