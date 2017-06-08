package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;

public class stringType extends Type {

	protected String string;

    public stringType(String string) {
        setString(string);
    }

    public void setString(String string){
    	this.string = string;
    }
    
    public String getString(){
    	return string;
    }

    @Override
    public Object get() {
        return this.getString();
    }
}
