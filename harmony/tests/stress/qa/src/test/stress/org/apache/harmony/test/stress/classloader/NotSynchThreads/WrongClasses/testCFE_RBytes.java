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
 * @version: $Revision: 1.7 $
 */

package org.apache.harmony.test.stress.classloader.NotSynchThreads.WrongClasses;

import junit.framework.TestCase;

import org.apache.harmony.test.stress.classloader.share.ClassLoaderTestError;
import org.apache.harmony.test.share.stress.ReliabilityRunner;
import org.apache.harmony.test.stress.classloader.share.WrongClasses.testCFE_RBytes_00;

public class testCFE_RBytes extends TestCase {

    public static final int THREADS_COUNT = 128;

    public static final int LOOP_COUNT = 1000;

    private static int lmtThrd;

    private static int lmtLoop;

    public static int[] tableResults = new int[THREADS_COUNT];

    public void test() {
        final int ARGS_COUNT = 5;

        String[] preTestArgs = new String[ARGS_COUNT];
        int testArgsCount;
        for (testArgsCount = 0; testArgsCount < ARGS_COUNT; testArgsCount++) {
            preTestArgs[testArgsCount] = System
                    .getProperty("org.apache.harmony.test."
                            + "stress.classloader.NotSynchThreads."
                            + "WrongClasses.testCFE_RBytes.arg" + testArgsCount);
            if (preTestArgs[testArgsCount] == null) {
                break;
            }
        }
        String[] testArgs = new String[testArgsCount];
        for (int k = 0; k < testArgsCount; k++) {
            testArgs[k] = preTestArgs[k];
        }

        // testArgs[0] - loop count
        // testArgs[1] - thread number
        // testArgs[2] - number of iterations
        // testArgs[3] - number of used classes
        // testArgs[4] - location of used classes

        lmtLoop = testArgs.length <= 0 ? LOOP_COUNT : (Integer
                .valueOf(testArgs[0])).intValue();
        lmtThrd = testArgs.length <= 1 ? THREADS_COUNT : (Integer
                .valueOf(testArgs[1])).intValue();

        if (lmtThrd > THREADS_COUNT) {
            lmtThrd = THREADS_COUNT;
        }
        if ((testArgs.length - 2) <= 0) {
            testThread.argsForTest = new String[0];
        } else {
            testThread.argsForTest = new String[testArgs.length - 2];
            for (int t = 2; t < testArgs.length; t++) {
                testThread.argsForTest[t - 2] = testArgs[t];
            }
        }

        ReliabilityRunner.debug("Start testCFE_RBytes");
        ReliabilityRunner.debug("Number of iterations: " + lmtLoop);
        ReliabilityRunner.debug("Number of created thread: " + lmtThrd);

        testCFE_RBytes_00.logIn = false;

        try {
            for (int i = 0; i < lmtLoop; i++) {
                Thread tThread[] = new Thread[lmtThrd];
                for (int j = 0; j < lmtThrd; j++) {
                    tThread[j] = new testThread(j);
                    tThread[j].start();
                }
                for (int j = 0; j < lmtThrd; j++) {
                    tThread[j].join();
                }
                for (int j = 0; j < lmtThrd; j++) {
                    if (tableResults[j] != ReliabilityRunner.RESULT_PASS) {
                        ReliabilityRunner.debug("Incorrect resulted value. Failed thread:  " + j
                                + "  value:" + tableResults[j]
                                                            + "   iteration: " + i);
                        ReliabilityRunner.mainTest.addError(this, new ClassLoaderTestError());
                    }
                }
            }
        } catch (Throwable e) {
            ReliabilityRunner.debug(e.toString());
            ReliabilityRunner.mainTest.addError(this, e);
        }
    }

    static class testThread extends Thread {

        private int index;

        public static String[] argsForTest;

        public testThread(int index) {
            this.index = index;
        }

        public void run() {
            testCFE_RBytes.tableResults[index] = new testCFE_RBytes_00()
                    .test(argsForTest);

        }
    }

}
