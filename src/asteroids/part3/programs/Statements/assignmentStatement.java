package asteroids.part3.programs.Statements;

import asteroids.part3.programs.Expressions.*;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

import java.util.Objects;

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
	    if (this.getProgram().isNotEnoughTimeLeft()) {
	        return;
        }
        if (this.getValue() instanceof selfExpression) {
	        throw new ClassNotFoundException("Improper type");
        }
	    for (Function function: this.getProgram().getFunctions()) {
            if (this.getFunction() == null && Objects.equals(function.getFunctionName(), this.getVariableName())){
                throw new ClassNotFoundException("Name in use for function");
            }
        }
	    Expression<? extends Type> exp = this.getValue();
	    if (this.getFunction() == null) {
	        if (exp instanceof additionExpression || exp instanceof changeSignExpression || exp instanceof multiplicationExpression || exp instanceof sqrtExpression || exp instanceof functionCallExpression || exp instanceof readParameterExpression) {
                exp = new doubleLiteralExpression((double)exp.evaluate(this.getProgram().getShip(), this.getFunction()).get(), sourceLocation);
                this.getProgram().getVariables().put(this.getVariableName(), exp);
            }
            else {
                this.getProgram().getVariables().put(this.getVariableName(), exp);
            }
        }
        else {
            if (exp instanceof additionExpression || exp instanceof changeSignExpression || exp instanceof multiplicationExpression || exp instanceof sqrtExpression || exp instanceof functionCallExpression || exp instanceof readParameterExpression) {
                exp = new doubleLiteralExpression((double)exp.evaluate(this.getProgram().getShip(), this.getFunction()).get(), sourceLocation);
                this.getProgram().getVariables().put("local_"+this.getVariableName()+this.getProgram().getRecursion(), exp);
            }
            else {
                this.getProgram().getVariables().put("local_"+this.getVariableName()+this.getProgram().getRecursion(), exp);
            }
        }
    }
}
