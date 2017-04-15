package asteroids.model;

/**
 * @author Jaro Deklerck
 */
public class Bullet extends Entity {

    protected Ship ship;
    private static final double MASS_DENSITY = 7.8*Math.pow(10,12);

    /**
     * Create a new ship with the given position, velocity, radius.
     * @param x
     * 		X coordinate for the new ship.
     * @param y
     * 		Y coordinate for the new ship.
     * @param xVelocity
     * 		Starting velocity in X-axis for the new ship.
     * @param yVelocity
     * 		Starting velocity in Y-axis for the new ship.
     * @param radius
     * 		Radius for the new ship.
     * @post ...
     * 		| (x != IsNaN) && (y != IsNaN) && (xVelocity != IsNaN) && (yVelocity != IsNaN) && (radius != IsNaN)
     * @effect setPosition
     * @effect setVelocity
     * @effect setRadius
     * @effect setMass
     * @throws EntityException
     */

    public Bullet(double x, double y, double xVelocity, double yVelocity, double radius) throws EntityException {
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
     * Sets the mass of the bullet.
     * @post ...
     *      | new mass == 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY
     */

    private void setMass() {
        this.mass = 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
    }

    /**
     * Returns the ship the bullet is loaded in (returns null if it belongs to a world).
     * @return
     */

    public Ship getShip() {
        if (this.getWorld() == null) {
            return this.ship;
        }
        else {
            return null;
        }
    }

    /**
     * Returns the source of the bullet.
     * @return
     */

    public Ship getSource() {
        return this.ship;
    }
}
