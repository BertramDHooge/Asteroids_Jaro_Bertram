package asteroids.model;


public class Ship {


	private double x;
	private double y;
	private double xVelocity;
	private double yVelocity;
	private double radius;
	private double orientation;
	private static final double SPEED_OF_LIGHT = 300000;
	private static final double MAX_ANGLE = 2 * Math.PI;
	private static final double MIN_ANGLE = 0;
	
	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on <code>(0, 0)</code> facing right. Its
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
	 * @post ...
	 * 		| (x != IsNaN) && (y != IsNaN) && (xVelocity != IsNaN) && (yVelocity != IsNaN) && (radius != IsNaN) && (orientation != IsNaN)
	 * @post ...
	 * 		| new x == x
	 * @post ...
	 * 		| new y == y
	 * @post ...
	 * 		| new xVelocity == xVelocity
	 * @post ...
	 * 		| new yVelocity == yVelocity
	 * @post ...
	 * 		| if (radius >= 10)
	 * 		|		then new radius == radius
	 * @post ...
	 * 		| if (orientation < 0)
	 * 		|		then while (this.orientation < 0)
	 * 		| 			do (new this.orientation += 2*Math.PI)
	 * 		|else if (orientation > 2*Math.PI)
	 * 		|		then (new this.orientation % (2*Math.PI)
	 * 		|else 
	 * 		|		new this.orientation == orientation
	 */
	
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws ShipException {
		if ((x <= 0 || x > 0) && (y <= 0 || y > 0) && (xVelocity <= 0 || xVelocity > 0) && (yVelocity <= 0 || yVelocity > 0) && (radius <= 0 || radius > 0) && (orientation <= 0 || orientation > 0)){
			if (!assertOrientation(orientation)){
				this.orientation = (orientation%(Math.PI*2));
			}
			this.orientation = orientation;
			if (x < Double.POSITIVE_INFINITY && x > Double.NEGATIVE_INFINITY && y < Double.POSITIVE_INFINITY && y > Double.NEGATIVE_INFINITY) {
				this.x = x;
				this.y = y;
			}
			else {
	            throw new ShipException("Wrong coordinates!");
	        }
	        if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) < Math.pow(SPEED_OF_LIGHT, 2)) {
	            this.xVelocity = xVelocity;
	            this.yVelocity = yVelocity;
	        }
	        else if (xVelocity < 0) {
	            this.xVelocity = 0;
	        }
	        else if (yVelocity < 0) {
	            this.yVelocity = 0;
	        }
	        else {
	            double Speed = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
	            this.xVelocity = (xVelocity * SPEED_OF_LIGHT) / Speed;
	            this.yVelocity = (yVelocity * SPEED_OF_LIGHT) / Speed;
	        }
			if (radius >= 10) {
				this.radius = radius;
			} 
			else {
				throw new ShipException("Wrong radius!");
			}
		}
		else {
			throw new ShipException("Values are nan!");
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
	 *  Return the velocity of ship as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 * @return new double[] {xVelocity, yVelocity}
	 */
	
	public double[] getVelocity() {
		return new double[] {xVelocity, yVelocity};
	}

	/**
	 * Return the radius of ship.
	 * @return radius
	 */
	
	public double getRadius() {
		return radius;
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
     * @post ...
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
	 * Update ship's position, assuming it moves dt
	 * seconds at its current velocity.
	 * @param dt
	 * 		Amount of time this ship moves.
	 * @post ...
	 * 		|new x == x + (dt * xVelocity)
	 * @post ...
	 * 		|new y == y + (dt * yVelocity)
	 */
	
	public void move(double dt) {
		x += dt * xVelocity;
		y += dt * yVelocity;
	}

	/**
	 * Update ship's velocity based on its current velocity, its
	 * direction and the given amount.
	 * @param amount
	 * 		Amount of change to this ship's velocity.
	 * @post ...
	 * 		| if (amount < 0)
	 * 		| 	then new amount == 0
	 * @post ...
	 * 		|new xVelocity == xVelocity + amount * Math.cos(orientation)
	 * @post ...
	 * 		|new yVelocity == yVelocity + amount * Math.sin(orientation)
	 * @post ...
	 * 		|if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) > Math.pow(SPEED_OF_LIGHT, 2))
	 * 		|		then new Speed == Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2)) && 
	 * 		|		xVelocity = (xVelocity * SPEED_OF_LIGHT) / Speed && 
	 *		|		yVelocity = (yVelocity * SPEED_OF_LIGHT) / Speed
	 * @post ...
	 * 		| if (xVelocity*xVelocity + yVelocity*yVelocity < 0)
	 * 		|		then new MIN_SPEED = 0 && 
	 * 		|		xVelocity = MIN_SPEED &&
	 * 		|		yVelocity = MIN_SPEED
	 */
	
	public void thrust(double amount) {
		if (amount < 0) {
			amount = 0;
		}
		xVelocity += amount * Math.cos(orientation);
		yVelocity += amount * Math.sin(orientation);
		if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) > Math.pow(SPEED_OF_LIGHT, 2)){
			double Speed = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
			xVelocity = (xVelocity * SPEED_OF_LIGHT) / Speed;
			yVelocity = (yVelocity * SPEED_OF_LIGHT) / Speed;
		}
	}
	
	
	/**
	 * Update the direction of ship by adding angle
	 * (in radians) to its current direction. Angle may be
	 * negative.
	 * @param angle
	 * 		Amount this ship turns.
	 * @post ...
	 * 		| new orientation == orientation + angle
	 * @post ...
	 * 		| while (orientation > MAX_ANGLE)
	 * 		|		do new orientation == orientation - MAX_ANGLE
	 * @post ...
	 * 		| while (orientation < MIN_ANGLE)
	 * 		|		do new orientation == orientation + MAX_ANGLE
	 */
	
