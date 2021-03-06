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
 * Created on 30.11.2004
 *
 */
package org.apache.harmony.test.func.api.java.util.jar.share;

import org.apache.harmony.share.Result;

/**
 *
 */

public class ProcessResult {
    int exitCode;

    String lastLine;

    public ProcessResult(int exitCode, String lastLine) {
        this.exitCode = exitCode;
        this.lastLine = lastLine;
    }
    
    public int getExitCode() {
        return exitCode;
        }
    public String getOutput() {
        return lastLine;
        }

    /**
     * @param string
     * @return
     */
    public static ProcessResult fail(String string) {
        return new ProcessResult(Result.FAIL, string);
    }
}