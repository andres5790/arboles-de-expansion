public class Imprenta {
    private Nodo computadora;
    private  Nodo impresora;
    private int tiempodeimpresion;

    public Imprenta(Nodo computadora, Nodo impresora, int cantidadimpresiones) {
        this.computadora = computadora;
        this.impresora = impresora;
        this.tiempodeimpresion = cantidadimpresiones;
    }

    public Nodo getComputadora() {
        return computadora;
    }

    public Nodo getImpresora() {
        return impresora;
    }

    public int getTiempodeimpresion() {
        return tiempodeimpresion;
    }

    @Override
    public String toString() {
        return computadora.getNombre()
                + " -> "
                + impresora.getNombre()
                + " : "
                + tiempodeimpresion;
    }
}
