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
; Author: Alexander D. Shipilov
; Version: $Revision: 1.3 $
;

.class public org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101p
.super java/lang/Thread

;
; initializer
.method public <init>()V
   aload_0
   invokespecial java/lang/Thread/<init>()V  
   return
.end method

;
; test method
.method public test([Ljava/lang/String;)I
   .limit stack 3
   .limit locals 4


; create locker array int[1]. array[0] = 105
   iconst_1
   newarray int
   astore_2
   aload_2
   iconst_0
   sipush 105
   iastore

; create first thread
   new org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101pFirst
   dup
   aload_2
   invokespecial org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101pFirst/<init>([I)V
   astore_3

; start first thread
   aload_3
   invokevirtual org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101pFirst/start()V

; do while first thread working
   aload_3
   invokevirtual org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101pFirst/join()V

; returning result
   aload_2
   iconst_0
   iaload
   ireturn

.end method

;
; standard main function
.method public static main([Ljava/lang/String;)V
  .limit stack 3
  .limit locals 1

  new org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101p
  dup
  invokespecial org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101p/<init>()V
  aload_0
  invokevirtual org/apache/harmony/vts/test/vm/jvms/instructions/sync/monitorexit/monitorexit01/monitorexit0101/monitorexit0101p/test([Ljava/lang/String;)I
  invokestatic java/lang/System/exit(I)V
  
  return
.end method
