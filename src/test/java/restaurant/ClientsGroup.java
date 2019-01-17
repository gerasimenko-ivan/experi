package restaurant;

public class ClientsGroup {
    public final int size; // number of clients


    public ClientsGroup(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "{group of size: " + size + "}";
    }
}
