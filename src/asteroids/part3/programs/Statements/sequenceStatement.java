package asteroids.part3.programs.Statements;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class sequenceStatement extends Statement {
	
	private List<Statement> statements;

	public sequenceStatement(List<Statement> statements, SourceLocation sourceLocation){
        super(sourceLocation);
	    setStatements(statements);
	}

	private void setStatements(List<Statement> statements) {
		this.statements = statements; 
	}
	
	public List<Statement> getStatements(){
		return this.statements;
	}

    @Override
    public void execute() throws ClassNotFoundException {
        if (this.getProgram().isNotEnoughTimeLeft()) {
            return;
        }
        for (Statement statement: statements) {
            statement.setProgram(this.getProgram());
            statement.setFunction(this.getFunction());
            statement.execute();
        }
    }
}
