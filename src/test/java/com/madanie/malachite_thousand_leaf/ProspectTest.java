package com.madanie.malachite_thousand_leaf;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.madanie.malachite_thousand_leaf.prospect.Prospect;
import com.madanie.malachite_thousand_leaf.prospect.ProspectRepository;
import com.madanie.malachite_thousand_leaf.prospect.ProspectService;
import com.madanie.malachite_thousand_leaf.prospect.ProspectServiceImpl;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@ExtendWith(SpringExtension.class)
class ProspectManagerTest {

   @TestConfiguration
   static class ProspectServiceImplTestContextConfiguration {
      @Bean
      public ProspectService prospectService() {
         return new ProspectServiceImpl();
      }
   }
   @Autowired
   private ProspectService ps;

	/** Test valid map into prospect */
	@Test
	void testProspectFromMap() {
		Map<String, String> testData = Map.of("Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5",
				"Years", "4");
      ps.save(testData);
      assertEquals(ps.findAll().size() ,1);
      Prospect p = ps.findAll().get(0);
		assertNotNull(p, "Prospect should not be null");
		assertEquals("TestCustomer", p.getCustomer());
		assertEquals(5000, p.getTotalLoan(), 000001);
		assertEquals(3.5, p.getInterest(), 000001);
		assertEquals(4, p.getYears());
	}
	/** Test invalid map into prospect */
	@Test
	void testProspectFromMapInvalid() {
		Map<String, String> testData = Map.of("Gustomer", "TestCustomer", "Total loan", "5000", "Interest", "3.5",
				"Years", "4");
		assertThrows(IllegalArgumentException.class, () -> ps.save(testData));
		Map<String, String> testData2 = Map.of("Customer", "TestCustomer", "Total loan", "5000a", "Interest", "3.5",
				"Years", "4");
		assertThrows(IllegalArgumentException.class, () -> ps.save(testData2));
		Map<String, String> testData3 = Map.of("Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5",
				"Years", "0");
		assertThrows(IllegalArgumentException.class, () -> ps.save(testData3));
		Map<String, String> testData4 = Map.of("Customer", "TestCustomer", "Tutal loan", "5000", "Interest", "4.5",
				"Years", "1");
		assertThrows(NullPointerException.class, () -> ps.save(testData4));
	}


	/** Test save with valid values */
	@Test
	void testvalidProspectSave() {
		assertDoesNotThrow(() -> ps.save("John Doe", 100000, 20, 20));
		assertDoesNotThrow(() -> ps.save("John Doe", 100000, 5, 3));
		assertDoesNotThrow(() -> ps.save("beans", 100000, 5, 20));
      assertEquals(ps.findAll().size() ,3);
	}
	/** Test save with invalid values */
	@Test
	void testInvalidProspectSave() {
		assertThrows(IllegalArgumentException.class, () -> ps.save("John Doe", 100000, 0, 20));
		assertThrows(IllegalArgumentException.class, () -> ps.save("John Doe", 100000, 5, 0));
		assertThrows(IllegalArgumentException.class, () -> ps.save(null, 100000, 5, 20));
	}

	/** check the prospects return correct monthly */
	@Test
	void testCalcPayment() {
		Map<String, String> testData = Map.of("Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5", "Years", "4");
      ps.save(testData);
		assertEquals(111.78, ps.findAll().get(0).getMonthly(), .01);

		Map<String, String> testData2 = Map.of("Customer", "AnotherCustomer", "Total loan", "10000", "Interest", "2.0", "Years", "6");
      ps.save(testData2);
		assertEquals(147.50, ps.findAll().get(1).getMonthly(), .01);
	}

	/** Ensure .toString works */
	@Test
	void testToString() {
		Map<String, String> testData = Map.of("Customer", "TestCustomer", "Total loan", "5000", "Interest", "3.5", "Years", "4");
      ps.save(testData);
		String prospectString = ps.findAll().get(0).toString();
		assertTrue(prospectString.matches(
				"^Prospect (?:[0-9]+|null)*: TestCustomer wants to borrow 5000€ for a period of 4 years and pay 111[.,]{1}78€ each month$"));
	}
}
