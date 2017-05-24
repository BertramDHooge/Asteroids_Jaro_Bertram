package asteroids.model;

import java.security.PrivateKey;

/**
 * @author Jaro Deklerck
 */
public class Asteroid extends MinorPlanets {

    private final double MASS_DENSITY = 2.65 * Math.pow(10, 12);

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

     private void setMass() {
         this.mass = 4/3.*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
     }

     @Override
     protected void setRadius(double radius) throws EntityException, WorldException {
         if (radius >= 5) {
             this.radius = radius;
         }
         else {
             throw new EntityException("Wrong radius!");
         }
     }

     @Override
     public void move(double dt) {
         x += dt * xVelocity;
         y += dt * yVelocity;
     }
}
