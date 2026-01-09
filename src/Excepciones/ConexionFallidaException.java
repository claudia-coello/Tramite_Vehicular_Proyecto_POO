package Excepciones;

public class ConexionFallidaException extends RuntimeException {
    public ConexionFallidaException(String message) {
        super(message);
    }
}
