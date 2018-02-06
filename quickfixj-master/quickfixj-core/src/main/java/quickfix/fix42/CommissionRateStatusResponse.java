
package quickfix.fix42;

import quickfix.FieldNotFound;


public class CommissionRateStatusResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF021";
	

	public CommissionRateStatusResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public CommissionRateStatusResponse(quickfix.field.RequestID requestID, quickfix.field.OpenCommissionRate openCommissionRate, quickfix.field.OpenCommissionAmt openCommissionAmt, quickfix.field.CloseCommissionRate closeCommissionRate, quickfix.field.CloseCommissionAmt closeCommissionAmt, quickfix.field.SecurityExchange securityExchange) {
		this();
		setField(requestID);
		setField(openCommissionRate);
		setField(openCommissionAmt);
		setField(closeCommissionRate);
		setField(closeCommissionAmt);
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

	public void set(quickfix.field.Symbol value) {
		setField(value);
	}

	public quickfix.field.Symbol get(quickfix.field.Symbol value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Symbol getSymbol() throws FieldNotFound {
		return get(new quickfix.field.Symbol());
	}

	public boolean isSet(quickfix.field.Symbol field) {
		return isSetField(field);
	}

	public boolean isSetSymbol() {
		return isSetField(55);
	}

	public void set(quickfix.field.MaturityMonthYear value) {
		setField(value);
	}

	public quickfix.field.MaturityMonthYear get(quickfix.field.MaturityMonthYear value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaturityMonthYear getMaturityMonthYear() throws FieldNotFound {
		return get(new quickfix.field.MaturityMonthYear());
	}

	public boolean isSet(quickfix.field.MaturityMonthYear field) {
		return isSetField(field);
	}

	public boolean isSetMaturityMonthYear() {
		return isSetField(200);
	}

	public void set(quickfix.field.OpenCommissionRate value) {
		setField(value);
	}

	public quickfix.field.OpenCommissionRate get(quickfix.field.OpenCommissionRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OpenCommissionRate getOpenCommissionRate() throws FieldNotFound {
		return get(new quickfix.field.OpenCommissionRate());
	}

	public boolean isSet(quickfix.field.OpenCommissionRate field) {
		return isSetField(field);
	}

	public boolean isSetOpenCommissionRate() {
		return isSetField(8039);
	}

	public void set(quickfix.field.OpenCommissionAmt value) {
		setField(value);
	}

	public quickfix.field.OpenCommissionAmt get(quickfix.field.OpenCommissionAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OpenCommissionAmt getOpenCommissionAmt() throws FieldNotFound {
		return get(new quickfix.field.OpenCommissionAmt());
	}

	public boolean isSet(quickfix.field.OpenCommissionAmt field) {
		return isSetField(field);
	}

	public boolean isSetOpenCommissionAmt() {
		return isSetField(8040);
	}

	public void set(quickfix.field.CloseCommissionRate value) {
		setField(value);
	}

	public quickfix.field.CloseCommissionRate get(quickfix.field.CloseCommissionRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CloseCommissionRate getCloseCommissionRate() throws FieldNotFound {
		return get(new quickfix.field.CloseCommissionRate());
	}

	public boolean isSet(quickfix.field.CloseCommissionRate field) {
		return isSetField(field);
	}

	public boolean isSetCloseCommissionRate() {
		return isSetField(8041);
	}

	public void set(quickfix.field.CloseCommissionAmt value) {
		setField(value);
	}

	public quickfix.field.CloseCommissionAmt get(quickfix.field.CloseCommissionAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CloseCommissionAmt getCloseCommissionAmt() throws FieldNotFound {
		return get(new quickfix.field.CloseCommissionAmt());
	}

	public boolean isSet(quickfix.field.CloseCommissionAmt field) {
		return isSetField(field);
	}

	public boolean isSetCloseCommissionAmt() {
		return isSetField(8042);
	}

	public void set(quickfix.field.CloseTdCommissionRate value) {
		setField(value);
	}

	public quickfix.field.CloseTdCommissionRate get(quickfix.field.CloseTdCommissionRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CloseTdCommissionRate getCloseTdCommissionRate() throws FieldNotFound {
		return get(new quickfix.field.CloseTdCommissionRate());
	}

	public boolean isSet(quickfix.field.CloseTdCommissionRate field) {
		return isSetField(field);
	}

	public boolean isSetCloseTdCommissionRate() {
		return isSetField(8043);
	}

	public void set(quickfix.field.CloseTdCommissionAmt value) {
		setField(value);
	}

	public quickfix.field.CloseTdCommissionAmt get(quickfix.field.CloseTdCommissionAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CloseTdCommissionAmt getCloseTdCommissionAmt() throws FieldNotFound {
		return get(new quickfix.field.CloseTdCommissionAmt());
	}

	public boolean isSet(quickfix.field.CloseTdCommissionAmt field) {
		return isSetField(field);
	}

	public boolean isSetCloseTdCommissionAmt() {
		return isSetField(8044);
	}

	public void set(quickfix.field.SettleFee value) {
		setField(value);
	}

	public quickfix.field.SettleFee get(quickfix.field.SettleFee value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SettleFee getSettleFee() throws FieldNotFound {
		return get(new quickfix.field.SettleFee());
	}

	public boolean isSet(quickfix.field.SettleFee field) {
		return isSetField(field);
	}

	public boolean isSetSettleFee() {
		return isSetField(8092);
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
