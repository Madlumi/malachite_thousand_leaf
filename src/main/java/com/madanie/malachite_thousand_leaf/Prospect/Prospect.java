package com.madanie.malachite_thousand_leaf.Prospect;

import java.util.Map;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.madanie.malachite_thousand_leaf.Util.Maths;

@Entity
public class Prospect {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   public Long getId(){return id;}

   private String customer;
   public String getCustomer(){ return customer; }

   private double totalLoan;
   public double getTotalLoan(){ return totalLoan; }

   private double interest; //yearly, in percent
   public double getInterest(){ return interest; }

   private int years;
   public int getYears(){return years;}

   public double getMonthly(){return Maths.mortagePayment(totalLoan, interest, years);}

   protected Prospect(){};
   public Prospect(
         final String customer, final double totalLoan,
         final double interest, final int years) throws IllegalArgumentException{
      if(years<1){ throw new IllegalArgumentException("years cannot be < 1"); }
      if(interest<=0){ throw new IllegalArgumentException("interest must be > 0"); }
      if(customer==null){ throw new IllegalArgumentException("must have customer"); }
      
      this.customer = customer; this.totalLoan = totalLoan; this.interest = interest; this.years = years;
   }

/**
 *Generate a prospect from a map<String, String>
 *Requred values: "Customer", "Total loan", "Interest", "Years"
 */
   public Prospect(final Map<String, String> values) throws IllegalArgumentException, NullPointerException{
      this(
            values.get("Customer"), 
            Double.parseDouble(values.get("Total loan")),  
            Double.parseDouble(values.get("Interest")), 
            Integer.parseInt(values.get("Years")));
   }
   
   @Override
   public String toString(){
      return String.format(
            "Prospect %d: %s wants to borrow %s€ for a period of %s years and pay %s€ each month", 
            getId(), 
            getCustomer(), 
            Maths.dropZeroDecimal(getTotalLoan()), 
            Maths.dropZeroDecimal(getYears()), 
            Maths.dropZeroDecimal(getMonthly()));
   }
}
