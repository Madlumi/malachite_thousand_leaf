package com.madanie.malachite_thousand_leaf.prospect;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ProspectService {

	public Iterable<Prospect> findAll();

	void save(final String customer, final double totalLoan, final double interest, final int years)
			throws IllegalArgumentException;

	void save(Map<String, String> values) throws IllegalArgumentException, NullPointerException;

	void printAll();

	void fromCsv(String file);
}
