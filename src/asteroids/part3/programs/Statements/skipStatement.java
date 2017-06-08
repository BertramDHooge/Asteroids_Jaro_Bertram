package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Statements.Statement;

/**
 * @author Jaro Deklerck
 */
public class skipStatement extends Statement {

    private SourceLocation sourceLocation;

    public skipStatement(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public void execute() throws ClassNotFoundException {
        if (this.getProgram().getExecuteTime() < 0.2) {
            this.getProgram().setNotEnoughTimeLeft(true);
            this.getProgram().setStopProgram(true);
            return;
        }
        if (this.getFunction() != null) {
            throw new ClassNotFoundException("Used in function bode");
        }
        this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
    }

}
