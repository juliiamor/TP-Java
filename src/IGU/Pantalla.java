package IGU;

import Discográfica.GeneroMusical;
import Discográfica.Gestion;
import Discográfica.Reportes;

import javax.swing.*;
import java.awt.*;

public class Pantalla extends JFrame {   // Hereda de JFrame

    private JPanel ventana;
    private JList<String> list1;
    private JButton artistasButton;
    private JTextField textField1; // ID del artista para la facturación
    private JButton facturarButton; // Botón para facturar
    private JComboBox<GeneroMusical> generoComboBox; // ComboBox para el género musical
    private JTextField textField2; // integrantes
    private JButton consultarButton;
    private JComboBox<GeneroMusical> generoComboBox2;
    private JTextField textField4;
    private JButton bajaButton;
    private JButton top10Button;
    private JTextField textField6;
    private JButton discosButton;
    private Gestion gestion;

    public Pantalla(Gestion gestion) {
        this.gestion = gestion;
        add(ventana);
        this.setSize(1000, 700);

        generoComboBox.setModel(new DefaultComboBoxModel<>(GeneroMusical.values()));
        generoComboBox2.setModel(new DefaultComboBoxModel<>(GeneroMusical.values()));
        // Acción del botón facturar
        facturarButton.addActionListener(e -> {
            String identificador = textField1.getText().trim();
            mostrarLiquidacion(identificador);
        });
        // Acción del botón para filtrar artistas por género musical
        consultarButton.addActionListener(e -> {
            GeneroMusical generoSeleccionado = (GeneroMusical) generoComboBox.getSelectedItem();
            String integrantesStr = textField2.getText().trim();
            byte cantidadIntegrantes;
            if (integrantesStr.isEmpty()) {
                cantidadIntegrantes = 0;
            } else {
                cantidadIntegrantes = Byte.parseByte(integrantesStr);
            }

            filtrarArtistasPorGeneroYIntegrantes(generoSeleccionado, cantidadIntegrantes);
        });
        top10Button.addActionListener(e -> {
            GeneroMusical generoSeleccionado = (GeneroMusical) generoComboBox2.getSelectedItem();
            mostrarTop10(generoSeleccionado);
        });
        discosButton.addActionListener( e -> {
            String identificador = textField6.getText().trim();
            mostrarUnidadesVendidas(identificador);
        });

        artistasButton.addActionListener(e -> {
            mostrarListadoArtistas();
        });
        artistasButton.addActionListener(e -> mostrarListadoArtistas());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void mostrarListadoArtistas() {
        StringBuilder listadoArtistas = gestion.listadoArtistasCompleto();
        String[] artistasArray = listadoArtistas.toString().split("\n");
        list1.setListData(artistasArray);
    }


    private void filtrarArtistasPorGeneroYIntegrantes(GeneroMusical genero,byte cantidadIntegrantes) {
        String artistasEncontrados = Reportes.consultaDatosConFiltros(cantidadIntegrantes,genero,gestion);
        if (artistasEncontrados.contains("error")) {
            JOptionPane.showMessageDialog(this, "No se ha ingresado ningun dato válido");
        }else {
            JTextArea textArea = new JTextArea(artistasEncontrados);
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            JOptionPane.showMessageDialog(this, scrollPane, "Artistas filtrados:", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void mostrarLiquidacion(String identificador) {
        StringBuilder liquidacion = gestion.facturacionUltMes(identificador);

        if (liquidacion.toString().contains("No se encontró al artista")) {
            JOptionPane.showMessageDialog(this, liquidacion.toString());
        }else {
            JTextArea textArea = new JTextArea(liquidacion.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            // Mostrar la ventana con la liquidación
            JOptionPane.showMessageDialog(this, scrollPane, "Liquidación del Artista", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarTop10(GeneroMusical genero){
            JTextArea textArea = new JTextArea(Reportes.Top10CancionesPorGenero(gestion.getArtistas(),genero));
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600,400));

            String titulo = "Top 10 canciones del género "+genero;
            JOptionPane.showMessageDialog(this,scrollPane,titulo,JOptionPane.INFORMATION_MESSAGE);

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