	public void turn(double angle) {
		orientation += angle;
		while (orientation > MAX_ANGLE){
			orientation -= MAX_ANGLE;
		}
		while (orientation < MIN_ANGLE){
			orientation += MAX_ANGLE;
		}
 	}

	/**
	 * Return the distance between this ship and ship.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 * @param ship
	 * 		Ship to compare distance to.
	 * @post ...
	 * 		| if (this == ship)
	 * 		|		then return 0.0
	 * @post ...
	 * 		| new xDistance == (ship.x - this.x)
	 * 		| new yDistance == (ship.y - this.y)
	 * 		| new distance == Math.sqrt(xDistance*xDistance + yDistance*yDistance) - (ship.radius + this.radius)
	 * @return distance
	 */
	
	public double getDistanceTo(Ship ship) throws ShipException {
		if (ship == null){
			throw new ShipException("Ship does not exist!!!");
		}
		if (this == ship){
			return 0.0;
		}
		double xDistance = (ship.x - this.x);
		double yDistance = (ship.y - this.y);
		double distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance) - (ship.radius + this.radius);
		return distance;
	}
	
	/**
	 * Check whether this ship and ship overlap. A ship
	 * always overlaps with itself.
	 * @param ship
	 * 		Ship to compare overlap to.
	 * @post ...
	 * 		| if (getDistance(ship) < 0) || if (this == ship) 
	 * 		|		then return true 
	 * 		| else return false
	 * @return
	 */
	
	public boolean overlap(Ship ship) throws ShipException {
		if (ship == null){
			throw new ShipException("Ship does not exist!!!");
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
	 * this ship and ship, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 * @param ship
	 * 		Ship to get time until collision to.
	 * @post ...
	 * 		| new currentDistance == getDistanceTo(ship)
	 * 		| new newDistance == Math.sqrt(Math.pow((ship.x + ship.xVelocity * 0.001) - (this.x + this.xVelocity * 0.001), 2) + Math.pow((ship.y + ship.yVelocity * 0.001) - (this.y + (this.yVelocity * 0.001)), 2)) - (this.radius + ship.radius)
	 * 		| if (currentDistance > newDistance)
	 * 		| 		then new time == 0.001 &&
	 * 		|		while (currentDistance > newDistance) 
	 *		|			do new time == time + 0.001 &&
	 *		| 			currentDistance == newDistance &&
	 *		|			newDistance == Math.sqrt(Math.pow((ship.x + ship.xVelocity * (0.001 + time)) - (this.x + this.xVelocity * (0.001 + time)), 2) + Math.pow((ship.y + ship.yVelocity * (0.001 + time)) - (this.y + (this.yVelocity * (0.001 + time))), 2)) - (this.radius + ship.radius)
	 * 		|		if (newDistance <= 0.0)
	 * 		|			then return time
	 * 		|		if (newDistance >= currentDistance)
	 * 		|			then return Double.POSITIVE_INFINITY
	 * @return
	 */
	
	public double getTimeToCollision(Ship ship) throws ShipException {
		if (ship == null){
			throw new ShipException("Ship does not exist!!!");
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
	 * Return the first position where this ship and ship
	 * collide, or null if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 * @param ship 
	 * 		Ship to get collision point with.
	 * @post ...
	 * 		| if (getTimeToCollision(ship) >= POSITIVE_INFINITY)
	 *		|		then return null
	 * @post ...
	 *		| new time = getTimeToCollision(ship) &&
	 *		| new thisX = this.x + time*this.xVelocity &&
			| new thisY = this.y + time*this.yVelocity &&
			| new shipX = ship.x + time*ship.xVelocity && 
			| new shipY = ship.y + time*ship.yVelocity &&
		 	| new distance = getDistanceTo(ship) + ship.radius + this.radius &&
		 	| new T = this.radius / distance &&
			| new xCollision = (1 - T) * thisX + T * shipX &&
			| new yCollision = (1 - T) * thisY + T * shipY
	 * @return new double[] {xCollision, yCollision}
	 */
	
	public double[] getCollisionPosition(Ship ship) throws ShipException {
		if (ship == null){
			throw new ShipException("Ship does not exist!!!");
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




}
