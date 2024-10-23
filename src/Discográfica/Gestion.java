package Discográfica;

import java.util.Iterator;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Gestion implements Serializable {
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

    //elimina un artista verificando que este exista

    public void removeArtista(String identificador){
        if(artistas.containsKey(identificador)){
            artistas.remove(identificador);
        }else{
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }

    // metodo para filtrar artistas x cant de integrantes y genero
    public List<Artista> filtrarArtistas(byte cantidadIntegrantes, GeneroMusical genero) {
        List<Artista> artistasFiltrados = new ArrayList<>();

        for (Artista artista : artistas.values()) {
            if (artista.getCantIntegrantes() == cantidadIntegrantes && artista.getGeneroMusical() == genero) {
                artistasFiltrados.add(artista);
            }
        }
        return artistasFiltrados;
    } //se puede usar solo un filtro para buscar o tienen que ser si o si con los dos???. Preguntar profe


    public TreeMap<String, Artista> getArtistas() {
        return artistas;
    }

    //metodo para buscar artistas x id
    public Artista filtraArtistaPorID(String identificador){
        return artistas.get(identificador); //si no encuentra devuelve null
    }

    //Informe completo de los datos de cada artista de la Discografica

    public String toString(){
        Iterator<Artista> iterator = artistas.values().iterator();
        Artista artista;
        String listado = "";
        while(iterator.hasNext()){
            artista=iterator.next();
            listado+=artista+"\n";
        }
        return listado;
    }
}
