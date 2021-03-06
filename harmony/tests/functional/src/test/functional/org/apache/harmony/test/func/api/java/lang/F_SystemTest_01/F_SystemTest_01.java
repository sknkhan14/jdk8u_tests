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
/*
 * Created on 13.01.2005
 * Last modification G.Seryakova
 * Last modified on 13.01.2005
 * 
 * This test invoke System.gc() and Thread.sleep(10) in cycle.
 * 
 * scenario
 */
package org.apache.harmony.test.func.api.java.lang.F_SystemTest_01;

import org.apache.harmony.test.func.share.ScenarioTest;

/**
 * This test invoke System.gc() and Thread.sleep(10) in cycle.
 * 
 */
public class F_SystemTest_01 extends ScenarioTest {
    
    public static void main(String[] args) {
        System.exit(new F_SystemTest_01().test(args)); 
    }

    public int test() {
        log.info("0");
        int count = 0;
        try {
            while (count < 2) {
                System.gc();
                count++;
                Thread.sleep(10);
                log.info(""+count);
            }
        } catch (InterruptedException e) {
            log.info("The process was interrupted");
        }
        log.info("3");
        return pass();
    }
}
