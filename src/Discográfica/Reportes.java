package Discográfica;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public abstract class Reportes {
    public Reportes() {
    }

    public static void Top10CancionesPorGenero(TreeMap<String, Artista> artistas,String genero) {
        //Lista para almacenar canciones del genero dado
        List<Cancion> cancionesDelGenero = new ArrayList<>();

        for (Artista artista : artistas.values()){
            if(artista.getGeneroMusical().equalsIgnoreCase(genero)){
                for(Disco disco : artista.getDiscos()){
                    cancionesDelGenero.addAll(disco.getCanciones());
                }
            }
        }
        //ordena las canciones por cantidad de reproducciones en orden descendente
        Collections.sort(cancionesDelGenero, Comparator.comparingLong(Cancion::getCantReprodUltMes).reversed());

        // genera una sublista con las primeras 10 canciones, si hay menos de 10 la genera con la cantidad que haya
        List<Cancion> top10Canciones = cancionesDelGenero.subList(0, Math.min(10, cancionesDelGenero.size()));

        System.out.println("Top 10 canciones del género"+ genero +":");
        for(int i=0;i< top10Canciones.size();i++){
            Cancion cancion = top10Canciones.get(i);
            System.out.println((i + 1) + ". " + cancion.getNombre() + " - " + cancion.getCantReprodUltMes() + " reproducciones");
        }
    }
}
