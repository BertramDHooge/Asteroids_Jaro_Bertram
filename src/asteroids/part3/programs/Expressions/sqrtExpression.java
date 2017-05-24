package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class sqrtExpression implements Expression<Type> {


    private Expression<? extends Type> e;
    private SourceLocation location;

    public sqrtExpression(Expression<? extends Type> e, SourceLocation location) {
        this.e = e;
        this.location = location;
    }

    public Expression<? extends Type> getE() {
        return e;
    }

    public void setE(Expression<? extends Type> e) {
        this.e = e;
    }

    @Override
    public void setSourceLocation(SourceLocation sourceLocation) {
        this.location = sourceLocation;
    }

    @Override
    public SourceLocation getSourceLocation(SourceLocation sourceLocation) {
        return location;
    }

    @Override
    public Type evaluate(Ship ship) throws ClassNotFoundException {
        return null;
    }

}
