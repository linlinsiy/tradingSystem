
package quickfix.fix42;

import quickfix.FieldNotFound;


public class SettlementResultComfirmStatusRequest extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF017";
	

	public SettlementResultComfirmStatusRequest() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public SettlementResultComfirmStatusRequest(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.SecurityExchange securityExchange) {
		this();
		setField(requestID);
		setField(clientID);
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

	public void set(quickfix.field.SettlementDate value) {
		setField(value);
	}

	public quickfix.field.SettlementDate get(quickfix.field.SettlementDate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SettlementDate getSettlementDate() throws FieldNotFound {
		return get(new quickfix.field.SettlementDate());
	}

	public boolean isSet(quickfix.field.SettlementDate field) {
		return isSetField(field);
	}

	public boolean isSetSettlementDate() {
		return isSetField(8028);
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

}
