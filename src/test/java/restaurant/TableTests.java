package restaurant;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TableTests {
    @Test
    public void tableConstructorTest() {
        int minSeatsNumber = 2;
        int maxSeatsNumber = 6;

        Table table1 = new Table(minSeatsNumber);
        Assert.assertEquals(table1.size, minSeatsNumber);

        Table table2 = new Table(maxSeatsNumber);
        Assert.assertEquals(table2.size, maxSeatsNumber);
    }

    @Test
    public void getEmptyChairsCountTest() {
        int seatsOnTheTable = 2;
        Table table1 = new Table(seatsOnTheTable);
        ClientsGroup group1 = new ClientsGroup(1);
        ClientsGroup group2 = new ClientsGroup(1);

        Assert.assertEquals(table1.getEmptyChairsCount(), seatsOnTheTable);
        table1.seatGroupOnTheTable(group1);
        Assert.assertEquals(table1.getEmptyChairsCount(), seatsOnTheTable - group1.size);
        table1.seatGroupOnTheTable(group2);
        Assert.assertEquals(table1.getEmptyChairsCount(), seatsOnTheTable - group1.size - group2.size);
    }

    @Test
    public void seatGroupOnTheTableTest() {
        int seatsOnTheTable = 6;
        Table table1 = new Table(seatsOnTheTable);

        ClientsGroup group1 = new ClientsGroup(3);
        ClientsGroup group2 = new ClientsGroup(4);

        table1.seatGroupOnTheTable(group1);
        int emptyChairsCount = table1.getEmptyChairsCount();
        Assert.assertEquals(emptyChairsCount, seatsOnTheTable - group1.size);

        try {
            table1.seatGroupOnTheTable(group2);
            Assert.fail("Exception should be thrown as the number of seats on the table = " + table1.size + " was exceeded, number of guests = " + (group1.size + group2.size));
        } catch (IllegalStateException ex) {
            String expectedExceptionMessage = "Size of group = " + group2.size + " exceeds count (OR NUMBER?????????) of empty chairs = " + emptyChairsCount;
            Assert.assertTrue(ex.getMessage().equals(expectedExceptionMessage), "Exception message was '" + ex.getMessage() + "' + expected = '" + expectedExceptionMessage + "'");
        }

    }
}
