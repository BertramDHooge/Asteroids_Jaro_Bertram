package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

public class doubleLiteralExpression implements Expression<Type> {
	
	private double value;
	private SourceLocation location;

	public doubleLiteralExpression(double value, SourceLocation location){
		setValue(value);
		setSourceLocation(location);
	}

	private void setValue(double value) {
		this.value = value;
	}
	
	public double getValue(){
		return this.value;
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
