package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class booleanType extends Type {

    protected boolean bool;

    public booleanType(Boolean bool) {
        this.bool = bool;
    }
    
    private void setBoolean(Boolean bool){
    	this.bool = bool;
    }
    
    public boolean getBoolean(){
    	return this.bool;
    }

}
