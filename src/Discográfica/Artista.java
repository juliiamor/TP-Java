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

    public void listadoUnidadesVendidasPorDisco(){
        long acumulaVentas=0;
        byte cantDiscos=0;
        float promedio;
        Iterator<Disco> iterator = discos.iterator();
        System.out.println("\nListado de Ventas por disco en el Mes\n");
        while(iterator.hasNext()){
            Disco disco = iterator.next();
            System.out.println(disco.toString());
            acumulaVentas+=disco.getUnidadesVendidasUltMes();
            cantDiscos++;
        }
        if(cantDiscos!=0){
            promedio=acumulaVentas/(float)cantDiscos;
            System.out.println("\nPromedio de ventas en el mes: "+promedio);
        }
    }

}
