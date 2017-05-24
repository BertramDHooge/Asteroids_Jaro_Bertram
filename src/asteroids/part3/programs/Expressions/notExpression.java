package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

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
	public Type evaluate(Ship ship) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
