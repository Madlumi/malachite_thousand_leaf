package com.madanie.malachite_thousand_leaf;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
   public String mortageCtr(Model model ){
      List<String> pstr = new ArrayList<>();
      pr.findAll().forEach(p -> {
         pstr.add(p.toString());
      });
      model.addAttribute("prospects", pstr);
      return "mortage";
   }


   @PostMapping("/mortage")
   public String mortagePostCtr(@RequestParam Map<String,String> m, Model model) { 
      try {
         Prospect p = Prospect.prospectFromMap(m);
         if (p!=null)pr.save(p);
      } catch (Exception e) {
         return "redirect:/mortage?error=true";
      }
      return "redirect:/mortage";
   }
}

