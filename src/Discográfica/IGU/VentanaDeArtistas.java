package IGU;

import Discografica.*;
import javax.swing.*;
import java.util.TreeMap;

public class VentanaDeArtistas extends JFrame {
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField6;
    private JTextField textField8;
    private JPanel Ventana;
    private JScrollPane PanelDiscos;
    private JScrollPane PanelRecitales;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JComboBox artistasComboBox;
    private JTextField textArtista;
    private JButton consultaButton;

    public VentanaDeArtistas(TreeMap<String, Artista> artistas){
        add(Ventana);
        this.setSize(1200,800);
        String[] identificadores = new String[artistas.size()];
        int i=0;
        for(String id: artistas.keySet()){
            identificadores[i]=id;
            i++;
        }
        artistasComboBox.setModel(new DefaultComboBoxModel<>(identificadores));
        consultaButton.addActionListener(e -> {
            String id = artistasComboBox.getSelectedItem().toString();
            try{
                Artista artista = artistas.get(id);
                textField2.setText(artista.getIdentificador());
                textField4.setText(artista.getNombre());
                textField6.setText(artista.getGeneroMusical().toString());
                textField8.setText(String.valueOf(artista.getCantIntegrantes()));
                textArea1.setText(artista.muestraDiscos());
                textArea2.setText(artista.muestraRecitales());
            }catch(IllegalArgumentException ex){

            }
        });


    }
}
