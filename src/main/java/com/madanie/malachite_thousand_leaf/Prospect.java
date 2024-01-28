package com.madanie.malachite_thousand_leaf;

import java.util.Map;

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

   //public functions---------------------------------------------------------------------------
   public Prospect(String customer, double totalLoan, double interest, double years) {
      this.customer = customer;
      this.totalLoan = totalLoan;
      this.interest = interest;
      this.years = years;
   }
   @Override
   public String toString(){
      return String.format("Prospect %d: %s wants to borrow %s € for a period of %s years and pay %s € each month", 
            getId(), getCustomer(), dropZeroDecimal(getTotalLoan()), dropZeroDecimal(getYears()), dropZeroDecimal(getMonthly()));
   }

   public static Prospect prospectFromMap(Map<String, String> values){
      try {
         String cust = values.get("Customer");
         double tl=Double.parseDouble(values.get("Total loan"));
         double intr = Double.parseDouble(values.get("Interest"));
         int y = Integer.parseInt(values.get("Years"));
         if(y<1){return null;} // we can add more validation here if needed
         return new Prospect(cust, tl, intr , y);
      } catch (Exception e) {
         return null;
      }
   }

   public static void printAll(ProspectRepo pr){
      pr.findAll().forEach(p -> {
         System.out.println("****************************************************************************************************");
         System.out.println(p.toString());
         System.out.println("****************************************************************************************************");
      });
   }
   //private functions-----------------------------------------------------------------------------
   private double calcPayment(int paymentsPerYear){
      double b = (getInterest() / paymentsPerYear) * .01 ;    // b = Interest on a monthly basis
      double U = getTotalLoan();                               // U = Total loan
      int p    = (int)(getYears() * paymentsPerYear);             // p = Number of payments
      double E = U * (b*Maths.pow((1 + b),p)) / (Maths.pow(( 1 + b),p)-1); //E = U[b(1 + b)^p]/[(1 + b)^p - 1]
      return E;                                                // E = Fixed monthly payment
   }
   //drop .00 of doubles
   private String dropZeroDecimal(double number){
      return ((number - (int)number)* (number - (int)number)< .001) ? String.format("%.0f", number) : String.format("%.2f", number);
   }
   protected Prospect(){}
}
