package club.excepciones;

public class SocioNoExisteException extends Exception {
    public SocioNoExisteException(String mensaje) {
        super(mensaje);
    }
}
