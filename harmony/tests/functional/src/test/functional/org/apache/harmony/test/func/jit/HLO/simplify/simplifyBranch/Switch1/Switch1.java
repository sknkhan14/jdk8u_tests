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
package org.apache.harmony.test.func.jit.HLO.simplify.simplifyBranch.Switch1;

import org.apache.harmony.share.Test;

/**
 */

/*
 * Created on 05.06.2006
 */

public class Switch1 extends Test {
    
    int i = 0;
    
    public static void main(String[] args) {
        System.exit((new Switch1()).test(args));
    }
        
    public int test() {
        int k = 1;
        try {
            switch (k/i) {
                default:
                i = 1;
                break;
            }
        } catch (Exception e) {
            log.info(e.toString());
            if (e.getClass().equals(ArithmeticException.class)) return pass();
        }
        return fail("TEST FAILED: ArithmeticException was expected");
    }
}


