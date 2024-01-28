package com.madanie.malachite_thousand_leaf;

import java.util.Map;

import com.opencsv.bean.CsvBindByName;

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
   
   //yearly, in percent
   private double interest;
   public double getInterest(){return interest;}
   
   private double years;
   public double getYears(){return years;}


   public double getMonthly(){return calcPayment(12);}
   public static Prospect prospectFromMap(Map<String, String> values){
      try {
         return new Prospect(values.get("Customer"), Double.parseDouble(values.get("Total loan")), Double.parseDouble(values.get("Interest")), Integer.parseInt(values.get("Years")));
      
      } catch (Exception e) {
      }
      return null;
   }

   public Prospect(String cust, double total, double intr, int yr){
      customer=cust;
      totalLoan=total;
      interest=intr;
      years=yr;
   }

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


   protected Prospect(){}
}
