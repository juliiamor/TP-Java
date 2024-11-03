package IGU;

import Discográfica.Artista;
import Discográfica.Gestion;

import javax.swing.*;

public class VentanaFacturacion extends JFrame{
    private JTextField textField3;
    private JPanel Ventana;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField7;

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
