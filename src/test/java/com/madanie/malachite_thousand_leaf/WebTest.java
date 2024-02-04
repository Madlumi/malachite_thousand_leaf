package com.madanie.malachite_thousand_leaf;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.madanie.malachite_thousand_leaf.prospect.ProspectService;

@WebMvcTest(WebController.class)
@ExtendWith(SpringExtension.class)
class WebTest {

	@MockBean
	private ProspectService ps;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testMortagePostCtr_invalidNum() throws Exception {
		// Perform POST request to /mortage with invalid data
		Map<String, String> values = Map.of("Customer", "TestCustomer", "Total loan", "5000", "Interest", "3b.5",
				"Years", "4");

		Mockito.doThrow(new NullPointerException()).when(ps).save(values);

		mockMvc.perform(MockMvcRequestBuilders.post("/mortage").param("Customer", "TestCustomer")
				.param("Total loan", "5000").param("Interest", "3b.5").param("Years", "4"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/mortage?error=true"));
		Mockito.verify(ps, Mockito.times(1)).save(Mockito.any());
	}

	@Test
	void testMortgageCtr() throws Exception {
		// Perform GET request to /mortage
		mockMvc.perform(MockMvcRequestBuilders.get("/mortage")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("prospects"))
				.andExpect(MockMvcResultMatchers.view().name("mortage"));
	}

	@Test
	void testMortgagePostCtr_Success() throws Exception {
		// Perform POST request to /mortage with valid data
		mockMvc.perform(MockMvcRequestBuilders.post("/mortage").param("Customer", "TestCustomer")
				.param("Total loan", "5000").param("Interest", "3").param("Years", "4"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/mortage"));
		Mockito.verify(ps, Mockito.times(1)).save(Mockito.any());
	}
}
