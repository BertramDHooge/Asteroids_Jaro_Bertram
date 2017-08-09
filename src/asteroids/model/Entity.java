package asteroids.model;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jaro Deklerck
 */
public class Entity {

    protected double x;
    protected double y;
    protected double xVelocity;
    protected double yVelocity;
    protected double radius;
    protected double mass;
    protected double boundary = 0;
    protected World world = null;
    protected static final double SPEED_OF_LIGHT = 300000;


    /**
     * Set the position of the entity
     * @param x
     *      x-coordinate
     * @param y
     *      y-coordinate
     * @throws EntityException
     * @post ...
     *      | if (x < Double.POSITIVE_INFINITY && x > Double.NEGATIVE_INFINITY && y < Double.POSITIVE_INFINITY && y > Double.NEGATIVE_INFINITY)
     * 		|   then new x == x &&
     * 		|       new y == y
     */

    protected void setPosition(double x, double y) throws EntityException {
        if (x <= Double.POSITIVE_INFINITY && x >= Double.NEGATIVE_INFINITY && y <= Double.POSITIVE_INFINITY && y >= Double.NEGATIVE_INFINITY) {
            this.x = x;
            this.y = y;
        }
        else {
            throw new EntityException("Wrong coordinates!");
        }
    }

    /**
     * Return the position of entity as an array of length 2, with the
     * x-coordinate at index 0 and the y-coordinate at index 1.
     * @return new double[] {x, y}
     */
    
    @Basic
    public double[] getPosition() {
        return new double[] {x, y};
    }

    /**
     * Set the velocity of the entity
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

    protected void setVelocity(double xVelocity, double yVelocity) {
        if (!(xVelocity <= 0 || xVelocity > 0)) {
            xVelocity = 0;
        }
        if (!(yVelocity <= 0 || yVelocity > 0)) {
            xVelocity = 0;
        }
        if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) > Math.pow(getMAX_SPEED(), 2)){
            double Speed = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
            xVelocity = (xVelocity * SPEED_OF_LIGHT) / Speed;
            yVelocity = (yVelocity * SPEED_OF_LIGHT) / Speed;
        }
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    /**
     *  Return the velocity of entity as an array of length 2, with the velocity
     * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
     * @return new double[] {xVelocity, yVelocity}
     */
    
    @Basic
    public double[] getVelocity() {
        return new double[] {xVelocity, yVelocity};
    }

    @Basic
    public double getMAX_SPEED() {return 0.;}

    @SuppressWarnings("Unnused")
    protected void setMAX_SPEED(double MAX_SPEED) {}

    /**
     * Set the radius of the entity
     * @param radius
     * @exception EntityException
     */

    protected void setRadius(double radius) throws EntityException, WorldException {

    }

    /**
     * Return the radius of entity.
     * @return radius
     */
    
    @Basic
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the mass of the entity
     * @return this.mass
     */
    
    @Basic
    public double getMass() {return this.mass;}

    public void move(double dt) throws WorldException, EntityException {

    }
    
    	/**
    	 * resolves the collision between an entity and a world boundary
    	 * @param collisionListener
    	 * @see implementaion
    	 * @return
    	 * @throws WorldException
    	 * @throws EntityException
    	 */

    public boolean resolveBoundaryCollision(CollisionListener collisionListener) throws WorldException, EntityException {
        collisionEffect(collisionListener, null);
        if (this instanceof Bullet) {
            if (((Bullet)this).getBounces() == ((Bullet) this).getMAX_BOUNCES() - 1) {
                this.terminate();
                return true;
            }
            else {
                ((Bullet)this).addBounce();
            }
        }
        if (this.boundary == 1 || this.boundary == 3) {
            this.setVelocity((-1) * this.getVelocity()[0], this.getVelocity()[1]);
        }
        else if (this.boundary == 2 || this.boundary == 4){
            this.setVelocity(this.getVelocity()[0], (-1) * this.getVelocity()[1]);
        }
        return false;
    }
    
