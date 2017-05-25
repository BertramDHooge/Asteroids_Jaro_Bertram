package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

public interface Expression<T> {
    public void setSourceLocation(SourceLocation sourceLocation);

    public SourceLocation getSourceLocation(SourceLocation sourceLocation);

    public abstract Type evaluate(Ship ship, Function function) throws ClassNotFoundException;

}
 