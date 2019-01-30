package compare.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static bad.robot.excel.matchers.WorkbookMatcher.sameWorkbook;

public class TestExcel {

    @Test
    public void test1 () {
        Workbook actual = null;
        Workbook expected_1 = null;
        Workbook expected_2 = null;
        try {
            actual = new HSSFWorkbook(new FileInputStream("C:\\Users\\gis\\IdeaProjects\\experi\\resources\\files\\excelToCompare\\Транспортные средства 30.01.2019.xls"));
            expected_1 = new HSSFWorkbook(new FileInputStream("C:\\Users\\gis\\IdeaProjects\\experi\\resources\\files\\excelToCompare\\Транспортные средства 30.01.2019 (1).xls"));
            expected_2 = new HSSFWorkbook(new FileInputStream("C:\\Users\\gis\\IdeaProjects\\experi\\resources\\files\\excelToCompare\\Транспортные средства 30.01.2019 (2).xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatcherAssert.assertThat(actual, sameWorkbook(expected_1));
        MatcherAssert.assertThat(actual, sameWorkbook(expected_2));
    }

}
