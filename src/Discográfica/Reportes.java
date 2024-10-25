package Discográfica;

import Archivos.GeneraArchivos;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public abstract class Reportes {

    public Reportes() {
    }

    public static String Top10CancionesPorGenero(TreeMap<String, Artista> artistas,GeneroMusical genero) {
        // Lista para almacenar canciones del género dado
        List<Cancion> cancionesDelGenero = new ArrayList<>();

        for (Artista artista : artistas.values()) {
            if (artista.getGeneroMusical()== genero) {
                for (Disco disco : artista.getDiscos()) {
                    cancionesDelGenero.addAll(disco.getCanciones());
                }
            }
        }
        // Ordena las canciones por cantidad de reproducciones en orden descendente
        Collections.sort(cancionesDelGenero, Comparator.comparingLong(Cancion::getCantReprodUltMes).reversed());

        // Genera una sublista con las primeras 10 canciones, si hay menos de 10 la genera con la cantidad que haya
        List<Cancion> top10Canciones = cancionesDelGenero.subList(0, Math.min(10, cancionesDelGenero.size()));

        StringBuilder resultado = new StringBuilder();
        // Imprimir en pantalla
        resultado.append("Top 10 canciones del género ").append(genero).append(":\n");
        for (int i = 0; i < top10Canciones.size(); i++) {
            Cancion cancion = top10Canciones.get(i);
            resultado.append((i + 1)).append(". ").append(cancion.getNombre()).append(" ").append(cancion.getCantReprodUltMes()).append(" reproducciones\n");
        }

        GeneraArchivos.generaArchivoTop10Canciones(genero, top10Canciones);
        return resultado.toString();
    }

    public static String unidadesVendidas(String identificador, TreeMap<String, Artista> artistas) {
        // Verifica si el artista existe
        if (artistas.containsKey(identificador)) {
            Artista artista = artistas.get(identificador);

            // Crea el contenido del reporte
            StringBuilder contenido = new StringBuilder();
            contenido.append("Reporte de Unidades Vendidas para ").append(artista.getNombre()).append(":\n");

            // Variables para calcular el total de unidades vendidas y el promedio
            long totalUnidadesVendidas = artista.unidadesDiscosVendidas(); //Utiliza el metodo ya existente que calcula el total de unidades de discos vendidos
            int cantidadDiscos = artista.getDiscos().size();

            // Recorre los discos del artista
            for (Disco disco : artista.getDiscos()) {
                // Escribe el nombre del disco y sus unidades vendidas
                String discoInfo = "Disco: " + disco.getNombre() + " - Unidades vendidas: " + disco.getUnidadesVendidasUltMes() + "\n";
                contenido.append(discoInfo);
            }

            // Escribe el total de discos
            String totalDiscos = "\nTotal de discos: " + totalUnidadesVendidas + "\n";
            contenido.append(totalDiscos);

            // Calcula y escribe el promedio de unidades vendidas
            if (cantidadDiscos > 0) {
                double promedioUnidades = (double) totalUnidadesVendidas / cantidadDiscos;
                String promedio = "Promedio de unidades vendidas por disco: " + promedioUnidades + "\n";
                contenido.append(promedio);
            } else {
                String noDiscos = "No hay discos para calcular el promedio.\n";
                contenido.append(noDiscos);
            }

            // Llama al método para generar el archivo con las unidades vendidas
            GeneraArchivos.generaArchivoUnidadesVendidas(artista.getNombre(), contenido.toString());
            return contenido.toString();
        } else {
            throw new IllegalArgumentException("Artista no encontrado");
        }
    }

    public static String consultaDatosConFiltros(byte cantIntegrantes, GeneroMusical genero, Gestion gestion) {
        StringBuilder resultado = new StringBuilder();
        List<Artista> artistasFiltrados = new ArrayList<>();
        if(cantIntegrantes==0 && genero!=GeneroMusical.INGRESE_GENERO){
            resultado.append("Artistas Filtrados por");
            artistasFiltrados = gestion.filtrarArtistas(genero);
            resultado.append(" Genero: ").append(genero);
        }else if(genero==GeneroMusical.INGRESE_GENERO && cantIntegrantes!=0){
            resultado.append("Artistas Filtrados por");
            artistasFiltrados = gestion.filtrarArtistas(cantIntegrantes);
            resultado.append(" Cantidad de Integrantes: ").append(cantIntegrantes);
        }else if(genero==GeneroMusical.INGRESE_GENERO && cantIntegrantes==0){
            resultado.append("error");
        }
        else{
            resultado.append("Artistas Filtrados por");
            artistasFiltrados = gestion.filtrarArtistas(cantIntegrantes,genero);
            resultado.append(" Genero ").append(genero).append(" y Cantidad de Integrantes ").append(cantIntegrantes);
        }
        Iterator<Artista> iterator = artistasFiltrados.iterator();
        Artista artista;
        while (iterator.hasNext()) {
            artista = iterator.next();
            resultado.append("\n\n").append(artista);
        }
        return resultado.toString();
    }
}
