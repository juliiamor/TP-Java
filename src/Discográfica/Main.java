package Discográfica;
import Archivos.ArchivosSerializados;
import Archivos.LecturaDeArchivosTXT;
import IGU.Pantalla;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Gestion gestion = new Gestion();
        LecturaDeArchivosTXT arch = new LecturaDeArchivosTXT();
        if(ArchivosSerializados.serializadoNoExiste()){
            arch.leeArtistas(gestion);  // carga de artistas desde txt
            arch.leeDiscos(gestion);    // carga de discos desde txt
            arch.leeCancion(gestion);   // carga de caciones desde txt
            arch.leeRecital(gestion);   // carga de recitales desde txt
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