package es.cdmfp.dwes.tienda;

/**
 * El objeto que recoge el formulario del pedido.
 *
 * <p>Clase normal CON set: Spring la crea vacia y la rellena llamando a los set,
 * uno por cada campo que llega. Un record no valdria aqui.</p>
 */
public class PedidoForm {

    private String articulo;
    private int unidades;
    private boolean socio;

    public String getArticulo()              { return articulo; }
    public void setArticulo(String articulo) { this.articulo = articulo; }

    public int getUnidades()              { return unidades; }
    public void setUnidades(int unidades) { this.unidades = unidades; }

    public boolean isSocio()            { return socio; }
    public void setSocio(boolean socio) { this.socio = socio; }
}
