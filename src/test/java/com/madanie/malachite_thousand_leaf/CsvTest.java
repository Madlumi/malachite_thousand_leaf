package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.Test;

import com.madanie.malachite_thousand_leaf.Util.Csv;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class CsvTest {

   /** Test the default file and ensure the map is correct*/
    @Test
    void testReadCsv() {
        String filePath = "testfiles/prospects.txt";  
        List<Map<String, String>> actualdata = Csv.readCsv(filePath);
        assertEquals(4, actualdata.size(), "Number of rows should match");
        List<Map<String, String>> expectedData = List.of(
            Map.of("Customer", "Juha", "Total loan", "1000", "Interest", "5", "Years", "2"),
            Map.of("Customer", "Karvinen", "Total loan", "4356", "Interest", "1.27", "Years", "6"),
            Map.of("Customer", "Claes Månsson", "Total loan", "1300.55", "Interest", "8.67", "Years", "2"),
            Map.of("Customer", "Clarencé,Andersson", "Total loan", "2000", "Interest", "6", "Years", "4")
        );
        assertEquals(expectedData, actualdata);
    }

    /** Test the case of empty file 
     * @return empty list*/
    @Test
    void testReadCsvEmpty() {
        String filePath = "testfiles/empty.txt";  
        List<Map<String, String>> actualdata = Csv.readCsv(filePath);
        assertEquals(0, actualdata.size(), "Number of rows should match");
        List<Map<String, String>> expectedData = List.of();
        assertEquals(expectedData, actualdata);
    }
    
    /** Test Missing file 
     * @return empty list*/
    @Test
    void testReadCsvNoPath() {
        String filePath = "testfiles/noFileFound.txt"; 
        List<Map<String, String>> actualdata = Csv.readCsv(filePath);
        assertEquals(0, actualdata.size(), "Number of rows should match");
        List<Map<String, String>> expectedData = List.of();
        assertEquals(expectedData, actualdata);
    }
    
    /** Test incorect first row. 
     * @return list with entries mathcing incorect header*/
    @Test
    void testReadCsvInvalidlong() {
        String filePath = "testfiles/invalidHeadLong.txt";  
        List<Map<String, String>> actualdata = Csv.readCsv(filePath);
        assertEquals(1, actualdata.size(), "Number of rows should match");
        List<Map<String, String>> expectedData = List.of(
            Map.of("Customer", "Juha", "Total loan", "1000", "Interest", "5", "Years", "2", "Extra","bean" )
              );
        assertEquals(expectedData, actualdata);
    }

    /** Test incorect first row. 
     * @return list with entries mathcing incorect header(none)*/
    @Test
    void testReadCsvInvalidShort() {
        String filePath = "testfiles/invalidHeadShort.txt";  
        List<Map<String, String>> actualdata = Csv.readCsv(filePath);
        assertEquals(0, actualdata.size(), "Number of rows should match");
        List<Map<String, String>> expectedData = List.of();
        assertEquals(expectedData, actualdata);
    }
}
