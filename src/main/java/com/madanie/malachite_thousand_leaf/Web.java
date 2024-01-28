package com.madanie.malachite_thousand_leaf;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Web {

   @Autowired
   private ProspectRepo pr;
   @GetMapping("/mortage")
   public String mc(Model model ){
      List<String> pstr = new ArrayList<>();
      pr.findAll().forEach(p -> {
         pstr.add(p.toString());
      });
      model.addAttribute("prospects", pstr);
      return "mortage";
   }


   @PostMapping("/mortage")
   public String mcp(@RequestParam(value = "customer") String customer,
         @RequestParam(value = "total") String total,
         @RequestParam(value = "interest") String interest,
         @RequestParam(value = "years") int years,
         Model model){
      try {
         pr.save(new Prospect(customer,Double.parseDouble(total),Double.parseDouble(interest),years ));
      } catch (Exception e) {
         return "redirect:/mortage?error=true";
      }
      return "redirect:/mortage";
   }
}

