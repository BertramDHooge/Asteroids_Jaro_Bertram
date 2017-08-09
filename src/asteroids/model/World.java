package asteroids.model;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.Basic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jaro Deklerck
 */

public class World {

    private double width;
    private double height;
    protected Set<Entity> entities = new HashSet<>();
    private double MAX_VALUE = Double.MAX_VALUE;

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
        if (!(width <= 0 || width > 0)) {
            width = 0;
        }
        if (!(height <= 0 || height > 0)) {
            height = 0;
        }
        if (width < 0 || width > MAX_VALUE) {
            if (width < 0) {
                this.width = 0;
            }
            if (width > MAX_VALUE) {
                this.width = MAX_VALUE;
            }
        } else {
            this.width = width;
        }
        if (height < 0 || height > MAX_VALUE) {
            if (height < 0) {
                this.height = 0;
            }
            if (height > MAX_VALUE) {
                this.height = MAX_VALUE;
            }
        } else {
            this.height = height;
        }
    }

    @SuppressWarnings("Unnused")
    private void setMAX_VALUE(double value) {
        if (value < MAX_VALUE) {
            this.MAX_VALUE = value;
        }
    }

    /**
     * Return the size of world as an array containing the width,
     * followed by the height.
     * @return new double[] {this.width, this.height}
     */
    
    @Basic
    public double[] getSize() {return new double[] {this.width, this.height};}

    public void addToWorld(Entity entity) throws WorldException, EntityException {
        if (entity == null) {
            throw new WorldException("Entity doesn't exist");
        }
        entity.addEntityToWorld(this);
    }

    public void addMultipleToWorld(Collection<Entity> entities) throws WorldException, EntityException {
        if (entities == null) {
            throw new WorldException("Entity list doesn't exist");
        }
        for (Entity entity: entities) {
            if (entity != null) {
                entity.addEntityToWorld(this);
            }
            else {
                throw new WorldException("Entity doesn't exist");
            }
        }
    }
    
    /**
     * removes entity from world
     * @param entity
     * @throws WorldException
     */

    public void removeFromWorld(Entity entity) throws WorldException {
        if (entity == null || !entities.contains(entity)) {
            throw new WorldException("Entity doesn't exist or is not situated in the world");
        }
        entity.removeEntityFromWorld(this);
    }
    
    /**
     * removes multiple entities from world
     * @param entities
     * @throws WorldException
     */

    @SuppressWarnings("Unnused")
    public void removeMultipleFromWorld(Collection<Entity> entities) throws WorldException {
        if (entities == null) {
            throw new WorldException("Entity list doesn't exist");
        }
        for (Entity entity: entities) {
            if (entity == null || !this.entities.contains(entity)) {
                throw new WorldException("Entity doesn't exist or is not situated in the world");
            }
            entity.removeEntityFromWorld(this);
        }
    }
    
    /**
     * return entity
     * @param ent
     * @return
     */

    public Set<? extends Entity> getEntities(String ent) {
        Entity entity = null;
        for (Entity entity1: entities) {
            entity = entity1;
            break;
        }
        if (entity != null) {
            return entity.getEntities(this, ent);
        }
        Set<Entity> set = new HashSet<>();
        return set;
    }

    /**
     * Terminates the World.
     */
    @Basic
    public void terminate() throws WorldException {
        for (Entity entity: entities) {
            entity.world = null;
        }
        this.width = Double.NaN;
        this.height = Double.NaN;
        this.entities = null;
    }

    /**
     * Returns the state of the world.
     * @return
     */
    
    @Basic
    public boolean isTerminated() {
        if ((width <= 0 || width > 0) && (height <=0 || height > 0)) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * returns the time until the next collision in world
     * @return
     */

    public double getTimeNextCollision() {
        double bound = getNextBoundaryCollision();
        double ent = getNextEntityCollision();
        return Math.min(bound, ent);
    }
    
    /**
     * returns the time until the next collision with a world boundary in world
     * @return
     */

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
    
    /**
     * return the time until the next collision between entities in world
     * @return
     */

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
    
    /**
     * returns the position of the next collision in world
     * @return
     */

    public double[] getPositionNextCollision() {
        if (this.getTimeNextCollision() != Double.POSITIVE_INFINITY) {
            double bound = getNextBoundaryCollision();
            double ent = getNextEntityCollision();
            if (bound <= ent) {
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
    
    /**
     * progresses the world by an amount of time
     * @param dt
     * @param collisionListener
     * @throws WorldException
     * @throws EntityException
     */

    public void evolve(double dt, CollisionListener collisionListener) throws WorldException, EntityException {
        if (!(dt >= 0 || dt < 0)) {
            throw new WorldException("Dt is NaN");
        }
        if (dt < 0) {
            throw new WorldException("Dt is negative");
        }
        double t = dt;
        while (t > 0) {
            double next = getTimeNextCollision();
            if (next < t) {
                for (Entity entity: entities) {
                    if (!(entity instanceof Ship && ((Ship)entity).getProgram() != null)) {
                        entity.move(next);
                    }
                }
                for (Entity entity: entities) {
                    if (entity instanceof Ship && ((Ship)entity).getProgram() != null) {
                        double s = entities.size();
                        entity.move(next);
                        if (s != entities.size()) {
                            break;
                        }
                    }
                }
                for (Entity entity: entities) {
                    double tb = entity.getTimeToCollisionBoundary();
                    if (tb <= 0.05 && tb >= 0) {
                        boolean q = entity.resolveBoundaryCollision(collisionListener);
                        if (q) {
                            t -= next;
                            break;
                        }
                    }
                }
                entityCollisionLoop:
                for (Entity entity1: entities) {
                    for (Entity entity2: entities) {
                        if (entity1 != entity2) {
                            double tc = entity1.getTimeCollisionEntity(entity2);
                            if (tc <= 0.075 && tc >= 0) {
                                boolean q = entity1.resolveEntityCollision(entity2, collisionListener);
                                if (q) {
                                    break entityCollisionLoop;
                                }
                            }
                        }
                    }
                }
                t -= next;
            }
            else {
                for (Entity entity: entities) {
                    if (!(entity instanceof Ship && ((Ship)entity).getProgram() != null)) {
                        entity.move(t);
                    }
                }
                for (Entity entity: entities) {
                    if (entity instanceof Ship && ((Ship)entity).getProgram() != null) {
                        double s = entities.size();
                        entity.move(t);
                        if (s != entities.size()) {
                            break;
                        }
                    }
                }
                t = 0;
            }
        }
    }

    public Object getEntityAt(double x, double y) {
        for (Entity entity : entities) {
            if (entity.x == x && entity.y == y) {
                return entity;
            }
        }
        return null;
    }

    @Basic
    public Set<? extends Object> getEntities() {
        Set<Entity> ent = new HashSet<>();
        if (entities == null) {
            return ent;
        }
        ent.addAll(entities);
        return ent;
    }
}
