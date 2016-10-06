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
package org.apache.harmony.test.func.jit.HLO.lazyexc.ExcObjectUse1;

/**
 */

/*
 * Created on 19.04.2006
 */

import org.apache.harmony.share.Test;


public class ExcObjectUse1 extends Test {
    
    Exception[] excArr = new Exception[] {new Exception(), new Exception()};
    
    public static void main(String[] args) {
        System.exit((new ExcObjectUse1()).test(args));
    }
    
    public int test() {
        log.info("Start ExcObjectUse1 lazyexc test...");
        for (int i=0; i<10000; i++) {
            for (int j=0; j<100; j++) {
                try {
                    //TODO? insert some not invariant code: throw new TestException(i,j)
                    throw new TestException();
                } catch (TestException e) {
                    excArr[1] = e;
                }
            }
        }
        if (excArr[1].getClass().equals(TestException.class)) return pass();
        else return fail("TEST FAILED: TestException object wasn't stored in array.\n" +
                "Check if lazyexc was incorrectly performed.");
    }
}

class TestException extends Exception {

}
