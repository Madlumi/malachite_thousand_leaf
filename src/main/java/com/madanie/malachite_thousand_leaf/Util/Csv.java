package com.madanie.malachite_thousand_leaf.Util;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Csv{
   public static List<Map<String, String>> read_csv(String file){
      //failure returns empty list. 
      List<Map<String, String>> data = new ArrayList<>();
      try (CSVReader reader = new CSVReader(new FileReader(file))) {
         //assume first line declares data fields
         String[] fields = reader.readNext();
         if (fields == null) { return data; }

         String[] line;
         while ((line = reader.readNext()) != null) {
            if (line.length != fields.length) { continue; }
            Map<String, String> row = new HashMap<>();
            for (int i = 0; i < fields.length; i++) { row.put(fields[i], line[i]); }
            data.add(row);
         }

      } catch (IOException | CsvValidationException e) {
         System.err.println("CSV Parse error:");
         e.printStackTrace();
      }
      return data;
   }
}
