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
; Author: Khen G. Kim
; Version: $Revision $
;

.class public org/apache/harmony/vts/test/vm/jvms/classFile/verifier/longAndDouble/longAndDouble01/longAndDouble02n

.super java/lang/Object

;
; standard initializer
.method public <init>()V
   .limit locals 1
   .limit stack 1
   aload_0
   invokespecial java/lang/Object/<init>()V
   return
.end method


;
; method test
.method public test()V
   .limit locals 4
   .limit stack 3 
   iconst_1
   istore_3 
   dconst_1
   dstore_2 
   iload_3 ; Try to use unusable 2+1 index for double value placed at index 2
   return
.end method


