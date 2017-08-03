package asteroids.part3.programs.Statements;

import asteroids.model.Ship;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class assertStatement extends Statement {

    private Expression<? extends Type> bool;
    private SourceLocation sourceLocation;

    public assertStatement(Expression<? extends Type> bool , SourceLocation sourceLocation) {
        super(sourceLocation);
        this.setBool(bool);

    }

    public Expression<? extends Type> getBool() {
        return bool;
    }

    public void setBool(Expression<? extends Type> bool) {
        this.bool = bool;
    }

    @Override
    public void execute() throws ClassNotFoundException {
        if (this.getProgram().isNotEnoughTimeLeft() || !this.getProgram().isAssertCheck()) {
            return;
        }
        Function funct = this.getFunction();
        Ship ship = this.getProgram().getShip();
        if (!(boolean)bool.evaluate(ship, funct).get()) {
            this.getProgram().setNotEnoughTimeLeft(true);
            this.getProgram().setStopProgram(true);
            throw new ClassNotFoundException("Assertion failed");
        }
    }

}
