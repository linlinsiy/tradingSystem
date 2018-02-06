
package quickfix.fix42;

import quickfix.FieldNotFound;


public class MarginRateStatusResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF019";
	

	public MarginRateStatusResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public MarginRateStatusResponse(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.SpecLongMarginRate specLongMarginRate, quickfix.field.SpecShortMarginRate specShortMarginRate, quickfix.field.HedgeLongMarginRate hedgeLongMarginRate, quickfix.field.HedgeShortMarginRate hedgeShortMarginRate, quickfix.field.SpecLongMarginAmt specLongMarginAmt, quickfix.field.SpecShortMarginAmt specShortMarginAmt, quickfix.field.HedgeLongMarginAmt hedgeLongMarginAmt, quickfix.field.HedgeShortMarginAmt hedgeShortMarginAmt, quickfix.field.SecurityExchange securityExchange) {
		this();
		setField(requestID);
		setField(clientID);
		setField(specLongMarginRate);
		setField(specShortMarginRate);
		setField(hedgeLongMarginRate);
		setField(hedgeShortMarginRate);
		setField(specLongMarginAmt);
		setField(specShortMarginAmt);
		setField(hedgeLongMarginAmt);
		setField(hedgeShortMarginAmt);
		setField(securityExchange);
	}
	
	public void set(quickfix.field.RequestID value) {
		setField(value);
	}

	public quickfix.field.RequestID get(quickfix.field.RequestID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.RequestID getRequestID() throws FieldNotFound {
		return get(new quickfix.field.RequestID());
	}

	public boolean isSet(quickfix.field.RequestID field) {
		return isSetField(field);
	}

	public boolean isSetRequestID() {
		return isSetField(8088);
	}

	public void set(quickfix.field.ClientID value) {
		setField(value);
	}

	public quickfix.field.ClientID get(quickfix.field.ClientID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ClientID getClientID() throws FieldNotFound {
		return get(new quickfix.field.ClientID());
	}

	public boolean isSet(quickfix.field.ClientID field) {
		return isSetField(field);
	}

	public boolean isSetClientID() {
		return isSetField(109);
	}

	public void set(quickfix.field.Account value) {
		setField(value);
	}

	public quickfix.field.Account get(quickfix.field.Account value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Account getAccount() throws FieldNotFound {
		return get(new quickfix.field.Account());
	}

	public boolean isSet(quickfix.field.Account field) {
		return isSetField(field);
	}

	public boolean isSetAccount() {
		return isSetField(1);
	}

	public void set(quickfix.field.Symbol value) {
		setField(value);
	}

	public quickfix.field.Symbol get(quickfix.field.Symbol value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Symbol getSymbol() throws FieldNotFound {
		return get(new quickfix.field.Symbol());
	}

	public boolean isSet(quickfix.field.Symbol field) {
		return isSetField(field);
	}

	public boolean isSetSymbol() {
		return isSetField(55);
	}

	public void set(quickfix.field.MaturityMonthYear value) {
		setField(value);
	}

	public quickfix.field.MaturityMonthYear get(quickfix.field.MaturityMonthYear value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaturityMonthYear getMaturityMonthYear() throws FieldNotFound {
		return get(new quickfix.field.MaturityMonthYear());
	}

	public boolean isSet(quickfix.field.MaturityMonthYear field) {
		return isSetField(field);
	}

	public boolean isSetMaturityMonthYear() {
		return isSetField(200);
	}

	public void set(quickfix.field.SpecLongMarginRate value) {
		setField(value);
	}

	public quickfix.field.SpecLongMarginRate get(quickfix.field.SpecLongMarginRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SpecLongMarginRate getSpecLongMarginRate() throws FieldNotFound {
		return get(new quickfix.field.SpecLongMarginRate());
	}

	public boolean isSet(quickfix.field.SpecLongMarginRate field) {
		return isSetField(field);
	}

	public boolean isSetSpecLongMarginRate() {
		return isSetField(8031);
	}

	public void set(quickfix.field.SpecShortMarginRate value) {
		setField(value);
	}

	public quickfix.field.SpecShortMarginRate get(quickfix.field.SpecShortMarginRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SpecShortMarginRate getSpecShortMarginRate() throws FieldNotFound {
		return get(new quickfix.field.SpecShortMarginRate());
	}

	public boolean isSet(quickfix.field.SpecShortMarginRate field) {
		return isSetField(field);
	}

	public boolean isSetSpecShortMarginRate() {
		return isSetField(8032);
	}

	public void set(quickfix.field.HedgeLongMarginRate value) {
		setField(value);
	}

	public quickfix.field.HedgeLongMarginRate get(quickfix.field.HedgeLongMarginRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.HedgeLongMarginRate getHedgeLongMarginRate() throws FieldNotFound {
		return get(new quickfix.field.HedgeLongMarginRate());
	}

	public boolean isSet(quickfix.field.HedgeLongMarginRate field) {
		return isSetField(field);
	}

	public boolean isSetHedgeLongMarginRate() {
		return isSetField(8033);
	}

	public void set(quickfix.field.HedgeShortMarginRate value) {
		setField(value);
	}

	public quickfix.field.HedgeShortMarginRate get(quickfix.field.HedgeShortMarginRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.HedgeShortMarginRate getHedgeShortMarginRate() throws FieldNotFound {
		return get(new quickfix.field.HedgeShortMarginRate());
	}

	public boolean isSet(quickfix.field.HedgeShortMarginRate field) {
		return isSetField(field);
	}

	public boolean isSetHedgeShortMarginRate() {
		return isSetField(8034);
	}

	public void set(quickfix.field.SpecLongMarginAmt value) {
		setField(value);
	}

	public quickfix.field.SpecLongMarginAmt get(quickfix.field.SpecLongMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SpecLongMarginAmt getSpecLongMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.SpecLongMarginAmt());
	}

	public boolean isSet(quickfix.field.SpecLongMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetSpecLongMarginAmt() {
		return isSetField(8035);
	}

	public void set(quickfix.field.SpecShortMarginAmt value) {
		setField(value);
	}

	public quickfix.field.SpecShortMarginAmt get(quickfix.field.SpecShortMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SpecShortMarginAmt getSpecShortMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.SpecShortMarginAmt());
	}

	public boolean isSet(quickfix.field.SpecShortMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetSpecShortMarginAmt() {
		return isSetField(8036);
	}

	public void set(quickfix.field.HedgeLongMarginAmt value) {
		setField(value);
	}

	public quickfix.field.HedgeLongMarginAmt get(quickfix.field.HedgeLongMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.HedgeLongMarginAmt getHedgeLongMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.HedgeLongMarginAmt());
	}

	public boolean isSet(quickfix.field.HedgeLongMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetHedgeLongMarginAmt() {
		return isSetField(8037);
	}

	public void set(quickfix.field.HedgeShortMarginAmt value) {
		setField(value);
	}

	public quickfix.field.HedgeShortMarginAmt get(quickfix.field.HedgeShortMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.HedgeShortMarginAmt getHedgeShortMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.HedgeShortMarginAmt());
	}

	public boolean isSet(quickfix.field.HedgeShortMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetHedgeShortMarginAmt() {
		return isSetField(8038);
	}

	public void set(quickfix.field.SecurityExchange value) {
		setField(value);
	}

	public quickfix.field.SecurityExchange get(quickfix.field.SecurityExchange value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SecurityExchange getSecurityExchange() throws FieldNotFound {
		return get(new quickfix.field.SecurityExchange());
	}

	public boolean isSet(quickfix.field.SecurityExchange field) {
		return isSetField(field);
	}

	public boolean isSetSecurityExchange() {
		return isSetField(207);
	}

	public void set(quickfix.field.Text value) {
		setField(value);
	}

	public quickfix.field.Text get(quickfix.field.Text value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Text getText() throws FieldNotFound {
		return get(new quickfix.field.Text());
	}

	public boolean isSet(quickfix.field.Text field) {
		return isSetField(field);
	}

	public boolean isSetText() {
		return isSetField(58);
	}

}
