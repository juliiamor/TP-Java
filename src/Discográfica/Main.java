package Discográfica;
import Archivos.ArchivosSerializados;
import Archivos.LecturaDeArchivosTXT;
import IGU.Pantalla;

import javax.swing.*;

/**
 * Clase principal de la aplicación que ejecuta el programa
 * @version 1.0
 */

public class Main {
    /**
     * Metodo principal del programa.
     *
     * Verifica si hay que cargar datos desde txt o del serializado, y ejecuta la ventana principal
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        Gestion gestion = new Gestion();
        LecturaDeArchivosTXT arch = new LecturaDeArchivosTXT();
        if(ArchivosSerializados.serializadoNoExiste()){
            arch.leeArtistas(gestion);
            arch.leeDiscos(gestion);
            arch.leeCancion(gestion);
            arch.leeRecital(gestion);
            arch.generaInforme();
            ArchivosSerializados.guardarArtistas(gestion.getArtistas());
        } else
            ArchivosSerializados.cargarArtistas(gestion.getArtistas());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Pantalla panta = new Pantalla(gestion);
                panta.setVisible(true);
                panta.setLocationRelativeTo(null);
            }
        });
    }
}