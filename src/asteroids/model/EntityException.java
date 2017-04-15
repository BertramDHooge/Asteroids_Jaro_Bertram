package asteroids.model;

@SuppressWarnings("serial")
public class EntityException extends Exception {

	  public EntityException(String message) {
	    super(message);
	  }

	  public EntityException(Throwable nested) {
	    super(nested);
	  }
	

}
