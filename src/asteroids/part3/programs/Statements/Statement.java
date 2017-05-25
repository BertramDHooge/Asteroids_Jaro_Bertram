package asteroids.part3.programs.Statements;

import asteroids.model.Program;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;

public abstract class Statement {

	private Program program;
	private Function function = null;
	public SourceLocation sourceLocation;

	public Statement(SourceLocation sourceLocation) {
	    setSourceLocation(sourceLocation);
    }

	public void setProgram(Program program) {
		this.program = program;
	}

    public Program getProgram() {
        return program;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public abstract void execute() throws ClassNotFoundException;

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
