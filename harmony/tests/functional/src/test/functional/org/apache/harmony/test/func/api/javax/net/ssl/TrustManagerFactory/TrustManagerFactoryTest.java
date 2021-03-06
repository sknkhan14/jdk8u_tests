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

package org.apache.harmony.test.func.api.javax.net.ssl.TrustManagerFactory;

import java.security.Security;

import javax.net.ssl.TrustManagerFactory;

import org.apache.harmony.share.MultiCase;
import org.apache.harmony.share.Result;

public class TrustManagerFactoryTest extends MultiCase {
    public static void main(String[] args) {
        System.exit(new TrustManagerFactoryTest().test(args));
    }

    public Result testGetDefaultAlgorithm() {
        if (!TrustManagerFactory.getDefaultAlgorithm().equals(
                Security.getProperty("ssl.KeyManagerFactory.algorithm"))
            && !TrustManagerFactory.getDefaultAlgorithm().equals("PKIX")) {
            return failed("expected TrustManagerFactory.getDefaultAlgorithm() to return value of security property ssl.TrustManagerFactory.algorithm");
        }

        String rnd = "" + System.currentTimeMillis() + Math.random();

        Security.setProperty("ssl.TrustManagerFactory.algorithm", rnd);
        if (!TrustManagerFactory.getDefaultAlgorithm().equals(rnd)) {
            return failed("expected TrustManagerFactory.getDefaultAlgorithm() to return value of security property ssl.TrustManagerFactory.algorithm after setting property got : " +  TrustManagerFactory.getDefaultAlgorithm());
        }

        return passed();
    }

}