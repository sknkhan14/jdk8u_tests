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
 * Please, note that compiled RemoveMe class should be deleted before the test
 * execution. 
*/

package org.apache.harmony.test.func.reg.jit.btest5014;

public class Btest5014 {   
    
    public static void main(String [] args) {
        if (args.length == 0) {
            new Test5014().test1();
        } else {
            new Test5014().test2();
        }
    }
}

class Test5014 {
   
    void test1() {}
    
    void test2() {
        RemoveMe [] arr = test();
        RemoveMe element = arr[3];
    }

    RemoveMe [] test() {
        return new RemoveMe [10];
    }
}

class RemoveMe {}
