package asteroids.part3.programs.Statements;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Types.booleanType;

public class whileStatement extends Statement {
	
	private Statement body;
	private Expression<? extends Type> condition;

	public whileStatement(Expression<? extends Type> condition, Statement body, SourceLocation sourceLocation) {
        super(sourceLocation);
	    setCondition(condition);
		setBody(body);
	}

	private void setBody(Statement body) {
		this.body = body;
	}
	
	public Statement getBody(){
		return this.body;
	}

	private void setCondition(Expression<? extends Type> condition) {
		this.condition = condition;
	}
	
	public Expression<?  extends Type> getCondition(){
		return this.condition;
	}

    @Override
    public void execute() throws ClassNotFoundException {
        Function func = this.getFunction();
        Ship ship = this.getProgram().getShip();

        while (((booleanType)this.getCondition().evaluate(ship, func)).getBoolean() && !this.getProgram().getBreaking()) {
//            this.getBody().setProgram(this.getProgram());
//            this.getBody().setFunction(this.getFunction());
            this.getBody().execute();
        }
        if (this.getProgram().getBreaking()) {
            this.getProgram().setBreaking(false);
        }
    }
}
