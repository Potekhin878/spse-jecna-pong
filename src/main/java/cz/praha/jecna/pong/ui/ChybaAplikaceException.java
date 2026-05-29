package cz.praha.jecna.pong.ui;

/**
 * Vlastní třída výjimky pro ošetření neočekávaných stavů při přepínání obrazovek nebo inicializaci GUI.
 */
public class ChybaAplikaceException extends Exception {
    public ChybaAplikaceException(String zprava) {
        super(zprava);
    }
}