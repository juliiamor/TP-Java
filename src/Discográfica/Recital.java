package Discográfica;

public class Recital {
    private String fecha;
    private float recaudacion;
    private float costosProduccion;

    public Recital(String fecha, float recaudacion,float costosProduccion){
        this.costosProduccion=costosProduccion;
        this.fecha=fecha;
        this.recaudacion=recaudacion;
    }

    public float getNeto(){return recaudacion-costosProduccion;}

    public String toString() {
        return "Fecha: "+fecha+" Recaudacion: "+recaudacion+" Costos de Produccion: "+costosProduccion+" Ingresos netos: "+getNeto();
    }
}
