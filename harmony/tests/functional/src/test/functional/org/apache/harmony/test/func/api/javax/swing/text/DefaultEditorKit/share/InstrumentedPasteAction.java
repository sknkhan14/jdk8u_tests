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
 
package org.apache.harmony.test.func.api.javax.swing.text.DefaultEditorKit.share;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.text.DefaultEditorKit;

import org.apache.harmony.test.func.api.javax.swing.share.InstrumentedUILog;


public class InstrumentedPasteAction extends  DefaultEditorKit.PasteAction {
    public void actionPerformed(ActionEvent arg0) {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.actionPerformed",  arg0} );
        super.actionPerformed(arg0);
    }
    public boolean isEnabled() {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.isEnabled"} );
        return super.isEnabled();
    }
    public void setEnabled(boolean arg0) {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.setEnabled", "" +  arg0} );
        super.setEnabled(arg0);
    }
    public synchronized PropertyChangeListener[] getPropertyChangeListeners() {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.getPropertyChangeListeners"} );
        return super.getPropertyChangeListeners();
    }
    public synchronized void addPropertyChangeListener(
            PropertyChangeListener arg0) {
        super.addPropertyChangeListener(arg0);
    }
    public synchronized void removePropertyChangeListener(
            PropertyChangeListener arg0) {
        super.removePropertyChangeListener(arg0);
    }
    protected Object clone() throws CloneNotSupportedException {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.clone"} );
        return super.clone();
    }
    public Object[] getKeys() {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.getKeys"} );
        return super.getKeys();
    }
    public Object getValue(String arg0) {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.getValue",  arg0} );
        return super.getValue(arg0);
    }
    public void putValue(String arg0, Object arg1) {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.putValue",  arg0,  arg1} );
        super.putValue(arg0, arg1);
    }
    protected void firePropertyChange(String arg0, Object arg1, Object arg2) {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.firePropertyChange",  arg0,  arg1,  arg2} );
        super.firePropertyChange(arg0, arg1, arg2);
    }
    public int hashCode() {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.hashCode"} );
        return super.hashCode();
    }
    protected void finalize() throws Throwable {
        super.finalize();
    }
    public boolean equals(Object arg0) {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.equals",  arg0} );
        return super.equals(arg0);
    }
    public String toString() {
        InstrumentedUILog.add(new Object[] {"DefaultEditorKit.PasteAction.toString"} );
        return super.toString();
    }
}
