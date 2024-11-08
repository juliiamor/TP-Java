package Discográfica;
import java.io.Serializable;

/**
 * Clase que representa una canción de un artista o grupo musical
 * Esta clase almacena información sobre el nombre de la canción, su duración y las reproducciones en el último mes
 *
 * Implementa la interface {@link Serializable} para la persistencia de datos
 *
 * @version 1.0
 * @see Artista
 * @see Serializable
 */

public class Cancion implements Serializable{
    /**
     * Nombre de la cancion
     */
    private String nombre;

    /**
     * Duracion de la cancion en formato "minutos:segundos"
     */
    private String duracion;

    /**
     * Cantidad de reproducciones de la cancion en el ultimo mes
     */
    private long cantReprodUltMes;

    /**
     * ID de version de serialización
     */
    private static final long serialVersionUID=123456789101112L;

    /**
     * Constructor de la clase Cancion.
     *
     * @param nombre Nombre de la canción
     * @param duracion Duracion de la cancion en formato "minutos:segundos"
     * @param cantReprodUltMes Cantidad de reproducciones de la cancion en el último mes
     */
    public Cancion(String nombre, String duracion, long cantReprodUltMes){
        this.cantReprodUltMes=cantReprodUltMes;
        this.nombre=nombre;
        this.duracion=duracion;
    }

    /**
     * Obtiene la duracion de la canción
     *
     * @return La duración de la canción en formato "minutos:segundos"
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Obtiene el nombre de la cancion
     *
     * @return El nombre de la cancion
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cantidad de reproducciones de la canción en el último mes
     *
     * @return La cantidad de reproducciones en el último mes
     */
    public long getCantReprodUltMes(){return cantReprodUltMes;}

    /**
     * Devuelve una representación de la canción como una cadena de texto
     * Esta cadena incluye los datos completos de la cancion
     *
     * @return Cadena con la información de la canción (nombre, duración, reproducciones)
     */
    public String toString(){
        return nombre+" - Duracion: "+duracion+" - Reproducciones en el Mes: "+cantReprodUltMes;
    }
}
