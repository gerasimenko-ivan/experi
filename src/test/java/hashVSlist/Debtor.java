package hashVSlist;

public class Debtor implements Comparable {
    public String name;
    public int id;

    public Debtor(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "{name: " + name + "}";
    }

    @Override
    public int hashCode() {
        /*int result = 17;
        result = 37 * result + (name == null ? 0 : name.hashCode());
        return result;      //*/

        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //return this.name.equals(((Debtor)obj).name);

        return super.equals(obj);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
