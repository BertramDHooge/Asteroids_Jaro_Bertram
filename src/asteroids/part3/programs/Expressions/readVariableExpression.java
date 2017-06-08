package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.doubleType;
import asteroids.part3.programs.Types.stringType;

public class readVariableExpression implements Expression<Type> {

    private String variableName;
    private SourceLocation sourceLocation;

    public readVariableExpression(String variableName, SourceLocation sourceLocation) {
        setVariableName(variableName);
        setSourceLocation(sourceLocation);
    }

    private void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return this.variableName;
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
        if (function == null) {
            return ship.getProgram().getVariables().get(this.getVariableName()).evaluate(ship, function);
        }
        else {
            if (ship.getProgram().getVariables().containsKey("local_"+this.getVariableName()+ship.getProgram().getRecursion())) {
                return ship.getProgram().getVariables().get("local_" + this.getVariableName()+ship.getProgram().getRecursion()).evaluate(ship, function);
            }
            else {
                return ship.getProgram().getVariables().get(this.getVariableName()).evaluate(ship, function);
            }
        }
    }
}
