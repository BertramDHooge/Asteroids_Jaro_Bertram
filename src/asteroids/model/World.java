package asteroids.model;

import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm;

import java.util.Set;

/**
 * @author Jaro Deklerck
 */
public class World {

    private double width;
    private double height;
    private Set<Ship> ships;
    private Set<Bullet> bullets;
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

    public void addShipToWorld(Ship ship) throws WorldException{
        double check = ship.getRadius() * 99/100;
        if (ship.getWorld() == null) {
            if (ship.getPosition()[0] >= check && ship.getPosition()[1] >= check && this.getSize()[0] - ship.getPosition()[0] >= check && this.getSize()[1] - ship.getPosition()[1] >= check) {
                this.ships.add(ship);
                ship.world = this;
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

    public void addBulletToWorld(Bullet bullet) throws WorldException{
        double check = bullet.getRadius() * 99/100;
        if (bullet.getWorld() == null) {
            if (bullet.getPosition()[0] >= check && bullet.getPosition()[1] >= check && this.getSize()[0] - bullet.getPosition()[0] >= check && this.getSize()[1] - bullet.getPosition()[1] >= check) {
                this.bullets.add(bullet);
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

    }
}
