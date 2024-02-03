package com.madanie.malachite_thousand_leaf.prospect;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madanie.malachite_thousand_leaf.util.Csv;

@Service
public class ProspectServiceImpl implements ProspectService {

	@Autowired
	private ProspectRepository pr;

	public Iterable<Prospect> findAll() {
		return pr.findAll();
	}

	/**
	 * Parse a map<String, String> into a prospect and save it
	 * 
	 * @param map of: "Customer":"String", "Total loan":"double",
	 *            "Interest":"double", "Years":"int". see Prospect(final String
	 *            customer, final double totalLoan, final double interest, final int
	 *            years) for validation
	 * @throws IllegalArgumentException, NullPointerException as Appropriate
	 */
	@Override
	public void save(Map<String, String> values) throws IllegalArgumentException, NullPointerException {
		Prospect p = new Prospect(values.get("Customer"), Double.parseDouble(values.get("Total loan")),
				Double.parseDouble(values.get("Interest")), Integer.parseInt(values.get("Years")));
		if (p != null) {
			pr.save(p);
		}

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
	public void fromCsv(String file) {
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
