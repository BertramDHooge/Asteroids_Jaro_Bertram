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
        if (this.getProgram().getExecuteTime() < 0.2) {
            this.getProgram().setNotEnoughTimeLeft(true);
            this.getProgram().setStopProgram(true);
            return;
        }
        if (this.getFunction() == null) {
            this.getProgram().getShip().thrustOff();
        }
        else {
            throw new ClassNotFoundException("Thrust in function body");
        }
        this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
    }

}
