package Discográfica;
import java.util.*;
import java.io.Serializable;

/**
 * Clase que gestiona la informacion de los Artistas y las regalías dependiendo de sus actividades
 *
 * Contiene un Mapa de artistas y metodos para el manejo de sus datos, métodos para gestionar porcentajes de regalías y filtrados
 * Implementa la interface {@link Serializable} para la persistencia de datos
 *
 * @version 1.0
 * @see Serializable
 */

public class Gestion implements Serializable {
    /**
     * Container de Artistas, ordenado por ID, utiliza el ID como clave
     */
    private TreeMap<String, Artista> artistas;

    /**
     * ID de version de serialización
     */
    private static final long serialVersionUID=123456L;

    /**
     * Constructor de la Clase
     * Inicializa el contenedor de artistas {@link TreeMap} vacío
     */
    public Gestion(){
        artistas = new TreeMap<>();
    }

    /**
     * Agrega artista al TreeMap artistas
     *
     * @param artista Artista a agregar
     * @throws IllegalArgumentException si el artista ya existe en el TreeMap artistas
     */
    public void addArtista(Artista artista){
        if(artistas.containsKey(artista.getIdentificador())){
            throw new IllegalArgumentException("El artista ya esta Registrado.");
        }else{
            artistas.put(artista.getIdentificador(),artista);
        }
    }

    /**
     *  Baja de artista del TreeMap artistas
     *
     * @param identificador ID del artista a dar de baja
     * @throws IllegalArgumentException si el artista no existe en el TreeMap artistas
     */
    public void removeArtista(String identificador){
        if(artistas.containsKey(identificador)){
            artistas.remove(identificador);
        }else{
            throw new IllegalArgumentException("El artista no existe");
        }
    }

    /**
     * Filtra artistas por Cantidad de Integrantes y el Género Musical
     *
     * Selecciona aquellos artistas que cumplen con lo especificado, y los almacena en un nuevo {@link TreeMap}
     *
     * @param cantidadIntegrantes número de integrantes
     * @param genero genero musical seleccionado
     * @return {@link TreeMap} con los artistas que cumplen en cantidadIntegrantes y genero
     */
    public TreeMap<String,Artista> filtrarArtistas(byte cantidadIntegrantes, GeneroMusical genero) {
        TreeMap<String,Artista> artistasFiltrados = new TreeMap<>();
        Artista artista;
        for(String id: artistas.keySet()){
            artista=artistas.get(id);
            if(artista.getCantIntegrantes() == cantidadIntegrantes && artista.getGeneroMusical()==genero){
                artistasFiltrados.put(id,artista);
            }
        }
        return artistasFiltrados;
    }

    /**
     * Filtra artistas por Cantidad de Integrantes
     *
     * Selecciona aquellos artistas que cumplen con lo especificado, y los almacena en un nuevo {@link TreeMap}
     *
     * @param cantidadIntegrantes número de integrantes
     * @return {@link TreeMap} con los artistas que cumplen en cantidadIntegrantes
     */
    public TreeMap<String,Artista> filtrarArtistas(byte cantidadIntegrantes){
        TreeMap<String,Artista> artistasFiltrados = new TreeMap<>();
        Artista artista;
        for(String id: artistas.keySet()){
            artista=artistas.get(id);
            if(artista.getCantIntegrantes() == cantidadIntegrantes){
                artistasFiltrados.put(id,artista);
            }
        }
        return artistasFiltrados;
    }

    /**
     * Filtra artistas por Género Musical
     *
     * Selecciona aquellos artistas que cumplen con lo especificado, y los almacena en un nuevo {@link TreeMap}
     *
     * @param genero genero musical seleccionado
     * @return {@link TreeMap} con los artistas que cumplen en genero
     */
    public TreeMap<String,Artista> filtrarArtistas(GeneroMusical genero){
        TreeMap<String,Artista> artistasFiltrados = new TreeMap<>();
        Artista artista;
        for(String id: artistas.keySet()){
            artista=artistas.get(id);
            if(artista.getGeneroMusical()==genero){
                artistasFiltrados.put(id,artista);
            }
        }
        return artistasFiltrados;
    }

