package Discográfica;
public class ArtistaEmergente extends Artista{
    public ArtistaEmergente(String identificador,String nombre,byte cantIntegrantes,String generoMusical){
        super(identificador, nombre, cantIntegrantes, generoMusical);
    }

    @Override
    public String toString() {
        return "ArtistaEmergente - "+super.toString();
    }
}
