package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class BlackHole extends Entity {
	
	protected double radiusChange;

    /**
     * Create a new black hole with the given position and radius.
     * @param x
     * 		X coordinate for the new black hole.
     * @param y
     * 		Y coordinate for the new black hole.
     * @param radius
     * 		Radius for the new black hole.
     * @param radiusChange
     * 		Value by which the radius is to be changed
     * @post ...
     * 		| (x != IsNaN) && (y != IsNaN) && (radius != IsNaN)
     * @effect setPosition
     * @effect setRadius
     * @effect setRadiusChange
     * @throws EntityException
     */

	
	
    public BlackHole(double x, double y, double radius) throws EntityException {
        if (!Double.isNaN(x) && !Double.isNaN(y) && !Double.isNaN(radius)){
            setPosition(x, y);
            setVelocity(0, 0);
            setRadius(radius);
            setRadiusChange(radiusChange);
        }
        else {
            throw new EntityException("Values are NaN!");
        }
    }
    
    /**
     * Sets the radius of the black hole
     * @param radius
     * @post ...
     * 		| radius >= 100
     * @throws EnityException
     */
    
    @Override
    protected void setRadius(double radius) throws EntityException {
        if (radius >= 100) {
            this.radius = radius;
        }
        else {
            throw new EntityException("Wrong radius!");
        }
    }
    
    /**
     * 	
     * @param radiusChange
     */
    
    protected void setRadiusChange(double radiusChange) {
    	// Currently empty because there are no means to change radius	
    }
    
    public double getRadiusChange() {
    	return radiusChange;
    }
    
    protected void changeRadius() {
    	this.radius += radiusChange;
    }

    /**
     * Checks for overlap when the radius of the black hole gets changed and acts depending upon the type of entity with which there is overlap
     * @param radius
     * @param blackHole
     * 		Black hole for which the overlap will be checked
     * @param entity
     * 		Entity with which overlap will be checked
     * @effect
     * 		
     * 		
     */
    
    @SuppressWarnings("unused")
    protected void resolveBlackHoleCollision(double radius) throws WorldException, EntityException {
    	//TODO herschrijven voor duidelijkheid
        if (this.getWorld() != null) {
            if (this.getPosition()[0] >= radius && this.getPosition()[1] >= radius && world.getSize()[0] - this.getPosition()[0] >= radius && world.getSize()[1] - this.getPosition()[1] >= radius) {
                for (Entity entity : world.entities) {
                    if (Math.hypot(entity.x-this.x,entity.y-this.y)-(entity.radius+radius) < 0 && this != entity) {
                        if (entity instanceof BlackHole) {
                            BlackHole bh = new BlackHole((this.x+entity.x)/2, (this.y+entity.y)/2, radius+entity.radius);
                            World w = this.getWorld();
                            this.terminate();
                            entity.terminate();
                            bh.addEntityToWorld(w);
                        }
                        else if (entity instanceof Bullet) {
                            this.setRadius(radius);
                        }
                        else {
                            entity.terminate();
                            this.setRadius(radius);
                        }
                    }
                }
            }
            else {
                throw new WorldException("Black hole outside boundaries.");
            }
        }
        else {	
            throw new WorldException("Black hole not located in world.");
        }
    }

    /**
     * Adds a black hole to the world.
     * @param world
     *      The world the black hole has to be added to.
     * @post ...
     * 		| new this.world == world
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
                boolean c = true;
                for (Entity entity : world.entities) {
                    if (this.overlapAddToWorld(entity)) {
                        if (entity instanceof BlackHole) {
                            BlackHole bh = new BlackHole((this.x+entity.x)/2, (this.y+entity.y)/2, this.radius+entity.radius);
                            this.terminate();
                            entity.terminate();
                            bh.addEntityToWorld(world);
                            c = false;
                        }
                        else if (entity instanceof Bullet) {
                        }
                        else {
                            entity.terminate();
                        }
                    }
                }
                if (c) {
                    world.entities.add(this);
                    this.setWorld(world);
                }
            } else {
                throw new WorldException("Black hole outside boundary.");
            }
        }
        else {
            throw new WorldException("Black hole is already located in a world!");
        }
    }

    /**
     * Remove a black hole from the world.
     * @param world
     *      The world the black hole has to be removed from.
     * @post ...
     * 		| new this.world == null
     * @throws WorldException
     */

    @Override
    public void removeEntityFromWorld(World world) throws WorldException {
        if (world.entities.contains(this)) {
            world.entities.remove(this);
            this.world = null;
        }
        else {
            throw new WorldException("Black hole is not in the world");
        }
    }


    /**
     * Terminates the black hole.
     * @post ...
     * 		|new this.world == null
     * 		|new this.x == NaN
     * 		|new this.y == NaN
     * 		|new this.radius == NaN
     */

    @Override
    public void terminate() throws WorldException, EntityException {
        if (this.world != null){
            this.world.entities.remove(this);
        }
        this.world = null;
        this.x = Double.NaN;
        this.y = Double.NaN;
        this.radius = Double.NaN;
    }

    /**
     * Returns the state of the black hole.
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
