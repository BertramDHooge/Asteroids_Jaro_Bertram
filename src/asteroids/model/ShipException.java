package asteroids.model;

@SuppressWarnings("serial")
public class ShipException extends Exception {
	/**
	 * <code>Facade</code> is not allowed to throw exceptions except for <code>ModelException</code>.
	 * 
	 * Do not use ModelException outside of <code>Facade</code>.
	 */
	
	  public ShipException(String message) {
	    super(message);
	  }

	  public ShipException(Throwable nested) {
	    super(nested);
	  }
	

}
