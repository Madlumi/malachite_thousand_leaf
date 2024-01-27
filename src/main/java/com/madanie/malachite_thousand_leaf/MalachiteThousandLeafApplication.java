package com.madanie.malachite_thousand_leaf;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MalachiteThousandLeafApplication {
   public static void main(String[] args) {
      String file = "prospects.txt";
      try {
         List<Prospect> p = new CsvToBeanBuilder(new FileReader(file)).withType(Prospect.class).withThrowExceptions(false).build().parse();
         for(Prospect pr : p ){System.out.println(pr.toString());}
      } catch (Exception e) {e.printStackTrace();}
      //SpringApplication.run(MalachiteThousandLeafApplication.class, args);
   }
}
