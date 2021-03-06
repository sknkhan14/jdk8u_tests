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

#include <stdio.h>
#include <jvmti.h>


extern "C" {
    JNIEXPORT jboolean JNICALL Java_org_apache_harmony_test_func_reg_vm_btest6080_Btest6080_foo(JNIEnv *, jclass);
}

JNIEXPORT jboolean JNICALL Java_org_apache_harmony_test_func_reg_vm_btest6080_Btest6080_foo(JNIEnv *env, jclass cls)
{

    jmethodID mid = NULL;

    mid = (env)->GetStaticMethodID(cls, "Call", "()V");
    if (mid == NULL) {
        return JNI_FALSE;
    }

    (env)->CallStaticVoidMethod(cls, mid);

    if ((env)->ExceptionOccurred()) {
        (env)->ExceptionDescribe();
        return true;
    } else {
        return false;
    }

    return true;
}

static jvmtiEnv *jvmti = NULL;

JNIEXPORT jint
JNICALL Agent_OnLoad(JavaVM * jvm, char *options, void *reserved)
{

    jint res;

    res = jvm->GetEnv((void **) &jvmti, JVMTI_VERSION_1_0);

    if (res != JNI_OK || jvmti == NULL) {
        return JNI_ERR;
    }

    fprintf(stderr, "Agent load ...\n");
    return JNI_OK;
}



