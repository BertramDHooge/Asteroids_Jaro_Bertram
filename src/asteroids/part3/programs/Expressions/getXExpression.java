package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

public class getXExpression implements Expression<Type> {
	
	private Expression<? extends Type> e;
	private SourceLocation location;

	public getXExpression(Expression<? extends Type> e, SourceLocation location){
		setExpression(e);
		setSourceLocation(location);
	}

	private void setExpression(Expression<? extends Type> e) {
		this.e = e;
	}
	
	public Expression<? extends Type> getExpression(){
		return this.e;
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
	public Type evaluate(Ship ship) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
