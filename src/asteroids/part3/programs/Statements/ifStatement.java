package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;

public class ifStatement extends Statement {
	private Expression<? extends Type> condition;
	private Statement ifBody;
	private Statement elseBody;
	private SourceLocation sourceLocation;
	
	
	

	public ifStatement(Expression<? extends Type> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation){
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
		setSourceLocation(sourceLocation);
	}

	private void setCondition(Expression<? extends Type> condition) {
		this.condition = condition;
	}
	
	public Expression<? extends Type> getCondition(){
		return this.condition;
	}
	
	private void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}
	
	public Statement getIfBody(){
		return this.ifBody;
	}
	
	private void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}
	
	public Statement getElseBody(){
		return this.elseBody;
	}
	
	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}

	

	
	
}
