package Discográfica;

import Archivos.GeneraArchivos;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase abstracta que proporciona métodos para generar reportes relacionados con artistas, discos y canciones
 *
 * Los reportes incluyen el top 10 de canciones por género, el reporte de unidades vendidas y el listado de artistas (ID - NOMBRE).
 *
 * Implementa funcionalidades relacionadas con el manejo de datos de artistas y discos musicales, y proporciona métodos
 * para generar reportes en formato de texto.
 *
 * @version 1.0
 */

public abstract class Reportes {

    /**
     * Constructor por defecto
     */
    public Reportes() {
    }

    /**
     * Genera un reporte de las 10 canciones más reproducidas del último mes por genero
     * Almacena y Ordena las canciones por cantidad de reproducciones en una coleccion List y devuelve las 10 más escuchadas
     * Genera el listado y es almacenado un archivo de Texto manejado por la clase {@link GeneraArchivos}
     *
     * @param artistas Mapa de artistas previamente ya filtrados
     * @param genero El género musical por el que se desea generar el reporte
     * @return Un String con el reporte de las top 10 canciones del género solicitado
     */
    public static String Top10CancionesPorGenero(TreeMap<String, Artista> artistas,GeneroMusical genero) {
        List<Cancion> cancionesDelGenero = new ArrayList<>();

        for (Artista artista:artistas.values()) {
            if (artista.getGeneroMusical() == genero) {
                for (Disco disco:artista.getDiscos()) {
                    cancionesDelGenero.addAll(disco.getCanciones());
                }
            }
        }

        Collections.sort(cancionesDelGenero, Comparator.comparingLong(Cancion::getCantReprodUltMes).reversed());

        List<Cancion> top10Canciones = cancionesDelGenero.subList(0, Math.min(10, cancionesDelGenero.size()));

        StringBuilder resultado = new StringBuilder();
        resultado.append("Top 10 canciones del género ").append(genero).append(":\n\n");
        for (int i = 0; i < top10Canciones.size(); i++) {
            Cancion cancion = top10Canciones.get(i);
            resultado.append("\t").append((i + 1)).append(". ").append(cancion.getNombre()).append(" - ").append(cancion.getCantReprodUltMes()).append(" reproducciones\n");
        }

        GeneraArchivos.generaArchivoTop10Canciones(genero, resultado.toString());
        return resultado.toString();
    }

    /**
     * Genera un reporte de las unidades vendidas de los discos de un artista, incluyendo detalles de cada disco
     * Calcula el total de unidades vendidas y el promedio de unidades vendidas por disco
     *
     * @param identificador El identificador del artista para el que se desea generar el reporte
     * @param artistas Un mapa de artistas
     * @return String con el reporte de las unidades vendidas para el artista indicado
     * @throws IllegalArgumentException Si el artista no existe en el mapa.
     */
    public static String unidadesVendidas(String identificador, TreeMap<String, Artista> artistas) {
        if (artistas.containsKey(identificador)) {
            Artista artista = artistas.get(identificador);

            StringBuilder contenido = new StringBuilder();
            contenido.append("Reporte de Unidades Vendidas de ").append(artista.getNombre()).append(":\n\n");

            long totalUnidadesVendidas = artista.unidadesDiscosVendidas();
            int cantidadDiscos = artista.cantidadDiscos();

            for (Disco disco : artista.getDiscos()) {
                String discoInfo = "Disco: " + disco.getNombre() + "\n\t- Unidades vendidas: " + disco.getUnidadesVendidasUltMes() + "\n";
                contenido.append(discoInfo);
            }

            String totalDiscos = "\nTotal de discos: " + cantidadDiscos;
            String totalDiscosVendidos = "\nTotal de discos vendidos: " + totalUnidadesVendidas + "\n";
            contenido.append(totalDiscos).append(totalDiscosVendidos);

            if (cantidadDiscos > 0) {
                double promedioUnidades = (double) totalUnidadesVendidas / cantidadDiscos;
                String promedio = "Promedio de unidades vendidas por disco: " + String.format("%.2f",promedioUnidades) + "\n";
                contenido.append(promedio);
            } else {
                String noDiscos = "No hay discos para calcular el promedio.\n";
                contenido.append(noDiscos);
            }

            GeneraArchivos.generaArchivoUnidadesVendidas(artista.getNombre(), contenido.toString());
            return contenido.toString();
        } else {
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }

    /**
     * Genera un listado de todos los artistas, mostrando su ID y nombre
     *
     * @param artistas Un mapa de artistas con su identificador como clave
     * @return Un String con el listado de artistas y sus ID
     */
    public static String muestraListadoArtistasId(TreeMap<String, Artista> artistas) {
        StringBuilder listado = new StringBuilder();
        for(Artista artista:artistas.values()) {
            listado.append(artista.getIdentificador()).append("\t\t").append(artista.getNombre()).append("\n").append("-----------------------------------------------------------------").append("\n");

        }
        return listado.toString();
    }
}
