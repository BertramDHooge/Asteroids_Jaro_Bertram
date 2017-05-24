package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

public class readParameterExpression implements Expression<Type> {
	
	private String parameterName;
	private SourceLocation sourceLocation;

	public readParameterExpression(String parameterName, SourceLocation sourceLocation){
		setParameterName(parameterName);
		setSourceLocation(sourceLocation);
	}

	private void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	public String getParameterName(){
		return this.parameterName;
	}

	@Override
	public void setSourceLocation(SourceLocation sourceLocation) {
		this.setSourceLocation(sourceLocation);
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
