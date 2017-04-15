package asteroids.model;

/**
 * @author Jaro Deklerck
 */
@SuppressWarnings("serial")
public class WorldException extends Exception {

    public WorldException(String message) {
        super(message);
    }

    public WorldException(Throwable nested) {
        super(nested);
    }
}
