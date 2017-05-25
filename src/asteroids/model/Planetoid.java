package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * @author Jaro Deklerck
 */
public class Planetoid extends MinorPlanets {

    private double totalTraveledDistance;
    private final double startRadius;
    private final double MASS_DENSITY = 0.917 * Math.pow(10, 12);

    public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double ttd) throws EntityException, WorldException {
        if ((x <= 0 || x > 0) && (y <= 0 || y > 0) && (xVelocity <= 0 || xVelocity > 0) && (yVelocity <= 0 || yVelocity > 0) && (radius <= 0 || radius > 0) && (ttd >= 0 || ttd < 0)){
            setPosition(x, y);
            setVelocity(xVelocity, yVelocity);
            this.startRadius = radius;
            setTotalTraveledDistance(ttd);
            setRadius(startRadius-(0.000001*getTotalTraveledDistance()));
            setMass();
        }
        else {
            throw new EntityException("Values are NaN!");
        }
    }
    
    /**
     * returns the total traveled distance
     * @return
     */

    @Basic
    public double getTotalTraveledDistance() {
        return totalTraveledDistance;
    }

    private void setTotalTraveledDistance(double ttd) {
        this.totalTraveledDistance = ttd;
    }

    /**
     * Sets the mass of the planetoid.
     * @post ...
     *      | new mass == 4/3*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY
     */
    @Basic
    private void setMass() {
        this.mass = 4/3.*Math.PI*Math.pow(this.getRadius(), 3)*MASS_DENSITY;
    }
    
    /**
     * sets the radius of the planetoid
     * @post ...
     * 		| new radius == radius
     */

    @Override
    protected void setRadius(double radius) throws EntityException, WorldException {
        if (radius >= 5) {
            this.radius = radius;
            setMass();
        }
        else {
            terminate();
        }
    }
    
    /**
     * spawn an asteroid in world
     * @param x
     * 		X value for the location of the planetoid
     * @param y
     * 		Y value for the location of the planetoid
     * @param radius
     * 		radius of the planetoid
     * @param speed
     * 		speed at which the planetoid moves 
     * @param world
     * 		world in which the planetoid is located
     * @throws EntityException
     * @throws WorldException
     */

    public void spawnAsteroids(double x, double y, double radius, double speed, World world) throws EntityException, WorldException {
        double orientation = Math.random() * 2*Math.PI;
        double newX  = x + Math.cos(orientation) * radius / 2;
        double newY = y + Math.sin(orientation) * radius / 2;
        double newVelX = Math.cos(orientation)*speed*1.5;
        double newVelY = Math.sin(orientation)*speed*1.5;
        Asteroid ast1 = new Asteroid(newX*1.01, newY*1.01, newVelX, newVelY, radius / 2);
        ast1.addEntityToWorld(world);
        if (orientation >= Math.PI) {
            orientation -= Math.PI;
        }
        else {
            orientation += Math.PI;
        }
        newX  = x + Math.cos(orientation) * radius / 2;
        newY = y + Math.sin(orientation) * radius / 2;
        newVelX = Math.cos(orientation)*speed*1.5;
        newVelY = Math.sin(orientation)*speed*1.5;
        Asteroid ast2 = new Asteroid(newX*1.01, newY*1.01
                , newVelX, newVelY, radius / 2);
        ast2.addEntityToWorld(world);
    }
    
    /**
     * moves the planetoid
     * @post ...
     * 		|new x == x + dt * xVelocity
     * 		|new y == y + dt * yVelocity
     */

    @Override
    public void move(double dt) throws WorldException, EntityException {
        double prevX = x;
        double prevY = y;
        x += dt * xVelocity;
        y += dt * yVelocity;
        double d = Math.hypot(x-prevX,y-prevY);
        setTotalTraveledDistance(getTotalTraveledDistance()+d);
        setRadius(startRadius-0.000001*getTotalTraveledDistance());
    }
    
    /**
     * terminates a planetoid effectively removing it from the world
     * @post ...
     * 		|new planetoid == null
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
        this.totalTraveledDistance = Double.NaN;
    }
}
