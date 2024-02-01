package com.madanie.malachite_thousand_leaf.Util;

public class Maths {
   //Does not handle negative numbers
   public static double pow(double base,  int factor){
      if(factor<0){return 0;}
      double result = 1;
      for (int i = 0; i<factor; i++){
         result *= base;
      }
      return result;
   }

   public static double mortagePayment(double totoal, double yearlyintrest, int years, int paymentsPerYear){
      double b = (yearlyintrest / paymentsPerYear) * .01 ;    // b = Interest on a monthly basis
      double U = totoal;
      int p    = (int)(years * paymentsPerYear);             // p = Number of payments
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
