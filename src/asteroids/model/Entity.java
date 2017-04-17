package asteroids.model;

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
    protected World world = null;
    protected boolean terminated;
    protected static final double SPEED_OF_LIGHT = 300000;


    /**
     * Set the position of the ship
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
        if (x < Double.POSITIVE_INFINITY && x > Double.NEGATIVE_INFINITY && y < Double.POSITIVE_INFINITY && y > Double.NEGATIVE_INFINITY) {
            this.x = x;
            this.y = y;
        }
        else {
            throw new EntityException("Wrong coordinates!");
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
     * Set the velocity of the ship
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
     * Set the radius of the ship
     * @param radius
     * @post ...
     *      | if (radius >= 10)
     *      |   then new radius == radius
     * @exception EntityException
     */

    protected void setRadius(double radius) throws EntityException {
        if (radius >= 10) {
            this.radius = radius;
        }
        else {
            throw new EntityException("Wrong radius!");
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
     * Sets the mass of the ship
     * @param mass
     *      Specified mass for the ship
     * @post ...
     *      | if (mass >= 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY)
     *      |   then new mass == mass
     *      | else
     *      |   then new mass == 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY
     */

    protected void setMass(double mass, double MASS_DENSITY) {
        if (mass >= 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY) {
            this.mass = mass;
        }
        else {
            this.mass = 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
        }
    }

    /**
     * Returns the mass of the ship
     * @return this.mass
     */

    public double getMass() {return this.mass;}

    /**
     * Returns the world the bullet belongs to (returns null if it is loaded in a ship or to the unbounded two-dimensional space).
     * @return
     */

    public World getWorld() {
        return this.world;
    }

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
        double distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance) - (entity.radius + this.radius);
        return distance;
    }

    /**
     * Check whether this ship and other ship overlap. A ship
     * always overlaps with itself.
     * @param ship
     * 		Ship to compare overlap to.
     * @throws IllegalArgumentException
     * @return
     * 		| if (getDistance(ship) < 0) || if (this == ship)
     * 		|		then return true
     * 		| else return false
     */

    public boolean overlap(Ship ship) throws IllegalArgumentException {
        if (ship == null){
            throw new IllegalArgumentException();
        }
        if (getDistanceTo(ship) < 0){
            return true;
        }
        if (this == ship){
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
        double currentDistance = getDistanceTo(ship);
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
        return Double.POSITIVE_INFINITY;
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

    public double getTimeToCollisionBoundary() {
        double width = this.getWorld().getSize()[0];
        double height = this.getWorld().getSize()[1];
        double timeX;
        double timeY;
        if (this.xVelocity > 0){
        	timeX = ((width - this.x) - this.radius) / this.xVelocity; 
        }
        else if (this.xVelocity < 0){
        	timeX = (this.x - this.radius) / this.xVelocity; 
        }
        else{
        	timeX = Double.POSITIVE_INFINITY;
        }
        if (this.yVelocity > 0){
        	timeY = ((height - this.y) - this.radius) / this.yVelocity; 
        }
        else if (this.yVelocity < 0){
        	timeY = (this.y - this.radius) / this.yVelocity;
        }
        else{
        	timeY = Double.POSITIVE_INFINITY;
        }
       if (timeX <= timeY){
    	   return timeX;
       }
       else {
    	   return timeY;
       }
    }
    
    public double[] getPositionCollisionBoundary() {
    	double time = this.getTimeToCollisionBoundary();
    	double thisX = (this.x + time*this.xVelocity);
        double thisY = this.y + time*this.yVelocity;
        if (thisX - radius == 0){
        	return new double[] {thisX - radius, thisY};
        }
        if (thisX + radius == this.getWorld().getSize()[0]){
        	return new double[] {thisX + radius, thisY};
        }
        if (thisY - radius == 0){
        	return new double[] {thisX, thisY - radius};
        }
        if (thisY+ radius == this.getWorld().getSize()[1]){
        	return new double[] {thisX, thisY + radius};
        }
        else {
        	return null;
        }
    }
    
    public double getTimeCollisionEntity(Entity entity) throws IllegalArgumentException {
    	 if (entity == null){
             throw new IllegalArgumentException();
         }
         double currentDistance = getDistanceTo(entity);
         double newDistance = Math.sqrt(Math.pow((entity.x + entity.xVelocity * 0.01) - (this.x + this.xVelocity * 0.01), 2) + Math.pow((entity.y + entity.yVelocity * 0.01) - (this.y + (this.yVelocity * 0.01)), 2)) - (this.radius + entity.radius);
         if (currentDistance > newDistance){
             double time = 0.00;
             while (currentDistance > newDistance && newDistance > 0.0){
                 time += 0.01;
                 currentDistance = newDistance;
                 newDistance = Math.sqrt(Math.pow((entity.x + entity.xVelocity * (0.01 + time)) - (this.x + this.xVelocity * (0.01 + time)), 2) + Math.pow((entity.y + entity.yVelocity * (0.01 + time)) - (this.y + this.yVelocity * (0.01 + time)), 2)) - (this.radius + entity.radius);
             }
             if (newDistance <= 0.0){
                 return time;
             }
             if (newDistance >= currentDistance){
                 return Double.POSITIVE_INFINITY;
             }
         }
         return Double.POSITIVE_INFINITY;

    }
    
    
}
