
package quickfix.fix42;

import quickfix.FieldNotFound;


public class UserLogonRequest extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF001";
	

	public UserLogonRequest() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public UserLogonRequest(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.EncryptMethod encryptMethod, quickfix.field.LogonPasswd logonPasswd) {
		this();
		setField(requestID);
		setField(clientID);
		setField(encryptMethod);
		setField(logonPasswd);
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

	public void set(quickfix.field.EncryptMethod value) {
		setField(value);
	}

	public quickfix.field.EncryptMethod get(quickfix.field.EncryptMethod value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EncryptMethod getEncryptMethod() throws FieldNotFound {
		return get(new quickfix.field.EncryptMethod());
	}

	public boolean isSet(quickfix.field.EncryptMethod field) {
		return isSetField(field);
	}

	public boolean isSetEncryptMethod() {
		return isSetField(98);
	}

	public void set(quickfix.field.LogonPasswd value) {
		setField(value);
	}

	public quickfix.field.LogonPasswd get(quickfix.field.LogonPasswd value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.LogonPasswd getLogonPasswd() throws FieldNotFound {
		return get(new quickfix.field.LogonPasswd());
	}

	public boolean isSet(quickfix.field.LogonPasswd field) {
		return isSetField(field);
	}

	public boolean isSetLogonPasswd() {
		return isSetField(8001);
	}

	public void set(quickfix.field.IPAddress value) {
		setField(value);
	}

	public quickfix.field.IPAddress get(quickfix.field.IPAddress value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.IPAddress getIPAddress() throws FieldNotFound {
		return get(new quickfix.field.IPAddress());
	}

	public boolean isSet(quickfix.field.IPAddress field) {
		return isSetField(field);
	}

	public boolean isSetIPAddress() {
		return isSetField(8096);
	}

	public void set(quickfix.field.MacAddress value) {
		setField(value);
	}

	public quickfix.field.MacAddress get(quickfix.field.MacAddress value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MacAddress getMacAddress() throws FieldNotFound {
		return get(new quickfix.field.MacAddress());
	}

	public boolean isSet(quickfix.field.MacAddress field) {
		return isSetField(field);
	}

	public boolean isSetMacAddress() {
		return isSetField(8097);
	}

}
