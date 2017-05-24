package asteroids.part3.programs;

import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Statements.Statement;

public class Function {


	private SourceLocation sourceLocation;
	private Statement body;
	private String functionName;
	private Program program;

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

}
