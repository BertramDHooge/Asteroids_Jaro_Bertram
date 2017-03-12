package asteroids.model;

import asteroids.util.*;

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
	
	/**
	 * defensief
	 * @param x
	 * @param y
	 * @param xVelocity
	 * @param yVelocity
	 * @param radius
	 * nominaal
	 * @param orientation
	 */
	
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalRadiusException {
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		if (radius >= 10) {
			this.radius = radius;
		} 
		else {
			throw new IllegalRadiusException();
		}
		this.orientation = orientation;
	}
	
	/**
	 * defensief
	 * @return
	 */
	
	public double[] getPosition() {
		return new double[] {x, y};
		}
	
	/**
	 * totaal
	 * @return
	 */
	
	public double[] getVelocity() {
		return new double[] {xVelocity, yVelocity};
	}

	/**
	 * defensief
	 * @return
	 */
	
	public double getRadius() {
		return radius;
	}

	/**
	 * nominaal
	 * @return
	 */
	
	public double getOrientation() {
		return orientation;
	}
	
	/**
	 * defensief
	 * @param dt
	 */
	
	public void move(double dt) {
		x += dt * xVelocity;
		y += dt * yVelocity;
	}

	/**
	 * totaal
	 * @param amount
	 */
	
	public void thrust(double amount) {
		xVelocity += amount * Math.cos(orientation);
		yVelocity += amount * Math.sin(orientation);
		if ((Math.pow(xVelocity, 2) +  Math.pow(yVelocity, 2)) > Math.pow(SPEED_OF_LIGHT, 2)){
			double Speed = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
			xVelocity = (xVelocity * SPEED_OF_LIGHT) / Speed;
			yVelocity = (yVelocity * SPEED_OF_LIGHT) / Speed;
		if (xVelocity*xVelocity + yVelocity*yVelocity < 0){
			double MIN_SPEED = 0;
			xVelocity = MIN_SPEED;
			yVelocity = MIN_SPEED;			
		}
		}
	}
	
	
	/**
	 * nominaal
	 * @param angle
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
	 * defensief
	 * @param ship2
	 * @return
	 */
	
	public double getDistanceBetween(Ship ship1, Ship ship2) {
		ship1.getPosition();
		ship1.getRadius();		
		ship2.getPosition();
		ship2.getRadius();
		double xDistance = Math.abs(ship2.x - ship1.x);
		double yDistance = Math.abs(ship2.y - ship1.y);
		double distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance) - (ship2.radius + ship1.radius);
		return distance;
	}
	
	/**
	 * defensief
	 * @param ship2
	 * @return
	 */
	
	public boolean overlap(Ship ship1, Ship ship2) {
		if (getDistanceBetween(ship1, ship2) < 0){
			return true;
		}
		else return false;
	}

	/**
	 * defensief
	 * @return
	 */
	
	public double getTimeToCollision(Ship ship1, Ship ship2) {
		ship1.getPosition();
		ship1.getVelocity();
		ship2.getPosition();
		ship2.getVelocity();		
		return 0;
	}

	/**
	 * defensief
	 * @return
	 */
	
	public double[] getCollisionPosition(Ship ship1, Ship ship2) {
		ship1.x += getTimeToCollision(ship1, ship2)*ship1.xVelocity;
		ship1.y += getTimeToCollision(ship1, ship2)*ship1.yVelocity;
		ship2.x += getTimeToCollision(ship1, ship2)*ship2.xVelocity;
		ship2.y += getTimeToCollision(ship1, ship2)*ship2.yVelocity;
		CollisionPosition = 
		return double CollisionPosition;
	}




}
