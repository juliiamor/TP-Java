package Discográfica;


import java.io.Serializable;

public class Recital implements Serializable {
    private String fecha;
    private float recaudacion;
    private float costosProduccion;
    private static final long serialVersionUID=12345L;

    public Recital(String fecha, float recaudacion,float costosProduccion){
        this.costosProduccion=costosProduccion;
        this.fecha=fecha;
        this.recaudacion=recaudacion;
    }

    public float getNeto(){return recaudacion-costosProduccion;}

    public String getFecha(){return fecha;}
    public String toString() {
        return "Fecha - "+fecha+" - Recaudacion - "+recaudacion+" - Costos de Produccion - "+costosProduccion;
    }
}
