package Discográfica;

import java.util.Iterator;
import java.util.TreeMap;

public class Gestion {
    private TreeMap<String, Artista> artistas;

    //constructor

    public Gestion(){
        artistas = new TreeMap<>();
    }

    //agrega un artista verificando que este no exista

    public void addArtista(Artista artista){
        if(artistas.containsKey(artista.getIdentificador())){
            throw new IllegalArgumentException("El artista ya esta Registrado.");
        }else{
            artistas.put(artista.getIdentificador(),artista);
        }
    }

    //agrega un disco verificando que el artista exista

    public void addDisco(String identificador,Disco disco){
        if(artistas.containsKey(identificador)){
            Artista artista = artistas.get(identificador);
            artista.addDisco(disco);
        }else{
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }

    //agrega un recital verificando que el artista exista

    public void addRecital(String identificador,Recital recital){
        if(artistas.containsKey(identificador)){
            Artista artista = artistas.get(identificador);
            artista.addRecital(recital);
        }else{
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }

    //elimina un artista verificando que este exista

    public void removeArtista(String identificador){
        if(artistas.containsKey(identificador)){
            artistas.remove(identificador);
        }else{
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }

    //Reporte de detalles de unidades vendidas para cada disco de un artista, informando el promedio de unidades vendidas por disco

    public String listadoUnidadesVendidasPorDisco(String identificador){
        String listado;
        if(artistas.containsKey(identificador)){
            Artista artista = artistas.get(identificador);
            listado="Listado de Unidades vendidas por disco: "+artista.listadoDatos()+artista.listadoUnidadesVendidasPorDisco();
        }else
            throw new IllegalArgumentException("Artista no encontrado");
        return listado;
    }

    //Consulta datos completos de artistas de x numero de integrantes y segun el genero del artista

    public String listadoCompletoPorGeneroIntegrantes(String genero,byte integrantes){
        String listado="Lista de Artistas del genero musical "+genero+" de "+integrantes+" Integrantes";
        Iterator<Artista> iterator = artistas.values().iterator();
        Artista artista;
        while(iterator.hasNext()){
            artista= iterator.next();
            if(artista.getCantIntegrantes()==integrantes && artista.getGeneroMusical().equals(genero)){
                listado+= artista.toString();
            }
        }
        return listado;
    }

    //Informe completo de los datos de cada artista de la Discografica

    public String toString(){
        String listado="Lista de Artistas de la Discografica";
        Iterator<Artista> iterator = artistas.values().iterator();
        Artista artista;
        while(iterator.hasNext()){
            artista = iterator.next();
            listado+="\n"+artista.toString();
        }
        return listado;
    }
}
