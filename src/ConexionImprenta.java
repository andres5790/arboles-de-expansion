import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashMap;

public class ConexionImprenta {

    private ArrayList<Nodo> nodos;
    private ArrayList<Imprenta> conexiones;

    public ConexionImprenta() {
        nodos = new ArrayList<>();
        conexiones = new ArrayList<>();
    }

    public void insertarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void insertarConexion(Imprenta conexion) {
        conexiones.add(conexion);
    }

    public void insertarConexion(Nodo computadora,
                                 Nodo impresora,
                                 int cantidadImpresiones) {

        conexiones.add(
                new Imprenta(
                        computadora,
                        impresora,
                        cantidadImpresiones
                )
        );
    }

    public String listarConexiones() {

        if (conexiones.isEmpty()) {
            return "No hay conexiones registradas";
        }

        StringBuilder datos = new StringBuilder();

        for (Imprenta conexion : conexiones) {
            datos.append(conexion.toString()).append("\n");
        }

        return datos.toString();
    }

    //Algoritmo Prim

    public String prim() {

        if (nodos.isEmpty()) {
            return "No existen nodos";
        }

        HashSet<String> visitados = new HashSet<>();

        PriorityQueue<Imprenta> cola =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                Imprenta::getTiempodeimpresion
                        )
                );

        Nodo inicio = nodos.get(0);

        visitados.add(inicio.getId());

        for (Imprenta conexion : conexiones) {

            if (conexion.getComputadora().getId().equals(inicio.getId())
                    || conexion.getImpresora().getId().equals(inicio.getId())) {

                cola.offer(conexion);
            }
        }

        StringBuilder resultado = new StringBuilder();

        int costoTotal = 0;

        while (!cola.isEmpty()) {

            Imprenta actual = cola.poll();

            Nodo origen = actual.getComputadora();
            Nodo destino = actual.getImpresora();

            boolean origenVisitado =
                    visitados.contains(origen.getId());

            boolean destinoVisitado =
                    visitados.contains(destino.getId());

            if (origenVisitado && destinoVisitado) {
                continue;
            }

            Nodo nuevo;

            if (!origenVisitado) {
                nuevo = origen;
            } else {
                nuevo = destino;
            }

            visitados.add(nuevo.getId());

            resultado.append(actual.toString())
                    .append("\n");

            costoTotal += actual.getTiempodeimpresion();

            for (Imprenta conexion : conexiones) {

                if (conexion.getComputadora().getId().equals(nuevo.getId())
                        || conexion.getImpresora().getId().equals(nuevo.getId())) {

                    cola.offer(conexion);
                }
            }
        }

        resultado.append("\nCosto Total: ")
                .append(costoTotal);

        return resultado.toString();
    }

    private int find(ArrayList<Integer> padre, int nodo){

        if(padre.get(nodo) == nodo){
            return nodo;
        }

        return find(
                padre,
                padre.get(nodo)
        );
    }

    private void union(ArrayList<Integer> padre,
                       int a,
                       int b){

        int raizA = find(padre, a);
        int raizB = find(padre, b);

        padre.set(raizA, raizB);
    }

    public String kruskal(){

        ArrayList<Imprenta> ordenadas =
                new ArrayList<>(conexiones);

        ordenadas.sort(
                Comparator.comparingInt(
                        Imprenta::getTiempodeimpresion
                )
        );

        ArrayList<Integer> padre =
                new ArrayList<>();

        for(int i = 0; i < nodos.size(); i++){
            padre.add(i);
        }

        StringBuilder resultado =
                new StringBuilder();

        int costoTotal = 0;

        for(Imprenta conexion : ordenadas){

            int origen = nodos.indexOf(
                    conexion.getComputadora()
            );

            int destino = nodos.indexOf(
                    conexion.getImpresora()
            );

            if(find(padre, origen)
                    !=
                    find(padre, destino)){

                resultado.append(
                        conexion.toString()
                ).append("\n");

                costoTotal +=
                        conexion.getTiempodeimpresion();

                union(
                        padre,
                        origen,
                        destino
                );
            }
        }

        resultado.append(
                "\nCosto Total: "
        ).append(costoTotal);

        return resultado.toString();
    }

    public void predefinir() {

        Nodo pc1 = new Nodo("PC0015", "PCLAB", "Computadora");
        Nodo pc2 = new Nodo("PC230", "PCSUB2", "Computadora");
        Nodo pc3 = new Nodo("PC734", "PCBIBLIOTECA", "Computadora");

        Nodo imp1 = new Nodo("I0123", "HP", "Impresora");
        Nodo imp2 = new Nodo("I6403", "Canon", "Impresora");

        insertarNodo(pc1);
        insertarNodo(pc2);
        insertarNodo(pc3);
        insertarNodo(imp1);
        insertarNodo(imp2);

        insertarConexion(pc1, imp1, 10);
        insertarConexion(pc1, imp2, 5);
        insertarConexion(pc2, imp1, 15);
        insertarConexion(pc2,imp2,7);
        insertarConexion(pc3, imp2, 3);
        insertarConexion(pc3,imp1,16);
    }
}