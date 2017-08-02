package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.booleanType;
import asteroids.part3.programs.Types.doubleType;

public class equalityExpression implements Expression<Type> {

    private Expression<? extends Type> e1;
    private Expression<? extends Type> e2;
    private SourceLocation location;

    public equalityExpression(Expression<? extends Type> e1, Expression<? extends Type> e2, SourceLocation location) {
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
    public Type evaluate(Ship ship, Function function) throws ClassNotFoundException {
        if (this.getE1().evaluate(ship, function) == null && this.getE2().evaluate(ship, function) == null) {
            return new booleanType(true);
        }
        else if (this.getE2().evaluate(ship, function) == null && this.getE1().evaluate(ship, function).get() == null) {
            return new booleanType(true);
        }
        else if (this.getE1().evaluate(ship, function) == null && this.getE2().evaluate(ship, function).get() == null) {
            return new booleanType(true);
        }
        else if (this.getE2().evaluate(ship, function) == null && this.getE1().evaluate(ship, function).get() != null) {
            return new booleanType(false);
        }
        else if (this.getE1().evaluate(ship, function) == null && this.getE2().evaluate(ship, function).get() != null) {
            return new booleanType(false);
        }
        if (this.getE1().evaluate(ship, function).get().equals(this.getE2().evaluate(ship, function).get())) {
            return new booleanType(true);
        } else {
            return new booleanType(false);
        }
    }
}