    /**
     * resolves the collision between entities
     * @param entity
     * @param collisionListener
     * @return 
     * @throws WorldException
     * @throws EntityException
     */

    public boolean resolveEntityCollision(Entity entity, CollisionListener collisionListener) throws WorldException, EntityException {
        double cd = (Math.hypot(entity.x-this.x, entity.y-this.y) - (this.radius + entity.radius));
        double nd = (Math.hypot((entity.x + 0.000001*entity.xVelocity)-(this.x + 0.000001*this.xVelocity), (entity.y + 0.000001*entity.yVelocity) - (this.y + 0.000001*this.yVelocity)) - (this.radius + entity.radius));
        if (this != entity && cd >= nd) {
            if (this instanceof Ship && entity instanceof Ship) {
                resolveCollision(entity);
            }
            else if ((this instanceof Ship || this instanceof Bullet || this instanceof MinorPlanets) && entity instanceof Bullet) {
                if (((Bullet)entity).getSource() == this) {
                    ((Ship)this).loadBullet((Bullet)entity, true);
                    return true;
                }
                else {
                    if (this instanceof Planetoid && ((Planetoid) this).radius >= 30) {
                        double X = x;
                        double Y = y;
                        double r = radius;
                        double speed = convertSpeed();
                        World world = this.world;
                        collisionEffect(collisionListener, entity);
                        this.terminate();
                        entity.terminate();
                        ((Planetoid)this).spawnAsteroids(X, Y, r, speed, world);
                        return true;
                    }
                    else {
                        collisionEffect(collisionListener, entity);
                        this.terminate();
                        entity.terminate();
                        return true;
                    }
                }
            }
            else if (this instanceof Bullet && (entity instanceof Ship || entity instanceof Bullet || entity instanceof MinorPlanets)) {
                if (((Bullet)this).getSource() == entity) {
                    ((Ship)entity).loadBullet((Bullet)this, true);
                    return true;
                }
                else {
                    if (entity instanceof Planetoid && ((Planetoid) entity).radius >= 30) {
                        double X = entity.x;
                        double Y = entity.y;
                        double r = entity.radius;
                        double speed = entity.convertSpeed();
                        World world = entity.world;
                        collisionEffect(collisionListener, entity);
                        this.terminate();
                        entity.terminate();
                        ((Planetoid)entity).spawnAsteroids(X, Y, r, speed, world);
                        return true;
                    }
                    else {
                        collisionEffect(collisionListener, entity);
                        this.terminate();
                        entity.terminate();
                        return true;
                    }
                }
            }
            else if (this instanceof MinorPlanets && entity instanceof MinorPlanets) {
                resolveCollision(entity);
            }
            else if (this instanceof Ship && entity instanceof Planetoid) {
                return this.rndTeleport();
            }
            else if (this instanceof Planetoid && entity instanceof Ship) {
                return entity.rndTeleport();
            }
            else if (this instanceof Ship && entity instanceof Asteroid) {
                collisionEffect(collisionListener, entity);
                this.terminate();
                return true;
            }
            else if (this instanceof Asteroid && entity instanceof Ship) {
                collisionEffect(collisionListener, entity);
                entity.terminate();
                return true;
            }
            else if (this instanceof BlackHole && (entity instanceof Ship || entity instanceof MinorPlanets)) {
                entity.terminate();
                return true;
            }
            else if ((this instanceof Ship || this instanceof MinorPlanets) && entity instanceof BlackHole) {
                this.terminate();
                return true;
            }
        }
        return false;
    }
    
    /**
     * determines the effect of a collision on an entity
     * @param collisionListener
     * @param entity
     * @see implementation
     */

