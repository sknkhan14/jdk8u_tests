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
package org.apache.harmony.test.func.jit.HLO.simplify.constPropagation.Volatile1;

import org.apache.harmony.share.Test;

/**
 */

/*
 * Created on 31.05.2006
 */

public class Volatile1 extends Test {
    
    public static void main(String[] args) {
        System.exit((new Volatile1()).test(args));
    }
        
    public int test() {
        log.info("Start Volatile1 test...");
        TestThread1 thread1 = new TestThread1();
        TestThread2 thread2 = new TestThread2(thread1);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
             return fail("TEST FAILED: unexpected " + e);
        } 
        if (thread2.error) 
            return fail("TEST FAILED: unexpected exception occurred in thread2");
        else return pass();
    }
    
}

class TestThread1 extends Thread {
    
    protected volatile int vol;
    private boolean flag = true;
    
    public void run() {
        vol = 0;
        for (int i=0; flag; i++) {
            if (vol == 1) break;
        }
    }
}

class TestThread2 extends TestThread1 {
    
    private TestThread1 thread;
    public boolean error = false;

    public TestThread2(TestThread1 thread) {
        this.thread = thread;
    }
    
    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            error = true;
        }
        thread.vol = 1;
    }
}





