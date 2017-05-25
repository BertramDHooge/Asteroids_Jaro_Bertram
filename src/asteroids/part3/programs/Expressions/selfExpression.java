package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.objectType;

public class selfExpression implements Expression<Type> {

    private SourceLocation location;

    public selfExpression(SourceLocation location) {
        setSourceLocation(location);
    }

    @Override
    public void setSourceLocation(SourceLocation location) {
        this.location = location;
    }

    @Override
    public SourceLocation getSourceLocation(SourceLocation location) {
        return this.location;
    }

    @Override
    public Type evaluate(Ship ship, Function function) throws ClassNotFoundException {
        return new objectType(ship);
    }
}
