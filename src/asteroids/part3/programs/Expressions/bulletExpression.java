package asteroids.part3.programs.Expressions;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.part3.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.Type;
import asteroids.part3.programs.Types.objectType;

import java.util.Set;

public class bulletExpression implements Expression<Type> {

    private SourceLocation location;

    public bulletExpression(SourceLocation location) {
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
    public Type evaluate(Ship ship, Function function) throws ClassNotFoundException {
        Set<Bullet> bullets = (Set<Bullet>) ship.getWorld().getEntities("Bullet");
        for (Bullet bullet : bullets) {
            if (bullet.getSource() == ship) {
                return new objectType(bullet);
            }
        }
        throw new ClassNotFoundException("No bullets from ship!!!");
    }

}
