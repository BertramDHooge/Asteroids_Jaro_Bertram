package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class lessThanExpression implements Expression<Type> {

    private Expression<? extends Type> e1;
    private Expression<? extends Type> e2;
    private SourceLocation location;

    public lessThanExpression(Expression<? extends Type> e1, Expression<? extends Type> e2, SourceLocation location) {
        setE1(e1);
        setE2(e2);
        setSourceLocation(location);
    }

    public Expression<? extends Type> getE1() {
        return e1;
    }

    public void setE1(Expression<? extends Type> e1) {
        this.e1 = e1;
    }

    public Expression<? extends Type> getE2() {
        return e2;
    }

    public void setE2(Expression<? extends Type> e2) {
        this.e2 = e2;
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
