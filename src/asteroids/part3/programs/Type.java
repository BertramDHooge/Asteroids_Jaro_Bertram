package asteroids.part3.programs;

import asteroids.part3.programs.Types.booleanType;
import asteroids.part3.programs.Types.doubleType;
import asteroids.part3.programs.Types.objectType;
import asteroids.part3.programs.Types.stringType;

public class Type {

    public Object get() {
        if (this instanceof doubleType) {
            return ((doubleType)this).getDouble();
        }
        else if (this instanceof booleanType) {
            return ((booleanType)this).getBoolean();
        }
        else if (this instanceof stringType) {
            return ((stringType)this).getString();
        }
        else if (this instanceof objectType) {
            return ((objectType)this).getObject();
        }
        else {
            return null;
        }
    }

}
