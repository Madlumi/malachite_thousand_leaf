package com.madanie.malachite_thousand_leaf.Prospect;
import com.madanie.malachite_thousand_leaf.Util.Csv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ProspectManager{
   //could possibly be merged with the prospect class, but not sure is good oop design

   private static String file;
   public static void setProspectFile(String f){ file = f; }
   public static String getProspectFile(){ return file; }

   @Bean 
   public int prospectsFromCsv(ProspectRepo pr){
      if(file!=null){
         for (Map<String, String> m : Csv.read_csv(file)) {
            try {
               Prospect p = new Prospect(m);
               if (p!=null){pr.save(p);}
            } catch (Exception e) {
            }
         }
      }
      printAll(pr);
      return 1;
   }

   public static void printAll(ProspectRepo pr){
      pr.findAll().forEach(p -> {
         System.out.println("****************************************************************************************************");
         System.out.println(p.toString());
         System.out.println("****************************************************************************************************");
      });
   }

}
