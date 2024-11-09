package Archivos;
import Discográfica.Artista;

import javax.swing.*;
import java.io.*;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Clase abstracta que maneja la carga y almacenamiento de datos de artistas en un archivo binario utilizando serialización
 *
 * Esta clase proporciona métodos estáticos para cargar y guardar Artistas en un archivo serializado, permitiendo persistencia de los datos
 *
 * @version 1.0
 */

public abstract class ArchivosSerializados {

    /**
     * Carga artistas desde el archivo "artistasSerializado.dat" y los inserta en el mapa de artistas.
     *
     * Contiene un ciclo infinito que es interrumpido cuando se llega al fin del archivo,
     * en cada iteracion lee un objeto del archivo y lo transforma en un objeto de clase Artista, agregandolo al TreeMap
     *
     * @param artistas El TreeMap donde se cargarán los artistas con sus identificadores como claves
     */
    public static void cargarArtistas(TreeMap<String, Artista> artistas) {
        try (ObjectInputStream cargaSerializados=new ObjectInputStream(new FileInputStream("src/Archivos/Archivos/artistasSerializado.dat"))) {
            Artista artista;
            while (true) {
                artista=(Artista) cargaSerializados.readObject();
                artistas.put(artista.getIdentificador(), artista);
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
        }
    }

    /**
     * Crea un archivo "artistasSerializado.dat" y almacena en este los artistas presentes en el TreeMap.
     *
     * @param artistas El TreeMap de artistas que será almacenado en el archivo.
     */
    public static void guardarArtistas(TreeMap<String, Artista> artistas) {
        try (ObjectOutputStream serializa=new ObjectOutputStream(new FileOutputStream("src/Archivos/Archivos/artistasSerializado.dat"))) {
            Iterator<Artista> iterator=artistas.values().iterator();
            Artista artista;
            while(iterator.hasNext()){
                artista = iterator.next();
                serializa.writeObject(artista);
            }
        } catch (IOException e) {
        }
    }

    /**
     * Verifica si el archivo serializado de artistas existe en el sistema de archivos
     *
     * @return true si el archivo no existe, false si el archivo existe.
     */
    public static boolean serializadoNoExiste(){
        File f = new File("src/Archivos/Archivos/artistasSerializado.dat");
        return !(f.exists());
    }
}
