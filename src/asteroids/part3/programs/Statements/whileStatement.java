package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;

public class whileStatement extends Statement {
	
	private SourceLocation sourceLocation;
	private Statement body;
	private Expression<? extends Type> condition;

	public whileStatement(Expression<? extends Type> condition, Statement body, SourceLocation sourceLocation) {
		setCondition(condition);
		setBody(body);
		setSourceLocation(sourceLocation);
	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;		
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}

	private void setBody(Statement body) {
		this.body = body;
	}
	
	public Statement getBody(){
		return this.body;
	}

	private void setCondition(Expression<? extends Type> condition) {
		this.condition = condition;
	}
	
	public Expression<?  extends Type> getCondition(){
		return this.condition;
	}
}
