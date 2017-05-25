package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;

public class breakStatement extends Statement {

	public breakStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

    @Override
    public void execute() throws ClassNotFoundException {
        this.getProgram().setBreaking(true);
    }

}
