package Discográfica;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

        //genera archivo con top 10
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Top10Canciones" + genero + ".txt"))) {
            writer.write("Top 10 canciones del género " + genero + ":\n");
            for (int i = 0; i < top10Canciones.size(); i++) {
                Cancion cancion = top10Canciones.get(i);
                writer.write((i + 1) + ". " + cancion.getNombre() + " - " + cancion.getCantReprodUltMes() + " reproducciones\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}

