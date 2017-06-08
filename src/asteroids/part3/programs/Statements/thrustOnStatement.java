package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;

/**
 * @author Jaro Deklerck
 */
public class thrustOnStatement extends Statement {

    public thrustOnStatement(SourceLocation location) {
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
            this.getProgram().getShip().thrustOn();
        }
        else {
            throw new ClassNotFoundException("Thrust in function body");
        }
        this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
    }

}
