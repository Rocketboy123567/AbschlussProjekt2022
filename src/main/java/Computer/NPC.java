package Computer;

public final class NPC extends Computer {
    boolean isFriendly;

    public NPC(String name, String gender, boolean isFriendly) {
        super(name, gender);
        this.isFriendly = isFriendly;
    }
}
