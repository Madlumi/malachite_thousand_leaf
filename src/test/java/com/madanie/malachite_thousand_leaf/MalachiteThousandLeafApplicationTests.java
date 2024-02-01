package com.madanie.malachite_thousand_leaf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.madanie.malachite_thousand_leaf.Prospect.ProspectManager;


@SpringBootTest
public class MalachiteThousandLeafApplicationTests{
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
    public void contextLoads() {}

    @Test
    public void mainMethodRunsSuccessfully() {
        String[] args = {"-i", "test.txt"} ;
        MalachiteThousandLeafApplication.main(args);
        assertEquals("test.txt",ProspectManager.getProspectFile());
    }
}
