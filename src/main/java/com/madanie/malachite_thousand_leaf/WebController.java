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

import com.madanie.malachite_thousand_leaf.prospect.ProspectService;

@Controller
public final class WebController {

	@Autowired
	private ProspectService ps;

	@GetMapping("/mortage")
	public String mortageCtr(final Model model) {
		List<String> pstr = new ArrayList<>();
		ps.findAll().forEach(p -> {
			pstr.add(p.toString());
		});
		model.addAttribute("prospects", pstr);
		return "mortage";
	}

	@PostMapping("/mortage")
	public String mortagePostCtr(@RequestParam final Map<String, String> m, final Model model) {
		try {
			ps.save(m);
		} catch (Exception e) {
			return "redirect:/mortage?error=true";
		}
		return "redirect:/mortage";
	}
}
