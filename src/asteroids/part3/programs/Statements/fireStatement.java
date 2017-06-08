package asteroids.part3.programs.Statements;

import asteroids.model.EntityException;
import asteroids.model.WorldException;
import asteroids.part3.programs.SourceLocation;

/**
 * @author Jaro Deklerck
 */
public class fireStatement extends Statement {

    public fireStatement(SourceLocation location) {
        super(location);
    }

    @Override
    public void execute() throws ClassNotFoundException {
        if (this.getProgram().getExecuteTime() < 0.2) {
            this.getProgram().setNotEnoughTimeLeft(true);
            this.getProgram().setStopProgram(true);
            return;
        }
        try {
            if (this.getFunction() == null) {
                this.getProgram().getShip().fireBullet();
            }
            else {
                throw new ClassNotFoundException("Used in function body");
            }
            this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
        } catch (EntityException | WorldException e) {
            throw new ClassNotFoundException("Exception while firing bullet");
        }
    }

}
