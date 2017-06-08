package asteroids.part3.programs.Statements;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Types.booleanType;

public class ifStatement extends Statement {
	private Expression<? extends Type> condition;
	private Statement ifBody;
	private Statement elseBody;

	public ifStatement(Expression<? extends Type> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation){
        super(sourceLocation);
        setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}

	private void setCondition(Expression<? extends Type> condition) {
		this.condition = condition;
	}
	
	public Expression<? extends Type> getCondition(){
		return this.condition;
	}
	
	private void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}
	
	public Statement getIfBody(){
		return this.ifBody;
	}
	
	private void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}
	
	public Statement getElseBody(){
		return this.elseBody;
	}


    @Override
    public void execute() throws ClassNotFoundException {
        if (this.getProgram().isNotEnoughTimeLeft()) {
            return;
        }
        Function funct = this.getFunction();
        Ship ship = this.getProgram().getShip();
        if (((booleanType) this.getCondition().evaluate(ship, funct)).getBoolean()) {
            this.getIfBody().setProgram(this.getProgram());
            this.getIfBody().setFunction(this.getFunction());
            this.getIfBody().execute();
        }
        else if (this.getElseBody() != null) {
            this.getElseBody().setProgram(this.getProgram());
            this.getElseBody().setFunction(this.getFunction());
            this.getElseBody().execute();
        }
    }
}
