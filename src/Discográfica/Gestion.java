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

    public void facturacionUltMes(String identificador){
        Artista artista = artistas.get(identificador);
        float total=0, aux;
        System.out.println("Descripcion \t\t\t Total recaudado");
        if (artista!=null){
            aux=artista.netoRecitalUltMes();
            System.out.println("Recitales \t\t\t "+aux);
            total+=aux;
            aux=artista.totalDiscosVendidos()*100;//no se si cada disco tiene el mismo precio o si tiene un costo
            System.out.println("Discos vendidos \t\t\t"+aux);
            total+=aux;
            aux=artista.reproduccionesUltMes()*0.85f;//cualquier cosa se cambia el precio del disco y de la reproduccion
            System.out.println("Reproducciones \t\t\t"+aux);
            if (artista instanceof ArtistaConsagrado){
                aux=total*0.2f;
                System.out.println("Regalias (20%) \t\t\t-"+aux);
            } else{
                aux=total*0.35f;
                System.out.println("Regalias (35%) \t\t\t-"+aux);
            }
            total-=aux;
            System.out.println("Total: \t\t\t"+total);
        } else{
            System.out.println("No se encontro al artista");
        }
    }
}
/*
    public void facturacionUltMes(String identificador){

        Artista artista = artistas.get(identificador);
        float total=0;
        System.out.println("Descripcion \t\t\t Total recaudado");
        if (artista!=null){
            System.out.println("Recitales \t\t\t "+artista.netoRecitalUltMes());
            System.out.println("Discos vendidos \t\t\t"+artista.totalDiscosVendidos()*100);
            total+= artista.netoRecitalUltMes();
            total+=artista.totalDiscosVendidos()*100;
        } else{
            System.out.println("No se encontro al artista");
        }
    }


    public void facturacionUltMes(String identificador){
        Artista artista = artistas.get(identificador);
        float total=0, nrum, tdv;
        System.out.println("Descripcion \t\t\t Total recaudado");
        if (artista!=null){
            nrum=artista.netoRecitalUltMes();
            System.out.println("Recitales \t\t\t "+nrum);
            tdv=artista.totalDiscosVendidos()*100;
            System.out.println("Discos vendidos \t\t\t"+tdv);
            total+=tdv;
            total+=nrum;

        } else{
            System.out.println("No se encontro al artista");
        }
    }



 */