package asteroids.model;

import asteroids.part2.CollisionListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jaro Deklerck
 */
public class World {

    private double width;
    private double height;
    protected Set<Ship> ships = new HashSet<>();
    protected Set<Bullet> bullets = new HashSet<>();
    protected Set<Entity> entities = new HashSet<>();

    /**
     * Creates a world with a width and a height
     * @param width
     *  The width for the new world.
     * @param height
     *  The height for the new world.
     * @throws WorldException
     * @post ...
     *      | (width != IsNaN) && (height != IsNaN)
     * @see implementation
     */

    public World(double width, double height) throws WorldException {
        if ((width <= 0 || width > 0) && (height <= 0 || height > 0)) {
            if (width < 0 || width > Double.MAX_VALUE) {
                if (width < 0) {
                    this.width = 0;
                }
                if (width > Double.MAX_VALUE) {
                    this.width = Double.MAX_VALUE;
                }
            } else {
                this.width = width;
            }
            if (height < 0 || height > Double.MAX_VALUE) {
                if (height < 0) {
                    this.height = 0;
                }
                if (height > Double.MAX_VALUE) {
                    this.height = Double.MAX_VALUE;
                }
            } else {
                this.height = height;
            }
        }
        else {
            throw new WorldException("Values are NaN!");
        }
    }

    /**
     * Return the size of world as an array containing the width,
     * followed by the height.
     * @return new double[] {this.width, this.height}
     */

    public double[] getSize() {return new double[] {this.width, this.height};}

    /**
     * Adds a ship to the world.
     * @param ship
     *      The ship to be added
     * @see implementation
     * @throws WorldException
     */

    public void addShipToWorld(Ship ship) throws WorldException, IllegalArgumentException{
        if (ship == null) {
            throw new IllegalArgumentException();
        }
        double check = ship.getRadius();
        if (ship.getWorld() == null) {
            if (ship.getPosition()[0] >= check && ship.getPosition()[1] >= check && this.getSize()[0] - ship.getPosition()[0] >= check && this.getSize()[1] - ship.getPosition()[1] >= check) {
                boolean checkOverlap = false;
                for (Entity entity : entities) {
                    boolean overlap = ship.overlapAddToWorld(entity);
                    if (overlap) {
                        checkOverlap = true;
                        break;
                    }
                }
                if (!checkOverlap) {
                    this.ships.add(ship);
                    this.entities.add(ship);
                    ship.world = this;
                }
                else {
                    throw new WorldException("Ship overlaps");
                }
            } else {
                throw new WorldException("Ship not located between boundaries");
            }
        }
        else {
            throw new WorldException("Ship already has a world!");
        }
    }

    /**
     * Remove a ship from the world.
     * @param ship
     *      The ship to be removed
     * @see implementation
     * @throws WorldException
     */

    public void removeShipFromWorld(Ship ship) throws WorldException {
        if (ships.contains(ship)) {
            ships.remove(ship);
            entities.remove(ship);
        }
        else {
            throw new WorldException("Ship is not in the world");
        }
    }

    /**
     * Returns the ships in the world
     * @return Set<ship> ships
     */

    public Set<? extends Ship> getShips() {return ships;}

    /**
     * Adds a bullet to the world.
     * @param bullet
     *      The bullet to be added
     * @see implementation
     * @throws WorldException
     */

    public void addBulletToWorld(Bullet bullet) throws WorldException, IllegalArgumentException{
        if (bullet == null) {
            throw new IllegalArgumentException();
        }
        double check = bullet.getRadius();
        if (bullet.getWorld() == null) {
            if (bullet.getPosition()[0] >= check && bullet.getPosition()[1] >= check && this.getSize()[0] - bullet.getPosition()[0] >= check && this.getSize()[1] - bullet.getPosition()[1] >= check) {
                boolean checkOverlap = false;
                Entity ent = null;
                for (Entity entity : entities) {
                    if (bullet.overlapAddToWorld(entity)) {
                        checkOverlap = true;
                        ent = entity;
                        break;
                    }
                }
                if ((!checkOverlap) || (ent != bullet.source)) {
                    this.bullets.add(bullet);
                    this.entities.add(bullet);
                    bullet.world = this;
                }
                else {
                    bullet.terminate();
                    ent.terminate();
                }
            } else {
                throw new WorldException("Bullet not located between boundaries");
            }
        }
        else {
            throw new WorldException("Bullet is already located in a world!");
        }
    }

