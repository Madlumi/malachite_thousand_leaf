package com.madanie.malachite_thousand_leaf.prospect;

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

	protected Prospect(final String customer, final double totalLoan, final double interest, final int years) {
		this.customer = customer;
		this.totalLoan = totalLoan;
		this.interest = interest;
		this.years = years;
	}

	@Override
	public String toString() {
		return String.format("Prospect %d: %s wants to borrow %s€ for a period of %s years and pay %s€ each month",
				getId(), getCustomer(), Maths.dropZeroDecimal(getTotalLoan()), Maths.dropZeroDecimal(getYears()),
				Maths.dropZeroDecimal(getMonthly()));
	}
}
