package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

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
	public Type evaluate(Ship ship) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
