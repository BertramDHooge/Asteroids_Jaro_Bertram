package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.model.*;
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
		} catch (EntityException e){
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
        try {
            return new Bullet(x, y, xVelocity, yVelocity, radius);
        } catch (EntityException e) {
            throw new ModelException(e);
        }
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
        try {
            return new World(width, height);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		world.terminate();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getSize();
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		// TODO Auto-generated method stub		
		return world.getShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getBullets();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		// TODO Auto-generated method stub
        try {
            world.addShipToWorld(ship);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		// TODO Auto-generated method stub
        try {
            world.removeShipFromWorld(ship);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
        try {
            world.addBulletToWorld(bullet);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
        try {
            world.removeBulletFromWorld(bullet);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getNbBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		ship.loadBullet(bullet);
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		// TODO Auto-generated method stub
		ship.loadBullets(bullets);
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
        try {
            ship.removeBullet(bullet);
        } catch (EntityException e) {
            throw new ModelException(e);
        }
    }

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
        try {
            ship.fireBullet();
        } catch (EntityException e) {
            throw new ModelException(e);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return ((Entity) object).getTimeToCollisionBoundary();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return ((Entity)object).getPositionCollisionBoundary();
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return ((Entity)entity1).getTimeCollisionEntity((Entity)entity2);
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return ((Entity)entity1).getPositionCollisionEntity((Entity)entity2);
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
        try {
            world.evolve(dt, collisionListener);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
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
