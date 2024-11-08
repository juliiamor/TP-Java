package Discográfica;

import java.text.DecimalFormat;
import java.io.Serializable;

/**
 * Clase que representa un recital de un artista o grupo musical.
 * Esta clase almacena información sobre la fecha del recital, la recaudación generada y los costos de producción.
 *
 * Implementa la interface {@link Serializable} para la persistencia de datos.
 *
 * @version 1.0
 * @see Serializable
 */

public class Recital implements Serializable {
    /**
     * Almacena la fecha del recital
     */
    private String fecha;

    /**
     * Recaudacion total del recital
     */
    private float recaudacion;

    /**
     * Costos de Produccion del recital
     */
    private float costosProduccion;

    /**
     * ID de version de serialización
     */
    private static final long serialVersionUID=12345L;

    /**
     * Constructor de la clase Recital.
     *
     * @param fecha La fecha en la que se realizo el recital
     * @param recaudacion La cantidad de dinero recaudado durante el recital
     * @param costosProduccion Los costos asociados a la producción del recital.
     */
    public Recital(String fecha, float recaudacion,float costosProduccion){
        this.costosProduccion=costosProduccion;
        this.fecha=fecha;
        this.recaudacion=recaudacion;
    }

    /**
     * Calcula el monto neto generado por el recital
     * El neto es la diferencia entre la recaudacion y los costos de produccion
     *
     * @return El monto neto generado (recaudación - costos de producción)
     */
    public float getNeto(){return recaudacion-costosProduccion;}

    /**
     * Obtiene la fecha del recital
     *
     * @return La fecha en formato de cadena
     */
    public String getFecha(){
        return fecha;
    }

    /**
     * Devuelve una representación del recital como una cadena de texto
     * Esta cadena incluye los datos completos de la recital
     *
     * @return Cadena con la información de la canción (nombre, duración, reproducciones)
     */
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Fecha - " + fecha + " - Recaudacion - " + df.format(recaudacion) + " - Costos de Produccion - " + df.format(costosProduccion);
    }
}
