package IGU;

import Archivos.ArchivosSerializados;
import Discográfica.Artista;
import Discográfica.GeneroMusical;
import Discográfica.Gestion;
import Discográfica.Reportes;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

public class Pantalla extends JFrame {   // Hereda de JFrame

    private JPanel ventana;
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
    private JButton mostrarDatosButton;
    private JTextArea textAreaListadoArtistas;
    private Gestion gestion;

    public Pantalla(Gestion gestion) {
        this.gestion = gestion;
        add(ventana);
        this.setSize(1000, 700);
        TreeMap<String,Artista> mapa=gestion.getArtistas();
        String[] identificadores = new String[gestion.getArtistas().size()];
        int i=0;
        for(String id: mapa.keySet()){
            identificadores[i]=id;
            i++;
        }

        generoComboBox.setModel(new DefaultComboBoxModel<>(GeneroMusical.values()));
        generoComboBox2.setModel(new DefaultComboBoxModel<>(GeneroMusical.values()));
        // Acción del botón facturar
        facturarButton.addActionListener(e -> {
            String identificador = textField1.getText().trim();
            mostrarLiquidacion(identificador);
        });
        // Acción del botón para filtrar artistas por género musical e Integrantes
        consultarButton.addActionListener(e -> {
            GeneroMusical generoSeleccionado = (GeneroMusical) generoComboBox.getSelectedItem();
            String integrantesStr = textField2.getText().trim();
            byte cantidadIntegrantes;
            try{
                if (integrantesStr.isEmpty()) {
                    cantidadIntegrantes = 0;
                } else {
                    cantidadIntegrantes = Byte.parseByte(integrantesStr);
                }
                if(cantidadIntegrantes<0){
                    JOptionPane.showMessageDialog(this, "El numero de integrantes no puede ser negativo");
                }else
                    filtrarArtistasPorGeneroYIntegrantes(generoSeleccionado, cantidadIntegrantes);
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Cantidad de integrantes invalida");
            }

        });
        top10Button.addActionListener(e -> {
            GeneroMusical generoSeleccionado = (GeneroMusical) generoComboBox2.getSelectedItem();
            mostrarTop10(generoSeleccionado);
        });
        discosButton.addActionListener( e -> {
            String identificador = textField6.getText().trim();
            mostrarUnidadesVendidas(identificador);
        });

        mostrarDatosButton.addActionListener(e -> {
            try{
                new VentanaDeArtistas(gestion.getArtistas()).setVisible(true);
            }catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(this, "No se ha encontrado ese artista");
            }
        });

        bajaButton.addActionListener(e -> {
            String identificador = textField4.getText().trim();
            if(identificador.isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese un ID", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                bajaArtista(identificador);
                ArchivosSerializados.guardarArtistas(gestion.getArtistas());
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void filtrarArtistasPorGeneroYIntegrantes(GeneroMusical genero,byte cantidadIntegrantes) {
        if(genero.toString().equals("INGRESE_GENERO") && cantidadIntegrantes==0){
            JOptionPane.showMessageDialog(this, "Ingrese Filtros");
        }else{
            TreeMap<String,Artista> artistasFiltrados;

            if(genero.toString()=="INGRESE_GENERO"){
                artistasFiltrados=gestion.filtrarArtistas(cantidadIntegrantes);
            } else if (cantidadIntegrantes==0) {
                artistasFiltrados=gestion.filtrarArtistas(genero);
            }else{
                artistasFiltrados=gestion.filtrarArtistas(cantidadIntegrantes,genero);
            }

            if(artistasFiltrados.isEmpty()){
                JOptionPane.showMessageDialog(this,"No existen artistas con esos filtros");
            }else{
                new VentanaDeArtistas(artistasFiltrados).setVisible(true);
            }
        }
    }

    public void bajaArtista(String identificador){
        try{
            gestion.removeArtista(identificador);
            JOptionPane.showMessageDialog(this,"Se ha dado de baja al artista con éxito","Baja Artista",JOptionPane.INFORMATION_MESSAGE);
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "El artista ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
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
            scrollPane.setPreferredSize(new Dimension(800,600));

            // Mostrar la ventana con la liquidación
            JOptionPane.showMessageDialog(this, scrollPane, "Liquidación del Artista", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarTop10(GeneroMusical genero){
        if(genero.equals(GeneroMusical.INGRESE_GENERO)){
            JOptionPane.showMessageDialog(this, "No se ha ingresado ningun género válido");
        }else{
            JTextArea textArea = new JTextArea(Reportes.Top10CancionesPorGenero(gestion.getArtistas(),genero));
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(800,600));

            String titulo = "Top 10 canciones del género "+genero;
            JOptionPane.showMessageDialog(this,scrollPane,titulo,JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarUnidadesVendidas(String identificador){
        try{
            JTextArea textArea = new JTextArea(Reportes.unidadesVendidas(identificador,gestion.getArtistas()));
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(800,600));

            String titulo = "Unidades de Discos vendidas en el mes del Artista "+identificador;
            JOptionPane.showMessageDialog(this,scrollPane,titulo,JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "El artista ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
