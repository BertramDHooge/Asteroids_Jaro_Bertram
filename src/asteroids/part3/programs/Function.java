package asteroids.part3.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Statements.Statement;

public class Function {


	private SourceLocation sourceLocation;
	private Statement body;
	private String functionName;
	private Program program;
	private Type parameter;

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
	
	public void setParameters(Type parameter){
		this.parameter = parameter;
	}

	public Type getParameters() {
		return this.parameter;
	}

	public Type execute(List<Expression<? extends Type>> actualArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
