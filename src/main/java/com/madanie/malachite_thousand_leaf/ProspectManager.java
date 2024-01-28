
package com.madanie.malachite_thousand_leaf;
import com.madanie.malachite_thousand_leaf.Util.Csv;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Map;

@SpringBootApplication
public class ProspectManager{
   
   private static String file;
   public static void setProspectFile(String f){
      file = f;
   }

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
         double intr = Double.parseDouble(values.get("Interest"));
         int y = Integer.parseInt(values.get("Years"));
         if(y<1){return null;} // we can add more validation here if needed
         return new Prospect(cust, tl, intr , y);
      } catch (Exception e) {
         return null;
      }
   }
}
