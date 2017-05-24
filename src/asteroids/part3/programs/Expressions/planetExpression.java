package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

public class planetExpression implements Expression<Type> {
	
	private SourceLocation location;

	public planetExpression(SourceLocation location){
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
	public Type evaluate(Ship ship) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
