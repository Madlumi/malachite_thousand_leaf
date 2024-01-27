package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProspectTest {

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
   @Test
   public void testProspectToString() {
      Prospect prospect = new Prospect();
      prospect.setCustomer("John Doe");
      prospect.setTotalLoan(10000.0);
      prospect.setInterest(5.0);
      prospect.setYears(2.5);
      String expected = "Prospect 0: John Doe wants to borrow 10000 € for a period of 2.50 years and pay 355.29 € each month";
      assertEquals(expected, prospect.toString());
   }

   @Test
   public void testProspectId() {
      Prospect prospect = new Prospect();
      prospect.setCustomer("John Doe");
      prospect.setTotalLoan(10000.0);
      prospect.setInterest(5.0);
      prospect.setYears(2.5);
      String expected = "Prospect 0: John Doe wants to borrow 10000 € for a period of 2.50 years and pay 355.29 € each month";
      assertEquals(expected, prospect.toString());
      Prospect prospect2 = new Prospect();
      prospect2.setCustomer("John Doe");
      prospect2.setTotalLoan(10000.0);
      prospect2.setInterest(5.0);
      prospect2.setYears(2.5);
      String expected2 = "Prospect 0: John Doe wants to borrow 10000 € for a period of 2.50 years and pay 355.29 € each month";
      assertEquals(expected2, prospect2.toString());
   }
   @Test
   public void testCalcPayment() {
      Prospect prospect = new Prospect();
      prospect.setTotalLoan(10000.0);
      prospect.setInterest(5.0);
      prospect.setYears(2.5);

      double monthlyPayment = prospect.getMonthly();
      assertEquals(355.29, monthlyPayment, 0.01); // Adjust delta as needed
   }

   // Add more test cases for the Prospect class as needed

}
