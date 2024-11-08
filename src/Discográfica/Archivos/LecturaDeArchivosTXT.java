package Archivos;
import Discografica.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.lang.String;

public class LecturaDeArchivosTXT{
    InformeConsistenciaDatos informe;

    public LecturaDeArchivosTXT(){
        informe = new InformeConsistenciaDatos();
    }

    public void generaInforme(){
        informe.generaInforme();
    }

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
                    informe.agregaError("Error: cantidad de datos erronea");
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

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
        if (!GeneroMusical.pertenece(gen)) {
            throw new IllegalArgumentException("Error: GENERO no incluido");
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
                    informe.agregaError("Error: cantidad de datos erronea");
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

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
            if (cantVendidas < 0 || cantVendidas > 9223372036854775807L) {
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

    public void leeCancion(Gestion mapaArtistas) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/Archivos/CANCIONES.txt"));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] bloque = linea.split(";");
                try {
                    //CANCION.....  id (string); nombre(disco) (string) ; nombre(cancion) ; duracion ; cantReproducciones(int)
                    validaDatosCancion(bloque, mapaArtistas);

                    String id = bloque[0];
                    String nombreDisco = bloque[1];
                    String nombre = bloque[2];
                    String duracion = bloque[3];
                    long cantReproducciones = Long.parseLong(bloque[4]);
                    mapaArtistas.filtraArtistaPorID(id).getDisco(nombreDisco).addCancion(new Cancion(nombre, duracion, cantReproducciones));
                } catch (StringIndexOutOfBoundsException e) {
                    informe.agregaError("Error: cantidad de datos erronea");
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

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
            if (cantVendidas < 0 || cantVendidas > 9223372036854775807L) {
                throw new IllegalArgumentException("Error: CANTIDADVENDIDA fuera de rango");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: CANTIDADVENDIDA contiene caracteres alfanumericos");
        }
    }

    public void validaDuracion(String duracion) throws IllegalArgumentException, StringIndexOutOfBoundsException {
        String[] bloque = duracion.split(":");
        if (bloque.length != 2) {
            throw new IllegalArgumentException("Error: Formato de duracion debe ser minutos:segundos");
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
                    informe.agregaError("Error: cantidad de datos erronea");
                } catch (IllegalArgumentException e) {
                    informe.agregaError("Error de datos en la linea: " + linea + "-" + e.getMessage());
                }
            }
            lector.close();
        } catch (IOException e) {
            informe.agregaError("Error al leer el archivo " + e.getMessage());
        }
    }

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



