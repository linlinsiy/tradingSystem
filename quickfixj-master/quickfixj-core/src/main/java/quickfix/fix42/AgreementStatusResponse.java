
package quickfix.fix42;

import quickfix.FieldNotFound;


public class AgreementStatusResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = " UF025";
	

	public AgreementStatusResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public AgreementStatusResponse(quickfix.field.RequestID requestID, quickfix.field.SecurityExchange securityExchange, quickfix.field.OneLotQty oneLotQty, quickfix.field.MaxLotQty maxLotQty, quickfix.field.MaxHoldPosition maxHoldPosition, quickfix.field.VarietyCode varietyCode, quickfix.field.VarietyName varietyName, quickfix.field.MinPxAlterUnit minPxAlterUnit) {
		this();
		setField(requestID);
		setField(securityExchange);
		setField(oneLotQty);
		setField(maxLotQty);
		setField(maxHoldPosition);
		setField(varietyCode);
		setField(varietyName);
		setField(minPxAlterUnit);
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

	public void set(quickfix.field.ExchangeName value) {
		setField(value);
	}

	public quickfix.field.ExchangeName get(quickfix.field.ExchangeName value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ExchangeName getExchangeName() throws FieldNotFound {
		return get(new quickfix.field.ExchangeName());
	}

	public boolean isSet(quickfix.field.ExchangeName field) {
		return isSetField(field);
	}

	public boolean isSetExchangeName() {
		return isSetField(8073);
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

	public void set(quickfix.field.SymbolSfx value) {
		setField(value);
	}

	public quickfix.field.SymbolSfx get(quickfix.field.SymbolSfx value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SymbolSfx getSymbolSfx() throws FieldNotFound {
		return get(new quickfix.field.SymbolSfx());
	}

	public boolean isSet(quickfix.field.SymbolSfx field) {
		return isSetField(field);
	}

	public boolean isSetSymbolSfx() {
		return isSetField(65);
	}

	public void set(quickfix.field.OneLotQty value) {
		setField(value);
	}

	public quickfix.field.OneLotQty get(quickfix.field.OneLotQty value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OneLotQty getOneLotQty() throws FieldNotFound {
		return get(new quickfix.field.OneLotQty());
	}

	public boolean isSet(quickfix.field.OneLotQty field) {
		return isSetField(field);
	}

	public boolean isSetOneLotQty() {
		return isSetField(8075);
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

	public void set(quickfix.field.MaxLotQty value) {
		setField(value);
	}

	public quickfix.field.MaxLotQty get(quickfix.field.MaxLotQty value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxLotQty getMaxLotQty() throws FieldNotFound {
		return get(new quickfix.field.MaxLotQty());
	}

	public boolean isSet(quickfix.field.MaxLotQty field) {
		return isSetField(field);
	}

	public boolean isSetMaxLotQty() {
		return isSetField(8076);
	}

	public void set(quickfix.field.MaxHoldPosition value) {
		setField(value);
	}

	public quickfix.field.MaxHoldPosition get(quickfix.field.MaxHoldPosition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxHoldPosition getMaxHoldPosition() throws FieldNotFound {
		return get(new quickfix.field.MaxHoldPosition());
	}

	public boolean isSet(quickfix.field.MaxHoldPosition field) {
		return isSetField(field);
	}

	public boolean isSetMaxHoldPosition() {
		return isSetField(8077);
	}

	public void set(quickfix.field.VarietyCode value) {
		setField(value);
	}

	public quickfix.field.VarietyCode get(quickfix.field.VarietyCode value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.VarietyCode getVarietyCode() throws FieldNotFound {
		return get(new quickfix.field.VarietyCode());
	}

	public boolean isSet(quickfix.field.VarietyCode field) {
		return isSetField(field);
	}

	public boolean isSetVarietyCode() {
		return isSetField(8078);
	}

	public void set(quickfix.field.VarietyName value) {
		setField(value);
	}

	public quickfix.field.VarietyName get(quickfix.field.VarietyName value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.VarietyName getVarietyName() throws FieldNotFound {
		return get(new quickfix.field.VarietyName());
	}

	public boolean isSet(quickfix.field.VarietyName field) {
		return isSetField(field);
	}

	public boolean isSetVarietyName() {
		return isSetField(8079);
	}

	public void set(quickfix.field.MinPxAlterUnit value) {
		setField(value);
	}

	public quickfix.field.MinPxAlterUnit get(quickfix.field.MinPxAlterUnit value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MinPxAlterUnit getMinPxAlterUnit() throws FieldNotFound {
		return get(new quickfix.field.MinPxAlterUnit());
	}

	public boolean isSet(quickfix.field.MinPxAlterUnit field) {
		return isSetField(field);
	}

	public boolean isSetMinPxAlterUnit() {
		return isSetField(8080);
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
