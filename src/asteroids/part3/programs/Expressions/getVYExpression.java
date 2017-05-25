package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Types.doubleType;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.ProgramException;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

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
	public Type evaluate(Ship ship, Function function) throws ClassNotFoundException, ProgramException {
		return new doubleType(ship.getVelocity()[1]);
	}
}
