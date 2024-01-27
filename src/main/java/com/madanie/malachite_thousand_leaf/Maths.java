package com.madanie.malachite_thousand_leaf;
public class Maths{
   //Does not handle negative numbers
   public static double pow(double base,  int factor){
      if(factor<0){return 0;}
      double result = 1;
      for (int i = 0; i<factor; i++){
         result *= base;
      }
      return result;
   }
}
