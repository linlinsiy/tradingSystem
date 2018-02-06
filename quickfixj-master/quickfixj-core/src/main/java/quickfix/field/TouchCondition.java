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

import quickfix.CharField;


public class TouchCondition extends CharField {

	static final long serialVersionUID = 20050617;

	public static final int FIELD = 8010;
	public static final char IMMEDIATELY = '1';
	public static final char STOP_LOSS = '2';
	public static final char CHECK_WIN = '3';
	public static final char EMBEDDED_SINGLE = '4';
	public static final char CONDITION_THAN_LATEST = '5';
	public static final char CONDITION_EQUAL_GREATER_THAN_LATEST = '6';
	public static final char CONDITION_LESS_THAN_LATEST = '7';
	public static final char CONDITION_LESS_THAN_EQUAL_LATEST = '8';
	public static final char CONDITION_THAN_SELL1 = '9';
	public static final char CONDITION_EQUAL_GREATER_THAN_SELL1 = 'A';
	public static final char CONDITION_LESS_THAN_SELL1 = 'B';
	public static final char CONDITION_LESS_THAN_EQUAL_SELL1 = 'C';
	public static final char CONDITION_THAN_BUY1 = 'D';
	public static final char CONDITION_EQUAL_GREATER_THAN_BUY1 = 'E';
	public static final char CONDITION_LESS_THAN_BUY1 = 'F';
	public static final char CONDITION_LESS_THAN_EQUAL_BUY1 = 'H';
	
	public TouchCondition() {
		super(8010);
	}

	public TouchCondition(char data) {
		super(8010, data);
	}
	
}
