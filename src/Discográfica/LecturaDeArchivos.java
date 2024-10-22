package Discográfica;
import java.io.*;
import java.util.Iterator;
import java.util.TreeMap;

public abstract class LecturaDeArchivos {

    public static void cargarArtistas(TreeMap<String, Artista> artistas) {
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/Discográfica/Archivos/artistasSerializado.dat"))) {
            while (true) {
                Artista artista=(Artista) ois.readObject();
                artistas.put(artista.getIdentificador(), artista);
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar artistas: "+e.getMessage());
        }
    }

    public static void guardarArtistas(TreeMap<String, Artista> artistas) {
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/Discográfica/Archivos/artistasSerializado.dat"))) {
            Iterator<Artista> iterator=artistas.values().iterator();
            Artista artista;
            while(iterator.hasNext()){
                artista = iterator.next();
                oos.writeObject(artista);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar artistas: "+e.getMessage());
        }
    }
}
