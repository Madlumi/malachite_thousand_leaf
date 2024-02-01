package com.madanie.malachite_thousand_leaf.Prospect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

import com.madanie.malachite_thousand_leaf.MalachiteThousandLeafApplication;
import com.madanie.malachite_thousand_leaf.Util.Csv;

@Configuration
public class ProspectManager{
   @Bean 
   public int initProspectRepo(ProspectRepo pr){
      if (MalachiteThousandLeafApplication.cmdargs!=null){
         String file = MalachiteThousandLeafApplication.cmdargs.getOptionValue("i", "prospects.txt");
         prospectsFromCsv(pr, file);
         printAll(pr);
      }
      return 1;
   }

   public void prospectsFromCsv(ProspectRepo pr, String file){
      if(file!=null){
         for (Map<String, String> m : Csv.read_csv(file)) {
            try {
               Prospect p = new Prospect(m);
               if (p!=null){pr.save(p);}
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   public static void printAll(ProspectRepo pr){
      pr.findAll().forEach(p -> {
         System.out.println("****************************************************************************************************");
         System.out.println(p.toString());
         System.out.println("****************************************************************************************************");
      });
   }

}
