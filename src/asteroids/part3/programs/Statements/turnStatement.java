package asteroids.part3.programs.Statements;

import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class turnStatement extends Statement {

    private Expression<? extends Type> angle;
    private SourceLocation location;

    public turnStatement(Expression<? extends Type> angle, SourceLocation location) {
        setAngle(angle);
        setLocation(location);
    }

    public Expression<? extends Type> getAngle() {
        return angle;
    }

    public void setAngle(Expression<? extends Type> angle) {
        this.angle = angle;
    }

    public SourceLocation getLocation() {
        return location;
    }

    public void setLocation(SourceLocation location) {
        this.location = location;
    }
}
