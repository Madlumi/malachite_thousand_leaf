package com.madanie.malachite_thousand_leaf.Prospect;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;

import com.madanie.malachite_thousand_leaf.Util.Csv;
public interface ProspectRepo extends CrudRepository<Prospect, Long> {
   default void printAll(){
      StringBuffer buff = new StringBuffer();
      final String SEPARATOR=  
         "****************************************************************************************************\n";
      findAll().forEach(p -> {
         buff.append(SEPARATOR+p.toString()+"\n"+SEPARATOR);
      });
      System.out.println(buff.toString());
   }

   default void prospectsFromCsv(final String file){
      if(file==null){return;}
      for(Map<String, String> m : Csv.readCsv(file)){
         try{
            Prospect p = new Prospect(m);
            if(p!=null){save(p);}
         }catch(Exception e){}
      }
   }
}
