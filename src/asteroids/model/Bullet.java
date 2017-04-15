package asteroids.model;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * @author Jaro Deklerck
 */
public class Bullet {

    private double x;
    private double y;
    private double xVelocity;
    private double yVelocity;
    private double radius;
    private double mass;
    private boolean terminated;
    private Ship ship;
    private World world;
    private static final double SPEED_OF_LIGHT = 300000;
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
     * @throws BulletException
     */

    public Bullet(double x, double y, double xVelocity, double yVelocity, double radius) throws BulletException {
        if ((x <= 0 || x > 0) && (y <= 0 || y > 0) && (xVelocity <= 0 || xVelocity > 0) && (yVelocity <= 0 || yVelocity > 0) && (radius <= 0 || radius > 0)){
            setPosition(x, y);
            setVelocity(xVelocity, yVelocity);
            setRadius(radius);
            setMass();
        }
        else {
            throw new BulletException("Values are NaN!");
        }
    }

    /**
     * Set the position of the bullet.
     * @param x
     *      x-coordinate
     * @param y
     *      y-coordinate
     * @throws BulletException
     * @post ...
     *      | if (x < Double.POSITIVE_INFINITY && x > Double.NEGATIVE_INFINITY && y < Double.POSITIVE_INFINITY && y > Double.NEGATIVE_INFINITY)
     * 		|   then new x == x &&
     * 		|       new y == y
     */

    private void setPosition(double x, double y) throws BulletException{
        if (x < Double.POSITIVE_INFINITY && x > Double.NEGATIVE_INFINITY && y < Double.POSITIVE_INFINITY && y > Double.NEGATIVE_INFINITY) {
            this.x = x;
            this.y = y;
        }
        else {
            throw new BulletException("Wrong coordinates!");
        }
    }

    /**
     * Return the position of ship as an array of length 2, with the
     * x-coordinate at index 0 and the y-coordinate at index 1.
     * @return new double[] {x, y}
     */

    public double[] getPosition() {
        return new double[] {x, y};
    }

    /**
     * Set the velocity of the bullet.
     * @param xVelocity
     * @param yVelocity
     * @post ...
     *      | if (xVelocity < 0)
     *      |   then xVelocity == 0 &&
     * 		|   new xVelocity == xVelocity
     * @post ...
     * 		| if (yVelocity < 0)
     *      |   then yVelocity == 0 &&
     * 		|   new yVelocity == yVelocity
     * @post ...
     * 		|if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) > Math.pow(SPEED_OF_LIGHT, 2))
     * 		|		then new Speed == Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2)) &&
     * 		|		new xVelocity == (xVelocity * SPEED_OF_LIGHT) / Speed &&
     *		|		new yVelocity == (yVelocity * SPEED_OF_LIGHT) / Speed
     */

    private void setVelocity(double xVelocity, double yVelocity) {
        if (xVelocity < 0) {
            xVelocity = 0;
        }
        if (yVelocity < 0) {
            yVelocity = 0;
        }
        if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) > Math.pow(SPEED_OF_LIGHT, 2)){
            double Speed = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
            xVelocity = (xVelocity * SPEED_OF_LIGHT) / Speed;
            yVelocity = (yVelocity * SPEED_OF_LIGHT) / Speed;
        }
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;

    }


    /**
     *  Return the velocity of ship as an array of length 2, with the velocity
     * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
     * @return new double[] {xVelocity, yVelocity}
     */

    public double[] getVelocity() {
        return new double[] {xVelocity, yVelocity};
    }

    /**
     * Set the radius of the bullet
     * @param radius
     * @post ...
     *      | if (radius >= 1)
     *      |   then new radius == radius
     * @exception BulletException
     */

    private void setRadius(double radius) throws BulletException {
        if (radius >= 1) {
            this.radius = radius;
        }
        else {
            throw new BulletException("Wrong radius!");
        }
    }

    /**
     * Return the radius of ship.
     * @return radius
     */

    public double getRadius() {
        return radius;
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
     * Returns the mass of the ship
     * @return this.mass
     */

    public double getMass() {return this.mass;}

    /**
     * Terminates the bullet
     */

    public void terminate() {
        this.terminated = true;
    }

    /**
     * Returns the state of the bullet.
     * @return
     */

    public boolean isTerminated() {return this.terminated;}

    /**
     * Returns the world the bullet belongs to (returns null if it is loaded in a ship or to the unbounded two-dimensional space).
     * @return
     */

    public World getWorld() {
        return this.world;
    }

    /**
     * Returns the ship the bullet is loaded in (returns null if it belongs to a world).
     * @return
     */

    public Ship getShip() {
        return this.ship;
    }

    /**
     * Returns the source of the bullet.
     * @return
     */

    public Ship getSource() {
        return this.ship;
    }
}
