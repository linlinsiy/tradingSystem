
package quickfix.fix42;

import quickfix.FieldNotFound;


public class SettlementResultComfirmRequest extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF015";
	

	public SettlementResultComfirmRequest() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public SettlementResultComfirmRequest(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.SettlementConfirm settlementConfirm, quickfix.field.SecurityExchange securityExchange) {
		this();
		setField(requestID);
		setField(clientID);
		setField(settlementConfirm);
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

	public void set(quickfix.field.SettlementConfirm value) {
		setField(value);
	}

	public quickfix.field.SettlementConfirm get(quickfix.field.SettlementConfirm value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SettlementConfirm getSettlementConfirm() throws FieldNotFound {
		return get(new quickfix.field.SettlementConfirm());
	}

	public boolean isSet(quickfix.field.SettlementConfirm field) {
		return isSetField(field);
	}

	public boolean isSetSettlementConfirm() {
		return isSetField(8029);
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