    private void collisionEffect(CollisionListener collisionListener, Entity entity) {
        if (collisionListener != null) {
            if (entity != null) {
                double posX = (this.getPosition()[0]+entity.getPosition()[0]+Math.abs(this.getRadius()-entity.getRadius()))/2;
                double posY = (this.getPosition()[1]+entity.getPosition()[1]+Math.abs(this.getRadius()-entity.getRadius()))/2;
                collisionListener.objectCollision(this, entity, posX, posY);
            }
            else {
                if (this.boundary == 1) {
                    collisionListener.boundaryCollision(this, this.getPosition()[0]-this.getRadius(), this.getPosition()[1]);
                }
                else if (this.boundary == 3) {
                    collisionListener.boundaryCollision(this, this.getPosition()[0]+this.getRadius(), this.getPosition()[1]);
                }
                else if (this.boundary == 2) {
                    collisionListener.boundaryCollision(this, this.getPosition()[0], this.getPosition()[1]-this.getRadius());
                }
                else if (this.boundary == 4) {
                    collisionListener.boundaryCollision(this, this.getPosition()[0], this.getPosition()[1]+this.getRadius());
                }
            }
        }
    }

    @Basic
    private double convertSpeed() {
        return Math.hypot(xVelocity,yVelocity);
    }
    
    /**
     * resolves collision
     * @param entity
     * @see implementation
     */

    private void resolveCollision(Entity entity) {
        double[] r = {entity.x - this.x, entity.y - this.y};
        double[] v = {entity.xVelocity - this.xVelocity, entity.yVelocity - this.yVelocity};
        double vr = v[0]*r[0]+v[1]*r[1];
        double mass1 = this.getMass();
        double mass2 = entity.getMass();
        double j = (2 * mass1 * mass2 * vr) / ((this.getRadius()+entity.getRadius())*(mass1 + mass2));
        double jx = j*r[0] / (this.getRadius()+entity.getRadius());
        double jy = j*r[1] / (this.getRadius()+entity.getRadius());
        this.setVelocity(this.getVelocity()[0]+jx/mass1, this.getVelocity()[1]+jy/mass1);
        entity.setVelocity(entity.getVelocity()[0]-jx/mass2, entity.getVelocity()[1]-jy/mass2);
    }
    
    /**
     * randomly teleport at certain collisions
     * @return
     * @throws EntityException
     * @throws WorldException
     */

