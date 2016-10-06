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

package org.apache.harmony.test.func.reg.jit.btest3012;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import org.apache.harmony.test.share.reg.RegressionTest;

public class Btest3012 extends RegressionTest {

    public static void main(String[] args) {
        System.exit(new Btest3012().test(Logger.global, args));
    }
    
    public int test(Logger logger, String [] args) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            C c = new C();
            c.i = 1234;
            c.j = 5678;

            oos.writeObject(c);
            oos.close();
            System.err.println("closed");

            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(baos.toByteArray()));
            System.err.println("opened");
        
            c  = (C) ois.readObject();
            System.err.println("read " + c.i + " " + c.j);
            System.err.println("(1234 5678 expected)");

            ois.close();
            return ((c.i == 1234) && (c.j == 5678)) ? passed() : failed();
        } catch (Exception e) {
            System.err.println("ERROR: Unexpected exception was thrown:");
            e.printStackTrace();
            return failed();
        }
    }    
}

class C implements Serializable {
    int i;
    long j;
}

