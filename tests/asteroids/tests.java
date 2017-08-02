package asteroids;

import static org.junit.Assert.*;

import asteroids.model.*;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.Expressions.Expression;
import asteroids.part3.programs.Expressions.conditionalExpression;
import asteroids.part3.programs.Expressions.doubleLiteralExpression;
import asteroids.part3.programs.Expressions.equalityExpression;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.internal.ProgramParser;
import org.junit.Before;
import org.junit.Test;

import asteroids.facade.FacadePart1;
import asteroids.part1.facade.IFacadePart1;
import asteroids.util.ModelException;

import java.util.List;

public class tests {
	private static final double EPSILON = 0.0001;

	IFacade facade;
    IProgramFactory<?, ?, ?, Program> programFactory;
    World filledWorld;
    Ship ship1, ship2, ship3;
    Bullet bullet1;

    @Before
    public void setUp() throws ModelException {
        facade = new asteroids.facade.Facade();
        programFactory = (IProgramFactory<?, ?, ?, Program>) facade.createProgramFactory();
        filledWorld = facade.createWorld(2000, 2000);
        ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
        for (int i = 1; i < 10; i++) {
            Bullet bulletToLoad = facade.createBullet(100, 120, 0, 0, 10);
            facade.loadBulletOnShip(ship1, bulletToLoad);
        }
        facade.addShipToWorld(filledWorld, ship1);
        ship2 = facade.createShip(200, 220, 10, 5, 50, 0, 1.0E20);
        facade.addShipToWorld(filledWorld, ship2);
        bullet1 = facade.createBullet(300, 320, 10, 5, 50);
        facade.addBulletToWorld(filledWorld, bullet1);
    }

