package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;

public class breakStatement extends Statement {

	public breakStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

    @Override
    public void execute() throws ClassNotFoundException {
        if (this.getProgram().isNotEnoughTimeLeft()) {
            return;
        }
	    if (!this.getProgram().isInWhile()) {
	        throw new ClassNotFoundException("Not in while");
        }
        this.getProgram().setBreaking(true);
    }

}
