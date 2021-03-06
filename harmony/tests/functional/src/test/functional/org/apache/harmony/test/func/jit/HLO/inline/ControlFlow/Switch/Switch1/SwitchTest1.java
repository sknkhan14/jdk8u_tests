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
package org.apache.harmony.test.func.jit.HLO.inline.ControlFlow.Switch.Switch1;

import java.util.Random;

import org.apache.harmony.share.Test;

/**
 */

/*
 * Created on 9.11.2005
 * 
 * Jitrino HLO test
 * 
 */

public class SwitchTest1 extends Test {
    
    public static void main(String[] args) {
        System.exit(new SwitchTest1().test(args));
    }
    
    int result = 0;
    
    public int test() {
        log.info("Start SwitchTest1 ...");
        try {
            int r = (new Random().nextInt()) % 2;
            log.info("random%2 == " + r);
            for (int i=0; i<10000; i++) {
                switch(r) {
                    case 0:
                        for (int j=0; j<1; j++)
                            for (int k=0; k<1; k++)
                                inlineMethod((byte) j, (byte) r);
                        break;
                    case 1:
                        for (int j=0; j<1; j++) 
                            for (int k=0; k<1; k++)
                                inlineMethod((byte) j, (byte) r);
                        break;
                    case -1:
                        for (int j=0; j<1; j++) 
                            for (int k=0; k<1; k++)
                                inlineMethod((byte) j, (byte) r);
                        break;
                    default:
                        result = -100;
                }
                break;
            }
            log.info("Result == " + result);
            if (result == 2*r) return pass();
            else return fail("TEST FAILED: result != " + 2*r);
        } catch (Throwable e) {
            log.add(e);
            return fail("TEST FAILED: unxpected " + e);
        }
    }

    final void inlineMethod(byte flag, byte param) {
            switch (flag) {
            case 0:
                result+=param;
            default:
                result+=param;
            }
    }
}
