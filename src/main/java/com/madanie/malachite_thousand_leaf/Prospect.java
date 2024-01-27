package com.madanie.malachite_thousand_leaf;

import com.opencsv.bean.CsvBindByName;

public class Prospect {
    public Prospect() {}

    @CsvBindByName(column = "Customer")
    private String customer;

    @CsvBindByName(column = "Total Loan")
    private double totalLoan;

    @CsvBindByName(column = "Interest")
    private double interest;

    @CsvBindByName(column = "Years")
    private double years;

    // Getter and setter for Customer
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    // Getter and setter for Total Loan
    public double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }

    // Getter and setter for Interest
    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    // Getter and setter for Years
    public double getYears() {
        return years;
    }

    public void setYears(double years) {
        this.years = years;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("Prospect: %s wants to borrow %.2f € for a period of %.2f years and pay %.2f € each month",
                customer, totalLoan, years, 1.1);
    }
}
