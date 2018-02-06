
package quickfix.fix42;

import quickfix.FieldNotFound;


public class UserLogonResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF002";
	

	public UserLogonResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public UserLogonResponse(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.LogonStatus logonStatus) {
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

	public void set(quickfix.field.AccountName value) {
		setField(value);
	}

	public quickfix.field.AccountName get(quickfix.field.AccountName value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.AccountName getAccountName() throws FieldNotFound {
		return get(new quickfix.field.AccountName());
	}

	public boolean isSet(quickfix.field.AccountName field) {
		return isSetField(field);
	}

	public boolean isSetAccountName() {
		return isSetField(8003);
	}

	public void set(quickfix.field.RiskLevel value) {
		setField(value);
	}

	public quickfix.field.RiskLevel get(quickfix.field.RiskLevel value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.RiskLevel getRiskLevel() throws FieldNotFound {
		return get(new quickfix.field.RiskLevel());
	}

	public boolean isSet(quickfix.field.RiskLevel field) {
		return isSetField(field);
	}

	public boolean isSetRiskLevel() {
		return isSetField(8004);
	}

	public void set(quickfix.field.AdditionalMargin value) {
		setField(value);
	}

	public quickfix.field.AdditionalMargin get(quickfix.field.AdditionalMargin value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.AdditionalMargin getAdditionalMargin() throws FieldNotFound {
		return get(new quickfix.field.AdditionalMargin());
	}

	public boolean isSet(quickfix.field.AdditionalMargin field) {
		return isSetField(field);
	}

	public boolean isSetAdditionalMargin() {
		return isSetField(8005);
	}

	public void set(quickfix.field.ClientSecuType value) {
		setField(value);
	}

	public quickfix.field.ClientSecuType get(quickfix.field.ClientSecuType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ClientSecuType getClientSecuType() throws FieldNotFound {
		return get(new quickfix.field.ClientSecuType());
	}

	public boolean isSet(quickfix.field.ClientSecuType field) {
		return isSetField(field);
	}

	public boolean isSetClientSecuType() {
		return isSetField(8006);
	}

	public void set(quickfix.field.Riskratio value) {
		setField(value);
	}

	public quickfix.field.Riskratio get(quickfix.field.Riskratio value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Riskratio getRiskratio() throws FieldNotFound {
		return get(new quickfix.field.Riskratio());
	}

	public boolean isSet(quickfix.field.Riskratio field) {
		return isSetField(field);
	}

	public boolean isSetRiskratio() {
		return isSetField(8011);
	}

	public void set(quickfix.field.LastLogonIP value) {
		setField(value);
	}

	public quickfix.field.LastLogonIP get(quickfix.field.LastLogonIP value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.LastLogonIP getLastLogonIP() throws FieldNotFound {
		return get(new quickfix.field.LastLogonIP());
	}

	public boolean isSet(quickfix.field.LastLogonIP field) {
		return isSetField(field);
	}

	public boolean isSetLastLogonIP() {
		return isSetField(8007);
	}

	public void set(quickfix.field.LastLogonTime value) {
		setField(value);
	}

	public quickfix.field.LastLogonTime get(quickfix.field.LastLogonTime value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.LastLogonTime getLastLogonTime() throws FieldNotFound {
		return get(new quickfix.field.LastLogonTime());
	}

	public boolean isSet(quickfix.field.LastLogonTime field) {
		return isSetField(field);
	}

	public boolean isSetLastLogonTime() {
		return isSetField(8008);
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
