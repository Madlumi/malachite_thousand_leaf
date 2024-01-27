package com.madanie.malachite_thousand_leaf;

import com.opencsv.bean.CsvBindByName;

public class Prospect {
   public Prospect(){}

   @CsvBindByName(column = "Customer")
   private String customer;
   public void   setCustomer(String customer){this.customer = customer;}
   public String getCustomer(){return customer;}
   @CsvBindByName(column = "Total Loan")
   private double totalLoan;
   public void   setTotalLoan(double totalLoan){this.totalLoan = totalLoan;}
   public double getTotalLoan(){return totalLoan;}
   //yearly, in percent
   @CsvBindByName(column = "Interest")
   private double interest;
   public void   setInterest(double interest){this.interest = interest;}
   public double getInterest(){return interest;}
   @CsvBindByName(column = "Years")
   private double years;
   public void   setYears(double years){this.years = years;}
   public double getYears(){return years;}

   private double monthly;
   public double getMonthly(){
      monthly=calcPayment(12);
      return monthly;
   }


   @Override
   public String toString(){
      return String.format("Prospect: %s wants to borrow %.2f € for a period of %.2f years and pay %.2f € each month",
            getCustomer(), getTotalLoan(), getYears(), getMonthly());
   }
   
   private double calcPayment(int paymentsPerYear){
      double b = (getInterest() / paymentsPerYear) * .01 ;    // b = Interest on a monthly basis
      double U = getTotalLoan();                               // U = Total loan
      int p    = (int)(getYears() * paymentsPerYear);             // p = Number of payments
      double E = U * (b*pow((1 + b),p)) / (pow(( 1 + b),p)-1); //E = U[b(1 + b)^p]/[(1 + b)^p - 1]
      return E;                                                // E = Fixed monthly payment
   }

   //TODO move to own class
   public static double pow(double base,  int factor){
      double result = 1;
      for (int i = 0; i<factor; i++){
         result *= base;
      }
      return result;
   }

}
