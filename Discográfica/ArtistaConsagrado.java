package Discográfica;
public class ArtistaConsagrado extends Artista{
    public ArtistaConsagrado(String identificador,String nombre,byte cantIntegrantes,GeneroMusical generoMusical){
        super(identificador, nombre, cantIntegrantes, generoMusical);
    }

    public String toString() {
        return "ArtistaConsagrado - "+super.toString();
    }
}

