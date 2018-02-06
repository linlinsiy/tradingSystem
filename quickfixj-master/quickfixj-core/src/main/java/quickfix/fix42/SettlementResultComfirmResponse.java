
package quickfix.fix42;

import quickfix.FieldNotFound;


public class SettlementResultComfirmResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF016";
	

	public SettlementResultComfirmResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public SettlementResultComfirmResponse(quickfix.field.RequestID requestID, quickfix.field.SettlementConfirmResult settlementConfirmResult, quickfix.field.SecurityExchange securityExchange) {
		this();
		setField(requestID);
		setField(settlementConfirmResult);
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

	public void set(quickfix.field.SettlementConfirmResult value) {
		setField(value);
	}

	public quickfix.field.SettlementConfirmResult get(quickfix.field.SettlementConfirmResult value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SettlementConfirmResult getSettlementConfirmResult() throws FieldNotFound {
		return get(new quickfix.field.SettlementConfirmResult());
	}

	public boolean isSet(quickfix.field.SettlementConfirmResult field) {
		return isSetField(field);
	}

	public boolean isSetSettlementConfirmResult() {
		return isSetField(8030);
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
