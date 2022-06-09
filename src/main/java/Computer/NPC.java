package Computer;

public final class NPC extends Computer {
    boolean isFriendly;

    public NPC(String name, boolean isFriendly) {
        super(name);
        this.isFriendly = isFriendly;
    }
}
