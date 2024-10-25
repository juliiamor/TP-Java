package Discográfica;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Disco implements Serializable{
    private long unidadesVendidasUltMes;
    private String nombre;
    private Set<Cancion> canciones;

    public Disco(long unidadesVendidasUltMes, String nombre) {
        this.unidadesVendidasUltMes = unidadesVendidasUltMes;
        this.nombre = nombre;
        this.canciones = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
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

    public String toString(){
        StringBuilder listado = new StringBuilder();
        listado.append("\t\tNombre: ").append(nombre).append(" - Unidades Vendidas en el Mes: ").append(unidadesVendidasUltMes).append("\n");
        Iterator<Cancion> iterator = canciones.iterator();
        Cancion cancion;
        byte i=0;
        while(iterator.hasNext()){
            i++;
            cancion=iterator.next();
            listado.append("\t\t").append(i).append(".   ").append(cancion).append("\n");
        }
        return listado.toString();
    }

    public boolean EsSencillo(){
        return canciones.size()==1;
    }
}
