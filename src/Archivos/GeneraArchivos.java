package Archivos;

import Discográfica.Cancion;
import Discográfica.GeneroMusical;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase abstracta que maneja la generación de archivos de texto con reportes específicos para almacenar
 * informacion sobre el Top 10 de canciones y unidades vendidas de un artista.
 *
 * Proporciona métodos estáticos para crear archivos y guardar datos en formato de texto,
 * permitiendo almacenar los reportes en archivos de forma clase y legible.
 *
 * @version 1.0
 */

public abstract class GeneraArchivos {
    /**
     * Genera un archivo de texto con el Top 10 de canciones de un género musical específico
     *
     * @param genero El género musical del cual se generará el reporte.
     * @param listado El listado generado con el top 10 canciones del genero indicado
     */
    public static void generaArchivoTop10Canciones(GeneroMusical genero, String listado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Archivos/Archivos/Top10Canciones_" + genero + ".txt"))) {
            writer.write(listado);
        } catch (IOException e) {
        }
    }

    /**
     * Genera un archivo de texto con un reporte de unidades vendidas de discos de un artista específico.
     *
     * @param artistaNombre Nombre del artista
     * @param contenido Listado con los datos de los discos del Artista indicado
     */
    public static void generaArchivoUnidadesVendidas(String artistaNombre, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Archivos/Archivos/UnidadesVendidas_" + artistaNombre + ".txt"))) {
            writer.write(contenido);
        } catch (IOException e) {
        }
    }
}
