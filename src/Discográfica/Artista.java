package Discográfica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta que contiene los datos de un artista y gestiona su informacion
 *
 * Contiene metodos para gestionar los datos, obtener lo recaudado y listar los datos
 *
 * Esta clase es abstracta y debe ser extendida por ArtistaConsagrado y ArtistaEmergente
 *
 * Implementa la interface {@link Serializable} para la persistencia de datos
 *
 * @version 1.0
 * @see Serializable
 */

public abstract class Artista implements Serializable {
    /**
     * Identificador único del artista o grupo musical
     */
    private String identificador;

    /**
     * Nombre del artista o grupo musical
     */
    private String nombre;

    /**
     * Género musical asociado al artista o grupo musical
     */
    private GeneroMusical generoMusical;

    /**
     * Cantidad de integrantes del grupo o grupo musical
     */
    private byte cantIntegrantes;

    /**
     * Container de discos publicados por el artista o grupo musical
     */
    private Set<Disco> discos;

    /**
     * Container de recitales en los que ha participado el artista o grupo musical
     */
    private Set<Recital> recitales;

    /**
     * ID de version de serialización
     */
    private static final long serialVersionUID=12345689L;

    /**
     * Constructor de la clase
     * Inicializa un objeto tipo Artista con los valores proporcionados, e inicializa vacios los {@link HashSet} de discos y recitales
     *
     * @param identificador ID del artista o grupo musical
     * @param nombre nombre del artista o grupo musical
     * @param cantIntegrantes Cantidad de integrantes del grupo
     * @param generoMusical Genero musical del artista o grupo musical
     */
    public Artista(String identificador,String nombre,byte cantIntegrantes,GeneroMusical generoMusical){
        this.identificador=identificador;
        this.nombre=nombre;
        this.cantIntegrantes=cantIntegrantes;
        this.generoMusical=generoMusical;
        discos = new HashSet<>();
        recitales = new HashSet<>();
    }

    /**
     * Obtiene Identificador
     *
     * @return atributo identificador
     */
    public String getIdentificador(){return identificador;}

    /**
     * Obtiene Nombre
     *
     * @return atributo nombre
     */
    public String getNombre(){return nombre;}

    /**
     * Obtiene Cantidad de Integrantes
     *
     * @return atributo cantIntegrantes
     */
    public byte getCantIntegrantes(){return cantIntegrantes;}

    /**
     * Obtiene Genero Musical
     *
     * @return atributo generoMusical
     */
    public GeneroMusical getGeneroMusical(){return generoMusical;}

    /**
     * Obtiene Coleccion de Discos
     *
     * @return atributo discos
     */
    public Set<Disco> getDiscos() {
        return discos;
    }

    /**
     * Obtiene Coleccion de Recitales
     *
     * @return atributo recitales
     */
    public Set<Recital> getRecitales() {
        return recitales;
    }

    /**
     * Agrega un Disco a la colección de discos del artista
     *
     * @throws IllegalArgumentException si el disco ya existe
     * @param disco es el disco a agregar a la colección
     */
    public void addDisco(Disco disco){
        if (!discos.add(disco)) {
            throw new IllegalArgumentException("El disco ya existe en la colección.");
        }
    }

    /**
     * Agrega un Recital a la colección de recitales del artista
     *
     * @throws IllegalArgumentException si el recital ya existe
     * @param recital es el recital a agregar a la colección
     */
    public void addRecital(Recital recital){
        if(!recitales.add(recital)){
            throw new IllegalArgumentException("El recital ya existe en la colección.");
        }
    }

    /**
     * Obtiene una representación en formato de texto de la coleccion de discos completa
     *
     * @return un listado completo con los datos de los discos del artista
     */
    public String muestraDiscos(){
        StringBuilder listado=new StringBuilder();
        Iterator<Disco> iteratorDiscos = discos.iterator();
        Disco disco;
        while (iteratorDiscos.hasNext()) {
            disco = iteratorDiscos.next();
            listado.append(disco).append("\n");
        }
        return listado.toString();
    }

