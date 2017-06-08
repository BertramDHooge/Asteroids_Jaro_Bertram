package asteroids.part3.programs;

import asteroids.model.Program;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Expressions.doubleLiteralExpression;
import asteroids.part3.programs.Statements.Statement;
import asteroids.part3.programs.Types.doubleType;

import java.util.HashMap;
import java.util.List;

public class Function {


	private SourceLocation sourceLocation;
	private Statement body;
	private String functionName;
	private Program program;
	private HashMap<String, Expression<? extends Type>> parameters = new HashMap<>();
	private Type returnValue;
	private boolean returnReached;

	public Function(String functionName, Statement body, SourceLocation sourceLocation) {
		setFunctionName(functionName);
		setBody(body);
		setSourceLocation(sourceLocation);
	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
		
	}
	
	public SourceLocation  getSourceLocation(){
		return this.sourceLocation;		
	}

	private void setBody(Statement body) {
		this.body = body;
		
	}

	public Statement getBody(){
		return this.body;
	}
	
	private void setFunctionName(String functionName) {
		this.functionName = functionName;
		
	}
	
	public String getFunctionName(){
		return this.functionName;
	}

	public void setProgram(Program program) {
		this.program = program;
		
	}
	
	public Program getProgram(){
		return this.program;
	}

	public Type execute(List<Expression<? extends Type>> actualArgs) throws ClassNotFoundException {
        for(int i = 1; i <= actualArgs.size(); i++){
            Expression<? extends Type> arg = (Expression<? extends Type>)actualArgs.toArray()[i-1];
            if (arg instanceof doubleLiteralExpression) {
                this.getParameters().put("$" + Integer.toString(i), arg);
            }
            else {
                this.getProgram().setRecursion(this.getProgram().getRecursion() - 1);
                Expression<? extends Type> eval = new doubleLiteralExpression(((doubleType)arg.evaluate(this.getProgram().getShip(), this)).getDouble(), this.getSourceLocation());
                this.getProgram().setRecursion(this.getProgram().getRecursion() + 1);
                this.getParameters().put("$" + Integer.toString(i), eval);
            }
        }
        while(!this.isReturnReached()){
            this.getBody().setFunction(this);
            this.getBody().execute();
            if (!this.isReturnReached()) {
                throw new ClassNotFoundException("Infinite loop");
            }
        }
        this.setReturnReached(false);
        return this.getReturnValue();
	}

    public Type getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Type returnValue) {
        this.returnValue = returnValue;
    }

    public boolean isReturnReached() {
        return returnReached;
    }

    public void setReturnReached(boolean returnReached) {
        this.returnReached = returnReached;
    }

    public HashMap<String, Expression<? extends Type>> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, Expression<? extends Type>> parameters) {
        this.parameters = parameters;
    }
}
