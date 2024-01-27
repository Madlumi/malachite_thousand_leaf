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

   @GetMapping("/")
   public String home(){
      return "index";
   }
   @GetMapping("/mortage")
   public String mc(Model model){
      List<String> prospectStrings = new ArrayList<>();
      for(int i = 0; i < 100; i++){
         prospectStrings.add("test:");
      }
      model.addAttribute("prospects", prospectStrings);
      return "mortage";
   }


      @PostMapping("/mortage")
   public String mcp(@RequestParam(value = "customer") String customer,
                     @RequestParam(value = "total") String total,
                     @RequestParam(value = "interest") String interest,
                     @RequestParam(value = "years") int years
                     ) {
      return "redirect:/mortage";
   }
}