	@Test(expected = ModelException.class)
	public void testCreateShipYIsNan() throws ModelException {
		facade.createShip(200, Double.NaN, 10, -10, 20, Math.PI);
	}

	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNan() throws ModelException {
		facade.createShip(200, 200, 10, -10, Double.NaN, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOrientationIsNan() throws ModelException {
		facade.createShip(200, 200, 10, -10, 20, Double.NaN);
	}
	
	@Test
	public void testCreateShipRadiusIsPositiveInfinity() throws ModelException {
		facade.createShip(200, 200, 10, -10, Double.POSITIVE_INFINITY, Math.PI);
	}
	
	@Test (expected = ModelException.class)
	public void testCreateShipOrientationIsBiggerThanMaxAngle() throws ModelException {
		facade.createShip(200, 200, 10, -10, 20, 3*Math.PI);
	}
	
	@Test (expected = ModelException.class)
	public void testCreateShipOrientationIsSmallerThanZero() throws ModelException {
		facade.createShip(200, 200, 10, -10, 20, -3*Math.PI);
	}
	
	@Test
	public void testGetVelocity() throws ModelException {
		Ship ship = facade.createShip(200, 200, 10, -10, 20, Math.PI);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
		assertEquals(10, velocity[0], EPSILON);
		assertEquals(-10, velocity[1], EPSILON);
	}
	
	@Test
	public void testGetOrientation() throws ModelException {
		Ship ship = facade.createShip(200, 200, 10, -10, 20, Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
		assertEquals(Math.PI, orientation, EPSILON);
	}
	
//	@Test
//	public void testThrust() throws ModelException {
//		Ship ship = facade.createShip(200, 200, 10, -10, 20, Math.PI);
//		double orientation = facade.getShipOrientation(ship);
//		double[] velocity1 = facade.getShipVelocity(ship);
//		facade.thrust(ship, 10);
//		double[] velocity2 = facade.getShipVelocity(ship);
//		assertNotNull(velocity2);
//		assertEquals(velocity1[0] + 10 * Math.cos(orientation), velocity2[0], EPSILON);
//		assertEquals(velocity1[1] + 10 * Math.sin(orientation), velocity2[1], EPSILON);
//	}
	
	@Test
	public void testCreateShipSpeedAboveLimit() throws ModelException {
		Ship ship = facade.createShip(200, 200, 500000, 400000, 20, Math.PI);
		double[] velocity = facade.getShipVelocity(ship);
		double Speed = Math.sqrt(Math.pow(500000, 2) + Math.pow(400000, 2));
		double xSpeed = (50 * 30 / Speed) * 100000000;
		double ySpeed = (40 * 30 / Speed) * 100000000;
        assertEquals(velocity[0], xSpeed, EPSILON);
        assertEquals(velocity[1], ySpeed, EPSILON);
	}

	
//	@Test
//	public void testThrustAmountUnderZero() throws ModelException {
//		Ship ship = facade.createShip(200, 200, 420, 666, 20, Math.PI);
//		facade.thrust(ship, -69);
//		double[] velocity = facade.getShipVelocity(ship);
//        assertEquals(velocity[0], 420, EPSILON);
//        assertEquals(velocity[1], 666, EPSILON);
//	}
	
	@Test
	public void testTurnOverMaxAngle() throws ModelException {
		Ship ship = facade.createShip(200, 200, 420, 666, 20, 2*Math.PI);
		facade.turn(ship, Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertEquals(orientation, Math.PI, EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testGetDistanceToShipNull() throws ModelException {
		Ship ship = null;
		facade.getDistanceBetween(ship, ship);
	}
	
	@Test
	public void testGetDistanceToShipEqual() throws ModelException {
		Ship ship = facade.createShip(200, 200, 420, 666, 20, Math.PI);
		double d = facade.getDistanceBetween(ship, ship);
		assertEquals(0, d, EPSILON);
	}

	@Test
	public void testGetDistanceToShip() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, 420, 666, 20, Math.PI);
		Ship ship2 = facade.createShip(20, 200, 420, 666, 20, Math.PI);
		double d = facade.getDistanceBetween(ship1, ship2);
		double[] position1 = facade.getShipPosition(ship1);
		double[] position2 = facade.getShipPosition(ship2);
		assertEquals(Math.sqrt(Math.pow(position2[0] - position1[0], 2) + Math.pow(position2[1] - position1[1], 2)), d, EPSILON);
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
		Ship ship1 = facade.createShip(200, 200, 10, -10, 20, Math.PI);
		Ship ship2 = facade.createShip(100, 100, 10, -10, 20, Math.PI);
		double time = facade.getTimeToCollision(ship1, ship2);
		assertEquals(Double.POSITIVE_INFINITY, time, EPSILON);
	}
	
	@Test
	public void testCollisionTime() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, -10, 0, 20, Math.PI);
		Ship ship2 = facade.createShip(100, 200, 0, 0, 20, Math.PI);
		double time = facade.getTimeToCollision(ship1, ship2);
		assertEquals(6, time, EPSILON);
	}
	
	@Test
	public void testCollisionPositionSameOrientationAndSpeed() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, 10, -10, 20, Math.PI);
		Ship ship2 = facade.createShip(100, 100, 10, -10, 20, Math.PI);
		double[] position = facade.getCollisionPosition(ship1, ship2);
		assertNull(position);
	}
	
