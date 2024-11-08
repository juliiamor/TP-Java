package Discográfica;

import java.io.Serializable;

/**
 * Clase que contiene los datos de un artista consagrado
 *
 * Implementa metodo para obtener porcentajes
 *
 * Implementa la interface {@link Serializable} para la persistencia de datos y extiende de {@link Artista}
 *
 * @version 1.0
 * @see Artista
 * @see Serializable
 */

public class ArtistaConsagrado extends Artista implements Serializable {

    /**
     * ID de version de serialización
     */
    private static final long serialVersionUID=1234568910L;

    /**
     * Constructor de la clase
     * Inicializa un objeto tipo Artista con los valores proporcionados, e invoca el constructor de la clase padre {@link Artista}
     *
     * @param identificador
     * @param nombre
     * @param cantIntegrantes
     * @param generoMusical
     */
    public ArtistaConsagrado(String identificador,String nombre,byte cantIntegrantes,GeneroMusical generoMusical){
        super(identificador, nombre, cantIntegrantes, generoMusical);
    }

    /**
     * Obtiene una representación en cadena de texto del Artista Consagrado
     *
     * @return un listado completo con los datos del artista
     */
    public String toString() {
        return "ArtistaConsagrado - "+super.toString();
    }

    /**
     * Contiene el porcentaje que esta destinado de la Discográfica, del total recaudado por el artista o grupo musical en el ultimo mes
     *
     * @return porcentaje
     */
    public float porcentajeRegalias() {
        return 0.2f;
    }
}

