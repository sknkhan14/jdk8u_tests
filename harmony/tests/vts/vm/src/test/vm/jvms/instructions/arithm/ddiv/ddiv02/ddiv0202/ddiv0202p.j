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
; Version: $Revision: 1.2 $
;

.class public org/apache/harmony/vts/test/vm/jvms/instructions/arithm/ddiv/ddiv02/ddiv0202/ddiv0202p
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokespecial java/lang/Object/<init>()V
   return
.end method

;
; test method
.method public test([Ljava/lang/String;)I
   .limit stack 4
   .limit locals 2

   ldc2_w 2187846742242790753 ; bitwise equal 2.0E-162 double
   invokestatic java/lang/Double/longBitsToDouble(J)D

   ldc2_w 7026117026808561016 ; bitwise equal 5.0E161 double
   invokestatic java/lang/Double/longBitsToDouble(J)D

   ddiv ; must be 2.0E-162 
   invokestatic java/lang/Double/doubleToLongBits(D)J

   ldc2_w 1 ; bitwise equal 4.0E-324 double (with masked last 6 bits)
   lcmp
   ifne Fail
   sipush 104
   ireturn

 Fail:
   sipush 105
   ireturn

   return
.end method

;
; standard main function
.method public static main([Ljava/lang/String;)V
  .limit stack 2
  .limit locals 1

  new org/apache/harmony/vts/test/vm/jvms/instructions/arithm/ddiv/ddiv02/ddiv0202/ddiv0202p
  dup
  invokespecial org/apache/harmony/vts/test/vm/jvms/instructions/arithm/ddiv/ddiv02/ddiv0202/ddiv0202p/<init>()V
  aload_0
  invokevirtual org/apache/harmony/vts/test/vm/jvms/instructions/arithm/ddiv/ddiv02/ddiv0202/ddiv0202p/test([Ljava/lang/String;)I
  invokestatic java/lang/System/exit(I)V

  return
.end method
