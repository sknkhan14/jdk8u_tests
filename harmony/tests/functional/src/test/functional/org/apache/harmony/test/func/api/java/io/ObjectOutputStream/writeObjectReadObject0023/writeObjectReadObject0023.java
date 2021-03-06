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
package org.apache.harmony.test.func.api.java.io.ObjectOutputStream.writeObjectReadObject0023;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.harmony.test.func.api.java.io.ObjectOutputStream.share.SerializationTestFramework;

public class writeObjectReadObject0023 extends SerializationTestFramework {
    public static void main(String[] args) {
        System.exit(new writeObjectReadObject0023().test(args));
    }

    protected int testIn(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        
        waitAtBarrier();
          Object  o = ois.readObject();

        if (!(o instanceof ArrayList)) {
            return fail("expected another value");
        }

        List list = (List) o;

        if (list.size() == 4 && "gg".equals(list.get(0))
                && new Boolean(false).equals(list.get(1))
                && new Long(4).equals(list.get(2)) && null == list.get(3)) {
            return pass();
        }

        return fail("wrong values restored");
    }

    protected int testOut(ObjectOutputStream oos) throws IOException {

            List list = new ArrayList();

            list.add("gg");
            list.add(new Boolean(false));
            list.add(new Long(4));
            list.add(null);

            waitAtBarrier();
            oos.writeObject(list);

        return pass();
    }
}

