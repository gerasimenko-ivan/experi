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
        ClientsGroup group3b = new ClientsGroup(3);
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
    public void testTwoTables() {
        List<Table> tables = new ArrayList<>();
        Table table6 = new Table(6);
        tables.add(table6);
        Table table2 = new Table(2);
        tables.add(table2);

        RestManager restManager = new RestManager(tables);

        // check placing on the tables
        ClientsGroup group2 = new ClientsGroup(2);
        restManager.onArrive(group2);
        Assert.assertEquals(restManager.lookup(group2), table2);

        ClientsGroup group6 = new ClientsGroup(6);
        restManager.onArrive(group6);
        Assert.assertEquals(restManager.lookup(group6), table6);


        // check placing into the queue
        ClientsGroup group2b = new ClientsGroup(2);
        int queueSizeBefore = restManager.getQueueSize();
        restManager.onArrive(group2b);
        int queueSizeAfter = restManager.getQueueSize();
        Assert.assertEquals(restManager.lookup(group2b), null);
        Assert.assertEquals(queueSizeAfter, queueSizeBefore + 1);

        // check removing from the queue
        queueSizeBefore = restManager.getQueueSize();
        restManager.onLeave(group2b);
        queueSizeAfter = restManager.getQueueSize();
        Assert.assertEquals(queueSizeAfter, queueSizeBefore - 1);


        // check removing from the table (with empty queue)
        restManager.onLeave(group2);
        Assert.assertEquals(restManager.checkAvailableTable(group2b), table2);



        restManager.onArrive(group2b);
        restManager.onArrive(group2);
        // check removing from the table (with NOT empty queue)
        restManager.onLeave(group2b);
        Assert.assertEquals(restManager.lookup(group2), table2);
        Assert.assertEquals(restManager.getQueueSize(), 0);
    }
}
