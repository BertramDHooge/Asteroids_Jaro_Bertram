package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.doubleType;

public class changeSignExpression implements Expression<Type> {
	
	private Expression<? extends Type> expression;
	private SourceLocation sourceLocation;

	public changeSignExpression(Expression<? extends Type> expression, SourceLocation sourceLocation){
		setExpression(expression);
		setSourceLocation(sourceLocation);
	}

	private void setExpression(Expression<? extends Type> expression) {
		this.expression = expression;
	}
	
	public Expression<? extends Type> getExpression(){
		return this.expression;
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
	public Type evaluate(Ship ship, Function function) throws ClassNotFoundException {
		return new doubleType(-1.0 * ((doubleType)this.expression).getDouble());
	}

}
