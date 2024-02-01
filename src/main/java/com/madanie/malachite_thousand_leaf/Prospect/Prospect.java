package com.madanie.malachite_thousand_leaf.Prospect;

import java.util.Map;

import com.madanie.malachite_thousand_leaf.Util.Maths;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Prospect {
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

   public Prospect(String customer, double totalLoan, double interest, double years) throws IllegalArgumentException{
      if(years<1){         throw new IllegalArgumentException("years cannot be < 1");}
      if(interest<=0){     throw new IllegalArgumentException("interest must be > 0");}
      if(customer==null){  throw new IllegalArgumentException("must have customer");}
      this.customer = customer;this.totalLoan = totalLoan;this.interest = interest;this.years = years;
   }
   public Prospect(Map<String, String> values) throws IllegalArgumentException, NullPointerException{
      this(values.get("Customer"), Double.parseDouble(values.get("Total loan")),  Double.parseDouble(values.get("Interest")), Integer.parseInt(values.get("Years")));
   }
   //drop .00 of doubles, could be moved into a class in the utils package
   private String dropZeroDecimal(double number){
      return (((number-(int)number) * (number-(int)number)) < .001) ? 
         String.format("%.0f", number) : 
         String.format("%.2f", number);
   }
   @Override
   public String toString(){
      return String.format("Prospect %d: %s wants to borrow %s€ for a period of %s years and pay %s€ each month", 
            getId(), getCustomer(), dropZeroDecimal(getTotalLoan()), dropZeroDecimal(getYears()), dropZeroDecimal(getMonthly()));
   }

   //could feasibly be broken off into the maths class
   private double calcPayment(int paymentsPerYear){
      double b = (getInterest() / paymentsPerYear) * .01 ;    // b = Interest on a monthly basis
      double U = getTotalLoan();                               // U = Total loan
      int p    = (int)(getYears() * paymentsPerYear);             // p = Number of payments
      double E = U * (b*Maths.pow((1 + b),p)) / (Maths.pow(( 1 + b),p)-1); //E = U[b(1 + b)^p]/[(1 + b)^p - 1]
      return E;                                                // E = Fixed monthly payment
   }

}
