package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.madanie.malachite_thousand_leaf.Prospect.Prospect;
import com.madanie.malachite_thousand_leaf.Prospect.ProspectManager;
import com.madanie.malachite_thousand_leaf.Prospect.ProspectRepo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
   @SpringBootTest
   @ExtendWith(MockitoExtension.class)
   class ProspectManagerTest {

      @Test
      void testProspectFromMap() {
         Map<String, String> testData = Map.of( "Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5", "Years", "4");
         Prospect p = new Prospect(testData);
         assertNotNull(p, "Prospect should not be null");
         assertEquals("TestCustomer", p.getCustomer());
         assertEquals(5000, p.getTotalLoan(),000001);
         assertEquals(3.5, p.getInterest(),000001);
         assertEquals(4, p.getYears());
      }
      @Test
      void testProspectFromMapInvalid() {
         Map<String, String> testData1 = Map.of( "Gustomer", "TestCustomer", "Total loan", "5000", "Interest", "3.5", "Years", "4");
         Map<String, String> testData2 = Map.of( "Customer", "TestCustomer", "Total loan", "5000a", "Interest", "3.5", "Years", "4");
         Map<String, String> testData3 = Map.of( "Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5", "Years", "0");
         Map<String, String> testData4 = Map.of( "Customer", "TestCustomer", "Tutal loan", "5000", "Interest", "4.5", "Years", "1");
         assertThrows(IllegalArgumentException.class, () -> new Prospect(testData1));
         assertThrows(IllegalArgumentException.class, () -> new Prospect(testData2));
         assertThrows(IllegalArgumentException.class, () -> new Prospect(testData3));
         assertThrows(NullPointerException.class, () -> new Prospect(testData4));
      }

      @Mock
      private ProspectRepo prospectRepo;

      @InjectMocks
      private ProspectManager prospectManager;


      @Test
      void testProspectsFromCsv() {
         ProspectManager.setProspectFile("testfiles/prospects.txt");
         prospectManager.prospectsFromCsv(prospectRepo);
         verify(prospectRepo, times(4)).save(any());
      }

      @Test
      void testCalcPayment() {
         Prospect prospect = new Prospect("TestCustomer", 5000, 3.5, 4);
         double monthlyPayment = prospect.getMonthly();
         assertEquals(111.78, monthlyPayment, 0.01, "Monthly payment should match");
         Prospect anotherProspect = new Prospect("AnotherCustomer", 10000, 2.0, 6);
         double anotherMonthlyPayment = anotherProspect.getMonthly();
         assertEquals(147.50, anotherMonthlyPayment, 0.01, "Monthly payment should match");
      }

      @Test
      void testToString() {
         Prospect prospect = new Prospect("TestCustomer", 5000, 3.5, 4);
         String prospectString = prospect.toString();
         assertTrue(prospectString.contains("Prospect"));
         assertTrue(prospectString.contains(": TestCustomer wants to borrow 5000€ for a period of 4 years and pay 111.78€ each month") );
      }

      @Test
      void testPrintAll() {
         List<Prospect> prospects = new ArrayList<Prospect>();
         prospects.add(new Prospect("TestCustomer1", 1200, 1, 1));
         prospects.add(new Prospect("TestCustomer2", 1200, 1, 2));
         prospects.add(new Prospect("TestCustomer3", 2400, 3.1, 4));

         when(prospectRepo.findAll()).thenReturn(prospects);
         PrintStream origOut= System.out;
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         System.setOut(new PrintStream(outputStream));
         ProspectManager.printAll(prospectRepo);
         System.setOut(origOut);
         //the mock implementation does not generate id, hence null
         String expectedOutput =  "****************************************************************************************************\n" +
            "Prospect null: TestCustomer1 wants to borrow 1200€ for a period of 1 years and pay 100.54€ each month\n" +
            "****************************************************************************************************\n" +
            "****************************************************************************************************\n" +
            "Prospect null: TestCustomer2 wants to borrow 1200€ for a period of 2 years and pay 50.52€ each month\n" +
            "****************************************************************************************************\n" +
            "****************************************************************************************************\n" +
            "Prospect null: TestCustomer3 wants to borrow 2400€ for a period of 4 years and pay 53.23€ each month\n" +
            "****************************************************************************************************\n";
         assertThat(outputStream.toString().trim()).isEqualTo(expectedOutput.trim());
      }
   }
