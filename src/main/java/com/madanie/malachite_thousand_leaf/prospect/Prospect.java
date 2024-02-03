package com.madanie.malachite_thousand_leaf.prospect;

import java.util.Map;

import com.madanie.malachite_thousand_leaf.util.Maths;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Prospect {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	private String customer;

	public String getCustomer() {
		return customer;
	}

	private double totalLoan;

	public double getTotalLoan() {
		return totalLoan;
	}

	private double interest; // yearly, in percent

	public double getInterest() {
		return interest;
	}

	private int years;

	public int getYears() {
		return years;
	}

	/** @return fixed monthly payment */
	public double getMonthly() {
		return Maths.mortagePayment(totalLoan, interest, years);
	}

	protected Prospect() {
	};

	/**
	 * Constructs a new Prospect for a mortage
	 * 
	 * @param customer  the name of the customer, must not be null.
	 * @param totalLoan the total amount.
	 * @param interest  the annual interest as a percentage, must be greater than 0;
	 * @param years     loan time in years, must be 1 or more.
	 * @throws IllegalArgumentException if any arg is invalid
	 */
	public Prospect(final String customer, final double totalLoan, final double interest, final int years)
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

		this.customer = customer;
		this.totalLoan = totalLoan;
		this.interest = interest;
		this.years = years;
	}

	/**
	 * Parse a map<String, String> into a prospect
	 * 
	 * @param map of: "Customer":"String", "Total loan":"double",
	 *            "Interest":"double", "Years":"int" see Prospect(final String
	 *            customer, final double totalLoan, final double interest, final int
	 *            years) for validation
	 * @throws IllegalArgumentException, NullPointerException as Appropriate
	 */
	public Prospect(final Map<String, String> values) throws IllegalArgumentException, NullPointerException {
		this(values.get("Customer"), Double.parseDouble(values.get("Total loan")),
				Double.parseDouble(values.get("Interest")), Integer.parseInt(values.get("Years")));
	}

	@Override
	public String toString() {
		return String.format("Prospect %d: %s wants to borrow %s€ for a period of %s years and pay %s€ each month",
				getId(), getCustomer(), Maths.dropZeroDecimal(getTotalLoan()), Maths.dropZeroDecimal(getYears()),
				Maths.dropZeroDecimal(getMonthly()));
	}
}
