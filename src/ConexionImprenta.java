import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashMap;

public class ConexionImprenta {
    //lista de vertices
    private ArrayList<Nodo> nodos;
    //lista de aristas
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
    //metodo para crear una conexion internamente
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
        //ordena los nodos segun su peso
        PriorityQueue<Imprenta> cola =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                Imprenta::getLongitud
                        )
                );

        //creacion del nodo inicial
        Nodo inicio = nodos.get(0);
        //se agrega el nodo inicial a la lista de visitados
        visitados.add(inicio.getId());

        for (Imprenta conexion : conexiones) {

            if (conexion.getOrigen().getId().equals(inicio.getId())
                    || conexion.getDestino().getId().equals(inicio.getId())) {

                cola.offer(conexion);
            }
        }

        StringBuilder resultado = new StringBuilder();

        int costoTotal = 0;

        while (!cola.isEmpty()) {

            Imprenta actual = cola.poll();
            //creacion de nodo de inicio y lllegada usando computadora e impresora
            Nodo origen = actual.getOrigen();
            Nodo destino = actual.getDestino();
            //comprueba si ya fue visitado o no
            boolean origenVisitado =
                    visitados.contains(origen.getId());

            boolean destinoVisitado =
                    visitados.contains(destino.getId());
            // en caso de ambos ya fueron visitados se pasa a la siguente conexion
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

            costoTotal += actual.getLongitud();

            for (Imprenta conexion : conexiones) {

                if (conexion.getOrigen().getId().equals(nuevo.getId())
                        || conexion.getDestino().getId().equals(nuevo.getId())) {

                    cola.offer(conexion);
                }
            }
        }

        resultado.append("\nCosto Total: ")
                .append(costoTotal);

        return resultado.toString();
    }
      // busca la raiz del arbol revisando si son del mismo grupo
    private int find(ArrayList<Integer> padre, int nodo){

        if(padre.get(nodo) == nodo){
            return nodo;
        }

        return find(
                padre,
                padre.get(nodo)
        );
    }
       //en caso de no estar en el mismo grupo los une
    private void union(ArrayList<Integer> padre,
                       int a,
                       int b){

        int raizA = find(padre, a);
        int raizB = find(padre, b);

        padre.set(raizA, raizB);
    }

    public String kruskal(){
         //copia de la lista original
        ArrayList<Imprenta> ordenadas =
                new ArrayList<>(conexiones);
         //ordena de menor timepo a mayor
        ordenadas.sort(
                Comparator.comparingInt(
                        Imprenta::getLongitud
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
                    conexion.getOrigen()
            );

            int destino = nodos.indexOf(
                    conexion.getDestino()
            );
           //comprobacion de que no existe ciclos
            if(find(padre, origen)
                    !=
                    find(padre, destino)){

                resultado.append(
                        conexion.toString()
                ).append("\n");

                costoTotal +=
                        conexion.getLongitud();

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
        Nodo r1=new Nodo("R001","WIFI23");
        Nodo r2=new Nodo("R002","LAN234");
        Nodo r3=new Nodo("R0003","netlifer");
        Nodo r4=new Nodo("R004","Queso23");
        Nodo r5=new Nodo("R005","Tpc45");
        Nodo r6=new Nodo("R006","Wifiveci");
        Nodo r7=new Nodo("R007","Patata");
        Nodo r8=new Nodo("R008","Casawifi");
        insertarNodo(r1);
        insertarNodo(r2);
        insertarNodo(r3);
        insertarNodo(r4);
        insertarNodo(r5);
        insertarNodo(r6);
        insertarNodo(r7);
        insertarNodo(r8);

        insertarConexion(r1,r2,7);
        insertarConexion(r1,r3,18);
        insertarConexion(r1,r4,3);
        insertarConexion(r2,r3,7);
        insertarConexion(r2,r7,9);
        insertarConexion(r3,r5,8);
        insertarConexion(r3,r4,7);
        insertarConexion(r4,r6,15);
        insertarConexion(r4,r5,9);
        insertarConexion(r5,r7,3);
        insertarConexion(r5,r6,3);
        insertarConexion(r5,r8,4);
        insertarConexion(r6,r8,10);
        insertarConexion(r7,r8,5);

    }



}