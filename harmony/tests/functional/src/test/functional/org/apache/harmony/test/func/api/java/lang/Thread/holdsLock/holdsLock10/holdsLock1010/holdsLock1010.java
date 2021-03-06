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

/*
*/


package org.apache.harmony.test.func.api.java.lang.Thread.holdsLock.holdsLock10.holdsLock1010;

import org.apache.harmony.share.Test;


import org.apache.harmony.test.func.share.MyLog;

public class holdsLock1010 extends Test {

    boolean  results[] = new boolean[100];
    String  logArray[] = new String[100];
    int     logIndex   = 0; 
        Object hlObjects[] = { null, new Object(), new Object() };
        boolean bhl[] = { false, false, false, false };

    void addLog(String s) {
        if ( logIndex < logArray.length )
            logArray[logIndex] = s;
        logIndex++;
    }

    void holdsLock1010() {

            ThreadholdsLock1010 t = new ThreadholdsLock1010("t1");
            label: {
                synchronized(hlObjects[2]) {
                    synchronized(hlObjects[1]) {
                        t.start();
                        try {
                            bhl[1] = ( Thread.holdsLock(hlObjects[1]) &&
                                       Thread.holdsLock(hlObjects[2])   );
                            hlObjects[1].wait();
                            bhl[2] = ( Thread.holdsLock(hlObjects[1]) &&
                                       Thread.holdsLock(hlObjects[2])   );
                        } catch (InterruptedException e) {
                            addLog("ERROR: unexpectead InterruptedException");
                            results[results.length -1] = false;
                            break label;
                        }
                    }
                }
            }
//-1
            results[1] = bhl[1];
//-1)
//-2
            results[2] = bhl[2];
//-2)
//-3
            results[3] = bhl[3];
//-3)
        return ;
    }

class ThreadholdsLock1010 extends Thread {
    ThreadholdsLock1010(String s) {super(s);}
    public void run() {
        synchronized(hlObjects[1]) {
            bhl[3] = ( Thread.holdsLock(hlObjects[1]) &&
                     ( ! Thread.holdsLock(hlObjects[2]) )  );
            hlObjects[1].notify();
        }
        synchronized(hlObjects[2]) {
        }
    }
}


    public int test() {

        logIndex = 0;

        String texts[] = { "Testcase FAILURE, results[#] = " ,
                           "Test P A S S E D"                ,
                           "Test F A I L E D"                ,
                           "#### U N E X P E C T E D : "     };

        int    failed   = 105;
        int    passed   = 104;
        int  unexpected = 106;

        int    toReturn = 0;
        String toPrint  = null;

        for ( int i = 0; i < results.length; i++ )
            results[i] = true;

        try {

            addLog("*********  Test holdsLock1010 begins ");
holdsLock1010();
            addLog("*********  Test holdsLock1010 results: ");

            boolean result = true;
            for ( int i = 1 ; i < results.length ; i++ ) {
                result &= results[i];
                if ( ! results[i] )
                    addLog(texts[0] + i);
            }
            if ( ! result ) {
                toPrint  = texts[2];
                toReturn = failed;
            }
            if ( result ) {
                toPrint  = texts[1];
                toReturn = passed;
            }
        } catch (Exception e) {
            toPrint  = texts[3] + e;
            toReturn = unexpected;
        }
        if ( toReturn != passed )
            for ( int i = 0; i < logIndex; i++ )
                MyLog.toMyLog(logArray[i]);

        MyLog.toMyLog(toPrint);
        return toReturn;
    }

    public static void main(String args[]) {
        System.exit(new holdsLock1010().test());
    }
}



