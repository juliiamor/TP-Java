package Discográfica;
import java.util.*;
import java.io.Serializable;

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

    public StringBuilder facturacionUltMes(String identificador) {
        Artista artista = artistas.get(identificador);
        double total = 0, aux;
        StringBuilder listado = new StringBuilder();

        if (artista != null) {
            listado.append("Artista: ").append(artista.getNombre());
            listado.append("\nDescripción \t\t\t Total recaudado").append("\n");
            aux = artista.netoRecitalUltMes();
            listado.append("Recitales \t\t\t ").append(String.format("%.2f",aux)).append("\n");
            total += aux;

            aux = artista.unidadesDiscosVendidas() * 100; // no se si cada disco tiene el mismo precio o si tiene un costo
            listado.append("Discos \t\t\t\t ").append(String.format("%.2f",aux)).append("\n");
            total += aux;

            aux = artista.reproduccionesUltMes() * 0.1f; // cualquier cosa se cambia el precio del disco y de la reproduccion
            listado.append("Reproducciones \t\t\t ").append(String.format("%.2f",aux)).append("\n");
            total += aux;

            if (artista instanceof ArtistaConsagrado) {
                aux = total * 0.2f;
                listado.append("Regalías (20%) \t\t\t-").append(String.format("%.2f",aux)).append("\n");
            } else {
                aux = total * 0.35f;
                listado.append("Regalías (35%) \t\t\t-").append(String.format("%.2f",aux)).append("\n");
            }
            total -= aux;
            listado.append("Total: \t\t\t\t ").append(String.format("%.2f",total)).append("\n");
        } else {
            listado.append("No se encontró al artista");
        }
        return listado;
    }
}
