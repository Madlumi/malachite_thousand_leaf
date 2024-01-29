package com.madanie.malachite_thousand_leaf;
import com.madanie.malachite_thousand_leaf.Util.Csv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ProspectManager{

   private static String file;
   public static void setProspectFile(String f){ file = f; }
   public static String getProspectFile(){ return file; }

   @Bean 
   public int prospectsFromCsv(ProspectRepo pr){
      if(file!=null){
         for (Map<String, String> m : Csv.read_csv(file)) {
            Prospect p = prospectFromMap(m);
            if (p!=null){pr.save(p);}
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

   public static Prospect prospectFromMap(Map<String, String> values){
      try {

         String cust = values.get("Customer");
         double tl=Double.parseDouble(values.get("Total loan"));
         //a 0 interest does not work with the provided formulla. If one wanted to offer that option, one could simply return total loan / payments in the calculation method
         double intr = Double.parseDouble(values.get("Interest"));
         int y = Integer.parseInt(values.get("Years"));
         if(y<1 || cust==null || intr == 0){return null;} // we can add more validation here if needed
         return new Prospect(cust, tl, intr , y);
      } catch (Exception e) {
         return null;
      }
   }
}
