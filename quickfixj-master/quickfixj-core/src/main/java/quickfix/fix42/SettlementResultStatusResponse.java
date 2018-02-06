
package quickfix.fix42;

import quickfix.FieldNotFound;


public class SettlementResultStatusResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF014";
	

	public SettlementResultStatusResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public SettlementResultStatusResponse(quickfix.field.RequestID requestID, quickfix.field.SettlementDate settlementDate, quickfix.field.Text text, quickfix.field.SecurityExchange securityExchange) {
		this();
		setField(requestID);
		setField(settlementDate);
		setField(text);
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

	public void set(quickfix.field.TotalRetNum value) {
		setField(value);
	}

	public quickfix.field.TotalRetNum get(quickfix.field.TotalRetNum value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TotalRetNum getTotalRetNum() throws FieldNotFound {
		return get(new quickfix.field.TotalRetNum());
	}

	public boolean isSet(quickfix.field.TotalRetNum field) {
		return isSetField(field);
	}

	public boolean isSetTotalRetNum() {
		return isSetField(8026);
	}

	public void set(quickfix.field.PresentRetNum value) {
		setField(value);
	}

	public quickfix.field.PresentRetNum get(quickfix.field.PresentRetNum value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PresentRetNum getPresentRetNum() throws FieldNotFound {
		return get(new quickfix.field.PresentRetNum());
	}

	public boolean isSet(quickfix.field.PresentRetNum field) {
		return isSetField(field);
	}

	public boolean isSetPresentRetNum() {
		return isSetField(8027);
	}

	public void set(quickfix.field.NextFlag value) {
		setField(value);
	}

	public quickfix.field.NextFlag get(quickfix.field.NextFlag value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.NextFlag getNextFlag() throws FieldNotFound {
		return get(new quickfix.field.NextFlag());
	}

	public boolean isSet(quickfix.field.NextFlag field) {
		return isSetField(field);
	}

	public boolean isSetNextFlag() {
		return isSetField(8095);
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
