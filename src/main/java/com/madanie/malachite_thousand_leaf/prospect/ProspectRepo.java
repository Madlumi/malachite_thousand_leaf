package com.madanie.malachite_thousand_leaf.prospect;

import java.util.Map;

import org.springframework.data.repository.CrudRepository;

import com.madanie.malachite_thousand_leaf.util.Csv;

/** Interface for managing Prospects */
public interface ProspectRepo extends CrudRepository<Prospect, Long> {

	/** prints all prospects in the repository, with '*' separators */
	default void printAll() {
		StringBuffer buff = new StringBuffer();
		final String SEPARATOR = "****************************************************************************************************\n";
		findAll().forEach(p -> {
			buff.append(SEPARATOR + p.toString() + "\n" + SEPARATOR);
		});
		System.out.println(buff.toString());
	}

	/** Populates the repository with prospects from a CSV file. */
	default void fromCsv(final String file) {
		if (file == null) {
			return;
		}
		for (Map<String, String> m : Csv.readCsv(file)) {
			try {
				Prospect p = new Prospect(m);
				if (p != null) {
					save(p);
				}
			} catch (Exception e) {
			}
		}
	}
}
