package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;

/**
 * @author Jaro Deklerck
 */
public class objectType extends Type {

    protected Object obj;

    public objectType(Object obj) {
        this.obj = obj;
    }
    
    private void setObject(Object obj){
    	this.obj = obj;
    }
    
    public Object getObject(){
    	return this.obj;
    }}
