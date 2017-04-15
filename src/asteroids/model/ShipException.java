package asteroids.model;

@SuppressWarnings("serial")
public class ShipException extends Exception {

	  public ShipException(String message) {
	    super(message);
	  }

	  public ShipException(Throwable nested) {
	    super(nested);
	  }
	

}
