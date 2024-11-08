package Discográfica;

/**
 * Enumeración que representa los diferentes géneros musicales disponibles
 * Incluye una lista de géneros musicales y un metodo para validar si un género dado pertenece a la enumeración
 */

public enum GeneroMusical {
    INGRESE_GENERO, ROCK, POP,CUMBIA,REGGAETON,ELECTRONICA, JAZZ, SOUL, HIP_HOP, CLASICA, HEAVY_METAL, FOLK, RAP;

    /**
     * Metodo para validar si un género dado pertenece a la clase ENUM de géneros musicales
     *
     * @param genero El género a validar, como una cadena de texto
     * @return true si el género pertenece a la clase ENUM, false si no pertenece
     */
    public static boolean pertenece(String genero) {
        for (GeneroMusical gen : GeneroMusical.values()) {
            if (gen.name().equalsIgnoreCase(genero)) {
                return true;
            }
        }
        return false;
    }
}
