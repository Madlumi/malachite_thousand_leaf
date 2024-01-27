package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(Web.class)
public class WebTest {

   private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
   private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
   private final PrintStream origOut = System.out;
   private final PrintStream origErr= System.err;

   @BeforeEach
   public void setUpStreams() {
      System.setOut(new PrintStream(outStream));
      System.setErr(new PrintStream(errStream));
   }

   @AfterEach
   public void restoreStreams() {
      System.setOut(origOut);
      System.setErr(origErr);
   }
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private ProspectHandler prospectHandler;

   @BeforeEach
   public void init(){
      MalachiteThousandLeafApplication.init("prospects.txt");
   }

   @Test
   public void testHomeEndpoint() throws Exception {
      mockMvc.perform(get("/"))
         .andExpect(status().isOk())
         .andExpect(view().name("index"));
   }

   @Test
   public void testMortgageEndpoint() throws Exception {
      List<Prospect> prospects = new ArrayList<>();
      when(prospectHandler.getProspectList()).thenReturn(prospects);

      mockMvc.perform(get("/mortage"))
         .andExpect(status().isOk())
         .andExpect(view().name("mortage"))
         .andExpect(model().attributeExists("prospects"));
   }

   @Test
   public void testMortgagePostSuccess() throws Exception {
      mockMvc.perform(post("/mortage")
            .param("customer", "John Doe")
            .param("total", "10000.0")
            .param("interest", "5.0")
            .param("years", "2"))
         .andExpect(status().is3xxRedirection())
         .andExpect(redirectedUrl("/mortage"));
      assertEquals(5, MalachiteThousandLeafApplication.propects.getProspectList().size());
   }

   @Test
   public void testMortgagePostError() throws Exception {
      mockMvc.perform(post("/mortage")
            .param("customer", "John Doe")
            .param("total", "invalidTotal")
            .param("interest", "5.0")
            .param("years", "2"))
         .andExpect(status().is3xxRedirection())
         .andExpect(redirectedUrl("/mortage?error=true"));
      assertEquals(4, MalachiteThousandLeafApplication.propects.getProspectList().size());
   }
}
