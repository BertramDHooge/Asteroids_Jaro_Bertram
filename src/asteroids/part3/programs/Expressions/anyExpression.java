package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.objectType;
import asteroids.part3.programs.Types.setType;

import java.util.HashSet;
import java.util.Set;

public class anyExpression implements Expression<Type> {

    private SourceLocation location;

    public anyExpression(SourceLocation location) {
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
        return new setType(ship.getWorld().getEntities());
    }

}
