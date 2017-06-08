package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;

import java.util.List;
import java.util.Objects;

public class functionCallExpression implements Expression<Type> {

    private String functionName;
    private List<Expression<? extends Type>> actualArgs;
    private SourceLocation sourceLocation;

    public functionCallExpression(String functionName, List<Expression<? extends Type>> actualArgs, SourceLocation sourceLocation) {
        setFunctionName(functionName);
        setActualArgs(actualArgs);
        setSourceLocation(sourceLocation);
    }

    private void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    private void setActualArgs(List<Expression<? extends Type>> actualArgs) {
        this.actualArgs = actualArgs;
    }

    public List<Expression<? extends Type>> getActualArgs() {
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
        List<Function> functions = ship.getProgram().getFunctions();
        for(Function func: functions) {
            if (Objects.equals(func.getFunctionName(), this.getFunctionName())) {
                func.getBody().setFunction(func);
                func.getBody().setProgram(ship.getProgram());
                func.setProgram(ship.getProgram());
                ship.getProgram().setRecursion(ship.getProgram().getRecursion() + 1);
                Type exec = func.execute(this.getActualArgs());
                ship.getProgram().setRecursion(ship.getProgram().getRecursion() - 1);
                return exec;
            }
        }
        throw new ClassNotFoundException("No function found");
    }

}
