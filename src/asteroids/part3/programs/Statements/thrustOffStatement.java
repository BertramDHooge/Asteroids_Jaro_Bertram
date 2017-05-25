package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;

/**
 * @author Jaro Deklerck
 */
public class thrustOffStatement extends Statement {

    public thrustOffStatement(SourceLocation location) {
        super(location);
    }

    @Override
    public void execute() throws ClassNotFoundException {
        this.getProgram().getShip().thrustOff();
    }

}
