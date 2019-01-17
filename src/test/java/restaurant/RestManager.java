package restaurant;

/*
* RESTAURANT EXERCISE (please use JAVA 7 syntax)

Your restaurant has a set of tables of different sizes: each table can accommodate 2, 3, 4, 5 or 6 persons. Clients arrive
alone or in groups, up to 6 persons. Clients within a given group must be seated together at one table, hence you can direct
a group only to a table, which can accommodate them all. If there is no table with the required number of empty chairs, the
group has to wait in the queue.

Once seated, the group cannot change the table, i.e. you cannot move a group from one table to another to make room for
new clients.

Client groups must be served in the order of arrival with one exception: if there is enough room at a table for a smaller
group arriving later, you can seat them before the larger group(s) in the queue. For example, if there is a six-person group
waiting for a six-seat table and there is a two-person group queuing or arriving you can send them directly to a table with
two empty chairs.

Groups may share tables, however if at the same time you have an empty table with the required number of chairs and enough
empty chairs at a larger one, you must always seat your client(s) at an empty table and not any partly seated one, even if
the empty table is bigger than the size of the group.

Of course the system assumes that any bigger group may get bored of seeing smaller groups arrive and get their tables ahead
of them, and then decide to leave, which would mean that they abandon the queue without being served.

Please fill RestManager class with appropriate data structures and implement its constructor and three public methods.
You are encouraged modify other classes too (to help us test them) and add new methods at your will.
* */

import java.util.*;

public class RestManager {

    public final List<Table> tables; // why FINAL: can be assigned only once
    private List<ClientsGroup> groupsQueue = new ArrayList<>();

    public RestManager (List<Table> tables)
    {
        // METHOD REQUESTED BY EMPLOYER
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
        // METHOD REQUESTED BY EMPLOYER
        Table availableTable = checkAvailableTable(group);

        if (availableTable != null)
            availableTable.seatGroupOnTheTable(group);
        else
            groupsQueue.add(group);
    }

    // client(s) leave, either served or simply abandoning the queue
    public void onLeave (ClientsGroup group)
    {
        // METHOD REQUESTED BY EMPLOYER
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
        // METHOD REQUESTED BY EMPLOYER
        for (Table table : tables) {
            if (table.isGroupOnTable(group))
                return table;
        }
        return null;
    }

    // returnes suitable table for group or null if it is not available
    public Table checkAvailableTable (ClientsGroup group)
    {
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

    public String getGroupsToTablesDisribution() {
        return "Should return something like {table-2: {group: 2}; table-4: {group: 2 + 2}}";
    }
}
