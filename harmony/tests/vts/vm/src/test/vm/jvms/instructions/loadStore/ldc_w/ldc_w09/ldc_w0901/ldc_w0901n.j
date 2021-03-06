;    Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
;
;    Licensed under the Apache License, Version 2.0 (the "License");
;    you may not use this file except in compliance with the License.
;    You may obtain a copy of the License at
;
;       http://www.apache.org/licenses/LICENSE-2.0
;
;    Unless required by applicable law or agreed to in writing, software
;    distributed under the License is distributed on an "AS IS" BASIS,
;    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;
;    See the License for the specific language governing permissions and
;    limitations under the License.


; Author: Alexander V. Esin
; Version: $Revision: 1.1 $
.bytecode 49.0
.class public org/apache/harmony/vts/test/vm/jvms/instructions/loadStore/ldc_w/ldc_w09/ldc_w0901/ldc_w0901n
.super java/lang/Object

.method public <init>()V
   aload_0
   invokespecial java/lang/Object/<init>()V
   return
.end method


; Test method
.method public test([Ljava/lang/String;)I
   .limit stack 2
   .limit locals 2

   .catch java/lang/NoClassDefFoundError from first to second using catcher
 first:
   ; Try to push nonexistent class file onto the stack
   ldc_w org/apache/harmony/vts/test/vm/jvms/instructions/loadStore/ldc_w/ldc_w09/ldc_w0901/some/bad/path/to/Class
 second:
   ;if there is no error thrown, that means that is the erroneous case
   sipush 105
   ireturn
 catcher:
   sipush 104
   ireturn
.end method

.method public static main([Ljava/lang/String;)V
  .limit stack 2
  .limit locals 1

  new org/apache/harmony/vts/test/vm/jvms/instructions/loadStore/ldc_w/ldc_w09/ldc_w0901/ldc_w0901n
  dup
  invokespecial org/apache/harmony/vts/test/vm/jvms/instructions/loadStore/ldc_w/ldc_w09/ldc_w0901/ldc_w0901n/<init>()V
  aload_0
  invokevirtual org/apache/harmony/vts/test/vm/jvms/instructions/loadStore/ldc_w/ldc_w09/ldc_w0901/ldc_w0901n/test([Ljava/lang/String;)I
  invokestatic java/lang/System/exit(I)V
  return
.end method
