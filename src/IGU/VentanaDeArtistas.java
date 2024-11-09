package IGU;

import Discográfica.*;
import javax.swing.*;
import java.util.TreeMap;

/**
 * Clase de la ventana que lista Artistas
 * Utiliza la biblioteca swing para generar la interfaz
 * Extiende de {@link JFrame}
 *
 * Esta clase otorga funciones para mostrar los datos completos de artistas
 *
 * @see JFrame
 */
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

    /**
     * Constructor de la Clase, genera una ventana en la que se muestran los datos completos de los artistas,
     * teniendo un boton desplegable para seleccionar al artista
     * @param artistas Mapa con todos los artistas que se quieren listar
     */
    public VentanaDeArtistas(TreeMap<String, Artista> artistas){
        add(Ventana);
        this.setSize(1600,800);
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
