package magicpot.hr.lerning.visitor;

import magicpot.hr.lerning.prototype.Entity.Cat;
import magicpot.hr.lerning.prototype.Entity.Dog;
import magicpot.hr.lerning.prototype.Entity.Sheep;

/**
 * Created by XXX on 15.10.2015..
 */
public interface Visitor {

    //overload
    double visit(Sheep s);
    double visit(Dog d);
    double visit(Cat c);
}
