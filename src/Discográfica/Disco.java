package Discográfica;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Disco implements Serializable{
    private long unidadesVendidasUltMes;
    private Set<Cancion> canciones;

    public Disco(long unidadesVendidasUltMes){
        this.unidadesVendidasUltMes=unidadesVendidasUltMes;
        canciones = new HashSet<>();
    }

    public long getUnidadesVendidasUltMes(){return unidadesVendidasUltMes;}

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public void addCancion(Cancion cancion){
        if(!canciones.add(cancion)){
            canciones.add(cancion);
        }
    }

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

    public String listadoCanciones(){
        String listado="";
        Iterator<Cancion> iterator = canciones.iterator();
        Cancion cancion;
        while(iterator.hasNext()){
            cancion=iterator.next();
            listado+=cancion+"\n";
        }
        return listado;
    }

    public String toString(){
        return "Disco - Unidades vendidas "+unidadesVendidasUltMes+"\n"+listadoCanciones()+"\n";
    }
}
