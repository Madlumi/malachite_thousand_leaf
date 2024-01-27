package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MathsTest {

   private static final double DELTA = 1e-15;
   @Test
   public void testPositiveBase(){
      assertEquals(1.0, Maths.pow(5.0, 0), DELTA);
      assertEquals(5.0, Maths.pow(5.0, 1), DELTA);
      assertEquals(25.0, Maths.pow(5.0, 2), DELTA);
      assertEquals(125.0, Maths.pow(5.0, 3), DELTA);
   }

   @Test
   public void testNegativeBase(){
      assertEquals(1.0, Maths.pow(-5.0, 0), DELTA);
      assertEquals(-5.0, Maths.pow(-5.0, 1), DELTA);
      assertEquals(25.0, Maths.pow(-5.0, 2), DELTA);
      assertEquals(-125.0, Maths.pow(-5.0, 3), DELTA);
   }
   @Test
   public void testZeroExponent(){
      assertEquals(1.0, Maths.pow(5.0, 0), DELTA);
      assertEquals(1.0, Maths.pow(0.0, 0), DELTA);
   }

   @Test
   public void testNegativeExponent(){
      //Negative exponents return 0 with current implementation
      assertEquals(0.0, Maths.pow(5.0, -1), DELTA);
      assertEquals(0.0, Maths.pow(2.0, -3), DELTA);
   }

   @Test
   public void testBaseZero(){
      assertEquals(1.0, Maths.pow(0.0, 0), DELTA);
      assertEquals(0.0, Maths.pow(0.0, 1), DELTA);
      assertEquals(0.0, Maths.pow(0.0, 2), DELTA);
   }

   @Test
   public void testBaseOne(){
      assertEquals(1.0, Maths.pow(1.0, 0), DELTA);
      assertEquals(1.0, Maths.pow(1.0, 1), DELTA);
      assertEquals(1.0, Maths.pow(1.0, 2), DELTA);
   }
   @Test
   public void testInfinity(){
      assertEquals(Double.POSITIVE_INFINITY, Maths.pow(Double.MAX_VALUE, 2), DELTA);
   }
   @Test
   public void testNaN(){
      assertEquals(Double.NaN, Maths.pow(Double.NaN, 2), DELTA);
   }
}
