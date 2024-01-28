package com.madanie.malachite_thousand_leaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import com.madanie.malachite_thousand_leaf.Util.Csv;


import java.util.Map;

@SpringBootApplication
public class MalachiteThousandLeafApplication {


   public static void main(String[] args) {
      SpringApplication.run(MalachiteThousandLeafApplication.class, args);
   }
   @Bean 
   public int initProspects(ProspectRepo pr){
      for (Map<String, String> m : Csv.read_csv("prospects.txt")) {
         Prospect p = Prospect.prospectFromMap(m);
         if (p!=null){pr.save(p);}
      }
      Prospect.printAll(pr);
      return 1;

   }
}
