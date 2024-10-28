package IGU;

import Discográfica.*;
import javax.swing.*;

public class VentanaDeArtistas extends JFrame {
    private JTextField IDENTIFICADORTextField;
    private JTextField textField2;
    private JTextField ARTISTATextField;
    private JTextField textField4;
    private JTextField GENEROMUSICALTextField;
    private JTextField textField6;
    private JTextField INTEGRANTESTextField;
    private JTextField textField8;
    private JTextField DISCOSDELARTISTATextField;
    private JTextField RECITALESDELARTISTATextField;
    private JPanel Ventana;
    private JScrollPane PanelDiscos;
    private JScrollPane PanelRecitales;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public VentanaDeArtistas(Artista artista){
        add(Ventana);
        this.setSize(1000,800);
        textField2.setText(artista.getIdentificador());
        textField4.setText(artista.getNombre());
        textField6.setText(artista.getGeneroMusical().toString());
        textArea1.setText(artista.muestraDiscos());
        textArea2.setText(artista.muestraRecitales());
        IDENTIFICADORTextField.setEditable(false);
        textField2.setEditable(false);
        ARTISTATextField.setEditable(false);
        textField4.setEditable(false);
        GENEROMUSICALTextField.setEditable(false);
        textField6.setEditable(false);
        INTEGRANTESTextField.setEditable(false);
        textField8.setEditable(false);
        DISCOSDELARTISTATextField.setEditable(false);
        RECITALESDELARTISTATextField.setEditable(false);
        textArea1.setEditable(false);
        textArea2.setEditable(false);
    }
}
