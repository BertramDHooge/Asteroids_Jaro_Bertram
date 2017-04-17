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
    private Set<Ship> ships = new HashSet<>();
    private Set<Bullet> bullets = new HashSet<>();
    private Set<Entity> entities = new HashSet<>();
    private Entity[] nextCollision = {null, null};
    private boolean terminated = false;

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
        double check = ship.getRadius() * 0.99;
        if (ship.getWorld() == null) {
            if (ship.getPosition()[0] > check && ship.getPosition()[1] > check && this.getSize()[0] - ship.getPosition()[0] > check && this.getSize()[1] - ship.getPosition()[1] > check) {
                boolean checkOverlap = false;
                for (Entity entity : entities) {
                    boolean overlap = ship.overlap(entity);
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
        double check = bullet.getRadius() * 0.99;
        if (bullet.getWorld() == null) {
            if (bullet.getPosition()[0] > check && bullet.getPosition()[1] > check && this.getSize()[0] - bullet.getPosition()[0] > check && this.getSize()[1] - bullet.getPosition()[1] > check) {
                boolean checkOverlap = false;
                for (Entity entity : entities) {
                    if (!checkOverlap && bullet.overlap(entity)) {
                        checkOverlap = true;
                        break;
                    }
                }
                if (!checkOverlap) {
                    this.bullets.add(bullet);
                    this.entities.add(bullet);
                    bullet.world = this;
                }
                else {
                    throw new WorldException("Bullet overlaps");
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

    public void terminate() {
        this.terminated = true;
    }

    /**
     * Returns the state of the world.
     * @return
     */

    public boolean isTerminated() {
        return terminated;
    }

    public double getTimeNextCollision() {
        double time = Double.POSITIVE_INFINITY;
        double nextTime;
        for (Entity entity1 : entities) {
            nextTime = entity1.getTimeToCollisionBoundary();
            if (nextTime < time) {
                time = nextTime;
                this.nextCollision[0] = entity1;
                this.nextCollision[1] = null;
            }
            for (Entity entity2 : entities) {
                if (entity1 != entity2) {
                    nextTime = entity1.getTimeCollisionEntity(entity2);
                    if (nextTime < time) {
                        time = nextTime;
                        this.nextCollision[0] = entity1;
                        this.nextCollision[1] = entity2;
                    }
                }
            }
        }
        return time;
    }

    public double[] getPositionNextCollision() {
        if (this.getTimeNextCollision() != Double.POSITIVE_INFINITY) {
            if (this.nextCollision[1] == null) {
                return this.nextCollision[0].getPositionCollisionBoundary();
            } else {
                return this.nextCollision[0].getPositionCollisionEntity(this.nextCollision[1]);
            }
        }
        else {
            return null;
        }
    }

    public void evolve(double dt, CollisionListener collisionListener) throws WorldException {
        double t = dt;
        while (t > 0) {
            double next = getTimeNextCollision();
            if (next < t) {
                if (nextCollision[1] == null) {
                    if (nextCollision[0] instanceof Bullet) {
                        if (((Bullet)nextCollision[0]).getBounces() >= 2) {
                            ((Bullet)nextCollision[0]).terminate();
                            t -= getTimeNextCollision();
                            continue;
                        }
                        else {
                            ((Bullet)nextCollision[0]).addBounce();
                        }
                    }
                    double thisX = nextCollision[0].x + next*nextCollision[0].xVelocity;
                    double thisY = nextCollision[0].y + next*nextCollision[0].yVelocity;
                    double rounding = 0.0000001;
                    double[] pos = {0.0, 0.0};
                    if (thisX - nextCollision[0].getRadius() >= -rounding && thisX - nextCollision[0].getRadius() <= rounding){
                        pos[0] = 0.0;
                        pos[1] = thisY;
                    }
                    if (thisX + nextCollision[0].getRadius() >= this.width - rounding && thisX + nextCollision[0].getRadius() <= this.width + rounding){
                        pos[0] = this.width;
                        pos[1] = thisY;
                    }
                    if (thisY - nextCollision[0].getRadius() >= -rounding && thisY - nextCollision[0].getRadius() <= rounding){
                        pos[0] = thisX;
                        pos[1] = 0.0;
                    }
                    if (thisY + nextCollision[0].getRadius() >= this.height - rounding && thisY + nextCollision[0].getRadius() <= this.height + rounding){
                        pos[0] = thisX;
                        pos[1] = this.height;
                    }
                    if (pos[0] <= 0 || pos[0] >= this.width) {
                        nextCollision[0].setVelocity(-1* nextCollision[0].getVelocity()[0], nextCollision[0].getVelocity()[1]);
                    }
                    else if (pos[1] <= 0 || pos[1] >= this.height) {
                        nextCollision[0].setVelocity(nextCollision[0].getVelocity()[0], -1 * nextCollision[0].getVelocity()[1]);
                    }
                }
                else if (nextCollision[0] instanceof Ship && nextCollision[1] instanceof Ship) {
                    resolveCollision(nextCollision[0], nextCollision[1]);
                }
                else if (nextCollision[0] instanceof  Ship && nextCollision[1] instanceof Bullet) {
                    if (((Bullet)nextCollision[1]).getSource() == nextCollision[0]) {
                        ((Ship)nextCollision[0]).loadBullet((Bullet)nextCollision[1]);
                    }
                    else {
                        ((Ship)nextCollision[0]).terminate();
                        ((Bullet)nextCollision[1]).terminate();
                    }
                }
                else if (nextCollision[0] instanceof  Bullet && nextCollision[1] instanceof Ship) {
                    if (((Bullet)nextCollision[0]).getSource() == nextCollision[1]) {
                        ((Ship)nextCollision[1]).loadBullet((Bullet)nextCollision[0]);
                    }
                    else {
                        ((Ship)nextCollision[1]).terminate();
                        ((Bullet)nextCollision[0]).terminate();
                    }
                }
                else {
                    ((Bullet)nextCollision[0]).terminate();
                    ((Bullet)nextCollision[1]).terminate();
                }
                t -= getTimeNextCollision();
            }
            else {
                for (Ship ship : ships) {
                    ship.move(t);
                    if (ship.isThrusterActive()) {
                        ship.thrust(ship.getAcceleration());
                    }
                }
                for (Bullet bullet : bullets) {
                    bullet.move(t);
                }
            }
        }
    }

    private void resolveCollision(Entity entity1, Entity entity2) {
        double[] r = {entity2.x - entity1.x, entity2.y - entity1.y};
        double[] v = {entity2.xVelocity - entity1.xVelocity, entity2.yVelocity - entity1.yVelocity};
        double vr = v[0]*r[0]+v[1]*r[1];
        double mass1 = ((Ship) entity1).getTotalMass();
        double mass2 = ((Ship) entity2).getTotalMass();
        double j = (2 * mass1 * mass2 * vr) / (entity1.getDistanceTo(entity2)*(mass1 + mass2));
        double jx = j*r[0] / entity1.getDistanceTo(entity2);
        double jy = j*r[1] / entity1.getDistanceTo(entity2);
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
