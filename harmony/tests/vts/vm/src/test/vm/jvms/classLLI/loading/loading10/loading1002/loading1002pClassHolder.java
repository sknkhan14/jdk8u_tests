/*
    Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

    See the License for the specific language governing permissions and
    limitations under the License.
*/
/** 
 * @author Mikhail Bolotov
 * @version $Revision: 1.1 $
 */  
package org.apache.harmony.vts.test.vm.jvms.classLLI.loading.loading10.loading1002;

public class loading1002pClassHolder {
	
	public static Class getDefault() {
		return loading1002pDefault[].class;
	}
	
	public static Class getPublic() {
		return loading1002pPublic[].class;
	}
	
	public static Class getProtected() {
		return loading1002pProtected[].class;
	}
	
	public static Class getPrivate() {
		return loading1002pPrivate[].class;
	}
	
	
        protected class loading1002pProtected {}

	private class loading1002pPrivate {}
}



class loading1002pDefault{}
