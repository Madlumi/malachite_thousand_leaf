package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.madanie.malachite_thousand_leaf.prospect.*;

import java.util.Collections;
@WebMvcTest(WebController.class)
@ExtendWith(SpringExtension.class)
class WebTest {
/*
    @MockBean
    private ProspectRepository prospectRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testMortgageCtr() throws Exception {
        // Mock data for the repository
        Mockito.when(prospectRepo.findAll()).thenReturn(Collections.singletonList(new Prospect("TestCustomer", 5000, 3.5, 4)));

        // Perform GET request to /mortage
        mockMvc.perform(MockMvcRequestBuilders.get("/mortage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("prospects"))
                .andExpect(MockMvcResultMatchers.view().name("mortage"));
    }

    @Test
    void testMortgagePostCtr_Success() throws Exception {
        // Perform POST request to /mortage with valid data
        mockMvc.perform(MockMvcRequestBuilders.post("/mortage")
                .param("Customer", "TestCustomer")
                .param("Total loan", "5000")
                .param("Interest", "3")
                .param("Years", "4"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/mortage"));
        Mockito.verify(prospectRepo, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void testMortgagePostCtr_Failure() throws Exception {
        // Perform POST request to /mortage with invalid data
        mockMvc.perform(MockMvcRequestBuilders.post("/mortage")
                .param("InvalidParam", "InvalidValue"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/mortage?error=true"));
        Mockito.verify(prospectRepo, Mockito.never()).save(Mockito.any());
    }
    @Test
    void testMortagePostCtr_invalidNum() throws Exception {
        // Perform POST request to /mortage with invalid data
        mockMvc.perform(MockMvcRequestBuilders.post("/mortage")
                .param("Customer", "TestCustomer")
                .param("Total loan", "5000")
                .param("Interest", "3b.5")
                .param("Years", "4"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/mortage?error=true"));
        Mockito.verify(prospectRepo, Mockito.never()).save(Mockito.any());
    }*/
}
