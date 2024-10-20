package Discográfica;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class Artista {
    private String identificador;
    private String nombre;
    private byte cantIntegrantes;
    private String generoMusical;
    private Set<Disco> discos;
    private Set<Recital> recitales;

    public Artista(String identificador,String nombre,byte cantIntegrantes,String generoMusical){
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

    public String getGeneroMusical(){return generoMusical;}

    public void addDisco(Disco disco){
        if(!discos.add(disco)){
            discos.add(disco);
        }
    }

    public void addRecital(Recital recital){
        if(!recitales.add(recital)){
            recitales.add(recital);
        }
    }

    public String listaRecitales(){
        String listado="\nRecitales";
        Iterator<Recital> iterator = recitales.iterator();
        Recital recital;
        while(iterator.hasNext()){
            recital = iterator.next();
            listado+="\n"+recital.toString();
        }
        return listado;
    }

    public String listaDiscos(){
        String listado="\nDiscos";
        Iterator<Disco> iterator = discos.iterator();
        Disco disco;
        while(iterator.hasNext()){
            disco = iterator.next();
            listado+="\n"+disco.toString();
        }
        return listado;
    }

    public void listadoCompleto(){
        System.out.println("identificador = " + identificador);
        System.out.println("nombre = " + nombre);
        System.out.println("cantIntegrantes = " + cantIntegrantes);
        System.out.println("generoMusical = " + generoMusical);
        System.out.println(listaDiscos());
        System.out.println(listaRecitales());
    }

    public void listadoUnidadesVendidasPorDisco(){
        Iterator<Disco> iterator= discos.iterator();
        Disco disco;
        System.out.println("Listado de Unidades vendidas por disco: ");
        while(iterator.hasNext()){
            disco= iterator.next();
            System.out.println(disco.toString());
        }
        if(!discos.isEmpty()){
            System.out.println("Promedio de unidades vendidas por Disco: "+(unidadesDiscosVendidas()/discos.size()));
        }
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
            total+=disco.getReproduccionesDisco();
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
}
