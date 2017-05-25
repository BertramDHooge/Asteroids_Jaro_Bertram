package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * @author Jaro Deklerck
 */
public class Bullet extends Entity {

    protected Ship ship;
    protected Ship source;
    protected int bounces = 0;
    private int MAX_BOUNCES = 3;
    private static final double MASS_DENSITY = 7.8*Math.pow(10,12);
    private double MAX_SPEED = SPEED_OF_LIGHT;

    /**
     * Create a new bullet with the given position, velocity, radius.
     * @param x
     * 		X coordinate for the new bullet.
     * @param y
     * 		Y coordinate for the new bullet.
     * @param xVelocity
     * 		Starting velocity in X-axis for the new bullet.
     * @param yVelocity
     * 		Starting velocity in Y-axis for the new bullet.
     * @param radius
     * 		Radius for the new bullet.
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
     * returns the max amount of wallcollisions for a bullet
     * @return MAX_BOUNCES
     */

    @Basic
    public int getMAX_BOUNCES() {
        return MAX_BOUNCES;
    }
    
    /**
     * sets the max amount of wallcollisions for a bullet
     * @param MAX_BOUNCES
     * 		max amount of wallcollisions a bullet can have
     */

    @SuppressWarnings("Unnused")
    private void setMAX_BOUNCES(int MAX_BOUNCES) {
        if (MAX_BOUNCES >= 0) {
            this.MAX_BOUNCES = MAX_BOUNCES;
        }
    }

    /**
     * Sets the mass of the bullet.
     * @post ...
     *      | new mass == 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY
     */
    @Basic
    private void setMass() {
        this.mass = 4/3.*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
    }

    @Override
    protected void setRadius(double radius) throws EntityException {
        if (radius >= 1) {
            this.radius = radius;
        }
        else {
            throw new EntityException("Wrong radius!");
        }
    }

    /**
     * Returns the ship the bullet is loaded in (returns null if it belongs to a world).
     * @return
     */
    @Basic
    public Ship getShip() {
        if (this.getWorld() == null) {
            return this.ship;
        }
        else {
            return null;
        }
    }
    
    /**
     * returns the max speed for a bullet
     * @return MAX_SPEED
     * 		max speed a bullet is allowed to have
     */

    @Basic
    @Override
    public double getMAX_SPEED() {
        return MAX_SPEED;
    }
    
    /**
     * sets the max speed a bullet can have
     * @post ...
     * 		|new MAX_SPEED == MAX_SPEED
     */

    @Override
    protected void setMAX_SPEED(double MAX_SPEED) {
        if (MAX_SPEED >= 0 && MAX_SPEED <= SPEED_OF_LIGHT) {
            this.MAX_SPEED = MAX_SPEED;
        }
    }
    
    /**
     * increases the counter for wallcollisions by 1
     * @post ...
     * 		|new bounces == bounces + 1
     */

    @Basic
    protected void addBounce() {
        this.bounces += 1;
    }
    
    /**
     * returns the amount of wallcollisions a ship has already had
     * @return bounces
     */

    @Basic
    public int getBounces() {
        return bounces;
    }

    /**
     * Returns the source of the bullet.
     * @return source
     */
    
    @Basic
    public Ship getSource() {
        return this.source;
    }
    
    /**
     * moves a bullet
     * @post ...
     * 		|new x == x + dt * xVelocity
     * 		|new y == y + dt * yVelocity
     */

    @Override
    public void move(double dt) {
        x += dt * xVelocity;
        y += dt * yVelocity;
    }

    /**
     * Adds a bullet to the world.
     * @param bullet
     *      The bullet to be added
     * @see implementation
     * @throws WorldException
     */
    
    @Override
    public void addEntityToWorld(World world) throws WorldException, IllegalArgumentException, EntityException {
        if (world == null) {
            throw new IllegalArgumentException();
        }
        double check = this.getRadius();
        if (this.getWorld() == null) {
            if (this.getPosition()[0] >= check && this.getPosition()[1] >= check && world.getSize()[0] - this.getPosition()[0] >= check && world.getSize()[1] - this.getPosition()[1] >= check) {
                boolean checkOverlap = false;
                Entity ent = null;
                for (Entity entity : world.entities) {
                    if (this.overlapAddToWorld(entity)) {
                        checkOverlap = true;
                        ent = entity;
                        break;
                    }
                }
                if (!checkOverlap) {
                    world.entities.add(this);
                    this.world = world;
                }
                else if (this.getSource() == null && this.ship == null) {
                    throw new WorldException("Overlap");
                }
                else if (ent == this.getSource()) {
                    ((Ship)ent).loadBullet(this, false);
                }
                else {
                    this.terminate();
                    ent.terminate();
                }
            } else {
                this.terminate();
            }
        }
        else {
            throw new WorldException("Bullet is already located in a world!");
        }
    }

    /**
     * Remove a bullet from the world.
     * @param bullet
     *      The bullet to be removed
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
            throw new WorldException("Bullet is not in the world");
        }
    }


    /**
     * Terminates the bullet
     * @post ...
     * 		|new bullet == null
     */
    
    @Override
    public void terminate() throws WorldException, EntityException {
        if (this.world != null){
            this.world.entities.remove(this);
        }
        this.world = null;
        if (this.ship != null) {
            this.ship.removeBullet(this);
        }
        this.ship = null;
        this.x = Double.NaN;
        this.y = Double.NaN;
        this.xVelocity = Double.NaN;
        this.yVelocity = Double.NaN;
        this.radius = Double.NaN;
        this.mass = Double.NaN;
        this.bounces = 0;
    }

    /**
     * Returns the state of the bullet.
     * @return
     */
    
    @Basic
    public boolean isTerminated() {
        if ((this.x <= 0 || this.x > 0) && (this.y <= 0 || this.y > 0)) {
            return false;
        }
        else {
            return true;
        }
    }
}
