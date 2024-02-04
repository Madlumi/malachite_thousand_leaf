package com.madanie.malachite_thousand_leaf.prospect;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProspectService {

	List<Prospect> findAll();

	void save(String customer, double totalLoan, double interest, int years) throws IllegalArgumentException;

	void save(Map<String, String> values) throws IllegalArgumentException, NullPointerException;

	void printAll();

	void fromCsv(String file);
}
