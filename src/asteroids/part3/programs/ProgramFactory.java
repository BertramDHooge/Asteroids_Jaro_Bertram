package asteroids.part3.programs;

import java.util.List;

import asteroids.part3.programs.Expressions.*;
import asteroids.part3.programs.Statements.*;
import asteroids.part3.programs.Statements.Statement;
import asteroids.part3.programs.Statements.assignmentStatement;
import asteroids.part3.programs.Statements.breakStatement;
import asteroids.part3.programs.Statements.ifStatement;
import asteroids.part3.programs.Statements.printStatement;
import asteroids.part3.programs.Statements.returnStatement;
import asteroids.part3.programs.Statements.sequenceStatement;
import asteroids.part3.programs.Statements.whileStatement;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Expressions.anyExpression;
import asteroids.part3.programs.Expressions.asteroidExpression;
import asteroids.part3.programs.Expressions.bulletExpression;
import asteroids.part3.programs.Expressions.changeSignExpression;
import asteroids.part3.programs.Expressions.doubleLiteralExpression;
import asteroids.part3.programs.Expressions.functionCallExpression;
import asteroids.part3.programs.Expressions.getVXExpression;
import asteroids.part3.programs.Expressions.getXExpression;
import asteroids.part3.programs.Expressions.getYExpression;
import asteroids.part3.programs.Expressions.notExpression;
import asteroids.part3.programs.Expressions.nullExpression;
import asteroids.part3.programs.Expressions.planetExpression;
import asteroids.part3.programs.Expressions.planetoidExpression;
import asteroids.part3.programs.Expressions.readParameterExpression;
import asteroids.part3.programs.Expressions.readVariableExpression;
import asteroids.part3.programs.Expressions.selfExpression;
import asteroids.part3.programs.Expressions.shipExpression;

public class ProgramFactory implements IProgramFactory <Expression<? extends Type >, Statement, Function, Program >{
	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Function(functionName, body, sourceLocation);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression<? extends Type> value,SourceLocation sourceLocation) {
		return new assignmentStatement(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression<? extends Type> condition, Statement body, SourceLocation sourceLocation) {
		return new whileStatement(condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new breakStatement(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression<? extends Type> value, SourceLocation sourceLocation) {
		return new returnStatement(value, sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression<? extends Type> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		return new ifStatement(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression<? extends Type> value, SourceLocation sourceLocation) {
		return new printStatement(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		return new sequenceStatement(statements, sourceLocation);
	}

	@Override
	public Expression<? extends Type> createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new readVariableExpression(variableName, sourceLocation);
	}

	@Override
	public Expression<? extends Type> createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		return new readParameterExpression(parameterName, sourceLocation);
	}

	@Override
	public Expression<? extends Type> createFunctionCallExpression(String functionName, List<Expression<? extends Type>> actualArgs, SourceLocation sourceLocation) {
		return new functionCallExpression(functionName, actualArgs, sourceLocation);
	}

	@Override
	public Expression<? extends Type> createChangeSignExpression(Expression<? extends Type> expression, SourceLocation sourceLocation) {
		return new changeSignExpression(expression, sourceLocation);
	}

	@Override
	public Expression<? extends Type> createNotExpression(Expression<? extends Type> expression, SourceLocation sourceLocation) {
		return new notExpression(expression, sourceLocation);
	}

	@Override
	public Expression<? extends Type> createDoubleLiteralExpression(double value, SourceLocation location) {
		return new doubleLiteralExpression(value, location);
	}

	@Override
	public Expression<? extends Type> createNullExpression(SourceLocation location) {
		return new nullExpression(location);
	}

	@Override
	public Expression<? extends Type> createSelfExpression(SourceLocation location) {
		return new selfExpression(location);
	}

	@Override
	public Expression<? extends Type> createShipExpression(SourceLocation location) {
		return new shipExpression(location);
	}

	@Override
	public Expression<? extends Type> createAsteroidExpression(SourceLocation location) {
		return new asteroidExpression(location);
	}

	@Override
	public Expression<? extends Type> createPlanetoidExpression(SourceLocation location) {
		return new planetoidExpression(location);
	}

	@Override
	public Expression<? extends Type> createBulletExpression(SourceLocation location) {
		return new bulletExpression(location);
	}

	@Override
	public Expression<? extends Type> createPlanetExpression(SourceLocation location) {
		return new planetExpression(location);
	}

	@Override
	public Expression<? extends Type> createAnyExpression(SourceLocation location) {
		return new anyExpression(location);
	}

	@Override
	public Expression<? extends Type> createGetXExpression(Expression<? extends Type> e, SourceLocation location) {
		return new getXExpression(e, location);
	}

	@Override
	public Expression<? extends Type> createGetYExpression(Expression<? extends Type> e, SourceLocation location) {
		return new getYExpression(e, location);
	}

	@Override
	public Expression<? extends Type> createGetVXExpression(Expression<? extends Type> e, SourceLocation location) {
		return new getVXExpression(e, location);
	}

	@Override
	public Expression<? extends Type> createGetVYExpression(Expression<? extends Type> e, SourceLocation location) {
		return new getVYExpression(e, location);
	}

	@Override
	public Expression<? extends Type> createGetRadiusExpression(Expression<? extends Type> e, SourceLocation location) {
		return new getRadiusExpression(e, location);
	}

	@Override
	public Expression<? extends Type> createLessThanExpression(Expression<? extends Type> e1,
			Expression<? extends Type> e2, SourceLocation location) {
		return new lessThanExpression(e1, e2, location);
	}

	@Override
	public Expression<? extends Type> createEqualityExpression(Expression<? extends Type> e1,
			Expression<? extends Type> e2, SourceLocation location) {
		return new equalityExpression(e1, e2, location);
	}

	@Override
	public Expression<? extends Type> createAdditionExpression(Expression<? extends Type> e1,
			Expression<? extends Type> e2, SourceLocation location) {
		return new additionExpression(e1, e2, location);
	}

	@Override
	public Expression<? extends Type> createMultiplicationExpression(Expression<? extends Type> e1,
			Expression<? extends Type> e2, SourceLocation location) {
		return new multiplicationExpression(e1, e2, location);
	}

	@Override
	public Expression<? extends Type> createSqrtExpression(Expression<? extends Type> e, SourceLocation location) {
		return new sqrtExpression(e, location);
	}

	@Override
	public Expression<? extends Type> createGetDirectionExpression(SourceLocation location) {
		return new getDirectionExpression(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new thrustOnStatement(location);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new thrustOffStatement(location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new fireStatement(location);
	}

	@Override
	public Statement createTurnStatement(Expression<? extends Type> angle, SourceLocation location) {
		return new turnStatement(angle, location);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new skipStatement(location);
	}

}
