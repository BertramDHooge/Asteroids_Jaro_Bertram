package asteroids.part3.programs.Statements;

import asteroids.model.Ship;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.doubleType;

/**
 * @author Jaro Deklerck
 */
public class turnStatement extends Statement {

    private Expression<? extends Type> angle;

    public turnStatement(Expression<? extends Type> angle, SourceLocation location) {
        super(location);
        setAngle(angle);
    }

    public Expression<? extends Type> getAngle() {
        return angle;
    }

    public void setAngle(Expression<? extends Type> angle) {
        this.angle = angle;
    }

    @Override
    public void execute() throws ClassNotFoundException {
        Function func = this.getFunction();
        Ship ship = this.getProgram().getShip();
        ship.turn(((doubleType)this.getAngle().evaluate(ship, func)).getDouble());
    }
}
