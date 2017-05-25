package asteroids.part3.programs.Expressions;

import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.ProgramException;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.objectType;

public class bulletExpression implements Expression<Type> {
	
	private SourceLocation location;

	public bulletExpression(SourceLocation location){
		setSourceLocation(location);
	}

	@Override
	public void setSourceLocation(SourceLocation location) {
		this.location = location;
	}

	@Override
	public SourceLocation getSourceLocation(SourceLocation location) {
		return this.location;
	}

	@Override
	public Type evaluate(Ship ship, Function function) throws ClassNotFoundException, ProgramException {
		Set<Bullet> bullets = (Set<Bullet>)ship.getWorld().getEntities("Bullet");
		for  (Bullet bullet: bullets){
			if (bullet.getSource() == ship){
				return new objectType(bullet);
			}
		}
		throw new ProgramException("No bullets from ship!!!");
	}

}
