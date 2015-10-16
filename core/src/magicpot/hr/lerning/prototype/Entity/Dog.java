package magicpot.hr.lerning.prototype.Entity;

import magicpot.hr.lerning.prototype.Animal;
import magicpot.hr.lerning.visitor.Visitable;
import magicpot.hr.lerning.visitor.Visitor;

/**
 * Created by XXX on 15.10.2015..
 */
public class Dog implements Animal, Visitable {
    private double weight = 0;

    public Dog(double w)
    {
        weight = w;
    }

    @Override
    public Animal makeClone() {
        Dog d = null;
        try {
            d = (Dog) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return d;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double accept(Visitor v) {
        return v.visit(this);
    }
}
