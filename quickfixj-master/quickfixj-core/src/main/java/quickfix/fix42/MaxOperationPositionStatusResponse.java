
package quickfix.fix42;

import quickfix.FieldNotFound;


public class MaxOperationPositionStatusResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF010";
	

	public MaxOperationPositionStatusResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public MaxOperationPositionStatusResponse(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.SecurityExchange securityExchange, quickfix.field.OpenClose openClose, quickfix.field.Side side, quickfix.field.HedgeFlag hedgeFlag) {
		this();
		setField(requestID);
		setField(clientID);
		setField(securityExchange);
		setField(openClose);
		setField(side);
		setField(hedgeFlag);
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

	public void set(quickfix.field.OpenClose value) {
		setField(value);
	}

	public quickfix.field.OpenClose get(quickfix.field.OpenClose value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OpenClose getOpenClose() throws FieldNotFound {
		return get(new quickfix.field.OpenClose());
	}

	public boolean isSet(quickfix.field.OpenClose field) {
		return isSetField(field);
	}

	public boolean isSetOpenClose() {
		return isSetField(77);
	}

	public void set(quickfix.field.Side value) {
		setField(value);
	}

	public quickfix.field.Side get(quickfix.field.Side value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Side getSide() throws FieldNotFound {
		return get(new quickfix.field.Side());
	}

	public boolean isSet(quickfix.field.Side field) {
		return isSetField(field);
	}

	public boolean isSetSide() {
		return isSetField(54);
	}

	public void set(quickfix.field.MaxOpenPosition value) {
		setField(value);
	}

	public quickfix.field.MaxOpenPosition get(quickfix.field.MaxOpenPosition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxOpenPosition getMaxOpenPosition() throws FieldNotFound {
		return get(new quickfix.field.MaxOpenPosition());
	}

	public boolean isSet(quickfix.field.MaxOpenPosition field) {
		return isSetField(field);
	}

	public boolean isSetMaxOpenPosition() {
		return isSetField(8023);
	}

	public void set(quickfix.field.MaxClosePosition value) {
		setField(value);
	}

	public quickfix.field.MaxClosePosition get(quickfix.field.MaxClosePosition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxClosePosition getMaxClosePosition() throws FieldNotFound {
		return get(new quickfix.field.MaxClosePosition());
	}

	public boolean isSet(quickfix.field.MaxClosePosition field) {
		return isSetField(field);
	}

	public boolean isSetMaxClosePosition() {
		return isSetField(8024);
	}

	public void set(quickfix.field.MaxCloseTdPosition value) {
		setField(value);
	}

	public quickfix.field.MaxCloseTdPosition get(quickfix.field.MaxCloseTdPosition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxCloseTdPosition getMaxCloseTdPosition() throws FieldNotFound {
		return get(new quickfix.field.MaxCloseTdPosition());
	}

	public boolean isSet(quickfix.field.MaxCloseTdPosition field) {
		return isSetField(field);
	}

	public boolean isSetMaxCloseTdPosition() {
		return isSetField(8025);
	}

	public void set(quickfix.field.HedgeFlag value) {
		setField(value);
	}

	public quickfix.field.HedgeFlag get(quickfix.field.HedgeFlag value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.HedgeFlag getHedgeFlag() throws FieldNotFound {
		return get(new quickfix.field.HedgeFlag());
	}

	public boolean isSet(quickfix.field.HedgeFlag field) {
		return isSetField(field);
	}

	public boolean isSetHedgeFlag() {
		return isSetField(8009);
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
