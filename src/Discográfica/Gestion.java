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
            throw new IllegalArgumentException("El artista no existe");
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
    }
    //filtra solo por cant de integrantes
    public List<Artista> filtrarArtistas(byte cantidadIntegrantes){
        List<Artista> artistasFiltrados = new ArrayList<>();

        for(Artista artista : artistas.values()){
            if(artista.getCantIntegrantes()== cantidadIntegrantes){
                artistasFiltrados.add(artista);
            }
        }
        return artistasFiltrados;
    }
    //filtra solo por genero
    public List<Artista> filtrarArtistas(GeneroMusical genero){
        List<Artista> artistasFiltrados = new ArrayList<>();

        for(Artista artista : artistas.values()){
            if(artista.getGeneroMusical()== genero){
                artistasFiltrados.add(artista);
            }
        }
        return artistasFiltrados;

    }

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

            aux = artista.totalDiscosVendidos() * 100; // no se si cada disco tiene el mismo precio o si tiene un costo
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


    /*

    Seria innecesario, porque ya tenemos el metodo toString() que emite el listado.

    public StringBuilder listadoArtistasCompleto() {
        StringBuilder listado = new StringBuilder();

        for (Artista artista : artistas.values()) {
            listado.append(artista.getNombre()).append(" (").append(artista instanceof ArtistaConsagrado? "Consagrado" : "Emergente").append("):\n");

            for (Disco disco : artista.getDiscos()) {
                listado.append("\tDisco: ").append(disco.getNombre()).append("\n");
                listado.append("\t\tCanciones:\n");
                for (Cancion cancion : disco.getCanciones()) {
                    listado.append("\t\t\t- ").append(cancion.getNombre()).append("\n"); // Asegúrate de que Cancion tenga el método getNombre()
                }
            }

            listado.append("\tRecitales:\n");
            for (Recital recital : artista.getRecitales()) {
                listado.append("\t\t- Fecha: ").append(recital.getFecha()).append(", Neto: ").append(recital.getNeto()).append("\n");
            }

            listado.append("\n");
        }

        return listado;
    }
    */
}
