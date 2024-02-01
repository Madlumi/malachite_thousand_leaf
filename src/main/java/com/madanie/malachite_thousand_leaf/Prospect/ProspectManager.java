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
      if(MalachiteThousandLeafApplication.getCmdargs()==null){ return 1; }
      String file = MalachiteThousandLeafApplication.cmdargs.getOptionValue("i", "prospects.txt");
      prospectsFromCsv(pr, file);
      printAll(pr);
      return 1;
   }

   public void prospectsFromCsv(ProspectRepo pr, String file){
      if(file==null){return;}
      for(Map<String, String> m : Csv.readCsv(file)){
         try{
            Prospect p = new Prospect(m);
            if(p!=null){pr.save(p);}
         }catch(Exception e){
            e.printStackTrace();
         }
      }
   }

   public static void printAll(ProspectRepo pr){
      final String SEPARATOR=  
         "****************************************************************************************************";
      pr.findAll().forEach(p -> {
         System.out.println(SEPARATOR);
         System.out.println(p.toString());
         System.out.println(SEPARATOR);
      });
   }

}
