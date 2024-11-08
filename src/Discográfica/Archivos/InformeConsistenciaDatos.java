package Archivos;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class InformeConsistenciaDatos {
    private StringBuilder informe;
    public InformeConsistenciaDatos(){
        informe=new StringBuilder("\tINFORME CONSISTENCIA DE DATOS CARGADOS DESDE ARCHIVO\n\n");
    }

    public void agregaError(String error){
        informe.append("\n").append(error);
    }

    public String toString(){
        return informe.toString();
    }

    public void generaInforme(){
        try{
            FileWriter file = new FileWriter("src/Archivos/Archivos/INFORME_Consistencia_de_Datos.txt");
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(informe.toString());
            writer.close();
        }catch(Exception e){

        }
    }
}
