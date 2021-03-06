/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 */

package org.apache.harmony.test.func.reg.jit.btest3493;

import org.apache.harmony.test.share.reg.RegressionTest;

import java.math.BigDecimal;

public class Btest3493_case1 extends RegressionTest {
   
    public static void main(String[] args) {
        System.exit(testFloatValueMinusZero() && testFloatValuePlusZero() 
                ? passed() : failed());
    }

    /**
      * Float value of a small negative BigDecimal
      */
     public static boolean testFloatValueMinusZero() {
         String a = 
        "-123809648392384754573567356745735.63567890295784902768787678287E-400";
         BigDecimal aNumber = new BigDecimal(a);
         int minusZero = -2147483648;
         float result = aNumber.floatValue();

         System.err.println("testFloatValueMinusZero: result is " + 
                 Float.floatToIntBits(result) + " (" + minusZero + " expected)");
         return (Float.floatToIntBits(result) == minusZero);
      }
     
    /**
      * Float value of a small positive BigDecimal
      */
     public static boolean testFloatValuePlusZero() {
         String a = 
         "123809648392384754573567356745735.63567890295784902768787678287E-400";
         BigDecimal aNumber = new BigDecimal(a);
         int zero = 0;
         float result = aNumber.floatValue();
         System.err.println("testFloatValuePlusZero: result is " + 
                 Float.floatToIntBits(result) + " (" + zero + " expected)");
         return (Float.floatToIntBits(result) == zero);
     }
    
}