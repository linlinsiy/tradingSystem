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

import quickfix.StringField;


public class MsgType extends StringField {

	static final long serialVersionUID = 20050617;

	public static final int FIELD = 35;
	public static final String HEARTBEAT = "0";
	public static final String TEST_REQUEST = "1";
	public static final String RESEND_REQUEST = "2";
	public static final String REJECT = "3";
	public static final String SEQUENCE_RESET = "4";
	public static final String LOGOUT = "5";
	public static final String INDICATION_OF_INTEREST = "6";
	public static final String ADVERTISEMENT = "7";
	public static final String EXECUTION_REPORT = "8";
	public static final String ORDER_CANCEL_REJECT = "9";
	public static final String LOGON = "A";
	public static final String NEWS = "B";
	public static final String EMAIL = "C";
	public static final String ORDER_SINGLE = "D";
	public static final String ORDER_LIST = "E";
	public static final String ORDER_CANCEL_REQUEST = "F";
	public static final String ORDER_CANCEL = "G";
	public static final String ORDER_STATUS_REQUEST = "H";
	public static final String ALLOCATION = "J";
	public static final String LIST_CANCEL_REQUEST = "K";
	public static final String LIST_EXECUTE = "L";
	public static final String LIST_STATUS_REQUEST = "M";
	public static final String LIST_STATUS = "N";
	public static final String ALLOCATION_ACK = "P";
	public static final String DONT_KNOW_TRADE = "Q";
	public static final String QUOTE_REQUEST = "R";
	public static final String QUOTE = "S";
	public static final String SETTLEMENT_INSTRUCTIONS = "T";
	public static final String MARKET_DATA_REQUEST = "V";
	public static final String MARKET_DATA_SNAPSHOT = "W";
	public static final String MARKET_DATA_INCREMENTAL_REFRESH = "X";
	public static final String MARKET_DATA_REQUEST_REJECT = "Y";
	public static final String QUOTE_CANCEL = "Z";
	public static final String QUOTE_STATUS_REQUEST = "a";
	public static final String MASS_QUOTE_ACKNOWLEDGEMENT = "b";
	public static final String SECURITY_DEFINITION_REQUEST = "c";
	public static final String SECURITY_DEFINITION = "d";
	public static final String SECURITY_STATUS_REQUEST = "e";
	public static final String SECURITY_STATUS = "f";
	public static final String TRADING_SESSION_STATUS_REQUEST = "g";
	public static final String TRADING_SESSION_STATUS = "h";
	public static final String MASS_QUOTE = "i";
	public static final String BUSINESS_MESSAGE_REJECT = "j";
	public static final String BID_REQUEST = "k";
	public static final String BID_RESPONSE = "l";
	public static final String LIST_STRIKE_PRICE = "m";
	public static final String LOGIN_REQUEST = "UF001";
	public static final String LOGIN_RESPONSE = "UF002";
	public static final String LOGOUT_REQUEST = "UF003";
	public static final String LOGOUT_RESPONSE = "UF004";
	public static final String CHANGE_PASSWORD_RQQUEST = "UF005";
	public static final String CHANGE_PASSWORD_RESPONSE = "UF006";
	public static final String QUERY_POSITION_REQUEST = "UF007";
	public static final String QUERY_POSITION_RESPONSE = "UF008";
	public static final String QUERY_MAXIMUM_OPERATING_QUANTITY_REQUEST = "UF009";
	public static final String QUERY_MAXIMUM_OPERATING_QUANTITY_RESPONSE = "UF010";
	public static final String QUERY_ALL_ORDER_STATUS_REQUEST = "UF011";
	public static final String QUERY_ALL_ORDER_STATUS_RESPONSE = "UF012";
	public static final String QUERY_SETTLEMENT_RESULT_REQUEST = "UF013";
	public static final String QUERY_SETTLEMENT_RESULT_RESPONSE = "UF014";
	public static final String SETTLEMENT_RESULTS_CONFIRM_REQUEST = "UF015";
	public static final String SETTLEMENT_RESULTS_CONFIRM_RESPONSE = "UF016";
	public static final String QUERY_SETTLEMENT_RESULTS_CONFIRM_REQUEST = "UF017";
	public static final String QUERY_DEPOSIT_RATE_REQUEST = "UF018";
	public static final String QUERY_DEPOSIT_RATE_RESPONSE = "UF019";
	public static final String QUERY_FORMALITIES_RATE_REQUEST = "UF020";
	public static final String QUERY_FORMALITIES_RATE_RESPONSE = "UF021";
	public static final String QUERY_CUSTOMER_FINANCES_REQUEST = "UF022";
	public static final String QUERY_CUSTOMER_FINANCES_RESPONSE = "UF023";
	public static final String QUERY_CONTRACT_REQUEST = "UF024";
	public static final String QUERY_CONTRACT_RESPONSE = "UF025";
	public static final String SUB_QUOTES_REQUEST = "UF026";
	public static final String SUB_QUOTES_RESPONSE = "UF027";
	public static final String CANCEL_SUB_QUOTES_REQUEST = "UF028";
	public static final String CANCEL_SUB_QUOTES_RESPONSE = "UF029";
	public static final String RESPONSE_GAP_RESEND_RESPONSE = "UF030";
	public static final String INFORMATION_PUBLISH = "UF031";
	public static final String INFORMATION_PUBLISH_CONFIRM = "UF032";
	public static final String REQUEST_ERROR_MESSAGE = "UF033";
	public static final String REQUEST_FOR_POSITION = "URP";
	public static final String POSITION_REPORT = "UPR";
	public static final String REQUEST_FOR_BALANCE = "URB";
	public static final String BALANCE_REPORT = "UBR";
	
	public MsgType() {
		super(35);
	}

	public MsgType(String data) {
		super(35, data);
	}
	
}
