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
    private JComboBox<GeneroMusical> generoComboBox; // ComboBox para el género musical
    private JTextField textField2; // integrantes
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

        generoComboBox.setModel(new DefaultComboBoxModel<>(GeneroMusical.values()));
        // Acción del botón facturar
        facturarButton.addActionListener(e -> {
            String identificador = textField1.getText().trim();
            mostrarLiquidacion(identificador);
        });
        // Acción del botón para filtrar artistas por género musical
        consultarButton.addActionListener(e -> {
            GeneroMusical generoSeleccionado = (GeneroMusical) generoComboBox.getSelectedItem();
            String integrantesStr = textField2.getText().trim();

            // Manejo de la entrada de la cantidad de integrantes
            byte cantidadIntegrantes;
            try {
                cantidadIntegrantes = Byte.parseByte(integrantesStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido para la cantidad de integrantes.");
                return;
            }

            filtrarArtistasPorGeneroYIntegrantes(generoSeleccionado, cantidadIntegrantes);
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    // Método para filtrar los artistas por género musical
    private void filtrarArtistasPorGeneroYIntegrantes(GeneroMusical genero,byte cantidadIntegrantes) {
        String artistasEncontrados = Reportes.consultaDatosConFiltros(cantidadIntegrantes,genero,gestion);
        JTextArea textArea = new JTextArea(artistasEncontrados);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Artistas filtrados:", JOptionPane.INFORMATION_MESSAGE);
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
}