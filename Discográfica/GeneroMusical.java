package Discográfica;

public enum GeneroMusical {
    INGRESE_GENERO, ROCK, POP,CUMBIA,REGGAETON,ELECTRONICA, JAZZ, SOUL, HIP_HOP, CLASICA, HEAVY_METAL, FOLK, RAP;

    // método para validar genero dentro Genero musical
    public static boolean pertenece(String genero) {
        for (GeneroMusical gen : GeneroMusical.values()) {
            if (gen.name().equalsIgnoreCase(genero)) {
                return true;
            }
        }
        return false;
    }
}
