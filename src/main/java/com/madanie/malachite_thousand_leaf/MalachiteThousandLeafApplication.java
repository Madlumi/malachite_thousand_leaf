package com.madanie.malachite_thousand_leaf;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MalachiteThousandLeafApplication {
   public static ProspectHandler propects;
   public static void main(String[] args) {
      String file = "prospects.txt";
      propects = new ProspectHandler().load(file);
      propects.print(true);
      SpringApplication.run(MalachiteThousandLeafApplication.class, args);
   }
}
