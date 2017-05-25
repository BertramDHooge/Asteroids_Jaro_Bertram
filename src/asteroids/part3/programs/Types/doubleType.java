package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;


public class doubleType extends Type {

    protected double dble;

    public doubleType(double dble) {
        this.dble = dble;
    }
    
    private void setDouble(Double dble){
    	this.dble = dble;
    }
    
    public double getDouble(){
    	return this.dble;
    }

}
