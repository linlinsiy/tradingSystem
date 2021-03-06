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


public class RelativePriceLimitType extends IntField {

	static final long serialVersionUID = 20050617;

	public static final int FIELD = 3016;
	public static final int Ticks = 1;
	public static final int Neartouch = 2;
	public static final int Base = 3;
	public static final int TodayOpen = 4;
	public static final int High = 5;
	public static final int Low = 6;
	public static final int Arrival = 7;
	public static final int Average = 8;
	
	public RelativePriceLimitType() {
		super(3016);
	}

	public RelativePriceLimitType(int data) {
		super(3016, data);
	}
	
}
