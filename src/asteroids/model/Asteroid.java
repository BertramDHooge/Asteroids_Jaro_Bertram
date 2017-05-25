package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * @author Jaro Deklerck
 */
public class Asteroid extends MinorPlanets {

    private final double MASS_DENSITY = 2.65 * Math.pow(10, 12);

    /**
     * Create a new asteroid with the given position, velocity and radius
     * @param x
	 * 		X coordinate for the new asteroid.
	 * @param y
	 * 		Y coordinate for the new asteroid.
	 * @param xVelocity
	 * 		Starting velocity in X-axis for the new asteroid.
	 * @param yVelocity
	 * 		Starting velocity in Y-axis for the new asteroid.
	 * @param radius
	 * 		Radius for the new asteroid.
	 * @post ...
	 * 		| (x != IsNaN) && (y != IsNaN) && (xVelocity != IsNaN) && (yVelocity != IsNaN) && (radius != IsNaN)
     * @throws EntityException
     * @throws WorldException
     * @effect setPosition
     * @effect setVelocity
     * @effect setRadius
     * @effect setMass
     */
    
    public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws EntityException, WorldException {
        if ((x <= 0 || x > 0) && (y <= 0 || y > 0) && (xVelocity <= 0 || xVelocity > 0) && (yVelocity <= 0 || yVelocity > 0) && (radius <= 0 || radius > 0)){
            setPosition(x, y);
            setVelocity(xVelocity, yVelocity);
            setRadius(radius);
            setMass();
        }
        else {
            throw new EntityException("Values are NaN!");
        }
    }

     /**
      * Sets the mass of the asteroid.
      * @post ...
      *      | new mass == 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY
      */
    
     @Basic
     private void setMass() {
         this.mass = 4/3.*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
     }
     
     /**
      * Sets the radius of the asteroid.
      * @post ...
      * 	|if (radius >=5)
      * 	|	 new radius == radius
      * @throws EntityException
      */

     @Override
     protected void setRadius(double radius) throws EntityException, WorldException {
         if (radius >= 5) {
             this.radius = radius;
         }
         else {
             throw new EntityException("Wrong radius!");
         }
     }
     
     /**
      * Moves the asteroid
      * @post ...
      * 	|new x == x + dt * xVelocity
      * 	|new y == y + dt * yVelocity
      */

     @Override
     public void move(double dt) {
         x += dt * xVelocity;
         y += dt * yVelocity;
     }
}
