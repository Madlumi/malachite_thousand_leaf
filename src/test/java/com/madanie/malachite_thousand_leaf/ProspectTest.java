package com.madanie.malachite_thousand_leaf;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.madanie.malachite_thousand_leaf.prospect.Prospect;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProspectManagerTest {

	/** Test valid map into prospect */
	@Test
	void testProspectFromMap() {
		Map<String, String> testData = Map.of("Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5",
				"Years", "4");
		Prospect p = new Prospect(testData);
		assertNotNull(p, "Prospect should not be null");
		assertEquals("TestCustomer", p.getCustomer());
		assertEquals(5000, p.getTotalLoan(), 000001);
		assertEquals(3.5, p.getInterest(), 000001);
		assertEquals(4, p.getYears());
	}

	/** Test invalid map into prospect */
	@Test
	void testProspectFromMapInvalid() {
		Map<String, String> testData1 = Map.of("Gustomer", "TestCustomer", "Total loan", "5000", "Interest", "3.5",
				"Years", "4");
		assertThrows(IllegalArgumentException.class, () -> new Prospect(testData1));
		Map<String, String> testData2 = Map.of("Customer", "TestCustomer", "Total loan", "5000a", "Interest", "3.5",
				"Years", "4");
		assertThrows(IllegalArgumentException.class, () -> new Prospect(testData2));
		Map<String, String> testData3 = Map.of("Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5",
				"Years", "0");
		assertThrows(IllegalArgumentException.class, () -> new Prospect(testData3));
		Map<String, String> testData4 = Map.of("Customer", "TestCustomer", "Tutal loan", "5000", "Interest", "4.5",
				"Years", "1");
		assertThrows(NullPointerException.class, () -> new Prospect(testData4));
	}

	/** check the prospects return correct monthly */
	@Test
	void testCalcPayment() {
		Prospect prospect = new Prospect("TestCustomer", 5000, 3.5, 4);
		double monthlyPayment = prospect.getMonthly();
		assertEquals(111.78, monthlyPayment, .01);

		Prospect anotherProspect = new Prospect("AnotherCustomer", 10000, 2.0, 6);
		double anotherMonthlyPayment = anotherProspect.getMonthly();
		assertEquals(147.50, anotherMonthlyPayment, 0.01);
	}

	/** Ensure .toString works */
	@Test
	void testToString() {
		Prospect prospect = new Prospect("TestCustomer", 5000, 3.5, 4);
		String prospectString = prospect.toString();
		assertTrue(prospectString.matches(
				"^Prospect (?:[0-9]+|null)*: TestCustomer wants to borrow 5000€ for a period of 4 years and pay 111[.,]{1}78€ each month$"));
	}

	/** Test Constructor with invalid values */
	@Test
	void testInvalidProspectConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new Prospect("John Doe", 100000, 0, 20));
		assertThrows(IllegalArgumentException.class, () -> new Prospect("John Doe", 100000, 5, 0));
		assertThrows(IllegalArgumentException.class, () -> new Prospect(null, 100000, 5, 20));
	}
}
