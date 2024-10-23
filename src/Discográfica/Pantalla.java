package Discográfica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla extends JFrame{   //Hereda de JFrame

    private JPanel ventana;
    private JList list1;
    private JButton artistasButton;
    private JTextField textField1; //id del artista para la facturacion
    private JButton facturarButton; //boton para facturar
    private JTextField textField2;
    private JTextField textField3;
    private JButton consultarButton;
    private JTextField textField4;
    private JButton bajaButton;
    private JTextField textField5;
    private JButton top10Button;
    private JTextField textField6;
    private JButton discosButton;
    private Gestion gestion;

    public Pantalla(Gestion gestion){
        this.gestion= gestion;
        add(ventana);
        this.setSize(1000,700);
        //accion del boton facturar
        facturarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificador = textField1.getText();
                mostrarLiquidacion(identificador);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void mostrarLiquidacion(String identificador) {
        Artista artista = gestion.filtraArtistaPorID(identificador);
        if (artista != null) {
            float liquidacion = artista.LiquidacionUltMes();
            String mensaje = "Liquidación para el artista " + artista.getNombre() + ": $" + liquidacion;
            JOptionPane.showMessageDialog(this, mensaje); // Muestra la liquidación en una ventana emergente
        } else {
            JOptionPane.showMessageDialog(this, "Artista no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
