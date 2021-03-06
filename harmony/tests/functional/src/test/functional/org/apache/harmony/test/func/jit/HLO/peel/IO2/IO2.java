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
package org.apache.harmony.test.func.jit.HLO.peel.IO2;

import java.nio.IntBuffer;

import org.apache.harmony.share.Test;

/**
 */

/*
 * Created on 06.06.2006
 */

public class IO2 extends Test {
        
        public static void main(String[] args) {
            System.exit((new IO2()).test(args));
        }
        
        public int test() {
            log.info("Start IO2 peel test...");
            final IntBuffer buf = IntBuffer.allocate(1000050);
            int i = 0;
            try {
                while (i < 1000) {
                    int j = 0;
                    while (j < 1000) {
                        buf.put(j);
                        j++;
                    }
                    i++;
                }
            } catch (Exception e) {
                fail("TEST FAILED: unexpected " + e);
            }
            if (buf.position() == 1000000) return pass();
            else return fail("TEST FAILED: check if IO operation was " +
                    "incorrectly hoisted outside a loop");
        }
}
