
package quickfix.fix42;

import quickfix.FieldNotFound;


public class UserChangePassWdRequest extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF005";
	

	public UserChangePassWdRequest() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public UserChangePassWdRequest(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.PassWdType passWdType, quickfix.field.OldPassWd oldPassWd, quickfix.field.NewPassWd newPassWd) {
		this();
		setField(requestID);
		setField(clientID);
		setField(passWdType);
		setField(oldPassWd);
		setField(newPassWd);
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

	public void set(quickfix.field.PassWdType value) {
		setField(value);
	}

	public quickfix.field.PassWdType get(quickfix.field.PassWdType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PassWdType getPassWdType() throws FieldNotFound {
		return get(new quickfix.field.PassWdType());
	}

	public boolean isSet(quickfix.field.PassWdType field) {
		return isSetField(field);
	}

	public boolean isSetPassWdType() {
		return isSetField(8089);
	}

	public void set(quickfix.field.OldPassWd value) {
		setField(value);
	}

	public quickfix.field.OldPassWd get(quickfix.field.OldPassWd value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OldPassWd getOldPassWd() throws FieldNotFound {
		return get(new quickfix.field.OldPassWd());
	}

	public boolean isSet(quickfix.field.OldPassWd field) {
		return isSetField(field);
	}

	public boolean isSetOldPassWd() {
		return isSetField(8090);
	}

	public void set(quickfix.field.NewPassWd value) {
		setField(value);
	}

	public quickfix.field.NewPassWd get(quickfix.field.NewPassWd value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.NewPassWd getNewPassWd() throws FieldNotFound {
		return get(new quickfix.field.NewPassWd());
	}

	public boolean isSet(quickfix.field.NewPassWd field) {
		return isSetField(field);
	}

	public boolean isSetNewPassWd() {
		return isSetField(8091);
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
