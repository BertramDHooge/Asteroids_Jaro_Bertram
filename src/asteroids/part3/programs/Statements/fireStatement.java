package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;

/**
 * @author Jaro Deklerck
 */
public class fireStatement extends Statement {

    private SourceLocation location;

    public fireStatement(SourceLocation location) {
        setLocation(location);
    }

    public SourceLocation getLocation() {
        return location;
    }

    public void setLocation(SourceLocation location) {
        this.location = location;
    }
}