    public boolean rndTeleport() throws EntityException, WorldException {
        double[] size = this.world.getSize();
        double posX = this.radius + Math.random() * (size[0]-this.radius*2);
        double posY = this.radius + Math.random() * (size[1]-this.radius*2);
        setPosition(posX, posY);
        for (Object entity: this.world.getEntities()) {
            if (this.overlap((Entity)entity) && this != entity) {
                if (entity instanceof Bullet && ((Bullet) entity).getSource() == this) {
                    ((Ship)this).loadBullet((Bullet)entity, true);
                }
                else {
                    this.terminate();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the world the bullet belongs to (returns null if it is loaded in a ship or to the unbounded two-dimensional space).
     * @return
     */
    
    @Basic
    public World getWorld() {
        return this.world;
    }

    @Basic
    protected void setWorld(World w) {
        this.world = w;
    }

    @Basic
    protected void terminate() throws WorldException, EntityException {

    }

    public void addEntityToWorld(World world) throws WorldException, IllegalArgumentException, EntityException {

    }

    public void removeEntityFromWorld(World world) throws WorldException {

    }
    
    /**
     * returns string of entities
     * @param world
     * @param ent
     * @see implementaion
     * @return
     */

    public Set<? extends Entity> getEntities(World world, String ent) {
        if (Objects.equals(ent, "Ship")) {
            Set<Ship> entities = new HashSet<>();
            for (Entity entity: world.entities) {
                if (entity instanceof Ship) {
                    entities.add((Ship)entity);
                }
            }
            return entities;
        }
        else if (Objects.equals(ent, "Bullet")) {
            Set<Bullet> entities = new HashSet<>();
            for (Entity entity: world.entities) {
                if (entity instanceof Bullet) {
                    entities.add((Bullet)entity);
                }
            }
            return entities;
        }
        else if (Objects.equals(ent, "Planetoid")) {
            Set<Planetoid> entities = new HashSet<>();
            for (Entity entity: world.entities) {
                if (entity instanceof Planetoid) {
                    entities.add((Planetoid) entity);
                }
            }
            return entities;
        }
        else if (Objects.equals(ent, "Asteroid")) {
            Set<Asteroid> entities = new HashSet<>();
            for (Entity entity: world.entities) {
                if (entity instanceof Asteroid) {
                    entities.add((Asteroid) entity);
                }
            }
            return entities;
        }
        return null;
    }

    /**
     * Return the distance between this ship and other ship.
     *
     * The absolute value of the result of this method is the minimum distance
     * either ship should move such that both ships are adjacent. Note that the
     * result must be negative if the ships overlap. The distance between a ship
     * and itself is 0.
     * @param entity
     * 		Ship to compare distance to.
     * @throws IllegalArgumentException
     * @return
     * 		| if (this == ship)
     * 		|		then return 0.0
     * 	    | else
     * 		|   new xDistance == (ship.x - this.x)
     * 		|   new yDistance == (ship.y - this.y)
     * 		|   new distance == Math.sqrt(xDistance*xDistance + yDistance*yDistance) - (ship.radius + this.radius)
     *      |   return distance
     */

    public double getDistanceTo(Entity entity) throws IllegalArgumentException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if (this == entity){
            return 0.0;
        }
        double xDistance = (entity.x - this.x);
        double yDistance = (entity.y - this.y);
        double distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        return distance;
    }

    /**
     * Check whether this ship and other ship overlap. A ship
     * always overlaps with itself.
     * @param entity
     * 		Ship to compare overlap to.
     * @throws IllegalArgumentException
     * @return
     * 		| if (getDistance(ship) < 0) || if (this == ship)
     * 		|		then return true
     * 		| else return false
     */

    public boolean overlap(Entity entity) throws IllegalArgumentException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        double d = getDistanceTo(entity);
        if (d <= 1.01*(this.radius + entity.radius)){
            return true;
        }
        if (this == entity){
            return true;
        }
        else return false;
    }
    
    /**
     * checks whether there is overlap when adding entity to world
     * @param entity
     * @return
     */

    public boolean overlapAddToWorld(Entity entity) {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        double d = Math.hypot(entity.x-this.x,entity.y-this.y)-(entity.radius+this.radius);
        if (d < 0){
            return true;
        }
        if (this == entity){
            return true;
        }
        else return false;
    }

    /**
     * Return the number of seconds until the first collision between
     * this ship and other ship, or Double.POSITIVE_INFINITY if
     * they never collide. A ship never collides with itself.
     * @param ship
     * 		Ship to get time until collision to.
     * @throws IllegalArgumentException
     * @return
     * 		|	if (newDistance <= 0.0)
     * 		|		then return time
     * 		|	if (newDistance >= currentDistance)
     * 		|		then return Double.POSITIVE_INFINITY
     */

    public double getTimeToCollision(Ship ship) throws IllegalArgumentException {
        if (ship == null){
            throw new IllegalArgumentException();
        }
        if (this.xVelocity == ship.xVelocity && this.yVelocity == ship.yVelocity){
        	return Double.POSITIVE_INFINITY;
        }
        double currentDistance = getDistanceTo(ship) - 0.99 * (this.radius + ship.radius);
        double newDistance = Math.sqrt(Math.pow((ship.x + ship.xVelocity * 0.01) - (this.x + this.xVelocity * 0.01), 2) + Math.pow((ship.y + ship.yVelocity * 0.01) - (this.y + (this.yVelocity * 0.01)), 2)) - (this.radius + ship.radius);
        if (currentDistance > newDistance){
            double time = 0.00;
            while (currentDistance > newDistance && newDistance > 0.0){
                time += 0.01;
                currentDistance = newDistance;
                newDistance = Math.sqrt(Math.pow((ship.x + ship.xVelocity * (0.01 + time)) - (this.x + this.xVelocity * (0.01 + time)), 2) + Math.pow((ship.y + ship.yVelocity * (0.01 + time)) - (this.y + this.yVelocity * (0.01 + time)), 2)) - (this.radius + ship.radius);
            }
            if (newDistance <= 0.0){
                return time;
            }
            if (newDistance >= currentDistance){
                return Double.POSITIVE_INFINITY;
            }
        }
        if (this.xVelocity == ship.xVelocity && this.yVelocity == ship.yVelocity){
        	return Double.POSITIVE_INFINITY;
        }
        else{
        	return Double.POSITIVE_INFINITY;
        	}
    }
    
    /**
     * Return the first position where this ship and other ship
     * collide, or null if they never collide. A ship never
     * collides with itself.
     *
     * The result of this method is either null or an array of length 2, where
     * the element at index 0 represents the x-coordinate and the element at
     * index 1 represents the y-coordinate.
     * @param ship
     * 		Ship to get collision point with.
     * @throws IllegalArgumentException
     * @return
     * 		| if (getTimeToCollision(ship) >= POSITIVE_INFINITY)
     *		|		then return null
     *	    | else
     *		|       return new double[] {xCollision, yCollision}
     */

    public double[] getCollisionPosition(Ship ship) throws IllegalArgumentException {
        if (ship == null){
            throw new IllegalArgumentException();
        }
        double time = getTimeToCollision(ship);
        if (getTimeToCollision(ship) >= Double.POSITIVE_INFINITY){
            return null;
        }
        double thisX = this.x + time*this.xVelocity;
        double thisY = this.y + time*this.yVelocity;
        double shipX = ship.x + time*ship.xVelocity;
        double shipY = ship.y + time*ship.yVelocity;
        double distance = Math.sqrt(Math.pow(shipX - thisX, 2) + Math.pow(shipY - thisY, 2));
        double T = (ship.radius / distance);
        double xCollision = (1 - T) * shipX + T * thisX;
        double yCollision = (1 - T) * shipY + T * thisY;
        return new double[] {xCollision, yCollision};
    }
    
    /**
     * return the time until the next collision with a world boundary in world
     * @return
     * @see implementation
     */

    public double getTimeToCollisionBoundary() {
        if (this.getWorld() == null) {
            return Double.POSITIVE_INFINITY;
        }
        double width = this.getWorld().getSize()[0];
        double height = this.getWorld().getSize()[1];
        double timeX;
        double timeY;
        if (this.xVelocity > 0){
        	timeX = ((width - this.x) - radius) / this.xVelocity;
        }
        else if (this.xVelocity < 0){
        	timeX = (this.x - radius) / this.xVelocity;
        }
        else{
        	timeX = Double.POSITIVE_INFINITY;
        }
        timeX = Math.abs(timeX);
        if (this.yVelocity > 0){
        	timeY = ((height - this.y) - radius) / this.yVelocity;
        }
        else if (this.yVelocity < 0){
        	timeY = (this.y - radius) / this.yVelocity;
        }
        else{
        	timeY = Double.POSITIVE_INFINITY;
        }
        timeY = Math.abs(timeY);
        if (timeX <= timeY){
    	    if (timeX >= 0) {
                if (this.xVelocity < 0) {
                    this.boundary = 1;
                }
                else {
                    this.boundary = 3;
                }
                return timeX;
            }
            else if (timeY >= 0) {
                if (this.yVelocity < 0) {
                    this.boundary = 4;
                }
                else {
                    this.boundary = 2;
                }
                return timeY;
            }
            else {
                return Double.POSITIVE_INFINITY;
            }
        }
        else {
            if (timeY >= 0) {
                if (this.yVelocity < 0) {
                    this.boundary = 4;
                }
                else {
                    this.boundary = 2;
                }
                return timeY;
            }
            else if (timeX >= 0) {
                if (this.xVelocity > 0) {
                    this.boundary = 1;
                }
                else {
                    this.boundary = 3;
                }
                return timeX;
            }
            else {
                return Double.POSITIVE_INFINITY;
            }
        }
    }
    
    /**
     * return the position of the next collision with a world boundary in world
     * @return
     * @see implementation
     */
    
    public double[] getPositionCollisionBoundary() {
    	double time = this.getTimeToCollisionBoundary();
        if (time == Double.POSITIVE_INFINITY) {
            return null;
        }
    	double thisX = this.x + time*this.xVelocity;
        double thisY = this.y + time*this.yVelocity;
        double rounding = 0.00001;
        double radius = this.radius;
        if (thisX - radius >= -rounding && thisX - radius <= rounding){
        	return new double[] {0.0, thisY};
        }
        if (thisX + radius >= this.getWorld().getSize()[0] - rounding && thisX + radius <= this.getWorld().getSize()[0] + rounding){
        	return new double[] {this.getWorld().getSize()[0], thisY};
        }
        if (thisY - radius >= -rounding && thisY - radius <= rounding){
        	return new double[] {thisX, 0.0};
        }
        if (thisY + radius >= this.getWorld().getSize()[1] - rounding && thisY + radius <= this.getWorld().getSize()[1] + rounding){
        	return new double[] {thisX, this.getWorld().getSize()[1]};
        }
        else {
        	return null;
        }
    }
    
    /**
     * return the time until the next  collision between entities
     * @param entity
     * @return
     * @see implementation
     * @throws IllegalArgumentException
     */
    
    public double getTimeCollisionEntity(Entity entity) throws IllegalArgumentException {
        if (entity == null){
             throw new IllegalArgumentException();
        }
        if (this instanceof BlackHole && entity instanceof Bullet || this instanceof Bullet && entity instanceof BlackHole) {
            return Double.POSITIVE_INFINITY;
        }
        double cd = (Math.hypot(entity.x-this.x, entity.y-this.y) - (this.radius + entity.radius));
        double nd = (Math.hypot((entity.x + 0.000001*entity.xVelocity)-(this.x + 0.000001*this.xVelocity), (entity.y + 0.000001*entity.yVelocity) - (this.y + 0.000001*this.yVelocity)) - (this.radius + entity.radius));
        if (cd <= nd) {
            return Double.POSITIVE_INFINITY;
        }
        double[] r = {entity.x - this.x, entity.y - this.y};
        double[] v = {entity.xVelocity - this.xVelocity, entity.yVelocity - this.yVelocity};
        double rr = r[0]*r[0]+r[1]*r[1];
        double vv = v[0]*v[0]+v[1]*v[1];
        if (vv == 0) {
            return Double.POSITIVE_INFINITY;
        }
        double vr = v[0]*r[0]+v[1]*r[1];
        double d = vr*vr-vv*(rr-Math.pow(entity.radius + this.radius,2));
        double value1 = (vr + Math.sqrt(d));
        double value = (-1) * (value1 / vv);
        if (vr >= 0) {
            return Double.POSITIVE_INFINITY;
        }
        else if (d <= 0) {
            return Double.POSITIVE_INFINITY;
        }
        else if (value < 0) {
            return 0.;
        }
        else {
            return value;
        }
    }
    
    /**
     * return the position of the next collision between entities
     * @param entity
     * @return
     * @see implementaion
     * @throws IllegalArgumentException
     */
    
    public double[] getPositionCollisionEntity(Entity entity) throws IllegalArgumentException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        double time = getTimeCollisionEntity(entity);
        if (time >= Double.POSITIVE_INFINITY){
            return null;
        }
        double thisX = this.x + time*this.xVelocity;
        double thisY = this.y + time*this.yVelocity;
        double shipX = entity.x + time*entity.xVelocity;
        double shipY = entity.y + time*entity.yVelocity;
        double distance = Math.sqrt(Math.pow(shipX - thisX, 2) + Math.pow(shipY - thisY, 2));
        double T = ((entity.radius) / distance);
        double xCollision = (1 - T) * shipX + T * thisX;
        double yCollision = (1 - T) * shipY + T * thisY;
        return new double[] {xCollision, yCollision};
    }
}
