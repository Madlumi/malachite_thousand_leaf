package com.madanie.malachite_thousand_leaf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MalachiteThousandLeafApplicationTests{

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
