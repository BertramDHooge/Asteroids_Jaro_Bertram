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

    }

}
