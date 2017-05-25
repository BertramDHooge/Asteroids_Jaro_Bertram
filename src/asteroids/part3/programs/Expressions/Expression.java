package asteroids.part3.programs.Expressions;

import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.model.Ship;

public interface Expression<T>{
	public void setSourceLocation(SourceLocation sourceLocation);
	public SourceLocation getSourceLocation(SourceLocation sourceLocation);
	public abstract Type evaluate(Ship ship, Function function) throws ClassNotFoundException;

}
 