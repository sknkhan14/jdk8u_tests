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
 * @author Anton Luht
 * @version $Revision: 1.3 $
 */
/*
 * Created on 01.12.2004
 *
 */
package org.apache.harmony.test.stress.api.java.io.share;

import java.io.File;
import java.io.IOException;

public interface PrepareTestCleanup {
    public int test(String[] args);
    
    public int prepare(File dir) throws IOException; 

    public int cleanup(File dir) throws IOException;

    public void setTestDir(File dir);
}
