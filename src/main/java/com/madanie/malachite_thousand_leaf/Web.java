package com.madanie.malachite_thousand_leaf;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Web {

   @GetMapping("/mortage")
   public String mc(Model model){
      List<String> prospectStrings = new ArrayList<>();
      for(Prospect p : MalachiteThousandLeafApplication.propects.getProspectList()){
         prospectStrings.add(p.toString());
      }
      model.addAttribute("prospects", prospectStrings);
      return "mortage";
   }


   @PostMapping("/mortage")
   public String mcp(@RequestParam(value = "customer") String customer,
         @RequestParam(value = "total") String total,
         @RequestParam(value = "interest") String interest,
         @RequestParam(value = "years") int years,
         Model model){
      try {
         Prospect p = new Prospect();
         p.setCustomer(customer);
         p.setTotalLoan(Double.parseDouble(total));
         p.setInterest(Double.parseDouble(interest));
         p.setYears(years);
         MalachiteThousandLeafApplication.propects.addProspect(p);
      } catch (Exception e) {
         //e.printStackTrace();
         return "redirect:/mortage?error=true";
      }
      return "redirect:/mortage";
   }
}

