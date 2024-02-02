package com.madanie.malachite_thousand_leaf.Prospect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.madanie.malachite_thousand_leaf.MalachiteThousandLeafApplication;

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
      pr.fromCsv(file);
      pr.printAll();
      return 1;
   }
}
