package asteroids.facade;

import asteroids.model.Ship;
import asteroids.model.ShipException;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade  {

	@Override
	public Ship createShip() throws ModelException {
		// TODO Auto-generated method stub
		return new Ship();
	}

	
	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try{
			return new Ship( x,  y,  xVelocity,  yVelocity,  radius,  orientation);
		} catch (ShipException e){
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPosition();
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocity();
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		// TODO Auto-generated method stub
		ship.move(dt);
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		// TODO Auto-generated method stub
		ship.thrust(amount);
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		// TODO Auto-generated method stub
		ship.turn(angle);
	}

	@Override
	public double getDistanceTo(Ship ship1, Ship ship2) throws ModelException {
		try {
			try {
				return ship1.getDistanceTo(ship2);
			} catch (NullPointerException n) {
				throw new ModelException(n);
			}
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
		try{
			try {
				return ship1.overlap(ship2);
			} catch (NullPointerException n) {
					throw new ModelException(n);
			}
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}
	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try{
			try{
				return ship1.getTimeToCollision(ship2);
			} catch (NullPointerException n) {
					throw new ModelException(n);
			}
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try{
			try {
				return ship1.getCollisionPosition(ship2);
			} catch (NullPointerException n) {
					throw new ModelException(n);
			}
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

}