    /**
     * Obtiene mapa de Artistas
     *
     * Metodo que proporciona acceso al {@link TreeMap} que contiene a los artistas, manteniendo el encapsulamiento de la colección de artistas
     *
     * @return retorna el TreeMap artistas
     */
    public TreeMap<String, Artista> getArtistas() {
        return artistas;
    }

    /**
     * Obtiene un artista
     *
     * @param identificador ID del artista a obtener
     * @return devuelve el artista en caso de que exista, si no es asi devuelve null
     */
    public Artista filtraArtistaPorID(String identificador){
        return artistas.get(identificador);
    }

    /**
     * Genera representación en cadena de texto de todos los artistas
     *
     * Permite obtener un listado con los datos de cada artista
     *
     * @return un String con el listado completo de artistas
     */
    public String toString(){
        Iterator<Artista> iterator = artistas.values().iterator();
        Artista artista;
        StringBuilder listado = new StringBuilder();
        while(iterator.hasNext()){
            artista=iterator.next();
            listado.append(artista).append("\n\n");
        }
        return listado.toString();
    }

    /**
     * Total recaudado por artista en recitales el ultimo mes
     *
     * @param id ID del artista
     * @throws IllegalArgumentException no existe un artista con Identificador = ID
     * @return Total recaudado en recitales en el ultimo mes del artista con Identificador = ID
     */
    public double totalRecitalesMes(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException("Artista no encontrado");
        }else{
            Artista artista = artistas.get(id);
            return artista.netoRecitalUltMes();
        }
    }

    /**
     * Total recaudado en reproducciones de un artista en el ultimo mes
     * Cada reproduccion se paga $0.1, por lo que se multiplican las reproducciones por 0.1
     *
     * @param id ID del artista
     * @throws IllegalArgumentException no existe un artista con Identificador = ID
     * @return Total recaudado reproducciones en el ultimo mes del artista con Identificador = ID
     */
    public double totalReproduccionesMes(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException("Artista no encontrado");
        }else{
            Artista artista = artistas.get(id);
            return artista.reproduccionesUltMes() * 0.1f;
        }
    }

    /**
     * Total recaudado por artista en discos el ultimo mes
     * Cada disco vendido cuenta $100, por lo que se multiplica las unidades por 100
     *
     * @param id ID del artista
     * @throws IllegalArgumentException no existe un artista con Identificador = ID
     * @return Total recaudado en discos en el ultimo mes del artista con Identificador = ID
     */
    public double totalDiscosVendidosMes(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException("Artista no encontrado");
        } else {
            Artista artista = artistas.get(id);
            return artista.unidadesDiscosVendidas() * 100;
        }
    }

    /**
     * Total recaudado por artista en el ultimo mes
     * Suma totalDiscosVendidosMes + totalReproduccionesMes + totalRecitalesMes, y obtiene el total recaudado
     *
     * @param id ID del artista
     * @throws IllegalArgumentException no existe un artista con Identificador = ID
     * @return Total recaudado en el ultimo mes del artista con Identificador = ID
     */
    public double totalGenerado(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException("Artista no encontrado");
        } else {
            return totalDiscosVendidosMes(id) + totalReproduccionesMes(id) + totalRecitalesMes(id);
        }
    }

    /**
     * Total destinado al Artista
     * Total generado - Total destinado a la Discografica
     *
     * @param id ID del artista
     * @throws IllegalArgumentException no existe un artista con Identificador = ID
     * @return Total que obtiene el artista de lo recaudado
     */
    public double totalArtista(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException("Artista no encontrado");
        } else {
            return totalGenerado(id) - totalDiscografica(id);
        }
    }

    /**
     * Total destinado a la Discográfica
     * Dependiendo si es un artista consagrado o emergente, la discografica obtiene un porcentaje de lo recaudado por el artista
     *
     * @param id ID del artista
     * @throws IllegalArgumentException no existe un artista con Identificador = ID
     * @return Total que obtiene la discografica de lo recaudado por el artista
     */
    public double totalDiscografica(String id) {
        if (!artistas.containsKey(id)) {
            throw new IllegalArgumentException("Artista no encontrado");
        } else {
            Artista artista = artistas.get(id);
            return totalGenerado(id) * artista.porcentajeRegalias();
        }
    }
}
