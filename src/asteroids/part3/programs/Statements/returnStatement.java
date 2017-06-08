package asteroids.part3.programs.Statements;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;

public class returnStatement extends Statement {
	private Expression<? extends Type> value;

	public returnStatement(Expression<? extends Type> value, SourceLocation sourceLocation){
        super(sourceLocation);
	    setValue(value);
	}

	private void setValue(Expression<? extends Type> value) {
		this.value = value;
	}
	
	public Expression<? extends Type> getValue(){
		return this.value;
	}

    @Override
    public void execute() throws ClassNotFoundException {
        if (this.getProgram().isNotEnoughTimeLeft()) {
            return;
        }
        Function func = this.getFunction();
        Ship ship = this.getProgram().getShip();
        func.setReturnValue(this.getValue().evaluate(ship, func));
        func.setReturnReached(true);
    }
}
