package asteroids.part3.programs.Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.stringType;

public class readParameterExpression implements Expression<Type> {

    private String parameterName;
    private SourceLocation sourceLocation;

    public readParameterExpression(String parameterName, SourceLocation sourceLocation) {
        setParameterName(parameterName);
        setSourceLocation(sourceLocation);
    }

    private void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public void setSourceLocation(SourceLocation sourceLocation) {
        this.setSourceLocation(sourceLocation);
    }

    public SourceLocation getSourceLocation(SourceLocation sourceLocation) {
        return this.sourceLocation;
    }

    @Override
    public Type evaluate(Ship ship, Function function) throws ClassNotFoundException {
        return new stringType(this.getParameterName());
    }

}
