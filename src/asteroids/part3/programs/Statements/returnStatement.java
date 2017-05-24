package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;

public class returnStatement extends Statement {
	private SourceLocation sourceLocation;
	private Expression<? extends Type> value;

	public returnStatement(Expression<? extends Type> value, SourceLocation sourceLocation){
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
	
	public Expression<? extends Type> getValue(){
		return this.value;
	}
}
