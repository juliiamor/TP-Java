package IGU;

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

    public VentanaFacturacion(){
        setTitle("Facturacion");
        add(Ventana);
        setSize(530, 600);
    }

    public static void main(String[] args) {
        new VentanaFacturacion().setVisible(true);
    }
}