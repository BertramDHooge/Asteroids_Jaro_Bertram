package asteroids.part3.programs.Statements;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class sequenceStatement extends Statement {
	
	private List<Statement> statements;
	private SourceLocation sourceLocation;
	

	public sequenceStatement(List<Statement> statements, SourceLocation sourceLocation){
		setStatements(statements);
		setSourceLocation(sourceLocation);
	}

	private void setStatements(List<Statement> statements) {
		this.statements = statements; 
	}
	
	public List<Statement> getStatements(){
		return this.statements;
	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}

}
