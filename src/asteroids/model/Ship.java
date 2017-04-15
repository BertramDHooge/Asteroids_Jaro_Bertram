package asteroids.model;


public class Ship {


	private double x;
	private double y;
	private double xVelocity;
	private double yVelocity;
	private double radius;
	private double orientation;
    private double mass;
    private boolean thrust = false;
    private boolean terminated;
    private World world;
	private static final double SPEED_OF_LIGHT = 300000;
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
     * @throws ShipException
	 */
	
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass) throws ShipException {
		if ((x <= 0 || x > 0) && (y <= 0 || y > 0) && (xVelocity <= 0 || xVelocity > 0) && (yVelocity <= 0 || yVelocity > 0) && (radius <= 0 || radius > 0) && (orientation <= 0 || orientation > 0) && (mass <= 0 || mass > 0)){
			setPosition(x, y);
            setVelocity(xVelocity, yVelocity);
			setRadius(radius);
            setOrientation(orientation);
            setMass(mass);
		}
		else {
			throw new ShipException("Values are NaN!");
		}
	}

    /**
     * Set the position of the ship
     * @param x
     *      x-coordinate
     * @param y
     *      y-coordinate
     * @throws ShipException
     * @post ...
     *      | if (x < Double.POSITIVE_INFINITY && x > Double.NEGATIVE_INFINITY && y < Double.POSITIVE_INFINITY && y > Double.NEGATIVE_INFINITY)
     * 		|   then new x == x &&
     * 		|       new y == y
     */

	private void setPosition(double x, double y) throws ShipException{
        if (x < Double.POSITIVE_INFINITY && x > Double.NEGATIVE_INFINITY && y < Double.POSITIVE_INFINITY && y > Double.NEGATIVE_INFINITY) {
            this.x = x;
            this.y = y;
        }
        else {
            throw new ShipException("Wrong coordinates!");
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
     * Set the radius of the ship
     * @param radius
     * @post ...
     *      | if (radius >= 10)
     *      |   then new radius == radius
     * @exception ShipException
     */

	private void setRadius(double radius) throws ShipException {
        if (radius >= 10) {
            this.radius = radius;
        }
        else {
            throw new ShipException("Wrong radius!");
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

    private void setMass(double mass) {
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

//	/**
//	 * Update ship's velocity based on its current velocity, its
//	 * direction and the given amount.
//	 * @param amount
//	 * 		Amount of change to this ship's velocity.
//	 * @post ...
//	 * 		| if (amount < 0)
//	 * 		| 	then new amount == 0
//     * @effect setVelocity
//	 */
//
//	public void thrust(double amount) {
//		if (amount < 0) {
//			amount = 0;
//		}
//		setVelocity(this.xVelocity + amount * Math.cos(orientation), this.yVelocity + amount * Math.sin(orientation));
//	}

    /**
     * Turn the thruster on.
     */

    public void thrustOn() {
        this.thrust = true;
    }

    /**
     * Turn the thruster off.
     */

    public void thrustOff() {
        this.thrust = false;
    }

    /**
     * Returns true when the thruster is activated and false when it's deactivated.
     * @return
     */

    public boolean isThrusterActive() {return this.thrust;}

    /**
     * Returns the acceleration of the ship
     * @return a
     */

    public double getAcceleration() {
        double a = (1.1*Math.pow(10, 21))/this.mass;
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
     * Terminates the ship.
     */

 	public void terminate() {
        this.terminated = true;
    }

    /**
     * Returns the state of the ship.
     * @return
     */

    public boolean isTerminated() {return this.terminated;}

    /**
     * Returns the world where the ship is in. If the ship isn't in a world, this will return null.
     * @return
     */

    public World getWorld() {return this.world;}

	/**
	 * Return the distance between this ship and other ship.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 * @param ship
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
	
	public double getDistanceTo(Ship ship) throws IllegalArgumentException {
		if (ship == null){
			throw new IllegalArgumentException();
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




}