    /**
     * Remove a bullet from the world.
     * @param bullet
     *      The bullet to be removed
     * @see implementation
     * @throws WorldException
     */

    public void removeBulletFromWorld(Bullet bullet) throws WorldException {
        if (bullets.contains(bullet)) {
            bullets.remove(bullet);
            entities.remove(bullet);
            bullet.world = null;
        }
        else {
            throw new WorldException("Bullet is not in the world");
        }
    }

    /**
     * Returns the bullets in the world.
     * @return Set<Bullet> bullets
     */

    public Set<? extends Bullet> getBullets() {return bullets;}

    /**
     * Terminates the World.
     */

    public void terminate() throws WorldException {
        for (Ship ship: ships) {
            ship.world = null;
        }
        for (Bullet bullet: bullets) {
            bullet.world = null;
        }
        this.width = Double.NaN;
        this.height = Double.NaN;
        this.ships = null;
        this.bullets = null;
        this.entities = null;
    }

    /**
     * Returns the state of the world.
     * @return
     */

    public boolean isTerminated() {
        if ((width <= 0 || width > 0) && (height <=0 || height > 0)) {
            return true;
        }
        else {
            return false;
        }
    }

    public double getTimeNextCollision() {
        double bound = getNextBoundaryCollision();
        double ent = getNextEntityCollision();
        return Math.min(bound, ent);
    }

    private double getNextBoundaryCollision() {
        double time = Double.POSITIVE_INFINITY;
        double nextTime;
        for (Entity entity1 : entities) {
            nextTime = entity1.getTimeToCollisionBoundary();
            if (nextTime < time) {
                time = nextTime;
            }
        }
        return time;
    }

    private double getNextEntityCollision() {
        double time = Double.POSITIVE_INFINITY;
        double nextTime;
        for (Entity entity1 : entities) {
            for (Entity entity2 : entities) {
                if (entity1 != entity2) {
                    nextTime = entity1.getTimeCollisionEntity(entity2);
                    if (nextTime < time) {
                        time = nextTime;
                    }
                }
            }
        }
        return time;
    }

    public double[] getPositionNextCollision() {
        if (this.getTimeNextCollision() != Double.POSITIVE_INFINITY) {
            double bound = getNextBoundaryCollision();
            double ent = getNextEntityCollision();
            if (bound >= ent) {
                for (Entity entity: entities) {
                    if (entity.getTimeToCollisionBoundary() == bound) {
                        return entity.getPositionCollisionBoundary();
                    }
                }
            }
            else {
                for (Entity entity1: entities) {
                    for (Entity entity2: entities) {
                        if (entity1 != entity2 && entity1.getTimeCollisionEntity(entity2) == ent) {
                            return entity1.getPositionCollisionEntity(entity2);
                        }
                    }
                }
            }
        }
        return null;
    }

