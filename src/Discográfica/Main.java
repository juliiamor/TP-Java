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
        if(ArchivosSerializados.serializadoNoExiste()){
            LecturaDeArchivosTXT.leeArtistas(gestion);  // carga de artistas desde txt
            LecturaDeArchivosTXT.leeDiscos(gestion);    // carga de discos desde txt
            LecturaDeArchivosTXT.leeCancion(gestion);   // carga de caciones desde txt
            LecturaDeArchivosTXT.leeRecital(gestion);   // carga de recitales desde txt
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