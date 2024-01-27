package com.madanie.malachite_thousand_leaf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void contextLoads() {
        // Ensure that the Spring Boot application context loads successfully
    }

    @Test
    public void mainMethodRunsSuccessfully() {
        String[] args = {"-i", "prospects.txt"} ;
        MalachiteThousandLeafApplication.main(args);
        assertNotNull(MalachiteThousandLeafApplication.propects);
        assertEquals(4,MalachiteThousandLeafApplication.propects.getProspectList().size());
    }
    @Test
    public void noFileSpecified() {
        String[] args = {};
        MalachiteThousandLeafApplication.main(args);
        assertNotNull(MalachiteThousandLeafApplication.propects);
        assertEquals(4,MalachiteThousandLeafApplication.propects.getProspectList().size());
    }
    @Test
    public void mainCustomFile() {
        String[] args = {"-i", "testfiles/invalidData.txt"};
        MalachiteThousandLeafApplication.main(args);
        assertThat(MalachiteThousandLeafApplication.propects).isNotNull();
        assertEquals(3,MalachiteThousandLeafApplication.propects.getProspectList().size());
    }
}
