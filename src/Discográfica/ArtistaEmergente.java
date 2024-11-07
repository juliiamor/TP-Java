package Discográfica;
public class ArtistaEmergente extends Artista{
    private static final long serialVersionUID=123456891011L;
    public ArtistaEmergente(String identificador,String nombre,byte cantIntegrantes,GeneroMusical generoMusical){
        super(identificador, nombre, cantIntegrantes, generoMusical);
    }

    @Override
    public String toString() {
        return "ArtistaEmergente - "+super.toString();
    }

    @Override
    public float porcentajeRegalias() {
        return 0.35f;
    }
}
