package asteroids.model;

/**
 * @author Jaro Deklerck
 */
public class MinorPlanets extends Entity {

    private double MAX_SPEED = SPEED_OF_LIGHT;

    public double getMAX_SPEED() {
        return MAX_SPEED;
    }

    private final void setMAX_SPEED(double MAX_SPEED) {
        if (MAX_SPEED >= 0 && MAX_SPEED <= SPEED_OF_LIGHT) {
            this.MAX_SPEED = MAX_SPEED;
        }
    }

    /**
     * Adds a minor planet to the world.
     * @param world
     *      The world the minor planet has to be added to.
     * @see implementation
     * @throws WorldException
     */
    @Override
    public void addEntityToWorld(World world) throws WorldException, IllegalArgumentException{
        if (world == null) {
            throw new IllegalArgumentException();
        }
        double check = this.getRadius();
        if (this.getWorld() == null) {
            if (this.getPosition()[0] >= check && this.getPosition()[1] >= check && world.getSize()[0] - this.getPosition()[0] >= check && world.getSize()[1] - this.getPosition()[1] >= check) {
                boolean checkOverlap = false;
                for (Entity entity : world.entities) {
                    boolean overlap = this.overlapAddToWorld(entity);
                    if (overlap) {
                        checkOverlap = true;
                        break;
                    }
                }
                if (!checkOverlap) {
                    world.entities.add(this);
                    this.world = world;
                }
                else {
                    throw new WorldException("Minor planet overlaps");
                }
            } else {
                throw new WorldException("Minor planet not located between boundaries");
            }
        }
        else {
            throw new WorldException("Minor planet already has a world!");
        }
    }

    /**
     * Remove a minor planet from the world.
     * @param world
     *      The world the minor planet has to be removed from.
     * @see implementation
     * @throws WorldException
     */
    @Override
    public void removeEntityFromWorld(World world) throws WorldException {
        if (world.entities.contains(this)) {
            world.entities.remove(this);
        }
        else {
            throw new WorldException("Minor planet is not in the world");
        }
    }

    /**
     * Terminates the ship
     */
    @Override
    public void terminate() throws WorldException{
        if (this.world != null) {
            world.removeFromWorld(this);
        }
        this.world = null;
        this.x = Double.NaN;
        this.y = Double.NaN;
        this.xVelocity = Double.NaN;
        this.yVelocity = Double.NaN;
        this.radius = Double.NaN;
        this.mass = Double.NaN;
    }

    public boolean isTerminated() {
        if ((this.x <= 0 || this.x > 0) && (this.y <= 0 || this.y > 0)) {
            return false;
        }
        else {
            return true;
        }
    }
}
