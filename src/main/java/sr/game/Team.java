package sr.game;

public class Team {
    private String name = null;

    public Team(String name) {
        if (null == name || "".equals(name)) throw new IllegalArgumentException();
        this.name = name;
    }

    public boolean equals(Object obj) {
        return obj instanceof Team && this.name.equals(((Team) obj).name);
    }

    @Override
    public int hashCode() {
        return name.length();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
