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

package org.apache.harmony.tests.java.awt;

import junit.framework.TestCase;

import java.awt.*;

public class DisplayModeTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DisplayModeTest.class);
    }

    /**
     * Test method for {@link java.awt.DisplayMode#equals(java.lang.Object)}.
     */
    public void testEqualsObject() {
        assertFalse(new DisplayMode(1, 2, 3, 10).equals((Object) null));
        assertFalse(new DisplayMode(1, 2, 3, 10).equals(new Object()));
    }

    /**
     * Test method for {@link java.awt.DisplayMode#equals(java.awt.DisplayMode)}.
     */
    public void testEqualsDisplayMode() {
        // Regression test for HARMONY-2444
        assertFalse(new DisplayMode(1, 2, 3, 10).equals((DisplayMode) null));
    }
}