public class SimpleObject {
    public int id;

    public SimpleObject withID(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        return true;
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        return id;
    }
}
