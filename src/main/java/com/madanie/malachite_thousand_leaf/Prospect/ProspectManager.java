package com.madanie.malachite_thousand_leaf.Prospect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

import com.madanie.malachite_thousand_leaf.MalachiteThousandLeafApplication;
import com.madanie.malachite_thousand_leaf.Util.Csv;

@Configuration
public class ProspectManager{
   private static final Logger LOG = LoggerFactory.getLogger(ProspectManager.class);

   /**
    *Initialized the prospect repo with the file provided in args or a default file
    *then prints all the prospect data
    */
   @Bean 
   public int initProspectRepo(ProspectRepo pr){
      if(MalachiteThousandLeafApplication.getCmdargs()==null){ return 1; }
      String file = MalachiteThousandLeafApplication.getCmdargs().getOptionValue("i", "prospects.txt");
      prospectsFromCsv(pr, file);
      printAll(pr);
      return 1;
   }
   public void prospectsFromCsv(final ProspectRepo pr, final String file){
      if(file==null){return;}
      for(Map<String, String> m : Csv.readCsv(file)){
         try{
            Prospect p = new Prospect(m);
            if(p!=null){pr.save(p);}
         }catch(Exception e){
            LOG.debug("invalid prospect: "+e.getMessage(),e);
         }
      }
   }

   public void printAll(final ProspectRepo pr){
      StringBuffer buff = new StringBuffer();
      final String SEPARATOR=  
         "****************************************************************************************************\n";
      pr.findAll().forEach(p -> {
         buff.append(SEPARATOR+p.toString()+"\n"+SEPARATOR);
      });
      System.out.println(buff.toString());
   }

}
