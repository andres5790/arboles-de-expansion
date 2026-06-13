public class Imprenta {
    private Nodo origen;
    private  Nodo destino;
    private int longitud;

    public Imprenta(Nodo origen, Nodo destino, int longuitud) {
        this.origen = origen;
        this.destino = destino;
        this.longitud = longuitud;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public int getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {

        return origen.getId() + " (" + origen.getNombre() + ")"
                + " ---- " + longitud + " ---- "
                + destino.getId() + " (" + destino.getNombre() + ")";
    }
}
