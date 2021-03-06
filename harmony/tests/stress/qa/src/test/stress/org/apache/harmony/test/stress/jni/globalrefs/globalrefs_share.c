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
 * @author Vladimir Nenashev
 * @version $Revision: 1.4 $
 */

#include"share.h"
#include<jni.h>

jclass java_lang_object = 0;
jclass clazz = 0;
jobject java_lang_out_of_memory_error = 0;
jmethodID cid = 0;  

void GlobalRefsTest_init(JNIEnv* env,
                         jclass c) {
  jclass oome_class;
  jmethodID oome_cid;
  setvbuf(stdout, (char*)NULL, _IONBF, 0);

  // get class and constructor IDs of java.lang.Object
  java_lang_object = (*env)->FindClass(env, "java/lang/Object");
  if (java_lang_object == NULL) {
      printf("Native code: Cannot find class java.lang.Object\n");
      return;
  }

  java_lang_object = (*env)->NewGlobalRef(env, java_lang_object);
  if (java_lang_object == NULL) {
      printf("Native code: Cannot create a global ref to java.lang.Object class\n");
      return;
  }

  cid = (*env)->GetMethodID(env, java_lang_object, "<init>", "()V");
  if (cid == NULL) {
    printf("Native code: Error getting constructor ID for java.lang.object\n");
    return;
  }

  clazz = (*env)->NewGlobalRef(env, c);
  if (clazz == NULL) {
    printf("Native code: Cannot create a global ref to this object's class\n");
    return;
  }

  oome_class = (*env)->FindClass(env, "java/lang/OutOfMemoryError");
  if (oome_class == NULL) {
    printf("Native code: Cannot find class java.lang.Object\n");
    return;
  }
  oome_cid = (*env)->GetMethodID(env, oome_class, "<init>", "()V");
  if (oome_cid == NULL) {
    printf("Native code: Error getting constructor ID for java.lang.OutOfMemoryError");
    return;
  }

  java_lang_out_of_memory_error = (*env)->NewObject(env, oome_class, oome_cid);
  if (java_lang_out_of_memory_error == NULL) {
      printf("Native code: Cannot allocate OOME object\n");
      return;
  }
  java_lang_out_of_memory_error = (*env)->NewGlobalRef(env, java_lang_out_of_memory_error);
  if (java_lang_out_of_memory_error == NULL) {
      printf("Native code: Cannot create a global ref to OOME object\n");
      return;
  }
}
