package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;

public class printStatement extends Statement {
	private SourceLocation sourceLocation;
	private Expression<? extends Type> value;

	public printStatement(Expression<? extends Type> value, SourceLocation sourceLocation){
		setValue(value);
		setSourceLocation(sourceLocation);
	}

	private void setValue(Expression<? extends Type> value) {
		this.value = value;
	}
	
	public Expression<? extends Type> getValue(){
		return this.value;
	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
}
