package Archivos;
import Discográfica.Artista;

import java.io.*;
import java.util.Iterator;
import java.util.TreeMap;

public abstract class ArchivosSerializados {

    public static void cargarArtistas(TreeMap<String, Artista> artistas) {
        try (ObjectInputStream cargaSerializados=new ObjectInputStream(new FileInputStream("src/Archivos/Archivos/artistasSerializado.dat"))) {
            Artista artista;
            while (true) {
                artista=(Artista) cargaSerializados.readObject();
                artistas.put(artista.getIdentificador(), artista);
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar artistas: "+e.getMessage());
        }
    }

    public static void guardarArtistas(TreeMap<String, Artista> artistas) {
        try (ObjectOutputStream serializa=new ObjectOutputStream(new FileOutputStream("src/Archivos/Archivos/artistasSerializado.dat"))) {
            Iterator<Artista> iterator=artistas.values().iterator();
            Artista artista;
            while(iterator.hasNext()){
                artista = iterator.next();
                serializa.writeObject(artista);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar artistas: "+e.getMessage());
        }
    }

    public static boolean serializadoNoExiste(){
        File f = new File("src/Archivos/Archivos/artistasSerializado.dat");
        return !(f.exists());
    }
}
