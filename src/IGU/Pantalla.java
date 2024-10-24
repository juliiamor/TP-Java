package IGU;

import Discográfica.GeneroMusical;
import Discográfica.Gestion;
import Discográfica.Reportes;

import javax.swing.*;
import java.awt.*;

public class Pantalla extends JFrame {   // Hereda de JFrame

    private JPanel ventana;
    private JList list1;
    private JButton artistasButton;
    private JTextField textField1; // ID del artista para la facturación
    private JButton facturarButton; // Botón para facturar
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

    public Pantalla(Gestion gestion) {
        this.gestion = gestion;
        add(ventana);
        this.setSize(1000, 700);

        // Acción del botón facturar
        facturarButton.addActionListener(e -> {
            String identificador = textField1.getText().trim();
            mostrarLiquidacion(identificador);
        });
        top10Button.addActionListener(e -> {
            String genero = textField5.getText().trim();
            mostrarTop10(genero);
        });
        discosButton.addActionListener( e -> {
            String identificador = textField6.getText().trim();
            mostrarUnidadesVendidas(identificador);
        });

        artistasButton.addActionListener(e -> {
            mostrarListadoArtistas();
        });


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void mostrarListadoArtistas(){
        //

    }
    public void mostrarLiquidacion(String identificador) {
        StringBuilder liquidacion = gestion.facturacionUltMes(identificador);

        if (liquidacion.toString().contains("No se encontró al artista")) {
            JOptionPane.showMessageDialog(this, liquidacion.toString());
            return;
        }

        JTextArea textArea = new JTextArea(liquidacion.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Mostrar la ventana con la liquidación
        JOptionPane.showMessageDialog(this, scrollPane, "Liquidación del Artista", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarTop10(String genero){
        try{
            GeneroMusical generoMusical=GeneroMusical.valueOf(genero.toUpperCase());

            JTextArea textArea = new JTextArea(Reportes.Top10CancionesPorGenero(gestion.getArtistas(),generoMusical));
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600,400));

            String titulo = "Top 10 canciones del género "+genero;
            JOptionPane.showMessageDialog(this,scrollPane,titulo,JOptionPane.INFORMATION_MESSAGE);

        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "El artista ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarUnidadesVendidas(String identificador){
        try{
            JTextArea textArea = new JTextArea(Reportes.unidadesVendidas(identificador,gestion.getArtistas()));
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600,400));

            String titulo = "Unidades de Discos vendidas en el mes del Artista "+identificador;
            JOptionPane.showMessageDialog(this,scrollPane,titulo,JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "El artista ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
