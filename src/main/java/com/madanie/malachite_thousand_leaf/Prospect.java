package com.madanie.malachite_thousand_leaf;

import com.madanie.malachite_thousand_leaf.Util.Maths;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Prospect {
   //Data---------------------------------------------------------------------------------------
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   public Long getId(){return id;}

   private String customer;
   public String getCustomer(){return customer;}
   
   private double totalLoan;
   public double getTotalLoan(){return totalLoan;}
   
   private double interest; //yearly, in percent
   public double getInterest(){return interest;}
   
   private double years;
   public double getYears(){return years;}

   public double getMonthly(){return calcPayment(12);}

   protected Prospect(){}
   public Prospect(String customer, double totalLoan, double interest, double years) {this.customer = customer;this.totalLoan = totalLoan;this.interest = interest;this.years = years;}

   //drop .00 of doubles
   private String dropZeroDecimal(double number){
      return ((number - (int)number)* (number - (int)number)< .001) ? String.format("%.0f", number) : String.format("%.2f", number);
   }
   @Override
   public String toString(){
      return String.format("Prospect %d: %s wants to borrow %s € for a period of %s years and pay %s € each month", 
            getId(), getCustomer(), dropZeroDecimal(getTotalLoan()), dropZeroDecimal(getYears()), dropZeroDecimal(getMonthly()));
   }

   private double calcPayment(int paymentsPerYear){
      double b = (getInterest() / paymentsPerYear) * .01 ;    // b = Interest on a monthly basis
      double U = getTotalLoan();                               // U = Total loan
      int p    = (int)(getYears() * paymentsPerYear);             // p = Number of payments
      double E = U * (b*Maths.pow((1 + b),p)) / (Maths.pow(( 1 + b),p)-1); //E = U[b(1 + b)^p]/[(1 + b)^p - 1]
      return E;                                                // E = Fixed monthly payment
   }

}
