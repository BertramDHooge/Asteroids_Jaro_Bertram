package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.doubleType;

/**
 * @author Jaro Deklerck
 */
public class conditionalExpression implements Expression<Type> {

    private Expression<? extends Type> bool;
    private Expression<? extends Type> then;
    private Expression<? extends Type> elseExp;
    private SourceLocation sourceLocation;

    public conditionalExpression(Expression<? extends Type> bool, Expression<? extends Type> then, Expression<? extends Type> elseExp, SourceLocation sourceLocation) {
        setBool(bool);
        setThen(then);
        setElseExp(elseExp);
        setSourceLocation(sourceLocation);
    }

    private void setBool(Expression<? extends Type> bool) {
        this.bool = bool;
    }

    public Expression<? extends Type> getBool() {
        return this.bool;
    }

    public Expression<? extends Type> getThen() {
        return then;
    }

    public void setThen(Expression<? extends Type> then) {
        this.then = then;
    }

    public Expression<? extends Type> getElseExp() {
        return elseExp;
    }

    public void setElseExp(Expression<? extends Type> elseExp) {
        this.elseExp = elseExp;
    }

    @Override
    public void setSourceLocation(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    @Override
    public SourceLocation getSourceLocation(SourceLocation sourceLocation) {
        return this.sourceLocation;
    }

    @Override
    public Type evaluate(Ship ship, Function function) throws ClassNotFoundException {
        if ((boolean)getBool().evaluate(ship, function).get()) {
            return getThen().evaluate(ship, function);
        }
        else {
            return getElseExp().evaluate(ship, function);
        }
    }
}
