package magicpot.hr.lerning.prototype.Entity;

import magicpot.hr.lerning.visitor.Visitable;
import magicpot.hr.lerning.visitor.Visitor;

public class Sheep implements magicpot.hr.lerning.prototype.Animal, Visitable {
    private double weight = 0;

    public Sheep(double w)
    {
        weight = w;
    }

    @Override
    public Sheep makeClone() {
        Sheep s = null;
        try {
            s = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return s;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double accept(Visitor v) {
        return v.visit(this);
    }
}
