package asteroids.part3.programs;

import java.util.List;

/**
 * @author Jaro Deklerck
 */
public class ProgramFactory<E, S, F, P> implements IProgramFactory<E, S, F, P> {
    @Override
    public P createProgram(List<F> functions, S main) {
        return null;
    }

    @Override
    public F createFunctionDefinition(String functionName, S body, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public S createAssignmentStatement(String variableName, E value, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public S createWhileStatement(E condition, S body, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public S createBreakStatement(SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public S createReturnStatement(E value, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public S createIfStatement(E condition, S ifBody, S elseBody, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public S createPrintStatement(E value, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public S createSequenceStatement(List<S> statements, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public E createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public E createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public E createFunctionCallExpression(String functionName, List<E> actualArgs, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public E createChangeSignExpression(E expression, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public E createNotExpression(E expression, SourceLocation sourceLocation) {
        return null;
    }

    @Override
    public E createDoubleLiteralExpression(double value, SourceLocation location) {
        return null;
    }

    @Override
    public E createNullExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createSelfExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createShipExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createAsteroidExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createPlanetoidExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createBulletExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createPlanetExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createAnyExpression(SourceLocation location) {
        return null;
    }

    @Override
    public E createGetXExpression(E e, SourceLocation location) {
        return null;
    }

    @Override
    public E createGetYExpression(E e, SourceLocation location) {
        return null;
    }

    @Override
    public E createGetVXExpression(E e, SourceLocation location) {
        return null;
    }

    @Override
    public E createGetVYExpression(E e, SourceLocation location) {
        return null;
    }

    @Override
    public E createGetRadiusExpression(E e, SourceLocation location) {
        return null;
    }

    @Override
    public E createLessThanExpression(E e1, E e2, SourceLocation location) {
        return null;
    }

    @Override
    public E createEqualityExpression(E e1, E e2, SourceLocation location) {
        return null;
    }

    @Override
    public E createAdditionExpression(E e1, E e2, SourceLocation location) {
        return null;
    }

    @Override
    public E createMultiplicationExpression(E e1, E e2, SourceLocation location) {
        return null;
    }

    @Override
    public E createSqrtExpression(E e, SourceLocation location) {
        return null;
    }

    @Override
    public E createGetDirectionExpression(SourceLocation location) {
        return null;
    }

    @Override
    public S createThrustOnStatement(SourceLocation location) {
        return null;
    }

    @Override
    public S createThrustOffStatement(SourceLocation location) {
        return null;
    }

    @Override
    public S createFireStatement(SourceLocation location) {
        return null;
    }

    @Override
    public S createTurnStatement(E angle, SourceLocation location) {
        return null;
    }

    @Override
    public S createSkipStatement(SourceLocation location) {
        return null;
    }
}
