package com.madanie.malachite_thousand_leaf.prospect;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.madanie.malachite_thousand_leaf.MalachiteThousandLeafApplication;
import com.madanie.malachite_thousand_leaf.util.Csv;

@Service
public class ProspectServiceImpl implements ProspectService {

	@Autowired
	private ProspectRepository pr;

	/**
	 * Initialized the prospect repository with the file provided in args or a
	 * default file then prints all the prospect data.
	 */
	@Bean
	public int initProspectRepo() {
		if (MalachiteThousandLeafApplication.getCmdargs() == null) {
			return 1;
		}
		String file = MalachiteThousandLeafApplication.getCmdargs().getOptionValue("i", "prospects.txt");
		fromCsv(file);
		printAll();
		return 1;
	}

	public List<Prospect> findAll() {
		return (List<Prospect>) pr.findAll();
	}

	/**
	 * Saves a new prospect.
	 * 
	 * @param customer  the name of the customer, must not be null.
	 * @param totalLoan the total amount.
	 * @param interest  the annual interest as a percentage, must be greater than 0;
	 * @param years     loan time in years, must be 1 or more.
	 * @throws IllegalArgumentException if any arg is invalid
	 */
	public void save(final String customer, final double totalLoan, final double interest, final int years)
			throws IllegalArgumentException {
		if (years < 1) {
			throw new IllegalArgumentException("years cannot be < 1");
		}
		if (interest <= 0) {
			throw new IllegalArgumentException("interest must be > 0");
		}
		if (customer == null) {
			throw new IllegalArgumentException("must have customer");
		}
		pr.save(new Prospect(customer, totalLoan, interest, years));
	}

	/**
	 * Parse a map<String, String> into a prospect and save it.
	 * 
	 * @param map of: "Customer":"String", "Total loan":"double",
	 *            "Interest":"double", "Years":"int". see Prospect(final String
	 *            customer, final double totalLoan, final double interest, final int
	 *            years) for validation
	 * @throws IllegalArgumentException, NullPointerException as Appropriate
	 */
	@Override
	public void save(final Map<String, String> values)
			throws IllegalArgumentException, NumberFormatException, NullPointerException {
		save(values.get("Customer"), Double.parseDouble(values.get("Total loan")),
				Double.parseDouble(values.get("Interest")), Integer.parseInt(values.get("Years")));

	}

	@Override
	public void printAll() {
		StringBuffer buff = new StringBuffer();
		final String SEPARATOR = "****************************************************************************************************\n";
		pr.findAll().forEach(p -> {
			buff.append(SEPARATOR + p.toString() + "\n" + SEPARATOR);
		});
		System.out.println(buff.toString());

	}

	@Override
	public void fromCsv(final String file) {
		if (file == null) {
			return;
		}
		for (Map<String, String> m : Csv.readCsv(file)) {
			try {
				save(m);
			} catch (Exception e) {
			}
		}

	}

}
