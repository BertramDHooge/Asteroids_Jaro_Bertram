package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;

public class assignmentStatement extends Statement{

	private SourceLocation sourceLocation;
	private Expression<? extends Type> value;
	private String variableName;

	public assignmentStatement(String variableName, Expression<? extends Type> value, SourceLocation sourceLocation) {
		setVariableName(variableName);
		setValue(value);
		setSourceLocation(sourceLocation);
	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}

	private void setValue(Expression<? extends Type> value) {
		this.value = value;
	}
	
	public Expression<? extends Type> value(){
		return this.value;
	}

	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public String getVariableName(){
		return this.variableName;
	}

	@Override
    public void execute() {

    }
}
