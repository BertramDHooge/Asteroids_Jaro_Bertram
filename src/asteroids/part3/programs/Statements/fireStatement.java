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
        try {
            this.getProgram().getShip().fireBullet();
        } catch (EntityException | WorldException e) {
            throw new ClassNotFoundException("Exception while firing bullet");
        }
    }

}
