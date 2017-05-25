package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.ProgramException;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.booleanType;
import asteroids.part3.programs.Types.doubleType;

public class notExpression implements Expression<Type> {
	
	private Expression<? extends Type> expression;
	private SourceLocation sourceLocation;

	public notExpression(Expression<? extends Type> expression, SourceLocation sourceLocation){
		setExpression(expression);
	}

	private void setExpression(Expression<? extends Type> expression) {
		this.expression = expression;
	}

	@Override
	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	@Override
	public SourceLocation getSourceLocation(SourceLocation sourceLocation) {
		return this.sourceLocation;
	}

	@Override
	public Type evaluate(Ship ship, Function function) throws ClassNotFoundException, ProgramException {
		if (((booleanType)this.expression).getBoolean() == true){
			return new booleanType(false);
		}
		else {
			return new booleanType(true);
		}
	}

}
