package asteroids.part3.programs.Expressions;

import java.util.List;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

public class functionCallExpression implements Expression<Type> {
	
	private String functionName;
	private List<Expression<? extends Type>> actualArgs;
	private SourceLocation sourceLocation;

	public functionCallExpression(String functionName, List<Expression<? extends Type>> actualArgs, SourceLocation sourceLocation){
		setFunctionName(functionName);
		setActualArgs(actualArgs);
		setSourceLocation(sourceLocation);
	}

	private void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getFunctionName(){
		return this.functionName;
	}

	private void setActualArgs(List<Expression<? extends Type>> actualArgs) {
		this.actualArgs = actualArgs;
	}
	
	public List<Expression<? extends Type>> getActualArgs(){
		return this.actualArgs;
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
		List<Function> AllFunctions = ship.getProgram().getFunctions();
		for(Function functions: AllFunctions){
			if (function.getFunctionName() == this.getFunctionName())
				return function.execute(this.getActualArgs());
		}
		return null;
	}

}
