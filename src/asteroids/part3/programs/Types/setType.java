package asteroids.part3.programs.Types;

import asteroids.part3.programs.Type;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jaro Deklerck
 */
public class setType extends Type {

    private Set<? extends Object> set = new HashSet<>();

    public setType(Set<? extends Object> set) {
        setSet(set);
    }

    public Set<? extends Object> getSet() {
        return set;
    }

    public void setSet(Set<? extends Object> set) {
        this.set = set;
    }

    @Override
    public Object get() {
        return this.getSet();
    }
}
