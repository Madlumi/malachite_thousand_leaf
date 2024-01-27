package com.madanie.malachite_thousand_leaf;

import com.opencsv.bean.CsvBindByName;

public class Prospect {

   //global id system, makes all the id's unique.
   //Moving the id handling to the handler class would allow unique for handler only id
   private static Long nextID=1L;
   private long id=0L;
   public Long getId(){return id;}
   public void setId(){
      if(id==0){
         id=nextID;
         nextID++;
      }
   }
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

   public Prospect(){}

   private double monthly;
   public double getMonthly(){
      monthly=calcPayment(12);
      return monthly;
   }

   private String dropZeroDecimal(double number){
      return ((number - (int)number)* (number - (int)number)< .001) ? String.format("%.0f", number) : String.format("%.2f", number);
   }
   @Override
   public String toString(){
      if(id==0)setId();//ensure id is set
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
