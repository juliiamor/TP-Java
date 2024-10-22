package Discográfica;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public abstract class Reportes {

    public Reportes() {
    }

    public static void Top10CancionesPorGenero(TreeMap<String, Artista> artistas, String genero) {
        // Lista para almacenar canciones del género dado
        List<Cancion> cancionesDelGenero = new ArrayList<>();

        for (Artista artista : artistas.values()) {
            if (artista.getGeneroMusical().equalsIgnoreCase(genero)) {
                for (Disco disco : artista.getDiscos()) {
                    cancionesDelGenero.addAll(disco.getCanciones());
                }
            }
        }
        // Ordena las canciones por cantidad de reproducciones en orden descendente
        Collections.sort(cancionesDelGenero, Comparator.comparingLong(Cancion::getCantReprodUltMes).reversed());

        // Genera una sublista con las primeras 10 canciones, si hay menos de 10 la genera con la cantidad que haya
        List<Cancion> top10Canciones = cancionesDelGenero.subList(0, Math.min(10, cancionesDelGenero.size()));

        // Imprimir en pantalla
        System.out.println("Top 10 canciones del género " + genero + ":");
        for (int i = 0; i < top10Canciones.size(); i++) {
            Cancion cancion = top10Canciones.get(i);
            System.out.println((i + 1) + ". " + cancion.getNombre() + " - " + cancion.getCantReprodUltMes() + " reproducciones");
        }

        GeneraArchivos.generaArchivoTop10Canciones(genero, top10Canciones);
    }

    public static void unidadesVendidas(String identificador, TreeMap<String, Artista> artistas) {
        // Verifica si el artista existe
        if (artistas.containsKey(identificador)) {
            Artista artista = artistas.get(identificador);

            // Crea el contenido del reporte
            StringBuilder contenido = new StringBuilder();
            contenido.append("Reporte de Unidades Vendidas para ").append(artista.getNombre()).append(":\n");

            // Variables para calcular el total de unidades vendidas y el promedio
            long totalUnidadesVendidas = artista.unidadesDiscosVendidas(); //Utiliza el metodo ya existente que calcula el total de unidades de discos vendidos
            int cantidadDiscos = artista.getDiscos().size();

            // Recorre los discos del artista
            for (Disco disco : artista.getDiscos()) {
                // Escribe el nombre del disco y sus unidades vendidas
                String discoInfo = "Disco: " + disco.getNombre() + " - Unidades vendidas: " + disco.getUnidadesVendidasUltMes() + "\n";
                contenido.append(discoInfo);
                System.out.print(discoInfo); // Imprime info del disco en pantalla
            }

            // Escribe el total de discos
            String totalDiscos = "\nTotal de discos: " + totalUnidadesVendidas + "\n";
            contenido.append(totalDiscos);
            System.out.print(totalDiscos); // Imprime total de discos en pantalla

            // Calcula y escribe el promedio de unidades vendidas
            if (cantidadDiscos > 0) {
                double promedioUnidades = (double) totalUnidadesVendidas / cantidadDiscos;
                String promedio = "Promedio de unidades vendidas por disco: " + promedioUnidades + "\n";
                contenido.append(promedio);
                System.out.print(promedio); // Imprime promedio en pantalla
            } else {
                String noDiscos = "No hay discos para calcular el promedio.\n";
                contenido.append(noDiscos);
                System.out.print(noDiscos); // Imprime mensaje en pantalla
            }

            // Llama al método para generar el archivo con las unidades vendidas
            GeneraArchivos.generaArchivoUnidadesVendidas(artista.getNombre(), contenido.toString());

        } else {
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }
}
