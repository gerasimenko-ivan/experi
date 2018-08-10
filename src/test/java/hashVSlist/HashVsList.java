package hashVSlist;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class HashVsList {

    @Test
    public void test() {
        Debtor d1 = new Debtor("d1", 1);
        Debtor d2 = new Debtor("d2", 2);
        Debtor d3 = new Debtor("d3", 3);
        Debtor d3new = new Debtor("d3", 3);

        HashSet<Debtor> debtors = new HashSet<>();
        debtors.add(d1);
        debtors.add(d2);
        debtors.add(d3);

        HashSet<Debtor> debtors2 = new HashSet<>();
        debtors2.add(d3new);
        debtors2.add(d2);
        debtors2.add(d1);

        System.out.println(d3.hashCode());
        System.out.println(d3new.hashCode());

        //Assert.assertEquals(debtors/*ACTUAL*/, debtors2/*EXPECTED*/);


        ArrayList<Debtor> listAct = new ArrayList<>();
        listAct.add(d1);
        listAct.add(d2);
        listAct.add(d3);

        ArrayList<Debtor> listExp = new ArrayList<>();
        listExp.add(d1);
        listExp.add(d2);
        listExp.add(d3new);


        //Collections.sort(listAct);

        Assert.assertEquals(listAct, listExp);
    }
}