	@Test
	public void testCollisionPosition() throws ModelException {
		Ship ship1 = facade.createShip(200, 200, -10, 0, 20, Math.PI);
		Ship ship2 = facade.createShip(100, 200, 0, 0, 20, Math.PI);
		double[] position = facade.getCollisionPosition(ship1, ship2);
		assertNotNull(position);
		assertEquals(120, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
	}

	@Test
    public void testCreateBlackHole() throws EntityException {
        BlackHole bh = new BlackHole(5,5,800);
        assertEquals(5, bh.getPosition()[0], EPSILON);
        assertEquals(5, bh.getPosition()[1], EPSILON);
        assertEquals(800, bh.getRadius(), EPSILON);
    }

    @Test (expected = EntityException.class)
    public void testCreateBlackHoleWrongRadius() throws EntityException {
        BlackHole bh = new BlackHole(5,5,50);
    }

    @Test
    public void testAddBlackHoleToWorldNoOtherEntities() throws EntityException, WorldException {
	    World w = new World(500,500);
        BlackHole bh = new BlackHole(250,250,100);
        bh.addEntityToWorld(w);
        assertEquals(w, bh.getWorld());
    }

    @Test
    public void testAddBlackHoleToWorldOverlappingShip() throws EntityException, WorldException, ModelException {
        World w = new World(500,500);
        Ship s = facade.createShip(200,250,0,0,50,Math.PI);
        BlackHole bh = new BlackHole(250,250,100);
        s.addEntityToWorld(w);
        bh.addEntityToWorld(w);
        assertEquals(1, w.getEntities().size(), EPSILON);
    }

    @Test
    public void testAddBlackHoleToWorldOverlappingBullet() throws EntityException, WorldException {
        World w = new World(500,500);
        Bullet b = new Bullet(200, 250, 0, 0, 5);
        BlackHole bh = new BlackHole(250,250,100);
        b.addEntityToWorld(w);
        bh.addEntityToWorld(w);
        assertEquals(2, w.getEntities().size(), EPSILON);
    }

    @Test
    public void testAddBlackHoleToWorldOverlappingBlackHole() throws EntityException, WorldException {
        World w = new World(500,500);
        BlackHole bh1 = new BlackHole(250,250,100);
        BlackHole bh2 = new BlackHole(250,250,100);
        bh1.addEntityToWorld(w);
        bh2.addEntityToWorld(w);
        assertEquals(1, w.getEntities().size(), EPSILON);
        assertEquals(200, ((Entity)w.getEntityAt(250, 250)).getRadius(), EPSILON);
    }

    @Test (expected = WorldException.class)
    public void testAddBlackHoleToWorldOutsideBoundary() throws EntityException, WorldException {
        World w = new World(500,500);
        BlackHole bh = new BlackHole(50,250,100);
        bh.addEntityToWorld(w);
    }

    @Test
    public void testEvolveBlackHoleCollisionEntities() throws EntityException, WorldException, ModelException {
        World w = new World(1000,1000);
        Ship s = facade.createShip(500,250,0,200,50,Math.PI);
        Bullet b = new Bullet(500, 750, 0, -200, 5);
        BlackHole bh = new BlackHole(500,500,100);
        s.addEntityToWorld(w);
        b.addEntityToWorld(w);
        bh.addEntityToWorld(w);
        w.evolve(2, null);
        assertEquals(2, w.getEntities().size(), EPSILON);
        assertEquals(b, w.getEntityAt(500,350));
    }

    @Test
    public void testConditionalExpressionTrue() throws ClassNotFoundException {
        Expression<Type> bool = new equalityExpression(new doubleLiteralExpression(5, null), new doubleLiteralExpression(5, null), null);
        Expression<Type> then = new doubleLiteralExpression(1, null);
        Expression<Type> elseExp = new doubleLiteralExpression(2, null);
        Expression<Type> cond = new conditionalExpression(bool, then, elseExp, null);
        double r = (double)cond.evaluate(null,null).get();
        assertEquals(1, r, EPSILON);
    }

    @Test
    public void testConditionalExpressionFalse() throws ClassNotFoundException {
        Expression<Type> bool = new equalityExpression(new doubleLiteralExpression(4, null), new doubleLiteralExpression(5, null), null);
        Expression<Type> then = new doubleLiteralExpression(1, null);
        Expression<Type> elseExp = new doubleLiteralExpression(2, null);
        Expression<Type> cond = new conditionalExpression(bool, then, elseExp, null);
        double r = (double)cond.evaluate(null,null).get();
        assertEquals(2, r, EPSILON);
    }
}
