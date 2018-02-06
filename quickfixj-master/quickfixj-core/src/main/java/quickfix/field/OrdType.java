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


public class OrdType extends CharField {

	static final long serialVersionUID = 20050617;

	public static final int FIELD = 40;
	public static final char MARKET = '1';
	public static final char LIMIT = '2';
	public static final char STOP = '3';
	public static final char STOP_LIMIT = '4';
	public static final char MARKET_ON_CLOSE = '5';
	public static final char WITH_OR_WITHOUT = '6';
	public static final char LIMIT_OR_BETTER = '7';
	public static final char LIMIT_WITH_OR_WITHOUT = '8';
	public static final char ON_BASIS = '9';
	public static final char ON_CLOSE = 'A';
	public static final char LIMIT_ON_CLOSE = 'B';
	public static final char FOREX_MARKET = 'C';
	public static final char PREVIOUSLY_QUOTED = 'D';
	public static final char PREVIOUSLY_INDICATED = 'E';
	public static final char FOREX_LIMIT = 'F';
	public static final char FOREX_SWAP = 'G';
	public static final char FOREX_PREVIOUSLY_QUOTED = 'H';
	public static final char FUNARI = 'I';
	public static final char PEGGED = 'P';
	public static final char BEST_PRICE = 'a';
	public static final char NEW_PRICE = 'b';
	public static final char NEW_PRICE_UP1_TICKS = 'c';
	public static final char NEW_PRICE_UP2_TICKS = 'd';
	public static final char NEW_PRICE_UP3_TICKS = 'e';
	public static final char Sell1 = 'f';
	public static final char SELL1_UP1_TICKS = 'g';
	public static final char SELL1_UP2_TICKS = 'h';
	public static final char SELL1_UP3_TICKS = 'i';
	public static final char Buy1 = 'j';
	public static final char BUY1_UP1_TICKS = 'k';
	public static final char BUY1_UP2_TICKS = 'l';
	public static final char BUY1_UP3_TICKS = 'm';
	
	public OrdType() {
		super(40);
	}

	public OrdType(char data) {
		super(40, data);
	}
	
}
