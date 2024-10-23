package Discográfica;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
        String listado="Identificador: "+identificador+" - Nombre: " + nombre + " - Integrantes: " + cantIntegrantes+" - Genero musical: " + generoMusical+"\n";
        Iterator<Disco> iteratorDiscos = discos.iterator();
        Disco disco;
        listado+="Lista de Discos\n";
        while(iteratorDiscos.hasNext()){
            disco = iteratorDiscos.next();
            listado+=disco;
        }
        Iterator<Recital> iteratorRecitales = recitales.iterator();
        Recital recital;
        listado+="Lista de Recitales\n";
        while(iteratorRecitales.hasNext()){
            recital = iteratorRecitales.next();
            listado+=recital+"\n";
        }
        return listado;
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

    public long reproduccionesMensuales(){
        long total=0;

        Iterator<Disco> iterator= discos.iterator();
        Disco disco;
        while(iterator.hasNext()){
            disco = iterator.next();
            total+=(disco.EsSencillo())?disco.getReproduccionesDisco()*1.5:disco.getReproduccionesDisco();
        }
        return total;
    }

    public float netoRecitales(){
        float total=0;
        Iterator<Recital> iterator = recitales.iterator();
        Recital recital;
        while(iterator.hasNext()){
            recital = iterator.next();
            total+=recital.getNeto();
        }
        return total;
    }

    public float LiquidacionUltMes(){ //falta terminar, solo tiene los recitales.
        float total=0;
        Iterator<Recital> iterator= recitales.iterator();
        Recital recital;
        while(iterator.hasNext()){
            recital= iterator.next();
            total+=recital.getNeto();
        }

        return total;
    }
}
