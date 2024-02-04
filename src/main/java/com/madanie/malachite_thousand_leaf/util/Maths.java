package com.madanie.malachite_thousand_leaf.util;

public class Maths {

	/**
	 * Raises the given base to the power.
	 * 
	 * @param factor cannot be negative
	 * @throws IllegalArgumentException If the factor is negative.
	 */
	public static double pow(final double base, final int factor) throws IllegalArgumentException {
		if (factor < 0) {
			throw new IllegalArgumentException("factor must not be negative");
		}

		double result = 1;
		for (int i = 0; i < factor; i++) {
			result *= base;
		}
		return result;
	}

	/**
	 * Calculates a fixed monthly mortgage payment amount.
	 *
	 * @param total          The total loan amount.
	 * @param yearlyInterest The yearly interest rate in percentages.
	 * @param years          The loan duration in years.
	 * @return The calculated monthly mortgage payment. If the loan duration is less
	 *         than or equal to 0, returns the total loan amount.
	 */
	public static double mortagePayment(final double total, final double yearlyintrest, final int years) {
		final int MONTHS = 12;
		final int PERCENT = 100;
		double intr = (yearlyintrest / MONTHS) / PERCENT;
		int payments = (int) (years * MONTHS);

		// possibly you want to instead return an illegal argumet exception
		if (payments <= 0) {
			return total;
		}

		// Bypass diveide by 0 eventuality
		if (yearlyintrest == 0) {
			return (total / payments);
		}

		// E = U[b(1 + b)^p]/[(1 + b)^p - 1]
		double monthlyPayment = total * (intr * pow((1 + intr), payments)) / (pow((1 + intr), payments) - 1);
		return monthlyPayment;
	}

	/**
	 * Formats a double, rounding to two decimals. If the decimals round to '.00',
	 * the result is instead formatted to zero decimals.
	 */
	public static String dropZeroDecimal(final double number) {
		final Double lowestBeforeZero = .00499;// .005 will round to .01, therefore strip at .00499
		return (pow((number - (int) number), 2) < pow(lowestBeforeZero, 2)) ? String.format("%.0f", number)
				: String.format("%.2f", number);
	}
}
