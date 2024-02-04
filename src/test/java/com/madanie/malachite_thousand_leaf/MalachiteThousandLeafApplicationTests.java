package com.madanie.malachite_thousand_leaf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MalachiteThousandLeafApplicationTests {
	@Test
	public void contextLoads() {
	}

	/** check flags work */
	@Test
	public void mainMethodRunsSuccessfully() {
		String[] args = { "-i", "test.txt", "-w" };
		MalachiteThousandLeafApplication.main(args);
		assertEquals("test.txt", MalachiteThousandLeafApplication.getCmdargs().getOptionValue("i"));
		assertTrue(MalachiteThousandLeafApplication.getCmdargs().hasOption("w"));
	}

	@Test
	public void mainMethodRunsSuccessfullyNoWeb() {
		String[] args = { "-i", "test.txt" };
		MalachiteThousandLeafApplication.main(args);
		assertEquals("test.txt", MalachiteThousandLeafApplication.getCmdargs().getOptionValue("i"));
		assertFalse(MalachiteThousandLeafApplication.getCmdargs().hasOption("w"));
	}
}
