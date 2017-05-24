package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class stringType extends Type {

    protected String str;

    public stringType(String string) {
        this.str = string;
    }

    public String getString() {return this.str;}
}
