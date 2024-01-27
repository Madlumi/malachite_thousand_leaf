package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ProspectHandlerTest {


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
   public void testLoadEmptyFile(){
      ProspectHandler prospectHandler = new ProspectHandler();
      prospectHandler.load("testfiles/empty.txt");
      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertTrue(prospectList.isEmpty());
   }

   @Test
   public void testLoadInvalidDataFile(){
      ProspectHandler prospectHandler = new ProspectHandler();
      prospectHandler.load("testfiles/invalidData.txt");
      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertEquals(3,prospectList.size());
   }

   @Test
   public void testLoadInvalidHeadLongFile(){
      ProspectHandler prospectHandler = new ProspectHandler();
      prospectHandler.load("testfiles/invalidHeadLong.txt");
      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertEquals(1,prospectList.size());
   }

   @Test
   public void testLoadInvalidHeadShortFile(){
      ProspectHandler prospectHandler = new ProspectHandler();
      prospectHandler.load("testfiles/invalidHeadShort.txt");

      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertTrue(prospectList.isEmpty());
   }


   @Test
   public void testLoadNoProspectsFile(){
      ProspectHandler prospectHandler = new ProspectHandler();
      prospectHandler.load("testfiles/noProspects.txt");

      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertTrue(prospectList.isEmpty());
   }

   @Test
   public void testLoadProspectsFile(){
      ProspectHandler prospectHandler = new ProspectHandler();
      prospectHandler.load("testfiles/prospects.txt");

      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertEquals(4,prospectList.size());
      assertEquals("Karvinen",prospectList.get(1).getCustomer());
      assertEquals(4356,prospectList.get(1).getTotalLoan());
      assertEquals(1.27,prospectList.get(1).getInterest(),.00001);
      assertEquals(6,prospectList.get(1).getYears(),.00001);


   }
   @Test
   public void testLoadProspectsFileId(){
      ProspectHandler prospectHandler = new ProspectHandler();
      prospectHandler.load("testfiles/prospects.txt");

      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertEquals(4,prospectList.size());
      assertEquals(1,prospectList.get(0).getId());
      assertEquals("Juha",prospectList.get(0).getCustomer());
      assertEquals(2,prospectList.get(1).getId());
      assertEquals("Karvinen",prospectList.get(1).getCustomer());

      assertEquals(3,prospectList.get(2).getId());
      assertEquals("Claes Månsson",prospectList.get(2).getCustomer());
      assertEquals(4,prospectList.get(3).getId());
      assertEquals("Clarencé,Andersson",prospectList.get(3).getCustomer());
   }
   @Test
   public void testAddProspect(){
      ProspectHandler prospectHandler = new ProspectHandler();
      Prospect prospect = new Prospect();
      prospectHandler.addProspect(prospect);

      List<Prospect> prospectList = prospectHandler.getProspectList();
      assertTrue(prospectList.contains(prospect));
   }
}

