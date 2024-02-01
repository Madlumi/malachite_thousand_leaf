package com.madanie.malachite_thousand_leaf.Util;

public class Maths {
   //Does not handle negative numbers
   public static double pow(double base,  int factor) throws IllegalArgumentException{
      if(factor<0){throw new IllegalArgumentException("factor must not be negative");}
      double result = 1;
      for (int i = 0; i<factor; i++){
         result *= base;
      }
      return result;
   }

   public static double mortagePayment(double total, double yearlyintrest, int years){
      double b = (yearlyintrest / 12) * .01 ;    // b = Interest on a monthly basis
      double U = total;
      int p    = (int)(years * 12);             // p = Number of payments
      if(p<=0){return total;}//does not allow illegal number to be sent to pow
      if(yearlyintrest==0){return (U/p);}
      double E = U * (b*pow((1 + b),p)) / (pow(( 1 + b),p)-1); //E = U[b(1 + b)^p]/[(1 + b)^p - 1]
      return E;                                                // E = Fixed monthly payment
   }

   //drop .00 of doubles
   public static String dropZeroDecimal(double number){
      return (((number-(int)number) * (number-(int)number)) < .001) ? 
         String.format("%.0f", number) : 
         String.format("%.2f", number);
   }
}
