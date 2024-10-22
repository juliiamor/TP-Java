package Discográfica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class GeneraArchivos {
    public static void generaArchivoTop10Canciones(String genero, List<Cancion> top10Canciones) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Discográfica/Archivos/Top10Canciones_" + genero + ".txt"))) {
            writer.write("Top 10 canciones del género " + genero + ":\n");
            for (int i = 0; i < top10Canciones.size(); i++) {
                Cancion cancion = top10Canciones.get(i);
                writer.write((i + 1) + ". " + cancion.getNombre() + " - " + cancion.getCantReprodUltMes() + " reproducciones\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo Top 10: " + e.getMessage());
        }
    }

    //Metodo para generar el archivo de unidades vendidas
    public static void generaArchivoUnidadesVendidas(String artistaNombre, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Discográfica/Archivos/UnidadesVendidas_" + artistaNombre + ".txt"))) {
            writer.write(contenido);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de Unidades Vendidas: " + e.getMessage());
        }
    }
}
