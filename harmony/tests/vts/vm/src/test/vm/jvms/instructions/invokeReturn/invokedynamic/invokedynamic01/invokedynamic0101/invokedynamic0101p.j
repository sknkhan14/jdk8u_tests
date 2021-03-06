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

;
; Author: Alexander V. Esin
; Version: $Revision: 1.2 $
;

.class public org/apache/harmony/vts/test/vm/jvms/instructions/invokeReturn/invokedynamic/invokedynamic01/invokedynamic0101/invokedynamic0101p
.super java/lang/Object

.method public <init>()V
   aload_0
   invokespecial java/lang/Object/<init>()V
   return
.end method

;
; test method
.method public test([Ljava/lang/String;)I
   .limit stack 2
   .limit locals 2
   
   aload_0
   invokedynamic org/apache/harmony/vts/test/vm/jvms/instructions/invokeReturn/invokedynamic/invokedynamic01/invokedynamic0101/invokedynamic0101p/dynamicTest()I
   ireturn ; return 104
.end method

;
; dynamicTest method
.method public dynamicTest()I
   .limit stack 1
   .limit locals 1

   sipush 104
   ireturn
.end method

;
; standard main function
.method public static main([Ljava/lang/String;)V
  .limit stack 2
  .limit locals 1

  new org/apache/harmony/vts/test/vm/jvms/instructions/invokeReturn/invokedynamic/invokedynamic01/invokedynamic0101/invokedynamic0101p
  dup
  invokespecial org/apache/harmony/vts/test/vm/jvms/instructions/invokeReturn/invokedynamic/invokedynamic01/invokedynamic0101/invokedynamic0101p/<init>()V
  aload_0
  invokevirtual org/apache/harmony/vts/test/vm/jvms/instructions/invokeReturn/invokedynamic/invokedynamic01/invokedynamic0101/invokedynamic0101p/test([Ljava/lang/String;)I
  invokestatic java/lang/System/exit(I)V
  return
.end method
