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

package org.apache.harmony.test.stress.jni.strings;

import org.apache.harmony.test.stress.jni.share.MTTest;
import org.apache.harmony.test.stress.jni.share.JNITestError;
import org.apache.harmony.test.share.stress.ReliabilityRunner;

/**
 * @author Vladimir Nenashev
 * @version $Revision: 1.8 $
 */

public class StringsTest4 extends MTTest {

    public StringsTest4(int threadId) {
        super(threadId);
    }
    
    public StringsTest4() {
        super(-1);
    }

    private static native void init();

    private native int nativeMethod(int len, int maxCnt);

    public void doTest() {
        try {
            int length = Integer.getInteger(
                    "org.apache.harmony.test.stress.jni."
                            + "strings.StringsTest4.length", 100000)
                    .intValue();
            int maxStrCount = Integer.getInteger(
                    "org.apache.harmony.test.stress.jni."
                            + "strings.StringsTest4.maxStrCount", 10)
                    .intValue();

            int res = nativeMethod(length, maxStrCount);

            if (res == 0) {
                testLog("Working thread",
                        "StringsTest4 FAILED: native method returned 0");
                ReliabilityRunner.debug("Test failed");
                ReliabilityRunner.mainTest.addError(this, new JNITestError());
            }
        } catch (Throwable t) {
            testLog("Working thread", "StringsTest4: ERROR: Exception caught:");
            testLog("Working thread", t);
            ReliabilityRunner.debug("Test error");
            ReliabilityRunner.mainTest.addError(this, new JNITestError());
        }
    }

    /**
     * Method called from native code to perform calculation on specified string
     * 
     * @param str
     *            string to be processed
     * @return - resulting string
     */
    private static String doCalc(String str) {
        StringBuffer buf = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--)
            buf.append(str.charAt(i));
        return buf.toString();
    }

    static {
        init();
    }
}
