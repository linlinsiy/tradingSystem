
package quickfix.fix42;

import quickfix.FieldNotFound;


public class UserLogoutResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF004";
	

	public UserLogoutResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public UserLogoutResponse(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.LogonStatus logonStatus) {
		this();
		setField(requestID);
		setField(clientID);
		setField(logonStatus);
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

	public void set(quickfix.field.LogonStatus value) {
		setField(value);
	}

	public quickfix.field.LogonStatus get(quickfix.field.LogonStatus value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.LogonStatus getLogonStatus() throws FieldNotFound {
		return get(new quickfix.field.LogonStatus());
	}

	public boolean isSet(quickfix.field.LogonStatus field) {
		return isSetField(field);
	}

	public boolean isSetLogonStatus() {
		return isSetField(8002);
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
