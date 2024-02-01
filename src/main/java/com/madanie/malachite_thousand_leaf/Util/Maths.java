package com.madanie.malachite_thousand_leaf.Util;

public class Maths {
   //Does not handle negative numbers
   public static double pow(final double base, final int factor) throws IllegalArgumentException{
      if(factor<0){throw new IllegalArgumentException("factor must not be negative");}
      double result = 1;
      for(int i = 0; i<factor; i++){
         result *= base;
      }
      return result;
   }

   public static double mortagePayment(final double total, final double yearlyintrest, final int years){
      final int MONTHS = 12;
      final int PERCENT = 100;
      double intr = (yearlyintrest / MONTHS) / PERCENT;    // b = Interest on a monthly basis
      int payments= (int)(years * MONTHS);             
      if(payments<=0){return total;}
      if(yearlyintrest==0){return(total/payments);} 
      //E = U[b(1 + b)^p]/[(1 + b)^p - 1]
      double monthlyPayment = total * (intr*pow((1 + intr),payments)) / (pow((1 + intr),payments)-1);
      return monthlyPayment;
   }

   //drop .00 of doubles
   public static String dropZeroDecimal(final double number){
      final Double lowestBeforeZero=.00499;
      return(pow((number-(int)number),2) < pow(lowestBeforeZero,2)) ? 
         String.format("%.0f", number) : String.format("%.2f", number);
   }
}
