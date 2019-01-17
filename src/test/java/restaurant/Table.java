package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public final int size; // number of chairs
    private List<ClientsGroup> clientsGroups = new ArrayList<>();

    public Table(int size) {
        this.size = size;
    }

    public void seatGroupOnTheTable(ClientsGroup group) {
        if (getEmptyChairsCount() >= group.size) {
            clientsGroups.add(group);
        } else {
            throw new IllegalStateException("Size of group = " + group.size + " exceeds count (OR NUMBER?????????) of empty chairs = " + getEmptyChairsCount());
        }
    }

    public void groupLeaves(ClientsGroup group) {
        clientsGroups.remove(group);
    }

    public boolean isGroupOnTable(ClientsGroup group) {
        if (clientsGroups.size() == 0)
            return false;
        for (ClientsGroup groupToCheck : clientsGroups) {
            if (groupToCheck.equals(group))
                return true;
        }
        return false;
    }

    public boolean isFree() {
        return clientsGroups.size() == 0;
    }

    public int getEmptyChairsCount() {
        if (clientsGroups.size() != 0) {
            int emptyChairsCount = size;
            for (ClientsGroup group : clientsGroups) {
                emptyChairsCount -= group.size;
            }
            return emptyChairsCount;
        }
        return size;
    }

    @Override
    public String toString() {
        return "{table of size: " + size + "}";
    }
}
