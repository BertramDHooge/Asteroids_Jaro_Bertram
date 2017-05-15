package asteroids.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Ship extends Entity {

	protected double orientation;
    private double totalMass;
    private boolean thruster = false;
    private Set<Bullet> bullets = new HashSet<>();
	private static final double MAX_ANGLE = 2 * Math.PI;
	private static final double MIN_ANGLE = 0;
    private static final double MASS_DENSITY = 1.42*Math.pow(10,12);
	
	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on (0, 0) facing right. Its
	 * speed is zero.
	 */
	
	public Ship() {
	}
	
	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation (in radians).
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
	 * @param orientation
	 * 		Starting orientation for the new ship.
     * @param mass
     *      Mass for the new ship
	 * @post ...
	 * 		| (x != IsNaN) && (y != IsNaN) && (xVelocity != IsNaN) && (yVelocity != IsNaN) && (radius != IsNaN) && (orientation != IsNaN) && (mass != IsNaN)
     * @effect setPosition
	 * @effect setVelocity
     * @effect setRadius
	 * @effect setOrientation
     * @effect setMass
     * @throws EntityException
	 */
	
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass) throws EntityException {
		if ((x <= 0 || x > 0) && (y <= 0 || y > 0) && (xVelocity <= 0 || xVelocity > 0) && (yVelocity <= 0 || yVelocity > 0) && (radius <= 0 || radius > 0) && (orientation <= 0 || orientation > 0) && (mass <= 0 || mass > 0)){
			setPosition(x, y);
            setVelocity(xVelocity, yVelocity);
			setRadius(radius);
            setOrientation(orientation);
            setMass(mass);
		}
		else {
			throw new EntityException("Values are NaN!");
		}
	}

    /**
     * Set the orientation of the ship
     * @param orientation
     * @effect assertOrientation
     * @post ...
     *      | new orientation == orientation
     * @post ...
     * 		| while (orientation > MAX_ANGLE)
     * 		|		do new orientation == orientation - MAX_ANGLE
     * @post ...
     * 		| while (orientation < MIN_ANGLE)
     * 		|		do new orientation == orientation + MAX_ANGLE
     */

	private void setOrientation(double orientation) {
        assert assertOrientation(orientation);
        this.orientation = orientation;
        while (this.orientation > MAX_ANGLE){
            this.orientation -= MAX_ANGLE;
        }
        while (orientation < MIN_ANGLE){
            this.orientation += MAX_ANGLE;
        }
    }

	/**
	 *Return the orientation of ship (in radians).
	 * @return orientation
	 */
	
	public double getOrientation() {
		return orientation;
	}

    /**
     *Assert the orientation of the ship.
     * @param orientation
     * 		Orientation of the ship
     * @return ...
     * 		|if (orientation <= MAX_ANGLE && orientation >= MIN_ANGLE)
     * 		|		then return true
     * 		|else return false
     */

    private boolean assertOrientation(double orientation) {
        if (orientation <= MAX_ANGLE && orientation >= MIN_ANGLE) {
            return true;
        }
        return false;
    }

    /**
     * Sets the mass of the ship
     * @param mass
     *      Specified mass for the ship
     * @post ...
     *      | if (mass >= 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY)
     *      |   then new mass == mass
     *      | else
     *      |   then new mass == 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY
     */

    protected void setMass(double mass) {
        if (mass >= 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY) {
            this.mass = mass;
        }
        else {
            this.mass = 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
        }
    }

    /**
     * Returns the total mass(ship+bullets) of the ship.
     * @return
     */

    public double getTotalMass() {
        totalMass = mass;
        for (Bullet bullet : bullets) {
            totalMass += bullet.getMass();
        }
        return totalMass;
    }

    public void thrust(double dt, double a) {
        this.setVelocity(xVelocity + a * Math.cos(orientation) * dt, yVelocity + a * Math.sin(orientation) * dt);
    }

    /**
     * Turn the thruster on.
     */

    public void thrustOn() {
        this.thruster = true;
    }

    /**
     * Turn the thruster off.
     */

    public void thrustOff() {
        this.thruster = false;
    }

    /**
     * Returns true when the thruster is activated and false when it's deactivated.
     * @return
     */

    public boolean isThrusterActive() {return this.thruster;}

    /**
     * Returns the acceleration of the ship
     * @return a
     */

    public double getAcceleration() {
        double a = (1.1*Math.pow(10, 21))/this.getTotalMass();
        return a;
    }
	
	/**
	 * Update the direction of ship by adding angle
	 * (in radians) to its current direction. Angle may be
	 * negative.
	 * @param angle
	 * 		Amount this ship turns.
	 * @effect setOrientation
	 */
	
	public void turn(double angle) {
		setOrientation(orientation + angle);
 	}

    /**
     * Returns the bullets on the ship
     * @return Set<Bullet> bullets
     */

    public Set<? extends Bullet> getBullets() {return bullets;}

    /**
     * Returns the number of bullets on the ship
     * @return bullets.size()
     */

    public int getNbBullets() {return bullets.size();}

    /**
     * Loads a bullet on a ship.
     * @param bullet
     *      The bullet to be loaded.
     * @see implementation
     */

    public void loadBullet(Bullet bullet) {
        this.bullets.add(bullet);
        bullet.ship = this;
        bullet.world = null;
        bullet.source = this;
    }

    /**
     * Loads a collection of bullets on a ship
     * @param bullets
     *      The collection of bullets to be loaded
     * @see implementation
     */

    public void loadBullets(Collection<Bullet> bullets) {
        this.bullets.addAll(bullets);
        for (Bullet bullet : bullets) {
            bullet.ship = this;
            bullet.world = null;
            bullet.source = this;
        }
    }

    /**
     * Removes a bullet from the ship.
     * @param bullet
     *      The bullet to be removed.
     * @throws BulletException
     * @see implementation
     */

    public void removeBullet(Bullet bullet) throws EntityException{
        if (this.bullets.contains(bullet)) {
            this.bullets.remove(bullet);
            bullet.ship = null;
        }
        else {
            throw new EntityException("Bullet is not loaded on the ship!");
        }
    }

    /**
     *
     */

    public void fireBullet() throws EntityException, WorldException{
        if (!this.bullets.isEmpty()) {
            Bullet bullet = null;
            for (Bullet rndBullet : this.bullets) {
                bullet = rndBullet;
                break;
            }
            this.removeBullet(bullet);
            bullet.setPosition(this.x + Math.sin(orientation)*1.01*(radius+bullet.radius), this.y + Math.cos(orientation)*1.01*(radius+bullet.radius));
            bullet.setVelocity(Math.cos(orientation)*250, Math.sin(orientation)*250);
            this.world.addBulletToWorld(bullet);
        }
    }

    /**
     * Terminates the ship
     */
    @Override
    public void terminate() throws WorldException{
        if (this.world != null) {
            world.removeShipFromWorld(this);
        }
        for (Bullet bullet : bullets) {
            bullet.terminate();
        }
        this.world = null;
        this.x = Double.NaN;
        this.y = Double.NaN;
        this.xVelocity = Double.NaN;
        this.yVelocity = Double.NaN;
        this.orientation = Double.NaN;
        this.radius = Double.NaN;
        this.mass = Double.NaN;
    }

    /**
     * Returns the state of the ship.
     * @return
     */

    public boolean isTerminated() {
        if ((this.x <= 0 || this.x > 0) && (this.y <= 0 || this.y > 0)) {
            return false;
        }
        else {
            return true;
        }
    }
}
