package Archivos;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Clase que se encarga de generar un informe con los errores encontrados en la consistencia de datos
 * La clase maneja la inconsistencia en datos, y tiene un atributo informe, en el que se escriben las lineas con inconsistencias
 * que encuentra en los datos que se ingresan en el archivo TXT.
 * Este informe se almacena en un archivo de texto "INFORME_Consistencia_de_Datos.txt"
 */
public class InformeConsistenciaDatos {
    /**
     * StringBuilder en el que se escribe cada linea inconsistente
     */
    private StringBuilder informe;

    /**
     * Constructor de la clase
     * Inicializa informe con un titulo
     */
    public InformeConsistenciaDatos(){
        informe=new StringBuilder("\tINFORME CONSISTENCIA DE DATOS CARGADOS DESDE ARCHIVO\n\n");
    }

    /**
     * Metodo que se encarga de agregar la linea del error al informe
     * @param error
     */
    public void agregaError(String error){
        informe.append("\n").append(error);
    }

    /**
     * Metodo toString que permite listar el informe completo
     * @return informe
     */
    public String toString(){
        return informe.toString();
    }

    /**
     * Genera el archivo "INFORME_Consistencia_de_Datos.txt" y almacena el informe completo
     */
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
