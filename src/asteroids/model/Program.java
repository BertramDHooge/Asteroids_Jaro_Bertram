package asteroids.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import asteroids.part3.programs.Function;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Statements.Statement;

/**
 * @author Jaro Deklerck
 */
public class Program {

	private Ship ship;
	private Double executeTime = 0.0;
	private Statement body;
	private List<Function> functions;
	private HashMap<String, Expression<?>> variables = new HashMap<>();
	private List<Object> executeResult = new LinkedList<>();
	private boolean breaking;
	
	public Program(List<Function> functions, Statement body){
		setFunctions(functions);
		setBody(body);
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
	public List<Function> getFunctions(){
		return this.functions;
	}

	public void setBody(Statement body) {
		this.body = body;
	}
	
	public Statement getBody(){
		return this.body;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip(){
		return this.ship;
	}
	
	public void setVariables(HashMap<String, Expression<?>> variables){
		this.variables = variables;
	}
	
	public HashMap<String, Expression<?>> getVariables(){
		return this.variables;
	}
	
	public void setExecuteResult(List<Object> executeResult){
		this.executeResult = executeResult;
	}
	
	public List<Object> getExecuteResult(){
		return this.executeResult;
	}

	public List<Object> execute(Double duration) throws ClassNotFoundException {
		Double executeTime = this.executeTime + duration;
		body.setProgram(this);
		body.execute();
//		while (executeTime >= 2.0){
//			body.execute();
//		}
		return executeResult;
	}

    public boolean getBreaking() {
        return breaking;
    }

    public void setBreaking(boolean breaking) {
        this.breaking = breaking;
    }

    public double getExecuteTime() {
	    return this.executeTime;
    }

    public void setExecuteTime(Double executeTime) {
        this.executeTime = executeTime;
    }
}
