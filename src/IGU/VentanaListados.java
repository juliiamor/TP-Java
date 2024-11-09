package IGU;

import Discográfica.Gestion;

import javax.swing.*;
import java.awt.*;

public class VentanaListados extends JFrame{
    private JPanel ventana;
    private JTextArea textArea1;
    private JLabel titulo;
    private JTextField textField1;

    public VentanaListados(String reporte, String titulo) {
        this.textArea1.setText(reporte);
        this.titulo.setText(titulo);
        add(ventana);
        this.setSize(800, 500);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
