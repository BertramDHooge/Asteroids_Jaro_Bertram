package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class doubleType extends Type {

    protected double dble;

    public doubleType(double dble) {
        this.dble = dble;
    }

    public double getDouble() {return this.dble;}
}
