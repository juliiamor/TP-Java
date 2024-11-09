package Archivos;
import Discográfica.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.lang.String;

/**
 * Clase que se encarga de la carga de datos desde un archivo TXT
 * La clase maneja la inconsistencia en datos, y tiene un atributo de tipo InformeConsistenciaDatos, para generar un informe de los errores
 * que encuentra en los datos que se ingresan en el archivo TXT.
 */
public class LecturaDeArchivosTXT {
    /**
     * Objeto de clase InformeConsistenciaDatos, se encarga de escribir en un txt las lineas de datos en los que hay inconsistencias
     */
    InformeConsistenciaDatos informe;

    /**
     * Constructor de la clase
     * Inicializa el informe
     */
    public LecturaDeArchivosTXT() {
        informe = new InformeConsistenciaDatos();
    }

    /**
     * Escribe el informe con los errores en el archivo correspondiente
     */
    public void generaInforme() {
        informe.generaInforme();
    }

    /**
     * Lee los datos desde el archivo "ARTISTAS.txt", valida la consistencia de datos, generando un informe de cada linea que contiene un error,
     *<p>
     * Lee cada linea el archivo, estando separada por punto y coma (;), y conteniendo informacion sobre un artista
     * Se basa en el siguiente formato de datos:
     * <pre>
     *         categoria;id;nombre;genero;integrantes
     * <pre>
     * - categoria: Indica si el artista es consagrado ('c') o emergente ('e')
     * - id: Identificador del artista (debe tener 6 caracteres)
     * - nombre: Nombre del artista (máximo de 30 caracteres).
     * - genero: Genero musical del artista (debe pertenecer al ENUM GeneroMusical).
     * - integrantes: Numero de integrantes en la banda (valor positivo).
     *
     * @param mapaArtistas Objeto de tipo gestion, en el que se agregan las instancias de Artista
     * @throws IOException si ocurre algun error al leer el archivo
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 5 bloques separados por punto y coma (;)
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     */
    public void leeArtistas(Gestion mapaArtistas) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/Archivos/ARTISTAS.txt"));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] bloque = linea.split(";");
                try {
                    validaDatosArtista(bloque);
                    char cat = bloque[0].charAt(0);
                    String id = bloque[1];
                    String nombre = bloque[2];
                    String gen = bloque[3];
                    byte integrantes = Byte.parseByte(bloque[4]);
                    if (cat == 'c')
                        mapaArtistas.addArtista(new ArtistaConsagrado(id, nombre, integrantes, GeneroMusical.valueOf(gen)));
                    else
                        mapaArtistas.addArtista(new ArtistaEmergente(id, nombre, integrantes, GeneroMusical.valueOf(gen)));
                } catch (StringIndexOutOfBoundsException e) {
                    informe.agregaError("Error en linea " + linea + " - " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

    /**
     * Valida la consistencia de los datos del Artista
     *
     * @param bloque linea de datos separadas en bloques en cada punto y coma (;)
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 5 bloques separados por punto y coma (;)
     * @throws NumberFormatException si la cantidad de integrantes ingresada no es un numero
     */
    public void validaDatosArtista(String[] bloque) throws IllegalArgumentException, StringIndexOutOfBoundsException, NumberFormatException {
        if (bloque.length != 5) {
            throw new StringIndexOutOfBoundsException("Error: cantidad de datos errónea.");
        }
        char cat = bloque[0].charAt(0);
        if (cat != 'c' && cat != 'e') {
            throw new IllegalArgumentException("Error: la categoria debe ser 'c'(consagrado) o 'e'(emergente)");
        }
        String id = bloque[1];
        if (bloque[1].length() != 6) {
            throw new IllegalArgumentException("Error: ID debe tener 6 caracteres");
        }
        String nom = bloque[2];
        if (bloque[2].length() > 25) {
            throw new IllegalArgumentException("Error: NOMBRE excede 25 caracteres");
        }
        String gen = bloque[3];
        if (!GeneroMusical.pertenece(gen) || gen.equals("INGRESE_GENERO")) {
            throw new IllegalArgumentException("Error: GENERO no incluido o Invalido");
        }
        try {
            byte integrantes = Byte.parseByte(bloque[4]);
            if (integrantes < 0) {
                throw new IllegalArgumentException("Error: INTEGRANTES debe ser un entero positivo.");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: INTEGRANTES contiene caracteres alfanumericos");
        }
    }

    /**
     * Lee los datos de discos desde el archivo "DISCOS.txt", valida cada línea,
     * y agrega los discos al artista correspondiente en el mapa de artistas.
     * Valida la consistencia de los datos ingresados
     * Lee cada linea el archivo, estando separada por punto y coma (;), y conteniendo informacion sobre un artista
     * <p>
     * Se basa en el siguiente formato de datos:
     *  <pre>
     *      idArtista;unidadesVendidas;nombreDisco
     * <pre>
     * - idArtista: Identificación del artista (debe coincidir con un ID).
     * - unidadesVendidas: Número de unidades vendidas del disco (debe ser un valor numérico válido).
     * - nombreDisco: Nombre del disco.
     *
     * @param mapaArtistas Objeto de tipo gestion, en el que se agregan los discos al Artista correspondiente
     * @throws IOException Si ocurre un error al intentar leer el archivo.
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 3 bloques separados por punto y coma (;)
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     */
    public void leeDiscos(Gestion mapaArtistas) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/Archivos/DISCOS.txt"));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] bloque = linea.split(";");
                try {
                    validaDatosDisco(bloque, mapaArtistas);
                    String id = bloque[0];
                    long unidadesVendidas = Long.parseLong(bloque[1]);
                    String nombre = bloque[2];
                    mapaArtistas.filtraArtistaPorID(id).addDisco(new Disco(unidadesVendidas, nombre));
                } catch (StringIndexOutOfBoundsException e) {
                    informe.agregaError("Error en linea " + linea + "-" + e.getMessage());
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

    /**
     * Valida los datos del Disco
     *
     * @param bloque linea de datos separadas en bloques en cada punto y coma (;)
     * @param mapaArtistas Objeto de tipo gestion en el que se cargan los datos de disco
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 3 bloques separados por punto y coma (;)
     * @throws NumberFormatException si la cantidad de unidades vendidas no es un numero
     */
    public void validaDatosDisco(String[] bloque, Gestion mapaArtistas) throws IllegalArgumentException, StringIndexOutOfBoundsException {
        if (bloque.length != 3) {
            throw new StringIndexOutOfBoundsException("Error: cantidad de datos errónea.");
        }
        String id = bloque[0];
        if (id.length() != 6) {
            throw new IllegalArgumentException("Error: ID debe tener 6 caracteres");
        }
        if (mapaArtistas.filtraArtistaPorID(id) == null) {
            throw new IllegalArgumentException("Error: ID no se encuentra entre los artistas");
        }
        try {
            long cantVendidas = Long.parseLong(bloque[1]);
            if (cantVendidas < 0 || cantVendidas > 9223372036854775806L) {
                throw new IllegalArgumentException("Error: CANTIDADVENDIDA fuera de rango");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: CANTIDADVENDIDA contiene caracteres alfanumericos");
        }
        String nom = bloque[2];
        if (bloque[2].length() > 25) {
            throw new IllegalArgumentException("Error: NOMBRE excede 25 caracteres");
        }
    }

    /**
     * Lee los datos de canciones desde el archivo "CANCIONES.txt", valida cada línea,
     * y agrega lal canciones en los discos y artista correspondiente en el mapa de artistas.
     * Valida la consistencia de los datos ingresados
     * <p>
     * Se basa en el siguiente formato de datos:
     * <pre>
     *      idArtista;nombreDisco;nombreCancion;duracion;cantReproducciones
     * <pre>
     * @param mapaArtistas
     * @throws IOException Si ocurre un error al intentar leer el archivo.
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 5 bloques separados por punto y coma (;)
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     */
    public void leeCancion(Gestion mapaArtistas) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/Archivos/CANCIONES.txt"));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] bloque = linea.split(";");
                try {
                    validaDatosCancion(bloque, mapaArtistas);

                    String id = bloque[0];
                    String nombreDisco = bloque[1];
                    String nombre = bloque[2];
                    String duracion = bloque[3];
                    long cantReproducciones = Long.parseLong(bloque[4]);
                    mapaArtistas.filtraArtistaPorID(id).getDisco(nombreDisco).addCancion(new Cancion(nombre, duracion, cantReproducciones));
                } catch (StringIndexOutOfBoundsException e) {
                    informe.agregaError("Error en linea: " + linea + "-" + e.getMessage());
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

    /**
     * Valida los datos de la Cancion
     *
     * @param bloque linea de datos separadas en bloques en cada punto y coma (;)
     * @param mapaArtistas Objeto de tipo gestion en el que se cargan los datos de disco
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 5 bloques separados por punto y coma (;)
     * @throws NumberFormatException si la cantidad de reproducciones no es un numero
     */
    public void validaDatosCancion(String[] bloque, Gestion mapaArtistas) throws IllegalArgumentException, StringIndexOutOfBoundsException {

        if (bloque.length != 5) {
            throw new StringIndexOutOfBoundsException("Error: cantidad de datos errónea.");
        }
        String id = bloque[0];
        if (id.length() != 6) {
            throw new IllegalArgumentException("Error: ID debe tener 6 caracteres");
        }
        if (mapaArtistas.filtraArtistaPorID(id) == null) {
            throw new IllegalArgumentException("Error: ID no se encuentra entre los artistas");
        }
        String nombre = bloque[1];
        if (nombre.length() > 25) {
            throw new IllegalArgumentException("Error: NOMBREDISCO excede 25 caracteres");
        }
        if (!(mapaArtistas.filtraArtistaPorID(id).estaDisco(nombre))) {
            throw new IllegalArgumentException("Error: NOMBREDISCO no se encuentra entre los discos del Artistas");
        }
        String nomCancion = bloque[2];
        if (nombre.length() > 25) {
            throw new IllegalArgumentException("Error: NOMBREDISCO excede 25 caracteres");
        }

        String duracion = bloque[3];
        try {
            validaDuracion(duracion);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: DURACION no es valido "+e.getMessage());
        }

        try {
            long cantVendidas = Long.parseLong(bloque[4]);
            if (cantVendidas < 0 || cantVendidas > 9223372036854775806L) {
                throw new IllegalArgumentException("Error: CANTIDADVENDIDA fuera de rango");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: CANTIDADVENDIDA contiene caracteres alfanumericos");
        }
    }

    /**
     * Valida la duracion de la cancion
     *
     * @param duracion Duracion de la cancion
     * @throws IllegalArgumentException si la duracion no es una duracion valida
     * @throws StringIndexOutOfBoundsException si el formato de duracion no es el esperado (mm:ss)
     */
    public void validaDuracion(String duracion) throws IllegalArgumentException, StringIndexOutOfBoundsException {
        String[] bloque = duracion.split(":");
        if (bloque.length != 2) {
            throw new StringIndexOutOfBoundsException("Error: Formato de duracion debe ser minutos:segundos");
        }
        try{
            short minutos = Byte.parseByte(bloque[0]);
            if (minutos < 0 ) {
                throw new IllegalArgumentException("Error: MINUTOS negativo");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Error: MINUTOS contiene caracteres alfanumericos");
        }
        try {
            short segundos = Short.parseShort(bloque[1]);
            if (segundos < 0 ) {
                throw new IllegalArgumentException("Error: SEGUNDOS negativo");
            }
            if(segundos>59){
                throw new IllegalArgumentException("Error: SEGUNDOS excede 59 unidades");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Error: SEGUNDOS contiene caracteres alfanumericos");
        }
    }

    /**
     * Lee los datos de recitales desde el archivo "RECITALES.txt", valida cada línea,
     * y agrega el recital a el artista correspondiente en el mapa de artistas.
     * Valida la consistencia de los datos ingresados
     * <p>
     * Se basa en el siguiente formato de datos:
     * <pre>
     *      idArtista;nombreDisco;nombreCancion;duracion;cantReproducciones
     * <pre>
     * @param mapaArtistas
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 5 bloques separados por punto y coma (;)
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     * @throws IOException Si ocurre un error al intentar leer el archivo.
     */
    public void leeRecital(Gestion mapaArtistas) {

        try {

            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/Archivos/RECITALES.txt"));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] bloque = linea.split(";");
                try {
                    validaDatosRecital(bloque, mapaArtistas);
                    String id = bloque[0];
                    float recaudacion = Float.parseFloat(bloque[2]);
                    String fecha = bloque[1];
                    float costosProduccion = Float.parseFloat(bloque[3]);
                    mapaArtistas.filtraArtistaPorID(id).addRecital(new Recital(fecha, recaudacion, costosProduccion));
                } catch (StringIndexOutOfBoundsException e) {
                    informe.agregaError("Error en linea "+linea+ "-" +e.getMessage());
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

    /**
     * Valida los datos del Recital
     *
     * @param bloque linea de datos separadas en bloques en cada punto y coma (;)
     * @param mapaArtistas Objeto de tipo gestion en el que se cargan los datos de disco
     * @throws IllegalArgumentException si la linea tiene alguna inconsistencia de datos
     * @throws StringIndexOutOfBoundsException si la linea tiene mas o menos de 5 bloques separados por punto y coma (;)
     */
    public void validaDatosRecital(String[] bloque, Gestion mapaArtistas) throws IllegalArgumentException , StringIndexOutOfBoundsException {
        if(bloque.length != 4) {
            throw new IllegalArgumentException("Error: cantidad de datos erronea.");
        }
        String id = bloque[0];
        if(id.length() != 6) {
            throw new IllegalArgumentException("Error: ID debe tener 6 caracteres");
        }
        if (mapaArtistas.filtraArtistaPorID(id) == null) {
            throw new IllegalArgumentException("Error: ID artista no existe");
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(bloque[1], dateFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha debe estar en formato dd-MM-yyyy y ser una fecha válida.");
        }
        try{
            float recaudacion = Float.parseFloat(bloque[2]);
            if(recaudacion < 0 ){
                throw new IllegalArgumentException("Error: RECAUDACION negativo");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Error: RECAUDACION posee caracteres alfanumericos");
        }
        try {
            float costosProduccion = Float.parseFloat(bloque[3]);
            if(costosProduccion < 0 ){
                throw new IllegalArgumentException("Error: COSTO PRODUCCION negativo");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Error: COSTOSPRODUCION posee caracteres alfanumericos");
        }
    }
}
