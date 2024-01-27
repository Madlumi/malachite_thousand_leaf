package com.madanie.malachite_thousand_leaf;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ProspectHandler{
   public List<Prospect> prospects= new ArrayList<Prospect>();
   public List<Prospect> getProspectList(){return prospects;}

   public ProspectHandler load(String file){
      try {
         List<Prospect> p = new CsvToBeanBuilder(new FileReader(file)).withType(Prospect.class).withThrowExceptions(false).build().parse();
         prospects.addAll(p);
      } catch (Exception e){e.printStackTrace();}
      return this;
   }
   public void print(boolean separators) {
      if(prospects==null){return;}
         for(Prospect p : prospects ){
            if(separators){System.out.println("****************************************************************************************************");}
            System.out.println(p.toString());
            if(separators){System.out.println("****************************************************************************************************");}
         }
   }
}
