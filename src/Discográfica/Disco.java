package Discográfica;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Disco {
    private long unidadesVendidasUltMes;
    private Set<Cancion> canciones;

    public Disco(long unidadesVendidasUltMes){
        this.unidadesVendidasUltMes=unidadesVendidasUltMes;
        canciones = new HashSet<>();
    }

    public long getUnidadesVendidasUltMes(){return unidadesVendidasUltMes;}

    public void addCancion(Cancion cancion){
        if(!canciones.add(cancion)){
            canciones.add(cancion);
        }
    }

    public long getReproduccionesDisco(){
        long total=0;
        Iterator<Cancion> iterator = canciones.iterator();
        while(iterator.hasNext()){
            Cancion cancion = iterator.next();
            total+=cancion.getCantReprodUltMes();
        }
        return total;
    }



    public String toString(){
        String retorna= "Disco\nUnidades Vendidas en el último mes: "+unidadesVendidasUltMes+"\nCanciones del disco: \n";
        Iterator<Cancion> iterator = canciones.iterator();
        while(iterator.hasNext()){
            Cancion cancion = iterator.next();
            retorna+="\n"+cancion.toString();
        }
        return retorna;
    }

}
