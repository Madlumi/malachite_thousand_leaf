package com.madanie.malachite_thousand_leaf.Util;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Csv{
   private static final Logger logger = LoggerFactory.getLogger(Csv.class);

   /** Reads a CSV file and returns its content as a list of maps, 
    * with the first row used as keys,
    * and subsequent rows as values.
    *
    * @param file The path to the CSV file.
    * @return A list of maps representing the CSV content, or an empty list in case of failure. */
   public static List<Map<String, String>> readCsv(final String file){
      List<Map<String, String>> data = new ArrayList<>();
      try(CSVReader reader = new CSVReader(new FileReader(file))){
         String[] fields = reader.readNext();
         if(fields == null){ return data; }

         String[] line;
         while((line = reader.readNext()) != null){
            if(line.length != fields.length){ continue; }
            Map<String, String> row = new HashMap<>();
            for(int i = 0; i < fields.length; i++){ row.put(fields[i], line[i]); }
            data.add(row);
         }

      }catch(IOException | CsvValidationException e){
         logger.error("CSV Parse error:", e);
      }
      return data;
   }
}
