package Discográfica;
import java.io.Serializable;
public class Cancion implements Serializable{
    private String nombre;
    private String duracion; //string o que??? porq dice minutos y segundos, puede ser 3:23
    private long cantReprodUltMes;

    public Cancion(String nombre, String duracion, long cantReprodUltMes){
        this.cantReprodUltMes=cantReprodUltMes;
        this.nombre=nombre;
        this.duracion=duracion;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public long getCantReprodUltMes(){return cantReprodUltMes;}

    public String toString(){
        return "Cancion "+nombre+" - Duracion: "+duracion+" - Reproducciones en el Mes: "+cantReprodUltMes;
    }
}
