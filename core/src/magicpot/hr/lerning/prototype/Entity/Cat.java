package magicpot.hr.lerning.prototype.Entity;

import magicpot.hr.lerning.prototype.Animal;
import magicpot.hr.lerning.visitor.Visitable;
import magicpot.hr.lerning.visitor.Visitor;

/**
 * Created by XXX on 15.10.2015..
 */
public class Cat implements Animal, Visitable{

    private double weight = 0;

    public Cat(double w)
    {
        weight = w;
    }

    @Override
    public Animal makeClone() {
        Cat c = null;
        try {
            c = (Cat) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return c;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double accept(Visitor v) {
        return v.visit(this);
    }
}
