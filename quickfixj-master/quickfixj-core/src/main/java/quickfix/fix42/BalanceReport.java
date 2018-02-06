
package quickfix.fix42;

import quickfix.FieldNotFound;


public class BalanceReport extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UBR";
	

	public BalanceReport() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public BalanceReport(quickfix.field.RequestID requestID, quickfix.field.Currency currency, quickfix.field.UseableAmt useableAmt, quickfix.field.LastRptRequested lastRptRequested) {
		this();
		setField(requestID);
		setField(currency);
		setField(useableAmt);
		setField(lastRptRequested);
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

	public void set(quickfix.field.Currency value) {
		setField(value);
	}

	public quickfix.field.Currency get(quickfix.field.Currency value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Currency getCurrency() throws FieldNotFound {
		return get(new quickfix.field.Currency());
	}

	public boolean isSet(quickfix.field.Currency field) {
		return isSetField(field);
	}

	public boolean isSetCurrency() {
		return isSetField(15);
	}

	public void set(quickfix.field.UseableAmt value) {
		setField(value);
	}

	public quickfix.field.UseableAmt get(quickfix.field.UseableAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.UseableAmt getUseableAmt() throws FieldNotFound {
		return get(new quickfix.field.UseableAmt());
	}

	public boolean isSet(quickfix.field.UseableAmt field) {
		return isSetField(field);
	}

	public boolean isSetUseableAmt() {
		return isSetField(8084);
	}

	public void set(quickfix.field.LastRptRequested value) {
		setField(value);
	}

	public quickfix.field.LastRptRequested get(quickfix.field.LastRptRequested value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.LastRptRequested getLastRptRequested() throws FieldNotFound {
		return get(new quickfix.field.LastRptRequested());
	}

	public boolean isSet(quickfix.field.LastRptRequested field) {
		return isSetField(field);
	}

	public boolean isSetLastRptRequested() {
		return isSetField(912);
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

}
