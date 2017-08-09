package asteroids.model;

import java.util.ArrayList;
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
	private int recursion = 0;
	private boolean inWhile;
	private boolean notEnoughTimeLeft;
	private boolean stopProgram;
	private boolean assertCheck;
	
	/**
	 * creates a program to be loaded onto a ship
	 * @param functions
	 * @param body
	 */
	
	public Program(List<Function> functions, Statement body){
		setFunctions(functions);
		setBody(body);
	}
	
	/**
	 * sets functions 
	 * @param functions
	 */

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
	/**
	 * returns functions
	 * @return
	 */
	
	public List<Function> getFunctions(){
		return this.functions;
	}
	
	/**
	 * sets body
	 * @param body
	 */

	public void setBody(Statement body) {
		this.body = body;
	}
	
	/**
	 * returns body
	 * @return
	 */
	
	public Statement getBody(){
		return this.body;
	}
	
	/**
	 * sets ship
	 * @param ship
	 * @post ...
	 * 		|new ship == ship
	 */

	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	/**
	 * returns ship
	 * @return
	 */
	
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * sets variables
	 * @param variables
	 */
	
	public void setVariables(HashMap<String, Expression<?>> variables){
		this.variables = variables;
	}
	
	/**
	 * returns variables
	 * @return
	 */
	
	public HashMap<String, Expression<?>> getVariables(){
		return this.variables;
	}
	
	/**
	 * sets result of execute
	 * @param executeResult
	 */
	
	public void setExecuteResult(List<Object> executeResult){
		this.executeResult = executeResult;
	}
	
	/**
	 * returns result of execute
	 * @return
	 */
	
	public List<Object> getExecuteResult(){
		return this.executeResult;
	}
	
	/**
	 * executes body
	 * @param duration
	 * @return
	 */

	public List<Object> execute(Double duration, boolean assertCheck) throws ClassNotFoundException {
	    this.setAssertCheck(assertCheck);
	    this.executeResult.clear();
		this.setExecuteTime(this.getExecuteTime()+duration);
		double t = this.getExecuteTime();
		body.setProgram(this);
		this.setNotEnoughTimeLeft(false);
		this.setStopProgram(false);
		body.execute();
		if (this.isStopProgram()) {
		    this.setExecuteTime(t);
		    return null;
        }
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

    public int getRecursion() {
        return recursion;
    }

    public void setRecursion(int recursion) {
        this.recursion = recursion;
    }

    public boolean isInWhile() {
        return inWhile;
    }

    public void setInWhile(boolean inWhile) {
        this.inWhile = inWhile;
    }

    public boolean isNotEnoughTimeLeft() {
        return notEnoughTimeLeft;
    }

    public void setNotEnoughTimeLeft(boolean notEnoughTimeLeft) {
        this.notEnoughTimeLeft = notEnoughTimeLeft;
    }

    public boolean isStopProgram() {
        return stopProgram;
    }

    public void setStopProgram(boolean stopProgram) {
        this.stopProgram = stopProgram;
    }

    public boolean isAssertCheck() {
        return assertCheck;
    }

    public void setAssertCheck(boolean assertCheck) {
        this.assertCheck = assertCheck;
    }
}
