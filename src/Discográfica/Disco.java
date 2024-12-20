package Discográfica;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Clase Disco representa un álbum musical o un sencillo con una coleccion de {@link Cancion} y la cantidad de unidades vendidas en el último mes.
 * Implementa la interface {@link Serializable} para la persistencia de datos
 *
 * @version 1.0
 * @see Serializable
 * @see Cancion
 */

public class Disco implements Serializable{
    /**
     * Cantidad de unidades vendidas del disco en el ultimo mes
     */
    private long unidadesVendidasUltMes;

    /**
     * Nombre del disco
     */
    private String nombre;

    /**
     * Canciones del disco
     */
    private Set<Cancion> canciones;

    /**
     * ID de version de serialización
     */
    private static final long serialVersionUID=12345678910111213L;

    /**
     * Constructor de la clase Disco.
     * Inicializa el disco con los datos proporcionados
     * Inicializa la coleccion de canciones vacia
     *
     * @param unidadesVendidasUltMes Unidades vendidas del disco en el último mes.
     * @param nombre Nombre del disco.
     */
    public Disco(long unidadesVendidasUltMes, String nombre) {
        this.unidadesVendidasUltMes = unidadesVendidasUltMes;
        this.nombre = nombre;
        this.canciones = new HashSet<>();
    }

    /**
     * Obtiene el nombre del disco
     *
     * @return Nombre del disco
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el número de unidades vendidas del último mes
     *
     * @return Unidades vendidas del último mes
     */
    public long getUnidadesVendidasUltMes(){return unidadesVendidasUltMes;}

    /**
     * Obtiene la coleccion de canciones del disco
     *
     * @return Un conjunto de canciones
     */
    public Set<Cancion> getCanciones() {
        return canciones;
    }

    /**
     * Agrega una canción al disco.
     * Si la canción ya existe en el conjunto, no la agrega de nuevo.
     *
     * @param cancion La canción a agregar.
     * @throws IllegalArgumentException ya existe la cancion
     */
    public void addCancion(Cancion cancion){
        if(!canciones.add(cancion)){
            throw new IllegalArgumentException("Cancion ya existe");
        }
    }

    /**
     * Calcula el total de reproducciones del disco sumando las reproducciones de cada canción en el ultimo mes
     *
     * @return El total de reproducciones en el ultimo mes del disco
     */
    public long getReproduccionesDisco(){
        long total=0;
        Iterator<Cancion> iterator = canciones.iterator();
        Cancion cancion;
        while(iterator.hasNext()){
            cancion = iterator.next();
            total+=cancion.getCantReprodUltMes();
        }
        return total;
    }

    /**
     * Devuelve una representación del disco como una cadena de texto
     * Esta cadena incluye los datos completos del disco
     *
     * @return Cadena con la información del disco
     */
    public String toString(){
        StringBuilder listado = new StringBuilder();
        listado.append("\n  Unidades Vendidas en el Mes: ").append(unidadesVendidasUltMes).append("\n\n  Nombre: ").append(nombre).append("\n\n");
        Iterator<Cancion> iterator = canciones.iterator();
        Cancion cancion;
        byte i=0;
        while(iterator.hasNext()){
            i++;
            cancion=iterator.next();
            listado.append("\t").append(i).append(".   ").append(cancion).append("\n\n");
        }
        return listado.toString();
    }

    /**
     * Verifica si el disco es un sencillo (si contiene solo una canción)
     *
     * @return true si el disco tiene una sola canción, false si tiene mas de una cancion
     */
    public boolean EsSencillo(){
        return canciones.size()==1;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales
     * Dos objetos de tipo Disco son considerados iguales si tienen el mismo valor para los atributos:
     * <p>
     * Utiliza Objects para comparar, teniendo en cuenta que alguno puede ser nulo
     *
     * @param obj El objeto con el que se desea comparar este Disco
     * @return true si el objeto es igual a este Disco, false de lo contrario
     */
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Disco disco = (Disco) obj;
        return (unidadesVendidasUltMes==disco.unidadesVendidasUltMes && Objects.equals(nombre, disco.nombre) && canciones.equals(disco.canciones));
    }

    /**
     * Calcula el valor hash del disco, basado en los atributos del mismo.
     * <p>
     * Este valor es utilizado por estructuras como {@link java.util.HashSet} o {@link java.util.HashMap} para realizar comparaciones rápidas
     * y evitar duplicados.
     *
     * @return El valor hash calculado para este disco.
     */
    public int hashCode(){
        return Objects.hash(nombre, unidadesVendidasUltMes, canciones);
    }
}
