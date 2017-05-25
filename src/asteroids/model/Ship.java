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
    private double MAX_SPEED = SPEED_OF_LIGHT;

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
		if ((x <= 0 || x > 0) && (y <= 0 || y > 0) && (radius <= 0 || radius > 0) && (orientation <= 0 || orientation > 0)){
			setPosition(x, y);
            setVelocity(xVelocity, yVelocity);
			setRadius(radius);
			if (orientation < MIN_ANGLE || orientation > MAX_ANGLE) {
			    throw new EntityException("Invalid orientation");
            }
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
        if (mass >= 4/3.*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY) {
            this.mass = mass;
        }
        else {
            this.mass = 4/3.*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
        }
    }

    @Override
    protected void setRadius(double radius) throws EntityException {
        if (radius >= 10) {
            this.radius = radius;
        }
        else {
            throw new EntityException("Wrong radius!");
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

    @Override
    public double getMAX_SPEED() {
        return MAX_SPEED;
    }

    @Override
    protected void setMAX_SPEED(double MAX_SPEED) {
        if (MAX_SPEED >= 0 && MAX_SPEED <= SPEED_OF_LIGHT) {
            this.MAX_SPEED = MAX_SPEED;
        }
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

    public void setThruster(boolean active) {
        thruster = active;
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
        if (thruster) {
            double a = (1.1 * Math.pow(10, 18)) / this.getTotalMass();
            return a;
        }
        else {
            return 0.0;
        }
    }

    @Override
    public void move(double dt) {
        x += dt * xVelocity;
        y += dt * yVelocity;
        if (this.isThrusterActive()) {
            this.thrust(dt, this.getAcceleration());
        }
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

    public Bullet closestBullet() {
        Set<Bullet> bullets = (Set<Bullet>)this.world.getEntities("Bullet");
        double closest = Double.POSITIVE_INFINITY;
        Bullet bul = null;
        for (Bullet bullet: bullets) {
            if (this.getDistanceTo(bullet) < closest) {
                bul = bullet;
            }
        }
        return bul;
    }

 	public Asteroid closestAsteroid() {
	    Set<Asteroid> asteroids = (Set<Asteroid>)this.world.getEntities("Asteroid");
	    double closest = Double.POSITIVE_INFINITY;
	    Asteroid ast = null;
	    for (Asteroid asteroid: asteroids) {
	        if (this.getDistanceTo(asteroid) < closest) {
	            ast = asteroid;
            }
        }
        return ast;
    }

    public Planetoid closestPlanetoid() {
        Set<Planetoid> planetoids = (Set<Planetoid>)this.world.getEntities("Planetoid");
        double closest = Double.POSITIVE_INFINITY;
        Planetoid plan = null;
        for (Planetoid planetoid: planetoids) {
            if (this.getDistanceTo(planetoid) < closest) {
                plan = planetoid;
            }
        }
        return plan;
    }

    public MinorPlanets closestMinorPlanet() {
	    MinorPlanets ast = closestAsteroid();
	    MinorPlanets plan = closestPlanetoid();
	    if (ast == null && plan == null) {
	        return null;
        }
        else if (ast == null) {
	        return plan;
        }
        else if (plan == null) {
	        return ast;
        }
        else if (this.getDistanceTo(plan) < this.getDistanceTo(ast)) {
            return plan;
        }
        else {
	        return ast;
        }
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

    public void loadBullet(Bullet bullet, boolean collision) throws WorldException {
        double d = Math.hypot(this.x - bullet.x, this.y - bullet.y) + bullet.radius;
        if (d < this.radius || collision) {
            if (bullet.world != null) {
                bullet.world.removeFromWorld(bullet);
            }
            this.bullets.add(bullet);
            bullet.ship = this;
            bullet.world = null;
            bullet.source = this;
            bullet.x = x;
            bullet.y = y;
        }
        else {
            throw new WorldException("Bullet not located in ship");
        }
    }

    /**
     * Loads a collection of bullets on a ship
     * @param bullets
     *      The collection of bullets to be loaded
     * @see implementation
     */

    public void loadBullets(Collection<Bullet> bullets) throws WorldException {
        if (bullets == null) {
            throw new WorldException("List is null");
        }
        for (Bullet bullet: bullets) {
            if (bullet != null) {
                double d = Math.hypot(this.x - bullet.x, this.y - bullet.y) + bullet.radius;
                if (d > this.radius) {
                    throw new WorldException("Bullet not located in ship");
                }
            }
            else {
                throw new WorldException("Bullet is null");
            }
        }
        for (Bullet bullet: bullets) {
            loadBullet(bullet, false);
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
        if (!this.bullets.isEmpty() && this.world != null) {
            Bullet bullet = null;
            for (Bullet rndBullet : this.bullets) {
                bullet = rndBullet;
                break;
            }
            this.removeBullet(bullet);
            bullet.source = this;
            bullet.setPosition(this.x + Math.cos(orientation) * 1.011 * (radius+bullet.radius), this.y + Math.sin(orientation) * 1.011 * (radius+bullet.radius));
            bullet.setVelocity(Math.cos(orientation)*250, Math.sin(orientation)*250);
            this.world.addToWorld(bullet);
        }
    }

    /**
     * Adds a ship to the world.
     * @param world
     *      The world the ship has to be added to.
     * @see implementation
     * @throws WorldException
     */
    @Override
    public void addEntityToWorld(World world) throws WorldException, IllegalArgumentException{
        if (world == null) {
            throw new IllegalArgumentException();
        }
        double check = this.getRadius();
        if (this.getWorld() == null) {
            if (this.getPosition()[0] >= check && this.getPosition()[1] >= check && world.getSize()[0] - this.getPosition()[0] >= check && world.getSize()[1] - this.getPosition()[1] >= check) {
                boolean checkOverlap = false;
                for (Entity entity : world.entities) {
                    boolean overlap = this.overlapAddToWorld(entity);
                    if (overlap) {
                        checkOverlap = true;
                        break;
                    }
                }
                if (!checkOverlap) {
                    world.entities.add(this);
                    this.world = world;
                }
                else {
                    throw new WorldException("Ship overlaps");
                }
            } else {
                throw new WorldException("Ship not located between boundaries");
            }
        }
        else {
            throw new WorldException("Ship already has a world!");
        }
    }

    /**
     * Remove a ship from the world.
     * @param ship
     *      The ship to be removed
     * @see implementation
     * @throws WorldException
     */
    @Override
    public void removeEntityFromWorld(World world) throws WorldException {
        if (world.entities.contains(this)) {
            world.entities.remove(this);
            this.world = null;
        }
        else {
            throw new WorldException("Ship is not in the world");
        }
    }

    /**
     * Terminates the ship
     */
    @Override
    public void terminate() throws WorldException, EntityException {
        if (this.world != null) {
            world.removeFromWorld(this);
        }
        bulletTerminateLoop:
        for (Bullet bullet : bullets) {
            bullet.source = null;
            bullet.terminate();
            break bulletTerminateLoop;
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
