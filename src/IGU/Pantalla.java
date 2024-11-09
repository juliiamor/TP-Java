package IGU;

import Archivos.ArchivosSerializados;
import Discográfica.Artista;
import Discográfica.GeneroMusical;
import Discográfica.Gestion;
import Discográfica.Reportes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeMap;


/**
 * Clase de la ventana principal de la Interfaz gráfica
 * Utiliza la biblioteca swing para generar la interfaz
 * Extiende de {@link JFrame}
 *
 * Esta clase otorga funciones y operaciones de gestion de artistas
 *
 * @see JFrame
 */
public class Pantalla extends JFrame {   // Hereda de JFrame

    private JPanel ventana;
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
    private JTextArea listadoReferenciaIdArtistas;

    /**
     * Objeto Gestion en el que se almacena todos los artistas
     */
    private Gestion gestion;

    /**
     * Constructor de la clase, inicializa los componentes de la Interfaz Gráfica
     * @param gestion Objeto de la clase {@link Gestion} que gestiona los datos de los artistas
     */
    public Pantalla(Gestion gestion) {
        this.gestion = gestion;
        add(ventana);
        this.setSize(1300, 800);
        TreeMap<String, Artista> mapa = gestion.getArtistas();
        String[] identificadores = new String[gestion.getArtistas().size()];
        int i = 0;
        for (String id : mapa.keySet()) {
            identificadores[i] = id;
            i++;
        }

        listadoReferenciaIdArtistas.setText(Reportes.muestraListadoArtistasId(gestion.getArtistas()));

        TextPrompt placeHolder1 = new TextPrompt("Ingrese ID", textField1);
        TextPrompt placeHolder2 = new TextPrompt("Ingrese n° de integrantes", textField2);
        TextPrompt placeHolder4 = new TextPrompt("Ingrese ID", textField4);
        TextPrompt placeHolder6 = new TextPrompt("Ingrese ID", textField6);
        placeHolder1.changeAlpha(0.75f);
        placeHolder1.changeStyle(Font.ITALIC);
        placeHolder2.changeAlpha(0.75f);
        placeHolder2.changeStyle(Font.ITALIC);
        placeHolder4.changeAlpha(0.75f);
        placeHolder4.changeStyle(Font.ITALIC);
        placeHolder6.changeAlpha(0.75f);
        placeHolder6.changeStyle(Font.ITALIC);

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
            try {
                if (integrantesStr.isEmpty()) {
                    cantidadIntegrantes = 0;
                } else {
                    cantidadIntegrantes = Byte.parseByte(integrantesStr);
                }
                if (cantidadIntegrantes < 0) {
                    JOptionPane.showMessageDialog(this, "El numero de integrantes no puede ser negativo");
                } else
                    filtrarArtistasPorGeneroYIntegrantes(generoSeleccionado, cantidadIntegrantes);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad de integrantes invalida");
            }

        });

        //Accion del boton de Top 10 Canciones
        top10Button.addActionListener(e -> {
            GeneroMusical generoSeleccionado = (GeneroMusical) generoComboBox2.getSelectedItem();
            mostrarTop10(generoSeleccionado);
        });

        //Accion del boton de Discos para listar los discos
        discosButton.addActionListener(e -> {
            String identificador = textField6.getText().trim();
            mostrarUnidadesVendidas(identificador);
        });

        //Accion del boton de mostrar Datos
        mostrarDatosButton.addActionListener(e -> {
            try {
                VentanaDeArtistas venta = new VentanaDeArtistas(gestion.getArtistas());
                venta.setVisible(true);
                venta.setLocationRelativeTo(null);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "No se ha encontrado ese artista");
            }
        });

        //Accion del boton de baja de un artista
        bajaButton.addActionListener(e -> {
            String identificador = textField4.getText().trim();
            if(identificador.isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese un ID", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                bajaArtista(identificador);
            }
        });

        //Termina el programa al cerrar la ventana principal
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Crea los componentes de la interfaz de usuario.
     * Este metodo está diseñado para realizar cualquier inicialización o configuración adicional de los componentes gráficos,
     * como crear componentes personalizados si fuera necesario.
     */
    private void createUIComponents() {
    }

    /**
     * Filtra los artistas según el género musical y la cantidad de integrantes.
     * Si ambos parámetros no son válidos, se muestra un mensaje indicando que se deben ingresar filtros
     * Si el filtro es exitoso, se muestra una ventana con los artistas filtrados.
     *
     * @param genero El género musical seleccionado
     * @param cantidadIntegrantes La cantidad de integrantes por la que se filtran los artistas
     */
    private void filtrarArtistasPorGeneroYIntegrantes(GeneroMusical genero,byte cantidadIntegrantes) {
        if(genero.toString().equals("INGRESE_GENERO") && cantidadIntegrantes==0){
            JOptionPane.showMessageDialog(this, "Ingrese Filtros");
        }else{
            TreeMap<String,Artista> artistasFiltrados;

            if(genero.toString().equals("INGRESE_GENERO")){
                artistasFiltrados=gestion.filtrarArtistas(cantidadIntegrantes);
            } else if (cantidadIntegrantes==0) {
                artistasFiltrados=gestion.filtrarArtistas(genero);
            }else{
                artistasFiltrados=gestion.filtrarArtistas(cantidadIntegrantes,genero);
            }

            if(artistasFiltrados.isEmpty()){
                JOptionPane.showMessageDialog(this,"No existen artistas con esos filtros");
            }else{
               VentanaDeArtistas venta = new VentanaDeArtistas(artistasFiltrados);
               venta.setVisible(true);
               venta.setLocationRelativeTo(null);
            }
        }
    }

    /**
     * Da de baja a un artista según su ID
     * Si el artista existe, se elimina y se guarda la lista actualizada de artistas en el archivo.
     * En caso de que el artista no exista, se muestra un mensaje de error.
     *
     * @param identificador El ID del artista que se desea eliminar
     */
    public void bajaArtista(String identificador){
        try{
            gestion.removeArtista(identificador);
            JOptionPane.showMessageDialog(this,"Se ha dado de baja al artista con éxito","Baja Artista",JOptionPane.INFORMATION_MESSAGE);
            ArchivosSerializados.guardarArtistas(gestion.getArtistas());
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "El artista ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra la liquidación del artista, con los detalles de sus recitales, reproducciones y ventas de discos,
     * el total recaudado, total destinado a Artista y total destinado a Discográfica
     * Si el artista no existe, se muestra un mensaje de error
     *
     * @param identificador El ID del artista para obtener la información de su liquidación.
     */
    public void mostrarLiquidacion(String identificador) {
        Artista artista = gestion.filtraArtistaPorID(identificador);
        if(artista!=null){
            VentanaFacturacion venta = new VentanaFacturacion(
                    artista.getNombre(),
                    gestion.totalRecitalesMes(identificador),
                    gestion.totalReproduccionesMes(identificador),
                    gestion.totalDiscosVendidosMes(identificador),
                    gestion.totalArtista(identificador),
                    gestion.totalDiscografica(identificador),
                    gestion.totalGenerado(identificador)
            );
            venta.setVisible(true);
            venta.setLocationRelativeTo(null);
        }else {
            JOptionPane.showMessageDialog(this, "El artista ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra el top 10 de canciones más reproducidas dentro de un género musical
     * Si el género no es válido, se muestra un mensaje de error.
     *
     * @param genero El género musical seleccionado.
     */
    public void mostrarTop10(GeneroMusical genero){
        if(genero.equals(GeneroMusical.INGRESE_GENERO)){
            JOptionPane.showMessageDialog(this, "No se ha ingresado ningun género válido");
        }else{
            VentanaListados vent = new VentanaListados(Reportes.Top10CancionesPorGenero(gestion.filtrarArtistas(genero),genero),"TOP 10");
            vent.setVisible(true);
            vent.setLocationRelativeTo(null);
        }
    }

    /**
     * Muestra una ventana con la cantidad de discos vendidos por un artista
     * Si el artista no existe, se muestra un mensaje de error.
     *
     * @param identificador El ID del artista para obtener detalles de las unidades vendidas.
     */
    public void mostrarUnidadesVendidas(String identificador){
        try{
            VentanaListados venta = new VentanaListados(Reportes.unidadesVendidas(identificador,gestion.getArtistas()),"Discos Vendidos");
            venta.setVisible(true);
            venta.setLocationRelativeTo(null);
        }
        catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "El artista ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
