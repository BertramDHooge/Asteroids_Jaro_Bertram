package asteroids.part3.programs.Statements;

import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Expressions.doubleLiteralExpression;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

public class assignmentStatement extends Statement{

	private Expression<? extends Type> value;
	private String variableName;

	public assignmentStatement(String variableName, Expression<? extends Type> value, SourceLocation sourceLocation) {
        super(sourceLocation);
        setVariableName(variableName);
		setValue(value);
	}

	private void setValue(Expression<? extends Type> value) {
		this.value = value;
	}
	
	public Expression<? extends Type> getValue(){
		return this.value;
	}

	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public String getVariableName(){
		return this.variableName;
	}

	@Override
    public void execute() throws ClassNotFoundException{
	    if (this.getFunction() == null) {
	        if (this.getValue() instanceof doubleLiteralExpression) {
                this.getProgram().getVariables().put(this.getVariableName(), this.getValue());
            }
            else {
	            this.setValue(new doubleLiteralExpression((double)this.getValue().evaluate(this.getProgram().getShip(), this.getFunction()).get(), sourceLocation));
                this.getProgram().getVariables().put(this.getVariableName(), this.getValue());
            }
        }
        else {
            if (this.getValue() instanceof doubleLiteralExpression) {
                this.getProgram().getVariables().put("local_"+this.getVariableName(), this.getValue());
            }
            else {
                this.setValue(new doubleLiteralExpression((double)this.getValue().evaluate(this.getProgram().getShip(), this.getFunction()).get(), sourceLocation));
                this.getProgram().getVariables().put("local_"+this.getVariableName(), this.getValue());
            }
        }
    }
}
