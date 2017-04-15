package asteroids.facade;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.ShipException;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacadePart2;
import asteroids.util.ModelException;

public class FacadePart2 implements IFacadePart2{

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
	public void turn(Ship ship, double angle) throws ModelException {
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
	
	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass)
			throws ModelException {
		try{
			return new Ship( x,  y,  xVelocity,  yVelocity,  radius,  orientation, mass);
		} catch (ShipException e){
			throw new ModelException(e);
		}
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.isThrusterActive();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		// TODO Auto-generated method
        if (active) {
            ship.thrustOn();
        }
        else {
            ship.thrustOff();
        }
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		// TODO Auto-generated method stub
		return new Bullet(x,  y,  xVelocity,  yVelocity,  radius);
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		bullet.terminate();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getPosition();
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getVelocity();
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getSource();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		// TODO Auto-generated method stub
		return new World(width, height);
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		world.terminateWorld();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.isTerminatedWorld();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getWorldSize;
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		// TODO Auto-generated method stub		
		return world.getWorldShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getWorldBullet();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		world.addShipToWorld(ship);
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		world.removeShipFromWorld(ship);
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		world.addBulletToWorld(bullet);
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		world.removeBulletFromWorld(bullet);
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getBulletsOnShip();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getNbBulletsOnShip();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		ship.loadBulletOnShip(bullet);
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		// TODO Auto-generated method stub
		ship.loadBulletsOnShip(bullet);
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		ship.removeBulletFromShip(bullet);
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		ship.fireBullet();
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return object.getTimeToCollisionBoundary();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return object.getPositionCollisionBoundary();
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return entity1.getTimeCollisionEntity(entity2);
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return entity1.getPositionCollisionEntity(entity2);
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getTimeNextCollision();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getPositionNextCollision();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		// TODO Auto-generated method stub
		world.evolve(dt, collisionListener);
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		// TODO Auto-generated method stub
		return world.getEntityAt(x, y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getEntities();
	}

}
