package IGU;

import javax.swing.*;

/**
 * Clase de la ventana que muestra la facturacion de un artista
 * Utiliza la biblioteca swing para generar la interfaz
 * Extiende de {@link JFrame}
 *
 * Esta clase otorga funciones para mostrar la facturacion de un artista
 *
 * @see JFrame
 */
public class VentanaFacturacion extends JFrame{
    private JTextField textField3;
    private JPanel Ventana;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField7;

    /**
     * Constructor de la clase, crea una ventana que muestra la facturacion
     *
     * @param artista nombre del artista
     * @param totalRecital total neto recaudado en recitales
     * @param totalRepro total recaudado en reproducciones
     * @param totalDiscos total recaudado en discos
     * @param totalArt total recaudado destinado al artista
     * @param totalDiscografica total recaudado destinado a la discografica
     * @param total total recaudado
     */
    public VentanaFacturacion(String artista,double totalRecital,double totalRepro,double totalDiscos,double totalArt,double totalDiscografica,double total){
        setTitle("Facturacion");
        add(Ventana);
        setSize(530, 600);
        textField1.setText("$ "+String.format("%.2f", totalArt));
        textField2.setText("$ "+String.format("%.2f",totalDiscografica));
        textField3.setText(artista);
        textField4.setText("$ "+String.format("%.2f",totalDiscos));
        textField5.setText("$ "+String.format("%.2f", totalRepro));
        textField6.setText("$ "+String.format("%.2f",totalRecital));
        textField7.setText("$ "+String.format("%.2f", total));
    }
}
