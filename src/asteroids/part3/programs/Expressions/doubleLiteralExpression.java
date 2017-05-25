package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.ProgramException;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.doubleType;

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
	public Type evaluate(Ship ship, Function function) throws ClassNotFoundException, ProgramException {
		return new doubleType(this.value);
	}

}
