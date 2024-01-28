package com.madanie.malachite_thousand_leaf;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class ProspectHandler{
   

   @Autowired
   private ProspectRepo pr;
   public ProspectHandler load(String file){
  //    try {
  //       List<Prospect> p = new CsvToBeanBuilder(new FileReader(file)).withType(Prospect.class).withThrowExceptions(false).build().parse();
  //       prospects.addAll(p);
  //    } catch (Exception e){e.printStackTrace();}
  //    return this;
   }
   

   public void print(boolean separators) {
      pr.findAll().forEach(p -> {
            if(separators){System.out.println("****************************************************************************************************");}
         System.out.println(p.toString());
            if(separators){System.out.println("****************************************************************************************************");}
      });
   }
}
