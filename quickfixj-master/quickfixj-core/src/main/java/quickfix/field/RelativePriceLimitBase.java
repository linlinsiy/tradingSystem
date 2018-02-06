/* Generated Java Source File */
/*******************************************************************************
 * Copyright (c) quickfixengine.org  All rights reserved.
 *
 * This file is part of the QuickFIX FIX Engine
 *
 * This file may be distributed under the terms of the quickfixengine.org
 * license as defined by quickfixengine.org and appearing in the file
 * LICENSE included in the packaging of this file.
 *
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE.
 *
 * See http://www.quickfixengine.org/LICENSE for licensing information.
 *
 * Contact ask@quickfixengine.org if any conditions of this licensing
 * are not clear to you.
 ******************************************************************************/

package quickfix.field;

import quickfix.IntField;


public class RelativePriceLimitBase extends IntField {

	static final long serialVersionUID = 20050617;

	public static final int FIELD = 3015;
	public static final int RPLNone = 0;
	public static final int RPLFar = 1;
	public static final int RPLNear = 2;
	public static final int RPLBase = 3;
	public static final int RPLLast = 4;
	public static final int RPLOpen = 5;
	public static final int RPLHigh = 6;
	public static final int RPLLow = 7;
	public static final int RPLArrival = 8;
	public static final int RPLAverage = 9;
	
	public RelativePriceLimitBase() {
		super(3015);
	}

	public RelativePriceLimitBase(int data) {
		super(3015, data);
	}
	
}
