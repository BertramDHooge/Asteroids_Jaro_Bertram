package asteroids.part3.programs;

@SuppressWarnings("serial")
public class ProgramException extends Exception{


	public ProgramException(String message) {
	    super(message);
	  }

	  public ProgramException(Throwable nested) {
	    super(nested);
	  }
	
}
