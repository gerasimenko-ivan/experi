package restaurant;

public class ClientsGroup {
    public final int size; // number of clients
    public final String name;


    public ClientsGroup(int size) {
        this.size = size;
        this.name = "";
    }
    public ClientsGroup(int size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{group of size: " + size + "; name: " + name + "}";
    }
}
