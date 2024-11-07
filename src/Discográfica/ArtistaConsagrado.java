package Discográfica;

import java.io.Serializable;

public class ArtistaConsagrado extends Artista implements Serializable {
    private static final long serialVersionUID=1234568910L;
    public ArtistaConsagrado(String identificador,String nombre,byte cantIntegrantes,GeneroMusical generoMusical){
        super(identificador, nombre, cantIntegrantes, generoMusical);
    }

    public String toString() {
        return "ArtistaConsagrado - "+super.toString();
    }

    @Override
    public float porcentajeRegalias() {
        return 0.2f;
    }
}

