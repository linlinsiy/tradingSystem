
package quickfix.fix42;

import quickfix.FieldNotFound;


public class RequestForPosition extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "URP";
	

	public RequestForPosition() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public RequestForPosition(quickfix.field.Account account, quickfix.field.RequestID requestID) {
		this();
		setField(account);
		setField(requestID);
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

}
