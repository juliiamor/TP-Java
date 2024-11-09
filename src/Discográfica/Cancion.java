package Discográfica;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa una canción de un artista o grupo musical
 * Esta clase almacena información sobre el nombre de la canción, su duración y las reproducciones en el último mes
 *
 * Implementa la interface {@link Serializable} para la persistencia de datos
 *
 * @version 1.0
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

    /**
     * Metodo equals para verificar si una cancion es igual a otra
     *
     * En caso de que el objeto no sea una instancia de Cancion devuelve falso, tambien verifica gracias a Objects, que no sea nulo alguno
     * de los dos campos a comparar
     *
     * @param obj objeto a verificar
     * @return false si no es igual, true si los objetos son iguales
     */
    public boolean equals(Object obj){
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Cancion cancion = (Cancion) obj;

        if (!Objects.equals(nombre, cancion.nombre)) {
            return false;
        }
        if (!Objects.equals(duracion, cancion.duracion)) {
            return false;
        }
        if (cantReprodUltMes != cancion.cantReprodUltMes) {
            return false;
        }

        return true;
    }

    /**
     *
     * Calcula el valor hash de la canción, basado en los atributos: nombre, duración y cantidad de reproducciones en el último mes.
     * <p>
     * Este valor es utilizado por estructuras como {@link java.util.HashSet} o {@link java.util.HashMap} para realizar comparaciones rápidas
     * y evitar duplicados.
     * 
     * @return El valor hash calculado para esta canción.
     */
    public int hashCode() {
        return Objects.hash(nombre, duracion, cantReprodUltMes);
    }
}
