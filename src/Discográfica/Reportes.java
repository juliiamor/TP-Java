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

    public void Top10CancionesPorGenero(TreeMap<String, Artista> artistas,String genero) {
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

    public void unidadesVendidas(String identificador, TreeMap<String, Artista> artistas) {
        // Verifica si el artista existe
        if (artistas.containsKey(identificador)) {
            Artista artista = artistas.get(identificador);

            //Crea el archivo de texto para almacenar el reporte
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("UnidadesVendidas_" + artista.getNombre() + ".txt"))) {
                // Encabezado en txt
                String encabezado = "Reporte de Unidades Vendidas para " + artista.getNombre() + ":\n";
                writer.write(encabezado);
                System.out.print(encabezado); // Imprime encabezado en pantalla

                // Variables para calcular el total de unidades vendidas y el promedio
                int totalUnidadesVendidas = 0;
                int cantidadDiscos = artista.getDiscos().size();

                // Recorre los discos del artista
                for (Disco disco : artista.getDiscos()) {
                    // Escribe el nombre del disco y sus unidades vendidas
                    String discoInfo = "Disco: " + disco.getNombre() + " - Unidades vendidas: " + disco.getUnidadesVendidasUltMes() + "\n";
                    writer.write(discoInfo);
                    System.out.print(discoInfo); // Imprime info del disco en pantalla
                    totalUnidadesVendidas += disco.getUnidadesVendidasUltMes(); // Suma las unidades vendidas
                }

                // Escribe el total de discos
                String totalDiscos = "\nTotal de discos: " + cantidadDiscos + "\n";
                writer.write(totalDiscos);
                System.out.print(totalDiscos); //Imprime total de discos en pantalla

                // Calcula y escribe el promedio de unidades vendidas
                if (cantidadDiscos > 0) {
                    double promedioUnidades = (double) totalUnidadesVendidas / cantidadDiscos;
                    String promedio = "Promedio de unidades vendidas por disco: " + promedioUnidades + "\n";
                    writer.write(promedio);
                    System.out.print(promedio); // Imprime promedio en pantalla
                } else {
                    String noDiscos = "No hay discos para calcular el promedio.\n";
                    writer.write(noDiscos);
                    System.out.print(noDiscos);
                }

            } catch (IOException e) {
                System.out.println("Error al escribir el archivo: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }
}

