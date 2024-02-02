package com.madanie.malachite_thousand_leaf;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.madanie.malachite_thousand_leaf.Util.Maths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MathsTest {

   private static final double DELTA = .000001;
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
      assertThrows(IllegalArgumentException.class, () -> Maths.pow(5.0, -1));
      assertThrows(IllegalArgumentException.class, () -> Maths.pow(2.0, -3));
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

   @Test
   public void testMortage(){
      assertEquals(183.30139746818807, Maths.mortagePayment(4300, 2.2, 2), DELTA);
      assertEquals(34.41940275959286, Maths.mortagePayment(2200,   4, 6), DELTA);
   }
   @Test
   public void testMortageNoIntrest(){
      assertEquals(100, Maths.mortagePayment(1200, 0, 1), DELTA);
      assertEquals(50, Maths.mortagePayment(1200, 0, 2), DELTA);
   }
   @Test
   public void testMortageNoYear(){
      assertEquals(3600, Maths.mortagePayment(3600, 2, 0), DELTA);
      assertEquals(1200, Maths.mortagePayment(1200, 5, -1), DELTA);
   }

   @Test
   public void testMortageNegative(){
      assertEquals(-183.30139746818807, Maths.mortagePayment(-4300, 2.2, 2), DELTA);
      assertEquals(-34.41940275959286, Maths.mortagePayment(-2200,   4, 6), DELTA);
      assertEquals(0, Maths.mortagePayment(0,   4, 6), DELTA);
   }

   @Test
   public void testDropZero(){
      assertEquals("0",       Maths.dropZeroDecimal(0.00000000000000001)); 
      assertEquals("1",       Maths.dropZeroDecimal(1.00000000000000001)); 
      assertEquals("25",      Maths.dropZeroDecimal(25.001000000000000)); 
      assertEquals("25.01",   Maths.dropZeroDecimal(25.005000000000000)); 
      assertEquals("25",      Maths.dropZeroDecimal(25.004000000000000)); 
      assertEquals("11.01",   Maths.dropZeroDecimal(11.01000000000000001)); 
      assertEquals("11.01",   Maths.dropZeroDecimal(11.01000000000000001)); 
      assertEquals("11.10",   Maths.dropZeroDecimal(11.1000000000000001)); 
   }
   @Test
   public void testDropZeroNeg(){
      assertEquals("-0",       Maths.dropZeroDecimal(-0.00000000000000001)); 
      assertEquals("-1",       Maths.dropZeroDecimal(-1.00000000000000001)); 
      assertEquals("-25",      Maths.dropZeroDecimal(-25.001000000000000)); 
      assertEquals("-25.01",   Maths.dropZeroDecimal(-25.005000000000000)); 
      assertEquals("-25",      Maths.dropZeroDecimal(-25.004000000000000)); 
      assertEquals("-11.01",   Maths.dropZeroDecimal(-11.01000000000000001)); 
      assertEquals("-11.01",   Maths.dropZeroDecimal(-11.01000000000000001)); 
      assertEquals("-11.10",   Maths.dropZeroDecimal(-11.1000000000000001)); 
   }
}
