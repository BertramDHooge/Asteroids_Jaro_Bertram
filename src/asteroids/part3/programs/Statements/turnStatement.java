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
        if (this.getProgram().getExecuteTime() < 0.2) {
            this.getProgram().setNotEnoughTimeLeft(true);
            this.getProgram().setStopProgram(true);
            return;
        }
        if (this.getFunction() !=  null) {
            throw new ClassNotFoundException("Used in function body");
        }
        Function func = this.getFunction();
        Ship ship = this.getProgram().getShip();
        double angle = ((doubleType)this.getAngle().evaluate(ship, func)).getDouble();
        if (angle > -2*Math.PI && angle < 2*Math.PI) {
            for (int i = 0; i < 5; i++) {
                ship.turn(angle * 0.2);
            }
        }
        else {
            throw new ClassNotFoundException("Invalid Angle");
        }
        this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
    }
}
