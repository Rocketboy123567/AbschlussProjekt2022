package Computer;

public class Stats {
    long health;
    long attack;
    long specialAttack;
    long defence;
    long specialDefence;
    long speed;

    public Stats(long health, long attack, long specialAttack, long defence, long specialDefence, long initiative) {
        this.health = health;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defence = defence;
        this.specialDefence = specialDefence;
        this.speed = initiative;
    }
}
