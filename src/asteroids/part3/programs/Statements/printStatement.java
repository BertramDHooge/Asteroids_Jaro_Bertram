package asteroids.part3.programs.Statements;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;

public class printStatement extends Statement {
	private SourceLocation sourceLocation;
	private Expression<? extends Type> value;

	public printStatement(Expression<? extends Type> value, SourceLocation sourceLocation){
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
        Function function = this.getFunction();
        Ship ship = this.getProgram().getShip();
        this.getProgram().getExecuteResult().add(this.getValue().evaluate(ship, function).get());
        System.out.println(this.getValue().evaluate(ship, function).get());
    }
}
