package asteroids.part3.programs.Statements;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Types.setType;

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
        if (this.getProgram().isNotEnoughTimeLeft()) {
            this.getProgram().setNotEnoughTimeLeft(false);
            return;
        }
        Function function = this.getFunction();
        Ship ship = this.getProgram().getShip();
        Type eval = this.getValue().evaluate(ship, function);
        if (eval != null) {
            if (eval instanceof setType && ((setType) eval).getSet().size() == 1) {
                for (Object obj: ((setType) eval).getSet()) {
                    this.getProgram().getExecuteResult().add(obj);
                    System.out.println(obj);
                }
            }
            else {
                this.getProgram().getExecuteResult().add(eval.get());
                System.out.println(eval.get());
            }
        }
        else {
            this.getProgram().getExecuteResult().add(eval);
            System.out.println(eval);
        }
    }
}
