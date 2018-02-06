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


public class PriceType extends IntField {

	static final long serialVersionUID = 20050617;

	public static final int FIELD = 423;
	public static final int PERCENTAGE = 1;
	public static final int PER_UNIT = 2;
	public static final int FIXED_AMOUNT_ABSOLUTE_VALUE = 3;
	public static final int DISCOUNT_PERCENTAGE_POINTS_BELOW_PAR = 4;
	public static final int PREMIUM_PERCENTAGE_POINTS_OVER_PAR = 5;
	public static final int BASIS_POINTS_RELATIVE_TO_BENCHMARK = 6;
	public static final int TED_PRICE = 7;
	public static final int TED_YIELD = 8;
	
	public PriceType() {
		super(423);
	}

	public PriceType(int data) {
		super(423, data);
	}
	
}
