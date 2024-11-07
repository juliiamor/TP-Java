package Discográfica;
import java.util.*;
import java.io.Serializable;

public class Gestion implements Serializable {
    private TreeMap<String, Artista> artistas;
    private static final long serialVersionUID=123456L;
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
            throw new IllegalArgumentException("El artista no existe");
        }
    }

    // metodo para filtrar artistas x cant de integrantes y genero
    public TreeMap<String,Artista> filtrarArtistas(byte cantidadIntegrantes, GeneroMusical genero) {
        TreeMap<String,Artista> artistasFiltrados = new TreeMap<>();
        Artista artista;
        for(String id: artistas.keySet()){
            artista=artistas.get(id);
            if(artista.getCantIntegrantes() == cantidadIntegrantes && artista.getGeneroMusical()==genero){
                artistasFiltrados.put(id,artista);
            }
        }
        return artistasFiltrados;
    }
    //filtra solo por cant de integrantes
    public TreeMap<String,Artista> filtrarArtistas(byte cantidadIntegrantes){
        TreeMap<String,Artista> artistasFiltrados = new TreeMap<>();
        Artista artista;
        for(String id: artistas.keySet()){
            artista=artistas.get(id);
            if(artista.getCantIntegrantes() == cantidadIntegrantes){
                artistasFiltrados.put(id,artista);
            }
        }
        return artistasFiltrados;
    }
    //filtra solo por genero
    public TreeMap<String,Artista> filtrarArtistas(GeneroMusical genero){
        TreeMap<String,Artista> artistasFiltrados = new TreeMap<>();
        Artista artista;
        for(String id: artistas.keySet()){
            artista=artistas.get(id);
            if(artista.getGeneroMusical()==genero){
                artistasFiltrados.put(id,artista);
            }
        }
        return artistasFiltrados;
    }

    public TreeMap<String, Artista> getArtistas() {
        return artistas;
    }

    public Artista getArtista(String id){
        if(artistas.containsKey(id)){
            return artistas.get(id);
        }else{
            throw new IllegalArgumentException();
        }
    }

    //metodo para buscar artistas x id
    public Artista filtraArtistaPorID(String identificador){
        return artistas.get(identificador); //si no encuentra devuelve null
    }

    //Informe completo de los datos de cada artista de la Discografica

    public String toString(){
        Iterator<Artista> iterator = artistas.values().iterator();
        Artista artista;
        StringBuilder listado = new StringBuilder();
        while(iterator.hasNext()){
            artista=iterator.next();
            listado.append(artista).append("\n\n");
        }
        return listado.toString();
    }



    public double totalRecitalesMes(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException();
        }else{
            Artista artista = artistas.get(id);
            return artista.netoRecitalUltMes();
        }
    }

    public double totalReproduccionesMes(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException();
        }else{
            Artista artista = artistas.get(id);
            return artista.reproduccionesUltMes() * 0.1f;
        }
    }

    public double totalDiscosVendidosMes(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException();
        } else {
            Artista artista = artistas.get(id);
            return artista.unidadesDiscosVendidas() * 100;
        }
    }

    public double totalGenerado(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException();
        } else {
            return totalDiscosVendidosMes(id) + totalReproduccionesMes(id) + totalRecitalesMes(id);
        }
    }

    public double totalArtista(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException();
        } else {
            return totalGenerado(id) - totalDiscografica(id);
        }
    }

    public double totalDiscografica(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException();
        } else {
            Artista artista = artistas.get(id);
            return totalGenerado(id) * artista.porcentajeRegalias();
        }
    }

   /* public StringBuilder facturacionUltMes(String identificador) {
        Artista artista = artistas.get(identificador);
        double total = 0;
        StringBuilder listado = new StringBuilder();

        if (artista != null) {
            listado.append("Artista: ").append(artista.getNombre());
            listado.append("\nDescripción \t\t\t Total recaudado").append("\n");
            listado.append("Recitales \t\t\t ").append(String.format("%.2f",totalRecitalesMes(artista))).append("\n");

            listado.append("Discos \t\t\t\t ").append(String.format("%.2f",totalDiscosVendidosMes(artista))).append("\n");

            listado.append("Reproducciones \t\t\t ").append(String.format("%.2f",totalReproduccionesMes(artista))).append("\n");

            listado.append("\n\nTotal a Discografica (").append(artista.porcentajeRegalias()*100).append("%) \t ").append(String.format("%.2f",totalDiscografica(identificador))).append("\n");

            listado.append("Total para el Artista: \t\t").append(String.format("%.2f",totalArtista(identificador))).append("\n");
        } else {
            listado.append("No se encontró al artista");
        }
        return listado;
    }*/
}
