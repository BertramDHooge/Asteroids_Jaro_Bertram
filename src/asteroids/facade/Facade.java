package asteroids.facade;

import asteroids.model.*;
import asteroids.part2.CollisionListener;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jaro Deklerck
 */
public class Facade implements IFacade {

    @Override
    public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
        try {
            return ship1.getDistanceTo(ship2);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ModelException(e);
        }
    }


    @Override
    public int getNbStudentsInTeam() {
        return 2;
    }

    @Override
    public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
        return (Set<? extends Asteroid>) world.getEntities("Asteroid");
    }

    @Override
    public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
        try {
            world.addToWorld(asteroid);
        } catch (WorldException | EntityException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
        try {
            world.removeFromWorld(asteroid);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
        return (Set<? extends Planetoid>) world.getEntities("Planetoid");
    }

    @Override
    public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
        try {
            world.addToWorld(planetoid);
        } catch (WorldException | EntityException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
        try {
            world.removeFromWorld(planetoid);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
        try {
            return new Asteroid(x, y, xVelocity, yVelocity, radius);
        } catch (EntityException | WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void terminateAsteroid(Asteroid asteroid) throws ModelException {
        try {
            asteroid.terminate();
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
        return asteroid.isTerminated();
    }

    @Override
    public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
        return asteroid.getPosition();
    }

    @Override
    public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
        return asteroid.getVelocity();
    }

    @Override
    public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
        return asteroid.getRadius();
    }

    @Override
    public double getAsteroidMass(Asteroid asteroid) throws ModelException {
        return asteroid.getMass();
    }

    @Override
    public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
        return asteroid.getWorld();
    }

    @Override
    public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance) throws ModelException {
        try {
            return new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
        } catch (EntityException | WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
        try {
            planetoid.terminate();
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
        return planetoid.isTerminated();
    }

    @Override
    public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
        return planetoid.getPosition();
    }

    @Override
    public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
        return planetoid.getVelocity();
    }

    @Override
    public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
        return planetoid.getRadius();
    }

    @Override
    public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
        return planetoid.getMass();
    }

    @Override
    public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
        return planetoid.getTotalTraveledDistance();
    }

    @Override
    public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
        return planetoid.getWorld();
    }

    @Override
    public Program getShipProgram(Ship ship) throws ModelException {
        return null;
    }

    @Override
    public void loadProgramOnShip(Ship ship, Program program) throws ModelException {

    }

    @Override
    public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
        return null;
    }

    @Override
    public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
        return null;
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
    public void turn(Ship ship, double angle) throws ModelException {
        ship.turn(angle);
    }

    @Override
    public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
        try{
            return ship1.overlap(ship2);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
        try{
            return ship1.getTimeToCollision(ship2);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
        try{
            return ship1.getCollisionPosition(ship2);
        } catch (IllegalArgumentException | NullPointerException e) {
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
        try {
            ship.terminate();
        } catch (WorldException | EntityException e) {
            throw new ModelException(e);
        }
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
        ship.setThruster(active);
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
        try {
            bullet.terminate();
        } catch (WorldException | EntityException e) {
            throw new ModelException(e);
        }
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
        try {
            world.terminate();
        } catch (WorldException e) {
            throw new ModelException(e);
        }
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
        return (Set<? extends Ship>) world.getEntities("Ship");
    }

    @Override
    public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
        // TODO Auto-generated method stub
        return (Set<? extends Bullet>) world.getEntities("Bullet");
    }

    @Override
    public void addShipToWorld(World world, Ship ship) throws ModelException {
        // TODO Auto-generated method stub
        try {
            world.addToWorld(ship);
        } catch (WorldException | EntityException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void removeShipFromWorld(World world, Ship ship) throws ModelException {
        // TODO Auto-generated method stub
        try {
            world.removeFromWorld(ship);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
        // TODO Auto-generated method stub
        try {
            world.addToWorld(bullet);
        } catch (WorldException | EntityException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
        // TODO Auto-generated method stub
        try {
            world.removeFromWorld(bullet);
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
        try {
            ship.loadBullet(bullet, false);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
    }

    @Override
    public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
        // TODO Auto-generated method stub
        try {
            ship.loadBullets(bullets);
        } catch (WorldException e) {
            throw new ModelException(e);
        }
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
        } catch (EntityException | WorldException e) {
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
        } catch (WorldException | EntityException e) {
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
