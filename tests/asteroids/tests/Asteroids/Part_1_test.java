package asteroids.tests.Asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.FacadePart1;
import asteroids.model.Ship;
import asteroids.part1.facade.IFacadePart1;
import asteroids.util.ModelException;

public class Part_1_test {
	private static final double EPSILON = 0.0001;

	IFacadePart1 facade;

	@Before
	public void setUp() {
		facade = new FacadePart1();
	}
	@Test(expected = ModelException.class)
	public void testCreateShipYIsNan() throws ModelException {
		facade.createShip(200, Double.NaN, 10, -10, 20, -Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXVelocityIsNan() throws ModelException {
		facade.createShip(200, 200, Double.NaN, -10, 20, -Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipYVelocityIsNan() throws ModelException {
		facade.createShip(200, 200, 10, Double.NaN, 20, -Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNan() throws ModelException {
		facade.createShip(200, 200, 10, -10, Double.NaN, -Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOrientationIsNan() throws ModelException {
		facade.createShip(200, 200, 10, -10, 20, Double.NaN);
	}
	
	@Test
	public void testCreateShipRadiusIsPositiveInfinity() throws ModelException {
		facade.createShip(200, 200, 10, -10, Double.POSITIVE_INFINITY, Math.PI);
	}
	
	@Test
	public void testCreateShipOrientationIsBiggerThanMaxAngle() throws ModelException {
		facade.createShip(200, 200, 10, -10, 20, 3*Math.PI);
	}
	
	@Test
	public void testCreateShipOrientationIsSmallerThanZero() throws ModelException {
		facade.createShip(200, 200, 10, -10, 20, -3*Math.PI);
	}
	
	@Test
	public void testGetVelocity() throws ModelException {
		Ship ship = facade.createShip(200, 200, 10, -10, 20, -Math.PI);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
		assertEquals(10, velocity[0], EPSILON);
		assertEquals(-10, velocity[1], EPSILON);
	}
	
	@Test
	public void testGetOrientation() throws ModelException {
		Ship ship = facade.createShip(200, 200, 10, -10, 20, -Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
		assertEquals(-Math.PI, orientation, EPSILON);
	}
	
	@Test
	public void testThrust() throws ModelException {
		Ship ship = facade.createShip(200, 200, 10, -10, 20, -Math.PI);
		double orientation = facade.getShipOrientation(ship);
		double[] velocity1 = facade.getShipVelocity(ship);
		facade.thrust(ship, 10);
		double[] velocity2 = facade.getShipVelocity(ship);
		assertNotNull(velocity2);
		assertEquals(velocity1[0] + 10 * Math.cos(orientation), velocity2[0], EPSILON);
		assertEquals(velocity1[1] + 10 * Math.sin(orientation), velocity2[1], EPSILON);
	}
	
	@Test
	public void testCreateShipSpeedAboveLimit() throws ModelException {
		Ship ship = facade.createShip(200, 200, 500000, 400000, 20, -Math.PI);
		double[] velocity = facade.getShipVelocity(ship);
		double Speed = Math.sqrt(Math.pow(500000, 2) + Math.pow(400000, 2));
		double xSpeed = (50 * 30 / Speed) * 100000000;
		double ySpeed = (40 * 30 / Speed) * 100000000;
        assertEquals(velocity[0], xSpeed, EPSILON);
        assertEquals(velocity[1], ySpeed, EPSILON);
	}
	
	@Test
	public void testThrustSpeedAboveLimit() throws ModelException {
		Ship ship = facade.createShip(200, 200, 30000, 25000, 20, -Math.PI);
		double[] velocity1 = facade.getShipVelocity(ship);
		facade.thrust(ship, 500000);
		double[] velocity2 = facade.getShipVelocity(ship);
		double Speed = Math.sqrt(Math.pow(velocity1[0] + 500000 * Math.cos(-Math.PI), 2) + Math.pow(velocity1[1] + 500000 * Math.sin(-Math.PI), 2));
		double xSpeed = ((velocity1[0] + 500000 * Math.cos(-Math.PI)) * 3 / Speed) * 100000;
		double ySpeed = ((velocity1[1] + 500000 * Math.sin(-Math.PI)) * 3 / Speed) * 100000;
        assertEquals(velocity2[0], xSpeed, EPSILON);
        assertEquals(velocity2[1], ySpeed, EPSILON);
	}
	
	@Test
	public void testThrustAmountUnderZero() throws ModelException {
		Ship ship = facade.createShip(200, 200, 420, 666, 20, -Math.PI);
		facade.thrust(ship, -69);
		double[] velocity = facade.getShipVelocity(ship);
        assertEquals(velocity[0], 420, EPSILON);
        assertEquals(velocity[1], 666, EPSILON);
	}
	
	@Test
	public void testTurnOverMaxAngle() throws ModelException {
		Ship ship = facade.createShip(200, 200, 420, 666, 20, 2*Math.PI);
		facade.turn(ship, Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertEquals(orientation, Math.PI, EPSILON);
	}
	
	@Test
	public void testTurnOverMinAngle() throws ModelException {
		Ship ship = facade.createShip(200, 200, 420, 666, 20, Math.PI);
		facade.turn(ship, -2*Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertEquals(orientation, Math.PI, EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testGetDistanceToShipNull() throws ModelException {
		Ship ship = null;
		facade.getDistanceTo(ship, ship);
	}
	
	@Test
	public void testGetDistanceToShipEqual() throws ModelException {
		Ship ship = facade.createShip(200, 200, 420, 666, 20, Math.PI);
		double d = facade.getDistanceTo(ship, ship);
		assertEquals(0, d, EPSILON);
	}
	
	@Test
	public void testGetDistanceToShip() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, 420, 666, 20, Math.PI);
		Ship ship2 = facade.createShip(20, 200, 420, 666, 20, Math.PI);
		double d = facade.getDistanceTo(ship1, ship2);
		double[] position1 = facade.getShipPosition(ship1);
		double[] position2 = facade.getShipPosition(ship2);
		double radius1 = facade.getShipRadius(ship1);
		double radius2 = facade.getShipRadius(ship2);
		assertEquals(Math.sqrt(Math.pow(position2[0] - position1[0], 2) + Math.pow(position2[1] - position1[1], 2)) - (radius1 + radius2), d, EPSILON);
	}
	
	@Test
	public void testOverlap() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, 420, 666, 20, Math.PI);
		Ship ship2 = facade.createShip(20, 200, 420, 666, 20, Math.PI);
		Ship ship3 = facade.createShip(190, 200, 420, 666, 20, Math.PI);
		boolean overlap1 = facade.overlap(ship1, ship2);
		boolean overlap2 = facade.overlap(ship1,  ship3);
		boolean overlap3 = facade.overlap(ship1,  ship1);
		assert !overlap1;
		assert overlap2;
		assert overlap3;
	}
	
	@Test
	public void testCollisionTimeSameOrientationAndSpeed() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, 10, -10, 20, -Math.PI);
		Ship ship2 = facade.createShip(100, 100, 10, -10, 20, -Math.PI);
		double time = facade.getTimeToCollision(ship1, ship2);
		assertEquals(Double.POSITIVE_INFINITY, time, EPSILON);
	}
	
	@Test
	public void testCollisionTime() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, -10, 0, 20, Math.PI);
		Ship ship2 = facade.createShip(100, 200, 0, 0, 20, -Math.PI);
		double time = facade.getTimeToCollision(ship1, ship2);
		assertEquals(6, time, EPSILON);
	}
	
	@Test
	public void testCollisionPositionSameOrientationAndSpeed() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, 10, -10, 20, -Math.PI);
		Ship ship2 = facade.createShip(100, 100, 10, -10, 20, -Math.PI);
		double[] position = facade.getCollisionPosition(ship1, ship2);
		assertNull(position);
	}
	
	@Test
	public void testCollisionPosition() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, -10, 0, 20, -Math.PI);
		Ship ship2 = facade.createShip(100, 200, 0, 0, 20, -Math.PI);
		double[] position = facade.getCollisionPosition(ship1, ship2);
		assertNotNull(position);
		assertEquals(120, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
	}
	
	
}
