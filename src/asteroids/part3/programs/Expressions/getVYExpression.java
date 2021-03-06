package asteroids.part3.programs.Expressions;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.doubleType;

public class getVYExpression implements Expression<Type> {

    private Expression<? extends Type> e;
    private SourceLocation location;

    public getVYExpression(Expression<? extends Type> e, SourceLocation location) {
        setE(e);
        setSourceLocation(location);
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
    public Type evaluate(Ship ship, Function function) throws ClassNotFoundException {
        return new doubleType(((Entity)this.getE().evaluate(ship, function).get()).getVelocity()[1]);
    }
}
