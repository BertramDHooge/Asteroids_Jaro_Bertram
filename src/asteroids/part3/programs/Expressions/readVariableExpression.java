package asteroids.part3.programs.Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.model.Ship;

public class readVariableExpression implements Expression<Type> {
	
	private String variableName;
	private SourceLocation sourceLocation;

	public readVariableExpression(String variableName, SourceLocation sourceLocation){
		setVariableName(variableName);
		setSourceLocation(sourceLocation);
	}

	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public String getVariableName(){
		return this.variableName;
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
