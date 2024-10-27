package Discográfica;
import Archivos.LecturaDeArchivosTXT;
import IGU.Pantalla;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Gestion gestion = new Gestion();            // creacion de collecion de artistas
        LecturaDeArchivosTXT.leeArtistas(gestion);  // carga de artistas desde txt
        LecturaDeArchivosTXT.leeDiscos(gestion);    // carga de discos desde txt
        LecturaDeArchivosTXT.leeCancion(gestion);   // carga de caciones desde txt
        LecturaDeArchivosTXT.leeRecital(gestion);   // carga de recitales desde txt

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Pantalla panta = new Pantalla(gestion);
                panta.setVisible(true);
                panta.setLocationRelativeTo(null);
            }
        });
    }

    /*puse datos para ir probando mientras no tenemos los archivos
        cargarDatosPrueba(gestion);
        gestion.facturacionUltMes("001");

    private static void cargarDatosPrueba(Gestion gestion) {
        ArtistaConsagrado artista1 = new ArtistaConsagrado("001", "Artista 1", (byte) 4, GeneroMusical.ROCK);
        ArtistaEmergente artista2 = new ArtistaEmergente("002", "Artista 2",(byte)3, GeneroMusical.POP);

        Cancion cancion1=new Cancion("Fearless","3:20",5000);
        Cancion cancion2=new Cancion("I almost do","4:20",6000);
        Cancion cancion3=new Cancion("starlight","2:50",9000);
        Cancion cancion4=new Cancion("begin again","5:00",5000);
        Cancion cancion5=new Cancion("tolerate it","3:54",5000);
        Cancion cancion6=new Cancion("marjorie","3:24",7000);
        Cancion cancion7=new Cancion("willow","2:20",6000);

        Disco disco1 = new Disco(5000,"Fearless");
        Disco disco2 = new Disco(2877,"red");

        Disco disco3 = new Disco(9833,"evermore");

        disco1.addCancion(cancion1);
        disco2.addCancion(cancion2);
        disco2.addCancion(cancion3);
        disco2.addCancion(cancion4);
        disco3.addCancion(cancion5);
        disco3.addCancion(cancion6);
        disco3.addCancion(cancion7);

        artista1.addDisco(disco1);
        artista2.addDisco(disco2);
        artista2.addDisco(disco3);

        Recital recital1 = new Recital("23-10-2024", 3647, 2000);
        Recital recital2 = new Recital("20-10-2022", 2000, 1500);
        Recital recital3 = new Recital("12-02-2024", 5083, 3000);
        Recital recital4 = new Recital("02-11-2024", 6000, 3000);
        artista1.addRecital(recital1);
        artista1.addRecital(recital2);
        artista2.addRecital(recital3);
        artista2.addRecital(recital4);


        // Añadir artistas a la colección
        gestion.addArtista(artista1);
        gestion.addArtista(artista2);

    }*/
}