    public void evolve(double dt, CollisionListener collisionListener) throws WorldException {
        double t = dt;
        while (t > 0) {
            double next = getTimeNextCollision();
            if (next < t) {
                double nextMove = next;
                while (nextMove > .2) {
                    for (Ship ship : ships) {
                        ship.move(.2);
                        if (ship.isThrusterActive()) {
                            ship.thrust(nextMove, ship.getAcceleration());
                        }
                    }
                    for (Bullet bullet : bullets) {
                        bullet.move(.2);
                    }
                    nextMove -= .2;
                }
                for (Ship ship : ships) {
                    ship.move(nextMove);
                    if (ship.isThrusterActive()) {
                        ship.thrust(nextMove, ship.getAcceleration());
                    }
                }
                for (Bullet bullet : bullets) {
                    bullet.move(nextMove);
                }
                entityBoundaryLoop:
                for (Entity entity: entities) {
                    double tb = entity.getTimeToCollisionBoundary();
                    if (tb == 0) {
                        if (entity instanceof Bullet) {
                            if (((Bullet)entity).getBounces() >= 2) {
                                entity.terminate();
                                t -= getTimeNextCollision();
                                break entityBoundaryLoop;
                            }
                            else {
                                ((Bullet)entity).addBounce();
                            }
                        }
                        if (entity.boundary == 1 || entity.boundary == 3) {
                            entity.setVelocity(((double)-1) * entity.getVelocity()[0], entity.getVelocity()[1]);
                        }
                        else if (entity.boundary == 2 || entity.boundary == 4){
                            entity.setVelocity(entity.getVelocity()[0], ((double)-1) * entity.getVelocity()[1]);
                        }
                    }
                }
                entityCollisionLoop:
                for (Entity entity1: entities) {
                    for (Entity entity2: entities) {
                        double cd = (Math.hypot(entity2.x-entity1.x, entity2.y-entity1.y) - (entity1.radius + entity2.radius));
                        double nd = (Math.hypot((entity2.x + 0.000001*entity2.xVelocity)-(entity1.x + 0.000001*entity1.xVelocity), (entity2.y + 0.000001*entity2.yVelocity) - (entity1.y + 0.000001*entity1.yVelocity)) - (entity1.radius + entity2.radius));
                        if (entity1 != entity2 && entity1.overlap(entity2) && cd > nd) {
                            if (entity1 instanceof Ship && entity2 instanceof Ship) {
                                resolveCollision(entity1, entity2);
                            }
                            else if (entity1 instanceof  Ship && entity2 instanceof Bullet) {
                                if (((Bullet)entity2).getSource() == entity1) {
                                    ((Ship)entity1).loadBullet((Bullet)entity2);
                                    break entityCollisionLoop;
                                }
                                else {
                                    entity1.terminate();
                                    entity2.terminate();
                                    break entityCollisionLoop;
                                }
                            }
                            else if (entity1 instanceof  Bullet && entity2 instanceof Ship) {
                                if (((Bullet)entity1).getSource() == entity2) {
                                    ((Ship)entity2).loadBullet((Bullet)entity1);
                                    break entityCollisionLoop;
                                }
                                else {
                                    entity1.terminate();
                                    entity2.terminate();
                                    break entityCollisionLoop;
                                }
                            }
                            else {
                                entity1.terminate();
                                entity2.terminate();
                                break entityCollisionLoop;
                            }
                        }
                    }
                }
                t -= next;
            }
            else {
                while (t > .2) {
                    for (Ship ship : ships) {
                        ship.move(.2);
                        if (ship.isThrusterActive()) {
                            ship.thrust(.2, ship.getAcceleration());
                        }
                    }
                    for (Bullet bullet : bullets) {
                        bullet.move(.2);
                    }
                    t -= .2;
                }
                for (Ship ship : ships) {
                    ship.move(t);
                    if (ship.isThrusterActive()) {
                        ship.thrust(t, ship.getAcceleration());
                    }
                }
                for (Bullet bullet : bullets) {
                    bullet.move(t);
                }
                t = 0;
            }
        }
    }

    private void resolveCollision(Entity entity1, Entity entity2) {
        double[] r = {entity2.x - entity1.x, entity2.y - entity1.y};
        double[] v = {entity2.xVelocity - entity1.xVelocity, entity2.yVelocity - entity1.yVelocity};
        double vr = v[0]*r[0]+v[1]*r[1];
        double mass1 = ((Ship) entity1).getMass();
        double mass2 = ((Ship) entity2).getMass();
        double j = (2 * mass1 * mass2 * vr) / ((entity1.getRadius()+entity2.getRadius())*(mass1 + mass2));
        double jx = j*r[0] / (entity1.getRadius()+entity2.getRadius());
        double jy = j*r[1] / (entity1.getRadius()+entity2.getRadius());
        entity1.setVelocity(entity1.getVelocity()[0]+jx/mass1, entity1.getVelocity()[1]+jy/mass1);
        entity2.setVelocity(entity2.getVelocity()[0]-jx/mass2, entity2.getVelocity()[1]-jy/mass2);
    }

    public Object getEntityAt(double x, double y) {
        for (Entity entity : entities) {
            if (entity.x == x && entity.y == y) {
                return entity;
            }
        }
        return null;
    }

    public Set<? extends Object> getEntities() {
        return entities;
    }
}
