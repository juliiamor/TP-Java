package Discográfica;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class Artista {
    private String identificador,nombre,generoMusical;
    private byte cantIntegrantes;
    private Set<Disco> discos;
    private Set<Recital> recitales;


    //Constructor
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
        if (!discos.add(disco)) {
            throw new IllegalArgumentException("El disco ya existe en la colección.");
        }
    }

    public void addRecital(Recital recital){
        if(!recitales.add(recital)){
            throw new IllegalArgumentException("El recital ya existe en la colección.");
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

    public String listadoDatos(){
        String listado=" identificador = " + identificador+" nombre = " + nombre + " cantIntegrantes = " + cantIntegrantes+" generoMusical = " + generoMusical;
        return listado;
    }

    public String listadoCompleto(){
        String listado=listadoDatos()+listaDiscos()+listaRecitales();
        return listado;
    }

    public String listadoUnidadesVendidasPorDisco(){
        Iterator<Disco> iterator= discos.iterator();
        Disco disco;
        String listado="";
        while(iterator.hasNext()){
            disco= iterator.next();
            listado+="\n"+disco.toString();
        }

        if(!discos.isEmpty()){
            float promedio=unidadesDiscosVendidas()/discos.size();
            listado+="Promedio de unidades vendidas por Disco: "+promedio;
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
