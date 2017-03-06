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
	
	
	public Ship() {
	}

	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) {
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.radius = radius;
		this.orientation = orientation;
	}
	
	public double[] getPosition() {
		return new double[] {x, y};
		}
	
	public double[] getVelocity() {
		return new double[] {xVelocity, yVelocity};
	}

	public double getRadius() {
		return radius;
	}

	public double getOrientation() {
		return orientation;
	}

	public void move(double dt) {
		x += dt * xVelocity;
		y += dt * yVelocity;
	}

	public void thrust(double amount) {
		xVelocity += amount * Math.cos(orientation);
		yVelocity += amount * Math.sin(orientation);
		if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) > Math.pow(SPEED_OF_LIGHT, 2)){
			double Speed = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
			xVelocity = (xVelocity * SPEED_OF_LIGHT) / Speed;
			yVelocity = (yVelocity * SPEED_OF_LIGHT) / Speed;
		}
	}

	public void turn(double angle) {
		orientation += angle;
		while (orientation > MAX_ANGLE){
			orientation -= MAX_ANGLE;
		}
		while (orientation < MIN_ANGLE){
			orientation += MAX_ANGLE;
		}
			
	}

	public double getDistanceTo(Ship ship2) {	
		ship2.getPosition();
		double distance = 0;
		return distance;
	}
	
	public boolean overlap(Ship ship2) {
		if (getDistanceTo(ship2) < 0){
			return true;
		}
		else return false;
	}

	public double getTimeToCollision() {
		return 0;
	}

	public double[] getCollisionPosition() {
		return null;
	}




}
