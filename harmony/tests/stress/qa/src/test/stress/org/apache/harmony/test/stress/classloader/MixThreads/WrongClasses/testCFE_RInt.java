/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */    

/**
 * @author: Vera Y.Petrashkova
 * @version: $Revision: 1.8 $
 */
package org.apache.harmony.test.stress.classloader.MixThreads.WrongClasses;

import junit.framework.TestCase;

import org.apache.harmony.test.stress.classloader.share.ClassLoaderTestError;
import org.apache.harmony.test.share.stress.ReliabilityRunner;
import org.apache.harmony.test.stress.classloader.share.WrongClasses.testCFE_RInt_00;

public class testCFE_RInt extends TestCase {

    public static final int THREADS_COUNT = 128;

    public static final int LOOP_COUNT = 1000;

    private static int lmtThrd;

    private static int lmtLoop;

    public static int[] tableResults = new int[THREADS_COUNT];

    public static int mode = 1;

    public static int loopTest = 1000;

    public void test() {
        final int ARGS_COUNT = 6;

        String[] preTestArgs = new String[ARGS_COUNT];
        int testArgsCount;
        for (testArgsCount = 0; testArgsCount < ARGS_COUNT; testArgsCount++) {
            preTestArgs[testArgsCount] = System
                    .getProperty("org.apache.harmony.test."
                            + "stress.classloader.MixThreads."
                            + "WrongClasses.testCFE_RInt.arg" + testArgsCount);
            if (preTestArgs[testArgsCount] == null) {
                break;
            }
        }
        String[] testArgs = new String[testArgsCount];
        for (int k = 0; k < testArgsCount; k++) {
            testArgs[k] = preTestArgs[k];
        }

        // testArgs[0] - running mode
        // testArgs[1] - loop count
        // testArgs[2] - threads number
        // testArgs[3] - number of iterations
        // testArgs[4] - number of used classes
        // testArgs[5] - location of used classes

        mode = testArgs.length <= 0 ? 1 : (Integer.valueOf(testArgs[0]))
                .intValue();
        lmtLoop = testArgs.length <= 1 ? LOOP_COUNT : (Integer
                .valueOf(testArgs[1])).intValue();
        lmtThrd = testArgs.length <= 2 ? THREADS_COUNT : (Integer
                .valueOf(testArgs[2])).intValue();

        if (lmtThrd > THREADS_COUNT) {
            lmtThrd = THREADS_COUNT;
        }
        if ((testArgs.length - 3) <= 0) {
            testThread1.argsForTest = new String[0];
            testThread2.argsForTest = new String[0];
        } else {
            testThread1.argsForTest = new String[testArgs.length - 3];
            testThread2.argsForTest = new String[testArgs.length - 3];
            for (int t = 3; t < testArgs.length; t++) {
                testThread1.argsForTest[t - 3] = testArgs[t];
                testThread2.argsForTest[t - 3] = testArgs[t];
            }
            int l = testThread1.argsForTest.length;
            if (l <= 1) {
                mode = 0;
                loopTest = 1;
            } else {
                if (mode == 0) {
                    loopTest = 1;
                } else {
                    loopTest = (Integer.valueOf(testThread1.argsForTest[0]))
                            .intValue();
                    testThread1.argsForTest[0] = Integer.toString(1);
                }
            }
        }

        ReliabilityRunner.debug("Start testCFE_RInt");
        ReliabilityRunner.debug("Number of iterations: " + lmtLoop);
        ReliabilityRunner.debug("Number of created thread: " + lmtThrd);

        testCFE_RInt_00.logIn = false;

        try {

            for (int i = 0; i < lmtLoop; i++) {

                Thread tThread[] = new Thread[lmtThrd];
                for (int n = 0; n < tableResults.length; n++) {
                    tableResults[n] = 0;
                }
                for (int j = 0; j < lmtThrd; j++) {
                    if ((j % 2) == 0) {
                        tThread[j] = new testThread1(j);
                    } else {
                        tThread[j] = new testThread2(j);
                    }
                    tThread[j].start();
                }

                for (int j = 0; j < lmtThrd; j++) {
                    tThread[j].join();
                }

                for (int j = 0; j < lmtThrd; j++) {
                    if ((tableResults[j] % 104) != 0) {
                        ReliabilityRunner
                                .debug("Incorrect answer. Failed thread: " + j
                                        + "  incorrect result: "
                                        + tableResults[j] + " iteration: " + i);
                        ReliabilityRunner.mainTest.addError(this,
                                new ClassLoaderTestError());
                    }
                }
            }
        } catch (Throwable e) {
            ReliabilityRunner.debug(e.toString());
            ReliabilityRunner.mainTest.addError(this, e);
        }
    }

    static class testThread1 extends Thread {

        private int index;

        public static String[] argsForTest;

        public testThread1(int index) {
            this.index = index;
        }

        public void run() {
            testCFE_RInt_00 t = new testCFE_RInt_00();
            testCFE_RInt_00.cnt++;
            try {
                for (int i = 0; i < testCFE_RInt.loopTest; i++) {
                    synchronized (testCFE_RInt_00.class) {
                        int ans = t.test(argsForTest);
                        testCFE_RInt.tableResults[index] += ans;
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace(System.out);
                testCFE_RInt.tableResults[index] += 105;
            }
        }
    }

    static class testThread2 extends Thread {

        private int index;

        public static String[] argsForTest;

        public testThread2(int index) {
            this.index = index;
        }

        public void run() {
            testCFE_RInt_00 t = new testCFE_RInt_00();
            testCFE_RInt_00.cnt++;
            int ans = t.test(argsForTest);
            testCFE_RInt.tableResults[index] = ans;
        }
    }
}