    /**
     * Obtiene una representación en formato de texto de la coleccion de recitales completa
     *
     * @return un listado completo con los datos de los recitales del artista
     */
    public String muestraRecitales(){
        StringBuilder listado = new StringBuilder();
        Iterator<Recital> iteratorRecitales = recitales.iterator();
        Recital recital;
        while (iteratorRecitales.hasNext()) {
            recital = iteratorRecitales.next();
            listado.append(recital).append("\n");
        }
        return listado.toString();
    }

    /**
     * Obtiene una representación en cadena de texto del Artista
     *
     * @return un listado completo con los datos del artista, incluyendo discos y recitales
     */
    public String toString(){
        StringBuilder listado = new StringBuilder();
        listado.append(identificador).append("- Nombre: ").append(nombre)
                .append(" - Integrantes: ").append(cantIntegrantes)
                .append(" - Género musical: ").append(generoMusical);

        listado.append("\n\tLista de Discos:\n");
        listado.append(muestraDiscos());
        listado.append("\n\tLista de Recitales:\n");
        listado.append(muestraRecitales());
        return listado.toString();
    }

    /**
     * Obtiene el total de la cantidad de Unidades de Discos Vendidas en el ultimo mes
     *
     * @return total de unidades de discos vendidas en el ultimo mes
     */
    public long unidadesDiscosVendidas(){
        long total=0;
        Iterator<Disco> iterator= discos.iterator();
        Disco disco;
        while(iterator.hasNext()){
            disco = iterator.next();
            total+=disco.getUnidadesVendidasUltMes();
        }
        return total;
    }

    /**
     * Obtiene el total de la cantidad de reproducciones en el ultimo mes
     *
     * @return total de reproducciones en el ultimo mes
     */
    public long reproduccionesUltMes(){
        long total=0;

        Iterator<Disco> iterator= discos.iterator();
        Disco disco;
        while(iterator.hasNext()){
            disco = iterator.next();
            total+=(disco.EsSencillo())?disco.getReproduccionesDisco()*1.5:disco.getReproduccionesDisco();
        }
        return total;
    }

    /**
     * Obtiene el total neto recaudado en recitales el ultimo mes
     *
     * A partir de la fecha actual, solamente tiene en cuenta para calcular el total los recitales de un mes atras
     *
     * @return total recaudado en recitales en el ultimo mes
     */
    public float netoRecitalUltMes() {
        float total=0;
        LocalDate ultMes = LocalDate.now().minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Iterator<Recital>iterator=recitales.iterator();
        while (iterator.hasNext()){
            Recital recital=iterator.next();
            LocalDate fechaRecital = LocalDate.parse(recital.getFecha(), formatter);
            if (!fechaRecital.isBefore(ultMes) && !fechaRecital.isAfter(LocalDate.now())){
                total+=recital.getNeto();
            }
        }
        return total;
    }

    /**
     * Obtiene un disco del artista o grupo musical
     *
     * @param nombre nombre del disco
     * @return disco seleccionado, en caso de que no exista null
     */
    public Disco getDisco(String nombre){
        for(Disco disco : discos){
            if(disco.getNombre().equals(nombre))
                return disco;
        }
        return null;
    }

    /**
     * Comprueba que el disco existe en la coleccion de discos del Artista o Grupo Musical
     *
     * @param nombre nombre del disco
     * @return true si existe, false si no existe
     */
    public boolean estaDisco(String nombre){
        for(Disco disco : discos){
            if(disco.getNombre().equals(nombre))
                return true;
        }
        return false;
    }

    /**
     * Cantidad de discos del artista o grupo musical
     *
     * @return el tamaño de la coleccion de discos del artista o grupo musical
     */
    public int cantidadDiscos(){
        return discos.size();
    }

    /**
     * Contiene el porcentaje que esta destinado de la Discográfica, del total recaudado por el artista o grupo musical en el ultimo mes
     *
     * Es un metodo abstracto ya que las clases que hereden de Artista deben implementar este metodo
     */
    public abstract float porcentajeRegalias();
}
