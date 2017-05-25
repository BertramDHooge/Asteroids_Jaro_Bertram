package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;

public class stringType extends Type {

	protected String string;

    public stringType(String string) {
        this.string = string;
    }

    public void setString(String string){
    	this.string = string;
    }
    
    public String getString(){
    	return string;
    }
}
