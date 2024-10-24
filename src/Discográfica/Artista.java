package Discográfica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.time.format.DateTimeFormatter;


public abstract class Artista implements Serializable {
    private String identificador,nombre;
    private GeneroMusical generoMusical;
    private byte cantIntegrantes;
    private Set<Disco> discos;
    private Set<Recital> recitales;


    //Constructor
    public Artista(String identificador,String nombre,byte cantIntegrantes,GeneroMusical generoMusical){
        this.identificador=identificador;
        this.nombre=nombre;
        this.cantIntegrantes=cantIntegrantes;
        this.generoMusical=generoMusical;
        discos = new HashSet<>();
        recitales = new HashSet<>();
    }

    public String getIdentificador(){return identificador;}

    public String getNombre(){return nombre;}

    public byte getCantIntegrantes(){return cantIntegrantes;}

    public GeneroMusical getGeneroMusical(){return generoMusical;}

    public Set<Disco> getDiscos() {
        return discos;
    }

    public Set<Recital> getRecitales() {
        return recitales;
    }

    public void addDisco(Disco disco){
        if (!discos.add(disco)) {
            throw new IllegalArgumentException("El disco ya existe en la colección.");
        }
    }

    public void addRecital(Recital recital){
        if(!recitales.add(recital)){
            throw new IllegalArgumentException("El recital ya existe en la colección.");
        }
    }

    public String toString(){
        StringBuilder listado = new StringBuilder();
        listado.append(identificador).append("- Nombre: ").append(nombre)
                .append(" - Integrantes: ").append(cantIntegrantes)
                .append(" - Género musical: ").append(generoMusical);

        listado.append("\n\tLista de Discos:\n");
        Iterator<Disco> iteratorDiscos = discos.iterator();
        Disco disco;
        while (iteratorDiscos.hasNext()) {
            disco = iteratorDiscos.next();
            listado.append(disco).append("\n");
        }
        listado.append("\n\tLista de Recitales:\n");
        Iterator<Recital> iteratorRecitales = recitales.iterator();
        Recital recital;
        while (iteratorRecitales.hasNext()) {
            recital = iteratorRecitales.next();
            listado.append(recital).append("\n");
        }
        return listado.toString();
    }

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

    public int totalDiscosVendidos(){
        int total=0;
        Disco disco;
        Iterator <Disco>iterator=discos.iterator();
        while(iterator.hasNext()){
            disco=iterator.next();
            total+=disco.getUnidadesVendidasUltMes();
        }
        return total;
    }
}
