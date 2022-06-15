package Computer;

public class Attacks {
    long basePower;
    String type;
    String attackType;
    String name;
    long accuracy;
    long pp;
    public Attacks(long accuracy, long basePower, String type, String attackType, String name, long pp){
        this.basePower=basePower;
        this.type=type;
        this.attackType=attackType;
        this.name=name;
        this.accuracy=accuracy;
        this.pp=pp;
    }
}
