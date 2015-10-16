package magicpot.hr.lerning.prototype;

/**
 * Created by XXX on 14.10.2015..
 */
public class CloneFactory {
    public static Animal getClone(Animal a)
    {
        return a.makeClone();
    }
}
