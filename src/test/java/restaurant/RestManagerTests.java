package restaurant;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RestManagerTests {

    @Test
    public void constructorRestManagerTest() {
        List<Table> tables = new ArrayList<>();
        tables.add(new Table(2));
        tables.add(new Table(5));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(4));
        tables.add(new Table(6));

        RestManager restManager = new RestManager(tables);
        for (int i = 0; i < restManager.tables.size() - 1; i++) {
            if (restManager.tables.get(i).size > restManager.tables.get(i+1).size)
                Assert.fail("Order of tables in RestManager is wrong {table i: " + i + "; seats: " + restManager.tables.get(i).size +
                        "} is before {table i+1: " + (i+1) + "; seats: " + restManager.tables.get(i+1).size + "}");
        }
    }

    @Test
    public void onArriveTestTwoTables() {
        // data
        List<Table> tables = new ArrayList<>();
        Table table6 = new Table(6);
        tables.add(table6);
        Table table2 = new Table(2);
        tables.add(table2);

        RestManager restManager = new RestManager(tables);
        ClientsGroup group2 = new ClientsGroup(2);
        ClientsGroup group2b = new ClientsGroup(2);
        ClientsGroup group3 = new ClientsGroup(3);
        ClientsGroup group6 = new ClientsGroup(6);

        // test
        restManager.onArrive(group2);
        Assert.assertEquals(restManager.lookup(group2), table2);

        restManager.onArrive(group6);
        Assert.assertEquals(restManager.lookup(group6), table6);


        restManager.onArrive(group2b);
        int queueSizeAfter = restManager.getQueueSize();
        Assert.assertEquals(restManager.lookup(group2b), null);
        Assert.assertEquals(queueSizeAfter, 1);

        restManager.onArrive(group3);
        queueSizeAfter = restManager.getQueueSize();
        Assert.assertEquals(restManager.lookup(group3), null);
        Assert.assertEquals(queueSizeAfter, 2);
    }

    @Test
    public void onArriveTestTwoTablesSharingTables() {
        // data
        List<Table> tables = new ArrayList<>();
        Table table6 = new Table(6);
        tables.add(table6);
        Table table2 = new Table(2);
        tables.add(table2);

        RestManager restManager = new RestManager(tables);
        ClientsGroup group2 = new ClientsGroup(2);
        ClientsGroup group2b = new ClientsGroup(2);
        ClientsGroup group3 = new ClientsGroup(3);
        ClientsGroup group3b = new ClientsGroup(3);
        ClientsGroup group2c = new ClientsGroup(2);

        // test
        restManager.onArrive(group3);
        Assert.assertEquals(restManager.lookup(group3), table6);

        restManager.onArrive(group2);
        Assert.assertEquals(restManager.lookup(group2), table2);

        restManager.onArrive(group2b);
        Assert.assertEquals(restManager.lookup(group2b), table6);

        // onArrive go to queue
        restManager.onArrive(group3b);
        int queueSizeAfter = restManager.getQueueSize();
        Assert.assertEquals(restManager.lookup(group3b), null);
        Assert.assertEquals(queueSizeAfter, 1);

        restManager.onArrive(group2c);
        queueSizeAfter = restManager.getQueueSize();
        Assert.assertEquals(restManager.lookup(group2c), null);
        Assert.assertEquals(queueSizeAfter, 2);
    }

    @Test
    public void onArriveTestTwoTablesOvertakingInQueue() {
        // data
        List<Table> tables = new ArrayList<>();
        Table table6 = new Table(6);
        tables.add(table6);
        Table table2 = new Table(2);
        tables.add(table2);

        RestManager restManager = new RestManager(tables);
        ClientsGroup group2 = new ClientsGroup(2);
        ClientsGroup group2b = new ClientsGroup(2);
        ClientsGroup group2c = new ClientsGroup(2);
        ClientsGroup group2d = new ClientsGroup(2);
        ClientsGroup group3 = new ClientsGroup(3);
        ClientsGroup group4 = new ClientsGroup(4);
        ClientsGroup group5 = new ClientsGroup(5);

        // test
        restManager.onArrive(group2);
        restManager.onArrive(group4);
        restManager.onArrive(group2b);
        Assert.assertEquals(restManager.lookup(group2), table2);
        Assert.assertEquals(restManager.lookup(group2b), table6);
        Assert.assertEquals(restManager.lookup(group4), table6);

        restManager.onArrive(group5);
        restManager.onArrive(group2c);
        restManager.onArrive(group2d);
        restManager.onArrive(group3);
        Assert.assertEquals(restManager.getQueueSize(), 4);

        restManager.onLeave(group4);
        Assert.assertEquals(restManager.lookup(group4), null);
        Assert.assertEquals(restManager.lookup(group2c), table6);
        Assert.assertEquals(restManager.lookup(group2d), table6);
        Assert.assertEquals(restManager.getQueueSize(), 2);
    }


    @Test
    public void complexTest() {
        // data
        List<Table> tables = new ArrayList<>();
        // 9 tables
        tables.add(new Table(2)); // 2 x 3
        tables.add(new Table(5)); // 5 x 1
        tables.add(new Table(2));
        tables.add(new Table(3)); // 3 x 2
        tables.add(new Table(2));
        tables.add(new Table(4)); // 4 x 1
        tables.add(new Table(6)); // 6 x 2
        tables.add(new Table(3));
        tables.add(new Table(6));
        RestManager restManager = new RestManager(tables);

        ClientsGroup group2a = new ClientsGroup(2, "A");
        ClientsGroup group2b = new ClientsGroup(2, "B");
        ClientsGroup group2c = new ClientsGroup(2, "C");
        ClientsGroup group2d = new ClientsGroup(2, "D");
        ClientsGroup group2e = new ClientsGroup(2, "E");
        ClientsGroup group2f = new ClientsGroup(2, "F");

        ClientsGroup group3a = new ClientsGroup(3, "A");
        ClientsGroup group3b = new ClientsGroup(3, "B");
        ClientsGroup group3c = new ClientsGroup(3, "C");

        ClientsGroup group4a = new ClientsGroup(4, "A");
        ClientsGroup group4b = new ClientsGroup(4, "B");
        ClientsGroup group4c = new ClientsGroup(4, "C");
        ClientsGroup group4d = new ClientsGroup(4, "D");

        ClientsGroup group5a = new ClientsGroup(5, "A");
        ClientsGroup group5b = new ClientsGroup(5, "B");
        ClientsGroup group5c = new ClientsGroup(5, "C");

        ClientsGroup group6a = new ClientsGroup(6, "A");
        ClientsGroup group6b = new ClientsGroup(6, "B");
        ClientsGroup group6c = new ClientsGroup(6, "C");

        // test
        restManager.onArrive(group2a);
        Assert.assertEquals(restManager.lookup(group2a).size, 2);

        restManager.onArrive(group6a);
        Assert.assertEquals(restManager.lookup(group6a).size, 6);

        restManager.onArrive(group4a);
        Assert.assertEquals(restManager.lookup(group4a).size, 4);   // all 4-seat-table are busy (just one)

        restManager.onArrive(group4b);
        Assert.assertEquals(restManager.lookup(group4b).size, 5);   // all 5-seat-table are busy (just one)

        restManager.onArrive(group3a);
        Assert.assertEquals(restManager.lookup(group3a).size, 3);

        restManager.onArrive(group2b);
        Assert.assertEquals(restManager.lookup(group2b).size, 2);

        restManager.onArrive(group4c);
        Assert.assertEquals(restManager.lookup(group4c).size, 6);   // all 6-seat-tables are busy (two of them)

        Assert.assertEquals(restManager.getQueueSize(), 0);
        restManager.onArrive(group4d);
        Assert.assertEquals(restManager.lookup(group4d), null);     // group of 4 goes to queue [4d]
        Assert.assertEquals(restManager.getQueueSize(), 1);

        restManager.onArrive(group2c);
        Assert.assertEquals(restManager.lookup(group2c).size, 2);   // all 2-seat-tables are busy (3 of them)

        restManager.onArrive(group2d);
        Assert.assertEquals(restManager.lookup(group2d).size, 3);   // all 3-seat-tables are busy (two of them)

        restManager.onArrive(group2e);
        Assert.assertEquals(restManager.lookup(group2e).size, 6);   // 6-seat-table now has groups (4+2)

        Assert.assertEquals(restManager.getQueueSize(), 1);
        restManager.onArrive(group3b);
        Assert.assertEquals(restManager.lookup(group3b), null);     // group of 3 goes to queue [3b>4d]
        Assert.assertEquals(restManager.getQueueSize(), 2);

        restManager.onArrive(group2f);
        Assert.assertEquals(restManager.lookup(group2f), null);     // group of 2 goes to queue [2f>3b>4d]
        Assert.assertEquals(restManager.getQueueSize(), 3);

        restManager.onArrive(group5a);
        Assert.assertEquals(restManager.lookup(group5a), null);     // group of 5 goes to queue [5a>2f>3b>4d]
        Assert.assertEquals(restManager.getQueueSize(), 4);

        restManager.onArrive(group3c);
        Assert.assertEquals(restManager.lookup(group3b), null);     // group of 3 goes to queue [3c>5a>2f>3b>4d]
        Assert.assertEquals(restManager.getQueueSize(), 5);

        restManager.onArrive(group5b);
        Assert.assertEquals(restManager.lookup(group5b), null);     // group of 5 goes to queue [5b>3c>5a>2f>3b>4d]
        Assert.assertEquals(restManager.getQueueSize(), 6);

        restManager.onLeave(group2a);
        Assert.assertEquals(restManager.lookup(group2f).size, 2);
        Assert.assertEquals(restManager.getQueueSize(), 5);         // group of 2 leaves queue [5b>3c>5a>(2f)>3b>4d]

        restManager.onArrive(group6b);
        Assert.assertEquals(restManager.lookup(group6b), null);     // group of 6 goes to queue [6b>5b>3c>5a>3b>4d]
        Assert.assertEquals(restManager.getQueueSize(), 6);

        restManager.onLeave(group4b);
        Assert.assertEquals(restManager.lookup(group4b), null);
        Assert.assertEquals(restManager.lookup(group4d).size, 5);
        Assert.assertEquals(restManager.getQueueSize(), 5);         // group of 4 leaves queue to seat [6b>5b>3bc>5a>3b>(4d)]

        restManager.onArrive(group6c);
        Assert.assertEquals(restManager.lookup(group6c), null);     // group of 6 goes to queue [6c>6b>5b>3c>5a>3b]
        Assert.assertEquals(restManager.getQueueSize(), 6);

        restManager.onLeave(group2b);
        Assert.assertEquals(restManager.lookup(group2b), null);
        Assert.assertEquals(restManager.getQueueSize(), 6);         // no changes

        restManager.onLeave(group2c);
        Assert.assertEquals(restManager.lookup(group2c), null);
        Assert.assertEquals(restManager.getQueueSize(), 6);         // no changes

        restManager.onLeave(group4d);
        Assert.assertEquals(restManager.lookup(group4d), null);
        Assert.assertEquals(restManager.lookup(group3b).size, 5);
        Assert.assertEquals(restManager.getQueueSize(), 5);         // group of 3 leaves queue to seat [6c>6b>5b>3c>5a>(3b)]

        restManager.onLeave(group4a);
        Assert.assertEquals(restManager.lookup(group4a), null);
        Assert.assertEquals(restManager.lookup(group3c).size, 4);
        Assert.assertEquals(restManager.getQueueSize(), 4);         // group of 3c leaves queue to table [6c>6b>5b>(3c)>5a]


        Assert.assertEquals(restManager.lookup(group2f).size, 2);
        Assert.assertEquals(restManager.lookup(group3a).size, 3);
        Assert.assertEquals(restManager.lookup(group2d).size, 3);
        Assert.assertEquals(restManager.lookup(group3c).size, 4);
        Assert.assertEquals(restManager.lookup(group3b).size, 5);
        Assert.assertEquals(restManager.lookup(group6a).size, 6);
        Assert.assertEquals(restManager.lookup(group4c).size, 6);
        Assert.assertEquals(restManager.lookup(group2e).size, 6);

        restManager.onLeave(group2e);
        Assert.assertEquals(restManager.lookup(group2e), null);
        Assert.assertEquals(restManager.getQueueSize(), 4);         // no changes

        restManager.onLeave(group6b);
        Assert.assertEquals(restManager.lookup(group6b), null);     // group of 6b leaves queue [6c>(6b)>5b>5a]
        Assert.assertEquals(restManager.getQueueSize(), 3);

        restManager.onLeave(group4c);
        Assert.assertEquals(restManager.lookup(group4c), null);
        Assert.assertEquals(restManager.lookup(group5a).size, 6);   // group of 5a leaves queue to table [6c>5b>(5a)]
        Assert.assertEquals(restManager.getQueueSize(), 2);
    }
}
