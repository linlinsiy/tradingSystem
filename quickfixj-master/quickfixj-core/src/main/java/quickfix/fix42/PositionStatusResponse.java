
package quickfix.fix42;

import quickfix.FieldNotFound;


public class PositionStatusResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF008";
	

	public PositionStatusResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public PositionStatusResponse(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.SecurityExchange securityExchange, quickfix.field.Side side, quickfix.field.HedgeFlag hedgeFlag, quickfix.field.CumQty cumQty, quickfix.field.TdPosition tdPosition, quickfix.field.YDPosition yDPosition) {
		this();
		setField(requestID);
		setField(clientID);
		setField(securityExchange);
		setField(side);
		setField(hedgeFlag);
		setField(cumQty);
		setField(tdPosition);
		setField(yDPosition);
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

	public void set(quickfix.field.LatestPx value) {
		setField(value);
	}

	public quickfix.field.LatestPx get(quickfix.field.LatestPx value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.LatestPx getLatestPx() throws FieldNotFound {
		return get(new quickfix.field.LatestPx());
	}

	public boolean isSet(quickfix.field.LatestPx field) {
		return isSetField(field);
	}

	public boolean isSetLatestPx() {
		return isSetField(8012);
	}

	public void set(quickfix.field.Side value) {
		setField(value);
	}

	public quickfix.field.Side get(quickfix.field.Side value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Side getSide() throws FieldNotFound {
		return get(new quickfix.field.Side());
	}

	public boolean isSet(quickfix.field.Side field) {
		return isSetField(field);
	}

	public boolean isSetSide() {
		return isSetField(54);
	}

	public void set(quickfix.field.HedgeFlag value) {
		setField(value);
	}

	public quickfix.field.HedgeFlag get(quickfix.field.HedgeFlag value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.HedgeFlag getHedgeFlag() throws FieldNotFound {
		return get(new quickfix.field.HedgeFlag());
	}

	public boolean isSet(quickfix.field.HedgeFlag field) {
		return isSetField(field);
	}

	public boolean isSetHedgeFlag() {
		return isSetField(8009);
	}

	public void set(quickfix.field.CumQty value) {
		setField(value);
	}

	public quickfix.field.CumQty get(quickfix.field.CumQty value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CumQty getCumQty() throws FieldNotFound {
		return get(new quickfix.field.CumQty());
	}

	public boolean isSet(quickfix.field.CumQty field) {
		return isSetField(field);
	}

	public boolean isSetCumQty() {
		return isSetField(14);
	}

	public void set(quickfix.field.TdPosition value) {
		setField(value);
	}

	public quickfix.field.TdPosition get(quickfix.field.TdPosition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TdPosition getTdPosition() throws FieldNotFound {
		return get(new quickfix.field.TdPosition());
	}

	public boolean isSet(quickfix.field.TdPosition field) {
		return isSetField(field);
	}

	public boolean isSetTdPosition() {
		return isSetField(8015);
	}

	public void set(quickfix.field.YDPosition value) {
		setField(value);
	}

	public quickfix.field.YDPosition get(quickfix.field.YDPosition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.YDPosition getYDPosition() throws FieldNotFound {
		return get(new quickfix.field.YDPosition());
	}

	public boolean isSet(quickfix.field.YDPosition field) {
		return isSetField(field);
	}

	public boolean isSetYDPosition() {
		return isSetField(8016);
	}

	public void set(quickfix.field.FrozenPosition value) {
		setField(value);
	}

	public quickfix.field.FrozenPosition get(quickfix.field.FrozenPosition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.FrozenPosition getFrozenPosition() throws FieldNotFound {
		return get(new quickfix.field.FrozenPosition());
	}

	public boolean isSet(quickfix.field.FrozenPosition field) {
		return isSetField(field);
	}

	public boolean isSetFrozenPosition() {
		return isSetField(8017);
	}

	public void set(quickfix.field.FrozenAmt value) {
		setField(value);
	}

	public quickfix.field.FrozenAmt get(quickfix.field.FrozenAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.FrozenAmt getFrozenAmt() throws FieldNotFound {
		return get(new quickfix.field.FrozenAmt());
	}

	public boolean isSet(quickfix.field.FrozenAmt field) {
		return isSetField(field);
	}

	public boolean isSetFrozenAmt() {
		return isSetField(8018);
	}

	public void set(quickfix.field.PositionDate value) {
		setField(value);
	}

	public quickfix.field.PositionDate get(quickfix.field.PositionDate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PositionDate getPositionDate() throws FieldNotFound {
		return get(new quickfix.field.PositionDate());
	}

	public boolean isSet(quickfix.field.PositionDate field) {
		return isSetField(field);
	}

	public boolean isSetPositionDate() {
		return isSetField(8019);
	}

	public void set(quickfix.field.AvgPx value) {
		setField(value);
	}

	public quickfix.field.AvgPx get(quickfix.field.AvgPx value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.AvgPx getAvgPx() throws FieldNotFound {
		return get(new quickfix.field.AvgPx());
	}

	public boolean isSet(quickfix.field.AvgPx field) {
		return isSetField(field);
	}

	public boolean isSetAvgPx() {
		return isSetField(6);
	}

	public void set(quickfix.field.Commission value) {
		setField(value);
	}

	public quickfix.field.Commission get(quickfix.field.Commission value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Commission getCommission() throws FieldNotFound {
		return get(new quickfix.field.Commission());
	}

	public boolean isSet(quickfix.field.Commission field) {
		return isSetField(field);
	}

	public boolean isSetCommission() {
		return isSetField(12);
	}

	public void set(quickfix.field.PositionProfit value) {
		setField(value);
	}

	public quickfix.field.PositionProfit get(quickfix.field.PositionProfit value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PositionProfit getPositionProfit() throws FieldNotFound {
		return get(new quickfix.field.PositionProfit());
	}

	public boolean isSet(quickfix.field.PositionProfit field) {
		return isSetField(field);
	}

	public boolean isSetPositionProfit() {
		return isSetField(8021);
	}

	public void set(quickfix.field.PositionPrice value) {
		setField(value);
	}

	public quickfix.field.PositionPrice get(quickfix.field.PositionPrice value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PositionPrice getPositionPrice() throws FieldNotFound {
		return get(new quickfix.field.PositionPrice());
	}

	public boolean isSet(quickfix.field.PositionPrice field) {
		return isSetField(field);
	}

	public boolean isSetPositionPrice() {
		return isSetField(8022);
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
