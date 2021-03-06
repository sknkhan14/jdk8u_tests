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

package org.apache.harmony.test.func.api.javax.swing.text.AbstractDocument;

import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;

import org.apache.harmony.test.func.api.javax.swing.share.InstrumentedAbstractDocument;
import org.apache.harmony.test.func.api.javax.swing.share.InstrumentedAttributeSet;
import org.apache.harmony.test.func.api.javax.swing.share.InstrumentedBranchElement;
import org.apache.harmony.test.func.api.javax.swing.share.InstrumentedElement;
import org.apache.harmony.test.func.api.javax.swing.share.InstrumentedLookAndFeel;
import org.apache.harmony.test.func.api.javax.swing.share.InstrumentedUILog;
import org.apache.harmony.share.MultiCase;
import org.apache.harmony.share.Result;

public class BranchElementTest extends MultiCase {
    public static void main(String[] args)
            throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new InstrumentedLookAndFeel());
        System.exit(new BranchElementTest().test(args));
    }

    public Result testConstructor() {
        InstrumentedAbstractDocument ad = new InstrumentedAbstractDocument();
        Element e = new InstrumentedElement();
        AttributeSet as = new InstrumentedAttributeSet();

        ad.writeLockExposed();
        InstrumentedUILog.clear();

        try {
            new InstrumentedBranchElement(ad, e, as);
            if (!InstrumentedUILog.equals((new Object[][] {
            /* 1 */{ "BranchElement.addAttributes", as },
            /* 2 */{ "AttributeSet.getAttributeCount" },
            /* 3 */{ "AttributeSet.getAttributeNames" },
            /* 4 */{ "AttributeSet.getAttribute", "AttributeName1" }, }))
                    && !InstrumentedUILog
                            .equals((new Object[][] {
                                    /* 1 */{ "BranchElement.addAttributes", as },
                                    /* 3 */{ "AttributeSet.getAttributeNames" },
                                    /* 4 */{ "AttributeSet.getAttribute",
                                            "AttributeName1" }, }))) {
                InstrumentedUILog.printLog();
                return failed("expected constructor to call another sequence of events");
            }
        } catch (Throwable ee) {
            InstrumentedUILog.printLogAsArray();
            return failed("got exception");
        } finally {
            ad.writeUnLockExposed();
        }

        return passed();
    }

    public Result testAttributes() {
        InstrumentedBranchElement e = new InstrumentedBranchElement();
        AttributeSet as = new InstrumentedAttributeSet();

        e.getInstrumentedAbstractDocument().writeLockExposed();

        InstrumentedUILog.clear();

        if (e.getAttributes() != e) {
            return failed("expected BranchElement.getAttributes() to return element itself");
        }

        if (!InstrumentedUILog
                .equals(new Object[][] { { "BranchElement.getAttributes" } })) {
            InstrumentedUILog.printLog();
            return failed("expected getAttributes() not to call any additional methods");
        }
        InstrumentedUILog.clear();

        if (e.getAttributeNames().hasMoreElements()) {
            return failed("expected BranchElement.getAttributeNames() to be empty by default");
        }

        if (!InstrumentedUILog
                .equals(new Object[][] { { "BranchElement.getAttributeNames" } })) {
            InstrumentedUILog.printLog();
            return failed("expected getAttributeNames() not to call any additional methods");
        }

        InstrumentedUILog.clear();

        if (e.getAttributeCount() != 0) {
            return failed("expected BranchElement.getAttributeCount() to return 0 by default");
        }

        if (!InstrumentedUILog
                .equals(new Object[][] { { "BranchElement.getAttributeCount" } })) {
            InstrumentedUILog.printLog();
            return failed("expected getAttributeCount() not to call any additional methods");
        }

        InstrumentedUILog.clear();

        try {
            e.addAttributes(as);
            if (!InstrumentedUILog.equals(new Object[][] {
            /* 1 */{ "BranchElement.addAttributes", as },
            /* 2 */{ "AttributeSet.getAttributeCount" },
            /* 3 */{ "AttributeSet.getAttributeNames" },
            /* 4 */{ "AttributeSet.getAttribute", "AttributeName1" }, })
                    && !InstrumentedUILog
                            .equals(new Object[][] {
                                    /* 1 */{ "BranchElement.addAttributes", as },
                                    /* 2 */{ "AttributeSet.getAttributeNames" },
                                    /* 3 */{ "AttributeSet.getAttribute",
                                            "AttributeName1" }, })) {
                InstrumentedUILog.printLog();
                return failed("expected addAttributes() to call another sequence of events");
            }

            as = new InstrumentedAttributeSet() {
                public int getAttributeCount() {
                    super.getAttributeCount();
                    return -1;
                };
            };

            InstrumentedUILog.clear();

            e.addAttributes(as);
            if (!InstrumentedUILog.equals(new Object[][] {
            /* 1 */{ "BranchElement.addAttributes", as },
            /* 2 */{ "AttributeSet.getAttributeNames" },
            /* 3 */{ "AttributeSet.getAttribute", "AttributeName1" }, })

            && !InstrumentedUILog.equals(new Object[][] {
            /* 1 */{ "BranchElement.addAttributes", as },
            /* 2 */{ "AttributeSet.getAttributeCount" },
            /* 3 */{ "AttributeSet.getAttributeNames" },
            /* 4 */{ "AttributeSet.getAttribute", "AttributeName1" }, })) {
                InstrumentedUILog.printLog();
                return failed("expected addAttributes() with negative elements count to call another sequence of events");
            }

            as = new InstrumentedAttributeSet() {
                public int getAttributeCount() {
                    super.getAttributeCount();
                    return 0;
                };
            };

            InstrumentedUILog.clear();

            e.addAttributes(as);

            if (!InstrumentedUILog.equals(new Object[][] {
            /* 1 */{ "BranchElement.addAttributes", as },
            /* 2 */{ "AttributeSet.getAttributeCount" },
            /* 3 */{ "AttributeSet.getAttributeNames" },
            /* 4 */{ "AttributeSet.getAttribute", "AttributeName1" }, })
                    && !InstrumentedUILog
                            .equals(new Object[][] {
                                    /* 1 */{ "BranchElement.addAttributes", as },
                                    /* 2 */{ "AttributeSet.getAttributeNames" },
                                    /* 3 */{ "AttributeSet.getAttribute",
                                            "AttributeName1" }, })) {
                InstrumentedUILog.printLog();
                return failed("expected addAttributes() with zero elements count to call another sequence of events");
            }

            if (!"AttributeValue1".equals(e.getAttribute("AttributeName1"))) {
                InstrumentedUILog.printLog();
                return failed("expected attribute value to be set");
            }

            if (e.getAttribute("attributename1") != null) {
                return failed("expected attribute value to be case-insensitive");
            }

            try {
                e.getAttribute(null);
                return failed("expected getAttribute(null) to throw NPE");
            } catch (NullPointerException npe) {
            }

            try {
                e.addAttribute(null, "abc");
                return failed("expected setAttribute(null, String) to throw NPE");
            } catch (NullPointerException npe) {
            }

            Enumeration eennuumm = e.getAttributeNames();
            if (!eennuumm.hasMoreElements()
                    || !"AttributeName1".equals(eennuumm.nextElement())
                    || eennuumm.hasMoreElements()) {
                return failed("expected attributeNames to contain only one name");
            }

            AttributeSet attrset = e.getAttributes();
            if (!attrset.isEqual(new InstrumentedAttributeSet())) {
                return failed("expected attributesets to be equal");
            }

        } finally {
            e.getInstrumentedAbstractDocument().writeUnLockExposed();
        }

        return passed();
    }

    public Result testDocument() {
        InstrumentedAbstractDocument d = new InstrumentedAbstractDocument();

        InstrumentedUILog.clear();
        InstrumentedBranchElement e = new InstrumentedBranchElement(d);

        if (e.getDocument() != d) {
            return failed("expected getDocument() to return constructor argument");
        }

        try {
            new InstrumentedBranchElement(null);
            return failed("expected BranchElement constructor with document == null to throw NPE");
        } catch (NullPointerException npe) {
        }

        return passed();
    }

    public Result testElements() {
        InstrumentedAbstractDocument ad = new InstrumentedAbstractDocument();
        Element ie = new InstrumentedElement();
        AttributeSet as = new InstrumentedAttributeSet();

        ad.writeLockExposed();

        try {
            InstrumentedBranchElement be = new InstrumentedBranchElement(ad,
                    ie, as);

            InstrumentedUILog.clear();

            if (be.getElement(1234) != null) {
                return failed("expected getElement(1234) to return null");
            }

            if (!InstrumentedUILog.equals(new Object[][] { {
                    "BranchElement.getElement", "1234" } })) {
                InstrumentedUILog.printLog();
                return failed("expected BranchElement.getElement(1234) no to call any more methods");
            }

            try {
                be.getElement(-1234);
                //return failed("expected getElement(-1234) to throw
                // ArrayIndexOutOfBoundsException");
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            InstrumentedUILog.clear();

            if (be.getElementCount() != 0) {
                return failed("expected getElementCount() to return 0");
            }

            if (!InstrumentedUILog
                    .equals(new Object[][] { { "BranchElement.getElementCount" } })) {
                InstrumentedUILog.printLog();
                return failed("expected BranchElement.getElementCount() no to call any more methods");
            }
        } finally {
            ad.writeUnLockExposed();
        }

        return passed();
    }

    public Result testGetElementIndex() {
        InstrumentedBranchElement be = new InstrumentedBranchElement() {
            public int getStartOffset() {
                try {
                    super.getStartOffset();
                } catch (NullPointerException e) {
                }
                return 15;
            }
        };
        InstrumentedUILog.clear();

        if (be.getElementIndex(1234) != 0 && be.getElementIndex(1234) != -1) {
            return failed("expected getElementIndex(1234) to return 0or -1");
        }

        if (!InstrumentedUILog.equals(new Object[][] {
                { "BranchElement.getElementIndex", "1234" },
                { "BranchElement.getStartOffset" } })) {
            InstrumentedUILog.printLog();
            return failed("expected BranchElement.getElementIndex(1234) no to call any more methods");
        }

        InstrumentedUILog.clear();

        if (be.getElementIndex(-1234) != 0) {
            return failed("expected getElementIndex(-1234) to return 0");
        }

        if (!InstrumentedUILog.equals(new Object[][] {
                { "BranchElement.getElementIndex", "-1234" },
                { "BranchElement.getStartOffset" } })) {
            InstrumentedUILog.printLog();
            return failed("expected BranchElement.getElementIndex(-1234) no to call any more methods");
        }

        return passed();
    }

    public Result testGetOffset() {
        InstrumentedBranchElement be = new InstrumentedBranchElement() {
            public int getStartOffset() {
                try {
                    super.getStartOffset();
                } catch (NullPointerException e) {
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                return 15;
            }

            public int getEndOffset() {
                try {
                    super.getEndOffset();
                } catch (NullPointerException e) {
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                return 25;
            }

        };
        InstrumentedUILog.clear();
        be.getStartOffset();

        if (!InstrumentedUILog
                .equals(new Object[][] { { "BranchElement.getStartOffset" } })) {
            InstrumentedUILog.printLog();
            return failed("expected BranchElement.getStartOffset() no to call any more methods");
        }

        InstrumentedUILog.clear();
        be.getEndOffset();
        if (!InstrumentedUILog
                .equals(new Object[][] { { "BranchElement.getEndOffset" } })) {
            InstrumentedUILog.printLog();
            return failed("expected BranchElement.getEndOffset() no to call any more methods");
        }

        InstrumentedUILog.clear();

        return passed();
    }

    public Result testReplace() {
        InstrumentedBranchElement be = new InstrumentedBranchElement() {
            public int getStartOffset() {
                try {
                    super.getStartOffset();
                } catch (NullPointerException e) {
                }
                return 0;
            }

            public int getEndOffset() {
                try {
                    super.getEndOffset();
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                return 3;
            }
        };

        try {
            be.replace(0, 1, new Element[] { null, null, null });
            return failed("expected ArrayIndexOutOfBoundsException in replace(0,1, Element[3])");
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (NegativeArraySizeException e) {
        }

        try {
            be.replace(0, 3, new Element[] { null });
            return failed("expected ArrayIndexOutOfBoundsException in replace(0,3, Element[1])");
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (NegativeArraySizeException e) {
        }

        try {
            be.replace(0, 3, new Element[] { null, null, null });
            return failed("expected ArrayIndexOutOfBoundsException in replace(0,3, Element[3])");
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (NegativeArraySizeException e) {
        }

        return passed();
    }

}