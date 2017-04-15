package asteroids.model;

@SuppressWarnings("serial")
public class BulletException extends Exception {

    public BulletException(String message) {
        super(message);
    }

    public BulletException(Throwable nested) {
        super(nested);
    }

}