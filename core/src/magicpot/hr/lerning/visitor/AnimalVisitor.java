package magicpot.hr.lerning.visitor;

import magicpot.hr.lerning.prototype.Animal;
import magicpot.hr.lerning.prototype.Entity.Cat;
import magicpot.hr.lerning.prototype.Entity.Dog;
import magicpot.hr.lerning.prototype.Entity.Sheep;

/**
 * Created by XXX on 15.10.2015..
 */
public class AnimalVisitor implements Visitor {

    private double factor = 1;

    public AnimalVisitor(double f){ factor = f;}

    @Override
    public double visit(Sheep s) {
        return s.getWeight()*factor;
    }

    @Override
    public double visit(Dog d) {
        return d.getWeight()*factor;
    }

    @Override
    public double visit(Cat c) {
        return c.getWeight()*factor;
    }
}
