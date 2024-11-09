package IGU;

import javax.swing.*;

/**
 * Clase de la ventana que muestra listados
 * Utiliza la biblioteca swing para generar la interfaz
 * Extiende de {@link JFrame}
 *
 * Esta clase otorga funciones para mostrar listados como los de Top10 Canciones o detalles de discos por artista
 *
 * @see JFrame
 */
public class VentanaListados extends JFrame{
    private JPanel ventana;
    private JTextArea textArea1;
    private JLabel titulo;
    private JTextField textField1;

    /**
     * Constructor de la clase, crea una ventana que muestra un listado
     * @param reporte listado a mostrar
     * @param titulo titulo de la ventana
     */
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
