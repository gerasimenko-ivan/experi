package restaurant;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class RestManager {

    public final List<Table> tables; // why FINAL: can be assigned only once
    private List<ClientsGroup> groupsQueue = new ArrayList<>();

    public RestManager (List<Table> tables)
    {
        // TODO
        Collections.sort(tables, new Comparator<Table>() {
            @Override
            public int compare(Table o1, Table o2) {
                return o1.size - o2.size;
            }
        });
        this.tables = tables;
    }

    // new client(s) show up
    public void onArrive (ClientsGroup group)
    {
        Table availableTable = checkAvailableTable(group);

        if (availableTable != null)
            availableTable.seatGroupOnTheTable(group);
        else
            groupsQueue.add(group);
    }

    // client(s) leave, either served or simply abandoning the queue
    public void onLeave (ClientsGroup group)
    {
        // TODO
        Table tableWithGroup = lookup(group);
        if (tableWithGroup != null) {
            tableWithGroup.groupLeaves(group);
            seatGroupFromQueue();
        }
        else
            groupsQueue.remove(group);

    }

    private void seatGroupFromQueue() {
        if (groupsQueue.size() > 0) {
            for (int i = 0; i < groupsQueue.size(); i++) {
                ClientsGroup group = groupsQueue.get(i);
                Table availableTable = checkAvailableTable(group);
                if (availableTable != null) {
                    availableTable.seatGroupOnTheTable(group);
                    groupsQueue.remove(i);
                    i--;
                }
            }
        }
    }

    // return table where a given client group is seated,
    // or null if it is still queuing or has already left
    public Table lookup (ClientsGroup group)
    {
        // TODO
        for (Table table : tables) {
            if (table.isGroupOnTable(group))
                return table;
        }
        return null;
    }

    // returnes suitable table for group or null if it is not available
    public Table checkAvailableTable (ClientsGroup group)
    {
        // TODO
        int numberOfTables = tables.size();
        Table bestPartiallyFreeTable = null;
        for (int i = 0; i < numberOfTables; i++) {
            Table currentTable = tables.get(i);
            if (currentTable.getEmptyChairsCount() >= group.size) {
                if (currentTable.isFree())
                    return currentTable;
                else {
                    if (bestPartiallyFreeTable == null) {
                        bestPartiallyFreeTable = currentTable;
                    } else
                        continue;
                }
            }
        }
        return bestPartiallyFreeTable;
    }

    public int getQueueSize() {
        return groupsQueue.size();
    }
}
