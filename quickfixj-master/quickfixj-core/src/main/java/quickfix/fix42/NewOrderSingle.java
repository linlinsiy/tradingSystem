
package quickfix.fix42;

import quickfix.FieldNotFound;

import quickfix.Group;
import quickfix.field.IOIID;

public class NewOrderSingle extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "D";
	

	public NewOrderSingle() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public NewOrderSingle(quickfix.field.ClOrdID clOrdID, quickfix.field.HandlInst handlInst, quickfix.field.Symbol symbol, quickfix.field.Side side, quickfix.field.TransactTime transactTime, quickfix.field.OrderQty orderQty, quickfix.field.OrdType ordType) {
		this();
		setField(clOrdID);
		setField(handlInst);
		setField(symbol);
		setField(side);
		setField(transactTime);
		setField(orderQty);
		setField(ordType);
	}
	
	public void set(quickfix.field.ClOrdID value) {
		setField(value);
	}

	public quickfix.field.ClOrdID get(quickfix.field.ClOrdID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ClOrdID getClOrdID() throws FieldNotFound {
		return get(new quickfix.field.ClOrdID());
	}

	public boolean isSet(quickfix.field.ClOrdID field) {
		return isSetField(field);
	}

	public boolean isSetClOrdID() {
		return isSetField(11);
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

	public void set(quickfix.field.ExecBroker value) {
		setField(value);
	}

	public quickfix.field.ExecBroker get(quickfix.field.ExecBroker value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ExecBroker getExecBroker() throws FieldNotFound {
		return get(new quickfix.field.ExecBroker());
	}

	public boolean isSet(quickfix.field.ExecBroker field) {
		return isSetField(field);
	}

	public boolean isSetExecBroker() {
		return isSetField(76);
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

	public void set(quickfix.field.NoAllocs value) {
		setField(value);
	}

	public quickfix.field.NoAllocs get(quickfix.field.NoAllocs value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.NoAllocs getNoAllocs() throws FieldNotFound {
		return get(new quickfix.field.NoAllocs());
	}

	public boolean isSet(quickfix.field.NoAllocs field) {
		return isSetField(field);
	}

	public boolean isSetNoAllocs() {
		return isSetField(78);
	}

	public static class NoAllocs extends Group {

		static final long serialVersionUID = 20050617;
		private static final int[] ORDER = {79, 80, 0};

		public NoAllocs() {
			super(78, 79, ORDER);
		}
		
	public void set(quickfix.field.AllocAccount value) {
		setField(value);
	}

	public quickfix.field.AllocAccount get(quickfix.field.AllocAccount value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.AllocAccount getAllocAccount() throws FieldNotFound {
		return get(new quickfix.field.AllocAccount());
	}

	public boolean isSet(quickfix.field.AllocAccount field) {
		return isSetField(field);
	}

	public boolean isSetAllocAccount() {
		return isSetField(79);
	}

	public void set(quickfix.field.AllocShares value) {
		setField(value);
	}

	public quickfix.field.AllocShares get(quickfix.field.AllocShares value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.AllocShares getAllocShares() throws FieldNotFound {
		return get(new quickfix.field.AllocShares());
	}

	public boolean isSet(quickfix.field.AllocShares field) {
		return isSetField(field);
	}

	public boolean isSetAllocShares() {
		return isSetField(80);
	}

	}

	public void set(quickfix.field.SettlmntTyp value) {
		setField(value);
	}

	public quickfix.field.SettlmntTyp get(quickfix.field.SettlmntTyp value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SettlmntTyp getSettlmntTyp() throws FieldNotFound {
		return get(new quickfix.field.SettlmntTyp());
	}

	public boolean isSet(quickfix.field.SettlmntTyp field) {
		return isSetField(field);
	}

	public boolean isSetSettlmntTyp() {
		return isSetField(63);
	}

	public void set(quickfix.field.FutSettDate value) {
		setField(value);
	}

	public quickfix.field.FutSettDate get(quickfix.field.FutSettDate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.FutSettDate getFutSettDate() throws FieldNotFound {
		return get(new quickfix.field.FutSettDate());
	}

	public boolean isSet(quickfix.field.FutSettDate field) {
		return isSetField(field);
	}

	public boolean isSetFutSettDate() {
		return isSetField(64);
	}

	public void set(quickfix.field.HandlInst value) {
		setField(value);
	}

	public quickfix.field.HandlInst get(quickfix.field.HandlInst value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.HandlInst getHandlInst() throws FieldNotFound {
		return get(new quickfix.field.HandlInst());
	}

	public boolean isSet(quickfix.field.HandlInst field) {
		return isSetField(field);
	}

	public boolean isSetHandlInst() {
		return isSetField(21);
	}

	public void set(quickfix.field.ExecInst value) {
		setField(value);
	}

	public quickfix.field.ExecInst get(quickfix.field.ExecInst value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ExecInst getExecInst() throws FieldNotFound {
		return get(new quickfix.field.ExecInst());
	}

	public boolean isSet(quickfix.field.ExecInst field) {
		return isSetField(field);
	}

	public boolean isSetExecInst() {
		return isSetField(18);
	}

	public void set(quickfix.field.MinQty value) {
		setField(value);
	}

	public quickfix.field.MinQty get(quickfix.field.MinQty value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MinQty getMinQty() throws FieldNotFound {
		return get(new quickfix.field.MinQty());
	}

	public boolean isSet(quickfix.field.MinQty field) {
		return isSetField(field);
	}

	public boolean isSetMinQty() {
		return isSetField(110);
	}

	public void set(quickfix.field.MaxFloor value) {
		setField(value);
	}

	public quickfix.field.MaxFloor get(quickfix.field.MaxFloor value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxFloor getMaxFloor() throws FieldNotFound {
		return get(new quickfix.field.MaxFloor());
	}

	public boolean isSet(quickfix.field.MaxFloor field) {
		return isSetField(field);
	}

	public boolean isSetMaxFloor() {
		return isSetField(111);
	}

	public void set(quickfix.field.ExDestination value) {
		setField(value);
	}

	public quickfix.field.ExDestination get(quickfix.field.ExDestination value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ExDestination getExDestination() throws FieldNotFound {
		return get(new quickfix.field.ExDestination());
	}

	public boolean isSet(quickfix.field.ExDestination field) {
		return isSetField(field);
	}

	public boolean isSetExDestination() {
		return isSetField(100);
	}

	public void set(quickfix.field.NoTradingSessions value) {
		setField(value);
	}

	public quickfix.field.NoTradingSessions get(quickfix.field.NoTradingSessions value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.NoTradingSessions getNoTradingSessions() throws FieldNotFound {
		return get(new quickfix.field.NoTradingSessions());
	}

	public boolean isSet(quickfix.field.NoTradingSessions field) {
		return isSetField(field);
	}

	public boolean isSetNoTradingSessions() {
		return isSetField(386);
	}

	public static class NoTradingSessions extends Group {

		static final long serialVersionUID = 20050617;
		private static final int[] ORDER = {336, 0};

		public NoTradingSessions() {
			super(386, 336, ORDER);
		}
		
	public void set(quickfix.field.TradingSessionID value) {
		setField(value);
	}

	public quickfix.field.TradingSessionID get(quickfix.field.TradingSessionID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TradingSessionID getTradingSessionID() throws FieldNotFound {
		return get(new quickfix.field.TradingSessionID());
	}

	public boolean isSet(quickfix.field.TradingSessionID field) {
		return isSetField(field);
	}

	public boolean isSetTradingSessionID() {
		return isSetField(336);
	}

	}

	public void set(quickfix.field.ProcessCode value) {
		setField(value);
	}

	public quickfix.field.ProcessCode get(quickfix.field.ProcessCode value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ProcessCode getProcessCode() throws FieldNotFound {
		return get(new quickfix.field.ProcessCode());
	}

	public boolean isSet(quickfix.field.ProcessCode field) {
		return isSetField(field);
	}

	public boolean isSetProcessCode() {
		return isSetField(81);
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

	public void set(quickfix.field.SecurityID value) {
		setField(value);
	}

	public quickfix.field.SecurityID get(quickfix.field.SecurityID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SecurityID getSecurityID() throws FieldNotFound {
		return get(new quickfix.field.SecurityID());
	}

	public boolean isSet(quickfix.field.SecurityID field) {
		return isSetField(field);
	}

	public boolean isSetSecurityID() {
		return isSetField(48);
	}

	public void set(quickfix.field.IDSource value) {
		setField(value);
	}

	public quickfix.field.IDSource get(quickfix.field.IDSource value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.IDSource getIDSource() throws FieldNotFound {
		return get(new quickfix.field.IDSource());
	}

	public boolean isSet(quickfix.field.IDSource field) {
		return isSetField(field);
	}

	public boolean isSetIDSource() {
		return isSetField(22);
	}

	public void set(quickfix.field.SecurityType value) {
		setField(value);
	}

	public quickfix.field.SecurityType get(quickfix.field.SecurityType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SecurityType getSecurityType() throws FieldNotFound {
		return get(new quickfix.field.SecurityType());
	}

	public boolean isSet(quickfix.field.SecurityType field) {
		return isSetField(field);
	}

	public boolean isSetSecurityType() {
		return isSetField(167);
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

	public void set(quickfix.field.MaturityDay value) {
		setField(value);
	}

	public quickfix.field.MaturityDay get(quickfix.field.MaturityDay value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaturityDay getMaturityDay() throws FieldNotFound {
		return get(new quickfix.field.MaturityDay());
	}

	public boolean isSet(quickfix.field.MaturityDay field) {
		return isSetField(field);
	}

	public boolean isSetMaturityDay() {
		return isSetField(205);
	}

	public void set(quickfix.field.PutOrCall value) {
		setField(value);
	}

	public quickfix.field.PutOrCall get(quickfix.field.PutOrCall value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PutOrCall getPutOrCall() throws FieldNotFound {
		return get(new quickfix.field.PutOrCall());
	}

	public boolean isSet(quickfix.field.PutOrCall field) {
		return isSetField(field);
	}

	public boolean isSetPutOrCall() {
		return isSetField(201);
	}

	public void set(quickfix.field.StrikePrice value) {
		setField(value);
	}

	public quickfix.field.StrikePrice get(quickfix.field.StrikePrice value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.StrikePrice getStrikePrice() throws FieldNotFound {
		return get(new quickfix.field.StrikePrice());
	}

	public boolean isSet(quickfix.field.StrikePrice field) {
		return isSetField(field);
	}

	public boolean isSetStrikePrice() {
		return isSetField(202);
	}

	public void set(quickfix.field.OptAttribute value) {
		setField(value);
	}

	public quickfix.field.OptAttribute get(quickfix.field.OptAttribute value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OptAttribute getOptAttribute() throws FieldNotFound {
		return get(new quickfix.field.OptAttribute());
	}

	public boolean isSet(quickfix.field.OptAttribute field) {
		return isSetField(field);
	}

	public boolean isSetOptAttribute() {
		return isSetField(206);
	}

	public void set(quickfix.field.ContractMultiplier value) {
		setField(value);
	}

	public quickfix.field.ContractMultiplier get(quickfix.field.ContractMultiplier value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ContractMultiplier getContractMultiplier() throws FieldNotFound {
		return get(new quickfix.field.ContractMultiplier());
	}

	public boolean isSet(quickfix.field.ContractMultiplier field) {
		return isSetField(field);
	}

	public boolean isSetContractMultiplier() {
		return isSetField(231);
	}

	public void set(quickfix.field.CouponRate value) {
		setField(value);
	}

	public quickfix.field.CouponRate get(quickfix.field.CouponRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CouponRate getCouponRate() throws FieldNotFound {
		return get(new quickfix.field.CouponRate());
	}

	public boolean isSet(quickfix.field.CouponRate field) {
		return isSetField(field);
	}

	public boolean isSetCouponRate() {
		return isSetField(223);
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

	public void set(quickfix.field.Issuer value) {
		setField(value);
	}

	public quickfix.field.Issuer get(quickfix.field.Issuer value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Issuer getIssuer() throws FieldNotFound {
		return get(new quickfix.field.Issuer());
	}

	public boolean isSet(quickfix.field.Issuer field) {
		return isSetField(field);
	}

	public boolean isSetIssuer() {
		return isSetField(106);
	}

	public void set(quickfix.field.EncodedIssuerLen value) {
		setField(value);
	}

	public quickfix.field.EncodedIssuerLen get(quickfix.field.EncodedIssuerLen value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EncodedIssuerLen getEncodedIssuerLen() throws FieldNotFound {
		return get(new quickfix.field.EncodedIssuerLen());
	}

	public boolean isSet(quickfix.field.EncodedIssuerLen field) {
		return isSetField(field);
	}

	public boolean isSetEncodedIssuerLen() {
		return isSetField(348);
	}

	public void set(quickfix.field.EncodedIssuer value) {
		setField(value);
	}

	public quickfix.field.EncodedIssuer get(quickfix.field.EncodedIssuer value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EncodedIssuer getEncodedIssuer() throws FieldNotFound {
		return get(new quickfix.field.EncodedIssuer());
	}

	public boolean isSet(quickfix.field.EncodedIssuer field) {
		return isSetField(field);
	}

	public boolean isSetEncodedIssuer() {
		return isSetField(349);
	}

	public void set(quickfix.field.SecurityDesc value) {
		setField(value);
	}

	public quickfix.field.SecurityDesc get(quickfix.field.SecurityDesc value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SecurityDesc getSecurityDesc() throws FieldNotFound {
		return get(new quickfix.field.SecurityDesc());
	}

	public boolean isSet(quickfix.field.SecurityDesc field) {
		return isSetField(field);
	}

	public boolean isSetSecurityDesc() {
		return isSetField(107);
	}

	public void set(quickfix.field.EncodedSecurityDescLen value) {
		setField(value);
	}

	public quickfix.field.EncodedSecurityDescLen get(quickfix.field.EncodedSecurityDescLen value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EncodedSecurityDescLen getEncodedSecurityDescLen() throws FieldNotFound {
		return get(new quickfix.field.EncodedSecurityDescLen());
	}

	public boolean isSet(quickfix.field.EncodedSecurityDescLen field) {
		return isSetField(field);
	}

	public boolean isSetEncodedSecurityDescLen() {
		return isSetField(350);
	}

	public void set(quickfix.field.EncodedSecurityDesc value) {
		setField(value);
	}

	public quickfix.field.EncodedSecurityDesc get(quickfix.field.EncodedSecurityDesc value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EncodedSecurityDesc getEncodedSecurityDesc() throws FieldNotFound {
		return get(new quickfix.field.EncodedSecurityDesc());
	}

	public boolean isSet(quickfix.field.EncodedSecurityDesc field) {
		return isSetField(field);
	}

	public boolean isSetEncodedSecurityDesc() {
		return isSetField(351);
	}

	public void set(quickfix.field.PrevClosePx value) {
		setField(value);
	}

	public quickfix.field.PrevClosePx get(quickfix.field.PrevClosePx value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PrevClosePx getPrevClosePx() throws FieldNotFound {
		return get(new quickfix.field.PrevClosePx());
	}

	public boolean isSet(quickfix.field.PrevClosePx field) {
		return isSetField(field);
	}

	public boolean isSetPrevClosePx() {
		return isSetField(140);
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

	public void set(quickfix.field.LocateReqd value) {
		setField(value);
	}

	public quickfix.field.LocateReqd get(quickfix.field.LocateReqd value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.LocateReqd getLocateReqd() throws FieldNotFound {
		return get(new quickfix.field.LocateReqd());
	}

	public boolean isSet(quickfix.field.LocateReqd field) {
		return isSetField(field);
	}

	public boolean isSetLocateReqd() {
		return isSetField(114);
	}

	public void set(quickfix.field.TransactTime value) {
		setField(value);
	}

	public quickfix.field.TransactTime get(quickfix.field.TransactTime value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TransactTime getTransactTime() throws FieldNotFound {
		return get(new quickfix.field.TransactTime());
	}

	public boolean isSet(quickfix.field.TransactTime field) {
		return isSetField(field);
	}

	public boolean isSetTransactTime() {
		return isSetField(60);
	}

	public void set(quickfix.field.OrderQty value) {
		setField(value);
	}

	public quickfix.field.OrderQty get(quickfix.field.OrderQty value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OrderQty getOrderQty() throws FieldNotFound {
		return get(new quickfix.field.OrderQty());
	}

	public boolean isSet(quickfix.field.OrderQty field) {
		return isSetField(field);
	}

	public boolean isSetOrderQty() {
		return isSetField(38);
	}

	public void set(quickfix.field.CashOrderQty value) {
		setField(value);
	}

	public quickfix.field.CashOrderQty get(quickfix.field.CashOrderQty value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CashOrderQty getCashOrderQty() throws FieldNotFound {
		return get(new quickfix.field.CashOrderQty());
	}

	public boolean isSet(quickfix.field.CashOrderQty field) {
		return isSetField(field);
	}

	public boolean isSetCashOrderQty() {
		return isSetField(152);
	}

	public void set(quickfix.field.OrdType value) {
		setField(value);
	}

	public quickfix.field.OrdType get(quickfix.field.OrdType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OrdType getOrdType() throws FieldNotFound {
		return get(new quickfix.field.OrdType());
	}

	public boolean isSet(quickfix.field.OrdType field) {
		return isSetField(field);
	}

	public boolean isSetOrdType() {
		return isSetField(40);
	}

	public void set(quickfix.field.Price value) {
		setField(value);
	}

	public quickfix.field.Price get(quickfix.field.Price value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Price getPrice() throws FieldNotFound {
		return get(new quickfix.field.Price());
	}

	public boolean isSet(quickfix.field.Price field) {
		return isSetField(field);
	}

	public boolean isSetPrice() {
		return isSetField(44);
	}

	public void set(quickfix.field.StopPx value) {
		setField(value);
	}

	public quickfix.field.StopPx get(quickfix.field.StopPx value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.StopPx getStopPx() throws FieldNotFound {
		return get(new quickfix.field.StopPx());
	}

	public boolean isSet(quickfix.field.StopPx field) {
		return isSetField(field);
	}

	public boolean isSetStopPx() {
		return isSetField(99);
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

	public void set(quickfix.field.ComplianceID value) {
		setField(value);
	}

	public quickfix.field.ComplianceID get(quickfix.field.ComplianceID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ComplianceID getComplianceID() throws FieldNotFound {
		return get(new quickfix.field.ComplianceID());
	}

	public boolean isSet(quickfix.field.ComplianceID field) {
		return isSetField(field);
	}

	public boolean isSetComplianceID() {
		return isSetField(376);
	}

	public void set(quickfix.field.SolicitedFlag value) {
		setField(value);
	}

	public quickfix.field.SolicitedFlag get(quickfix.field.SolicitedFlag value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SolicitedFlag getSolicitedFlag() throws FieldNotFound {
		return get(new quickfix.field.SolicitedFlag());
	}

	public boolean isSet(quickfix.field.SolicitedFlag field) {
		return isSetField(field);
	}

	public boolean isSetSolicitedFlag() {
		return isSetField(377);
	}

	public void set(IOIID value) {
		setField(value);
	}

	public IOIID get(IOIID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public IOIID getIOIid() throws FieldNotFound {
		return get(new IOIID());
	}

	public boolean isSet(IOIID field) {
		return isSetField(field);
	}

	public boolean isSetIOIid() {
		return isSetField(23);
	}

	public void set(quickfix.field.QuoteID value) {
		setField(value);
	}

	public quickfix.field.QuoteID get(quickfix.field.QuoteID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.QuoteID getQuoteID() throws FieldNotFound {
		return get(new quickfix.field.QuoteID());
	}

	public boolean isSet(quickfix.field.QuoteID field) {
		return isSetField(field);
	}

	public boolean isSetQuoteID() {
		return isSetField(117);
	}

	public void set(quickfix.field.TimeInForce value) {
		setField(value);
	}

	public quickfix.field.TimeInForce get(quickfix.field.TimeInForce value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TimeInForce getTimeInForce() throws FieldNotFound {
		return get(new quickfix.field.TimeInForce());
	}

	public boolean isSet(quickfix.field.TimeInForce field) {
		return isSetField(field);
	}

	public boolean isSetTimeInForce() {
		return isSetField(59);
	}

	public void set(quickfix.field.EffectiveTime value) {
		setField(value);
	}

	public quickfix.field.EffectiveTime get(quickfix.field.EffectiveTime value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EffectiveTime getEffectiveTime() throws FieldNotFound {
		return get(new quickfix.field.EffectiveTime());
	}

	public boolean isSet(quickfix.field.EffectiveTime field) {
		return isSetField(field);
	}

	public boolean isSetEffectiveTime() {
		return isSetField(168);
	}

	public void set(quickfix.field.ExpireDate value) {
		setField(value);
	}

	public quickfix.field.ExpireDate get(quickfix.field.ExpireDate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ExpireDate getExpireDate() throws FieldNotFound {
		return get(new quickfix.field.ExpireDate());
	}

	public boolean isSet(quickfix.field.ExpireDate field) {
		return isSetField(field);
	}

	public boolean isSetExpireDate() {
		return isSetField(432);
	}

	public void set(quickfix.field.ExpireTime value) {
		setField(value);
	}

	public quickfix.field.ExpireTime get(quickfix.field.ExpireTime value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ExpireTime getExpireTime() throws FieldNotFound {
		return get(new quickfix.field.ExpireTime());
	}

	public boolean isSet(quickfix.field.ExpireTime field) {
		return isSetField(field);
	}

	public boolean isSetExpireTime() {
		return isSetField(126);
	}

	public void set(quickfix.field.GTBookingInst value) {
		setField(value);
	}

	public quickfix.field.GTBookingInst get(quickfix.field.GTBookingInst value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.GTBookingInst getGTBookingInst() throws FieldNotFound {
		return get(new quickfix.field.GTBookingInst());
	}

	public boolean isSet(quickfix.field.GTBookingInst field) {
		return isSetField(field);
	}

	public boolean isSetGTBookingInst() {
		return isSetField(427);
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

	public void set(quickfix.field.CommType value) {
		setField(value);
	}

	public quickfix.field.CommType get(quickfix.field.CommType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CommType getCommType() throws FieldNotFound {
		return get(new quickfix.field.CommType());
	}

	public boolean isSet(quickfix.field.CommType field) {
		return isSetField(field);
	}

	public boolean isSetCommType() {
		return isSetField(13);
	}

	public void set(quickfix.field.Rule80A value) {
		setField(value);
	}

	public quickfix.field.Rule80A get(quickfix.field.Rule80A value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Rule80A getRule80A() throws FieldNotFound {
		return get(new quickfix.field.Rule80A());
	}

	public boolean isSet(quickfix.field.Rule80A field) {
		return isSetField(field);
	}

	public boolean isSetRule80A() {
		return isSetField(47);
	}

	public void set(quickfix.field.ForexReq value) {
		setField(value);
	}

	public quickfix.field.ForexReq get(quickfix.field.ForexReq value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ForexReq getForexReq() throws FieldNotFound {
		return get(new quickfix.field.ForexReq());
	}

	public boolean isSet(quickfix.field.ForexReq field) {
		return isSetField(field);
	}

	public boolean isSetForexReq() {
		return isSetField(121);
	}

	public void set(quickfix.field.SettlCurrency value) {
		setField(value);
	}

	public quickfix.field.SettlCurrency get(quickfix.field.SettlCurrency value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SettlCurrency getSettlCurrency() throws FieldNotFound {
		return get(new quickfix.field.SettlCurrency());
	}

	public boolean isSet(quickfix.field.SettlCurrency field) {
		return isSetField(field);
	}

	public boolean isSetSettlCurrency() {
		return isSetField(120);
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

	public void set(quickfix.field.EncodedTextLen value) {
		setField(value);
	}

	public quickfix.field.EncodedTextLen get(quickfix.field.EncodedTextLen value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EncodedTextLen getEncodedTextLen() throws FieldNotFound {
		return get(new quickfix.field.EncodedTextLen());
	}

	public boolean isSet(quickfix.field.EncodedTextLen field) {
		return isSetField(field);
	}

	public boolean isSetEncodedTextLen() {
		return isSetField(354);
	}

	public void set(quickfix.field.EncodedText value) {
		setField(value);
	}

	public quickfix.field.EncodedText get(quickfix.field.EncodedText value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EncodedText getEncodedText() throws FieldNotFound {
		return get(new quickfix.field.EncodedText());
	}

	public boolean isSet(quickfix.field.EncodedText field) {
		return isSetField(field);
	}

	public boolean isSetEncodedText() {
		return isSetField(355);
	}

	public void set(quickfix.field.FutSettDate2 value) {
		setField(value);
	}

	public quickfix.field.FutSettDate2 get(quickfix.field.FutSettDate2 value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.FutSettDate2 getFutSettDate2() throws FieldNotFound {
		return get(new quickfix.field.FutSettDate2());
	}

	public boolean isSet(quickfix.field.FutSettDate2 field) {
		return isSetField(field);
	}

	public boolean isSetFutSettDate2() {
		return isSetField(193);
	}

	public void set(quickfix.field.OrderQty2 value) {
		setField(value);
	}

	public quickfix.field.OrderQty2 get(quickfix.field.OrderQty2 value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OrderQty2 getOrderQty2() throws FieldNotFound {
		return get(new quickfix.field.OrderQty2());
	}

	public boolean isSet(quickfix.field.OrderQty2 field) {
		return isSetField(field);
	}

	public boolean isSetOrderQty2() {
		return isSetField(192);
	}

	public void set(quickfix.field.OpenClose value) {
		setField(value);
	}

	public quickfix.field.OpenClose get(quickfix.field.OpenClose value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OpenClose getOpenClose() throws FieldNotFound {
		return get(new quickfix.field.OpenClose());
	}

	public boolean isSet(quickfix.field.OpenClose field) {
		return isSetField(field);
	}

	public boolean isSetOpenClose() {
		return isSetField(77);
	}

	public void set(quickfix.field.CoveredOrUncovered value) {
		setField(value);
	}

	public quickfix.field.CoveredOrUncovered get(quickfix.field.CoveredOrUncovered value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CoveredOrUncovered getCoveredOrUncovered() throws FieldNotFound {
		return get(new quickfix.field.CoveredOrUncovered());
	}

	public boolean isSet(quickfix.field.CoveredOrUncovered field) {
		return isSetField(field);
	}

	public boolean isSetCoveredOrUncovered() {
		return isSetField(203);
	}

	public void set(quickfix.field.CustomerOrFirm value) {
		setField(value);
	}

	public quickfix.field.CustomerOrFirm get(quickfix.field.CustomerOrFirm value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CustomerOrFirm getCustomerOrFirm() throws FieldNotFound {
		return get(new quickfix.field.CustomerOrFirm());
	}

	public boolean isSet(quickfix.field.CustomerOrFirm field) {
		return isSetField(field);
	}

	public boolean isSetCustomerOrFirm() {
		return isSetField(204);
	}

	public void set(quickfix.field.MaxShow value) {
		setField(value);
	}

	public quickfix.field.MaxShow get(quickfix.field.MaxShow value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxShow getMaxShow() throws FieldNotFound {
		return get(new quickfix.field.MaxShow());
	}

	public boolean isSet(quickfix.field.MaxShow field) {
		return isSetField(field);
	}

	public boolean isSetMaxShow() {
		return isSetField(210);
	}

	public void set(quickfix.field.PegDifference value) {
		setField(value);
	}

	public quickfix.field.PegDifference get(quickfix.field.PegDifference value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PegDifference getPegDifference() throws FieldNotFound {
		return get(new quickfix.field.PegDifference());
	}

	public boolean isSet(quickfix.field.PegDifference field) {
		return isSetField(field);
	}

	public boolean isSetPegDifference() {
		return isSetField(211);
	}

	public void set(quickfix.field.DiscretionInst value) {
		setField(value);
	}

	public quickfix.field.DiscretionInst get(quickfix.field.DiscretionInst value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.DiscretionInst getDiscretionInst() throws FieldNotFound {
		return get(new quickfix.field.DiscretionInst());
	}

	public boolean isSet(quickfix.field.DiscretionInst field) {
		return isSetField(field);
	}

	public boolean isSetDiscretionInst() {
		return isSetField(388);
	}

	public void set(quickfix.field.DiscretionOffset value) {
		setField(value);
	}

	public quickfix.field.DiscretionOffset get(quickfix.field.DiscretionOffset value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.DiscretionOffset getDiscretionOffset() throws FieldNotFound {
		return get(new quickfix.field.DiscretionOffset());
	}

	public boolean isSet(quickfix.field.DiscretionOffset field) {
		return isSetField(field);
	}

	public boolean isSetDiscretionOffset() {
		return isSetField(389);
	}

	public void set(quickfix.field.ClearingFirm value) {
		setField(value);
	}

	public quickfix.field.ClearingFirm get(quickfix.field.ClearingFirm value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ClearingFirm getClearingFirm() throws FieldNotFound {
		return get(new quickfix.field.ClearingFirm());
	}

	public boolean isSet(quickfix.field.ClearingFirm field) {
		return isSetField(field);
	}

	public boolean isSetClearingFirm() {
		return isSetField(439);
	}

	public void set(quickfix.field.ClearingAccount value) {
		setField(value);
	}

	public quickfix.field.ClearingAccount get(quickfix.field.ClearingAccount value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ClearingAccount getClearingAccount() throws FieldNotFound {
		return get(new quickfix.field.ClearingAccount());
	}

	public boolean isSet(quickfix.field.ClearingAccount field) {
		return isSetField(field);
	}

	public boolean isSetClearingAccount() {
		return isSetField(440);
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

	public void set(quickfix.field.TouchCondition value) {
		setField(value);
	}

	public quickfix.field.TouchCondition get(quickfix.field.TouchCondition value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TouchCondition getTouchCondition() throws FieldNotFound {
		return get(new quickfix.field.TouchCondition());
	}

	public boolean isSet(quickfix.field.TouchCondition field) {
		return isSetField(field);
	}

	public boolean isSetTouchCondition() {
		return isSetField(8010);
	}

	public void set(quickfix.field.PriceType value) {
		setField(value);
	}

	public quickfix.field.PriceType get(quickfix.field.PriceType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PriceType getPriceType() throws FieldNotFound {
		return get(new quickfix.field.PriceType());
	}

	public boolean isSet(quickfix.field.PriceType field) {
		return isSetField(field);
	}

	public boolean isSetPriceType() {
		return isSetField(423);
	}

	public void set(quickfix.field.CloseFlag value) {
		setField(value);
	}

	public quickfix.field.CloseFlag get(quickfix.field.CloseFlag value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CloseFlag getCloseFlag() throws FieldNotFound {
		return get(new quickfix.field.CloseFlag());
	}

	public boolean isSet(quickfix.field.CloseFlag field) {
		return isSetField(field);
	}

	public boolean isSetCloseFlag() {
		return isSetField(7001);
	}

	public void set(quickfix.field.StrategyType value) {
		setField(value);
	}

	public quickfix.field.StrategyType get(quickfix.field.StrategyType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.StrategyType getStrategyType() throws FieldNotFound {
		return get(new quickfix.field.StrategyType());
	}

	public boolean isSet(quickfix.field.StrategyType field) {
		return isSetField(field);
	}

	public boolean isSetStrategyType() {
		return isSetField(3001);
	}

	public void set(quickfix.field.StrategyStatus value) {
		setField(value);
	}

	public quickfix.field.StrategyStatus get(quickfix.field.StrategyStatus value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.StrategyStatus getStrategyStatus() throws FieldNotFound {
		return get(new quickfix.field.StrategyStatus());
	}

	public boolean isSet(quickfix.field.StrategyStatus field) {
		return isSetField(field);
	}

	public boolean isSetStrategyStatus() {
		return isSetField(3100);
	}

	public void set(quickfix.field.StartTime value) {
		setField(value);
	}

	public quickfix.field.StartTime get(quickfix.field.StartTime value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.StartTime getStartTime() throws FieldNotFound {
		return get(new quickfix.field.StartTime());
	}

	public boolean isSet(quickfix.field.StartTime field) {
		return isSetField(field);
	}

	public boolean isSetStartTime() {
		return isSetField(3002);
	}

	public void set(quickfix.field.EndTime value) {
		setField(value);
	}

	public quickfix.field.EndTime get(quickfix.field.EndTime value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.EndTime getEndTime() throws FieldNotFound {
		return get(new quickfix.field.EndTime());
	}

	public boolean isSet(quickfix.field.EndTime field) {
		return isSetField(field);
	}

	public boolean isSetEndTime() {
		return isSetField(3003);
	}

	public void set(quickfix.field.ParticipationRate value) {
		setField(value);
	}

	public quickfix.field.ParticipationRate get(quickfix.field.ParticipationRate value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ParticipationRate getParticipationRate() throws FieldNotFound {
		return get(new quickfix.field.ParticipationRate());
	}

	public boolean isSet(quickfix.field.ParticipationRate field) {
		return isSetField(field);
	}

	public boolean isSetParticipationRate() {
		return isSetField(3004);
	}

	public void set(quickfix.field.Style value) {
		setField(value);
	}

	public quickfix.field.Style get(quickfix.field.Style value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.Style getStyle() throws FieldNotFound {
		return get(new quickfix.field.Style());
	}

	public boolean isSet(quickfix.field.Style field) {
		return isSetField(field);
	}

	public boolean isSetStyle() {
		return isSetField(3005);
	}

	public void set(quickfix.field.RefPrice value) {
		setField(value);
	}

	public quickfix.field.RefPrice get(quickfix.field.RefPrice value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.RefPrice getRefPrice() throws FieldNotFound {
		return get(new quickfix.field.RefPrice());
	}

	public boolean isSet(quickfix.field.RefPrice field) {
		return isSetField(field);
	}

	public boolean isSetRefPrice() {
		return isSetField(3007);
	}

	public void set(quickfix.field.OPG value) {
		setField(value);
	}

	public quickfix.field.OPG get(quickfix.field.OPG value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OPG getOPG() throws FieldNotFound {
		return get(new quickfix.field.OPG());
	}

	public boolean isSet(quickfix.field.OPG field) {
		return isSetField(field);
	}

	public boolean isSetOPG() {
		return isSetField(3008);
	}

	public void set(quickfix.field.MOC value) {
		setField(value);
	}

	public quickfix.field.MOC get(quickfix.field.MOC value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MOC getMOC() throws FieldNotFound {
		return get(new quickfix.field.MOC());
	}

	public boolean isSet(quickfix.field.MOC field) {
		return isSetField(field);
	}

	public boolean isSetMOC() {
		return isSetField(3009);
	}

	public void set(quickfix.field.MinDisplaySize value) {
		setField(value);
	}

	public quickfix.field.MinDisplaySize get(quickfix.field.MinDisplaySize value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MinDisplaySize getMinDisplaySize() throws FieldNotFound {
		return get(new quickfix.field.MinDisplaySize());
	}

	public boolean isSet(quickfix.field.MinDisplaySize field) {
		return isSetField(field);
	}

	public boolean isSetMinDisplaySize() {
		return isSetField(3010);
	}

	public void set(quickfix.field.CountEligibleVolumewithinLimitPrice value) {
		setField(value);
	}

	public quickfix.field.CountEligibleVolumewithinLimitPrice get(quickfix.field.CountEligibleVolumewithinLimitPrice value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CountEligibleVolumewithinLimitPrice getCountEligibleVolumewithinLimitPrice() throws FieldNotFound {
		return get(new quickfix.field.CountEligibleVolumewithinLimitPrice());
	}

	public boolean isSet(quickfix.field.CountEligibleVolumewithinLimitPrice field) {
		return isSetField(field);
	}

	public boolean isSetCountEligibleVolumewithinLimitPrice() {
		return isSetField(3011);
	}

	public void set(quickfix.field.ResetEligibleVolumewithinLimitPrice value) {
		setField(value);
	}

	public quickfix.field.ResetEligibleVolumewithinLimitPrice get(quickfix.field.ResetEligibleVolumewithinLimitPrice value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.ResetEligibleVolumewithinLimitPrice getResetEligibleVolumewithinLimitPrice() throws FieldNotFound {
		return get(new quickfix.field.ResetEligibleVolumewithinLimitPrice());
	}

	public boolean isSet(quickfix.field.ResetEligibleVolumewithinLimitPrice field) {
		return isSetField(field);
	}

	public boolean isSetResetEligibleVolumewithinLimitPrice() {
		return isSetField(3012);
	}

	public void set(quickfix.field.MaxPriceLevels value) {
		setField(value);
	}

	public quickfix.field.MaxPriceLevels get(quickfix.field.MaxPriceLevels value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxPriceLevels getMaxPriceLevels() throws FieldNotFound {
		return get(new quickfix.field.MaxPriceLevels());
	}

	public boolean isSet(quickfix.field.MaxPriceLevels field) {
		return isSetField(field);
	}

	public boolean isSetMaxPriceLevels() {
		return isSetField(3013);
	}

	public void set(quickfix.field.MaxOrdersPerLevels value) {
		setField(value);
	}

	public quickfix.field.MaxOrdersPerLevels get(quickfix.field.MaxOrdersPerLevels value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.MaxOrdersPerLevels getMaxOrdersPerLevels() throws FieldNotFound {
		return get(new quickfix.field.MaxOrdersPerLevels());
	}

	public boolean isSet(quickfix.field.MaxOrdersPerLevels field) {
		return isSetField(field);
	}

	public boolean isSetMaxOrdersPerLevels() {
		return isSetField(3014);
	}

	public void set(quickfix.field.RelativePriceLimitBase value) {
		setField(value);
	}

	public quickfix.field.RelativePriceLimitBase get(quickfix.field.RelativePriceLimitBase value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.RelativePriceLimitBase getRelativePriceLimitBase() throws FieldNotFound {
		return get(new quickfix.field.RelativePriceLimitBase());
	}

	public boolean isSet(quickfix.field.RelativePriceLimitBase field) {
		return isSetField(field);
	}

	public boolean isSetRelativePriceLimitBase() {
		return isSetField(3015);
	}

	public void set(quickfix.field.RelativePriceLimitType value) {
		setField(value);
	}

	public quickfix.field.RelativePriceLimitType get(quickfix.field.RelativePriceLimitType value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.RelativePriceLimitType getRelativePriceLimitType() throws FieldNotFound {
		return get(new quickfix.field.RelativePriceLimitType());
	}

	public boolean isSet(quickfix.field.RelativePriceLimitType field) {
		return isSetField(field);
	}

	public boolean isSetRelativePriceLimitType() {
		return isSetField(3016);
	}

	public void set(quickfix.field.RelativePriceLimitOffset value) {
		setField(value);
	}

	public quickfix.field.RelativePriceLimitOffset get(quickfix.field.RelativePriceLimitOffset value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.RelativePriceLimitOffset getRelativePriceLimitOffset() throws FieldNotFound {
		return get(new quickfix.field.RelativePriceLimitOffset());
	}

	public boolean isSet(quickfix.field.RelativePriceLimitOffset field) {
		return isSetField(field);
	}

	public boolean isSetRelativePriceLimitOffset() {
		return isSetField(3017);
	}

	public void set(quickfix.field.AMOpenPercent value) {
		setField(value);
	}

	public quickfix.field.AMOpenPercent get(quickfix.field.AMOpenPercent value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.AMOpenPercent getAMOpenPercent() throws FieldNotFound {
		return get(new quickfix.field.AMOpenPercent());
	}

	public boolean isSet(quickfix.field.AMOpenPercent field) {
		return isSetField(field);
	}

	public boolean isSetAMOpenPercent() {
		return isSetField(3018);
	}

	public void set(quickfix.field.PMClosePercent value) {
		setField(value);
	}

	public quickfix.field.PMClosePercent get(quickfix.field.PMClosePercent value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.PMClosePercent getPMClosePercent() throws FieldNotFound {
		return get(new quickfix.field.PMClosePercent());
	}

	public boolean isSet(quickfix.field.PMClosePercent field) {
		return isSetField(field);
	}

	public boolean isSetPMClosePercent() {
		return isSetField(3019);
	}

	public void set(quickfix.field.AMPercent value) {
		setField(value);
	}

	public quickfix.field.AMPercent get(quickfix.field.AMPercent value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.AMPercent getAMPercent() throws FieldNotFound {
		return get(new quickfix.field.AMPercent());
	}

	public boolean isSet(quickfix.field.AMPercent field) {
		return isSetField(field);
	}

	public boolean isSetAMPercent() {
		return isSetField(3020);
	}

	public void set(quickfix.field.SerialNum value) {
		setField(value);
	}

	public quickfix.field.SerialNum get(quickfix.field.SerialNum value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SerialNum getSerialNum() throws FieldNotFound {
		return get(new quickfix.field.SerialNum());
	}

	public boolean isSet(quickfix.field.SerialNum field) {
		return isSetField(field);
	}

	public boolean isSetSerialNum() {
		return isSetField(3105);
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

	public void set(quickfix.field.GrossTradeAmt value) {
		setField(value);
	}

	public quickfix.field.GrossTradeAmt get(quickfix.field.GrossTradeAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.GrossTradeAmt getGrossTradeAmt() throws FieldNotFound {
		return get(new quickfix.field.GrossTradeAmt());
	}

	public boolean isSet(quickfix.field.GrossTradeAmt field) {
		return isSetField(field);
	}

	public boolean isSetGrossTradeAmt() {
		return isSetField(381);
	}

	public void set(quickfix.field.RegId value) {
		setField(value);
	}

	public quickfix.field.RegId get(quickfix.field.RegId value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.RegId getRegId() throws FieldNotFound {
		return get(new quickfix.field.RegId());
	}

	public boolean isSet(quickfix.field.RegId field) {
		return isSetField(field);
	}

	public boolean isSetRegId() {
		return isSetField(3106);
	}

	public void set(quickfix.field.BatchNum value) {
		setField(value);
	}

	public quickfix.field.BatchNum get(quickfix.field.BatchNum value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.BatchNum getBatchNum() throws FieldNotFound {
		return get(new quickfix.field.BatchNum());
	}

	public boolean isSet(quickfix.field.BatchNum field) {
		return isSetField(field);
	}

	public boolean isSetBatchNum() {
		return isSetField(3107);
	}

	public void set(quickfix.field.UserId value) {
		setField(value);
	}

	public quickfix.field.UserId get(quickfix.field.UserId value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.UserId getUserId() throws FieldNotFound {
		return get(new quickfix.field.UserId());
	}

	public boolean isSet(quickfix.field.UserId field) {
		return isSetField(field);
	}

	public boolean isSetUserId() {
		return isSetField(3112);
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

	public void set(quickfix.field.OrigClOrdID value) {
		setField(value);
	}

	public quickfix.field.OrigClOrdID get(quickfix.field.OrigClOrdID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OrigClOrdID getOrigClOrdID() throws FieldNotFound {
		return get(new quickfix.field.OrigClOrdID());
	}

	public boolean isSet(quickfix.field.OrigClOrdID field) {
		return isSetField(field);
	}

	public boolean isSetOrigClOrdID() {
		return isSetField(41);
	}

	public void set(quickfix.field.QtyMode value) {
		setField(value);
	}

	public quickfix.field.QtyMode get(quickfix.field.QtyMode value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.QtyMode getQtyMode() throws FieldNotFound {
		return get(new quickfix.field.QtyMode());
	}

	public boolean isSet(quickfix.field.QtyMode field) {
		return isSetField(field);
	}

	public boolean isSetQtyMode() {
		return isSetField(3113);
	}

	public void set(quickfix.field.SecondaryOrderID value) {
		setField(value);
	}

	public quickfix.field.SecondaryOrderID get(quickfix.field.SecondaryOrderID value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SecondaryOrderID getSecondaryOrderID() throws FieldNotFound {
		return get(new quickfix.field.SecondaryOrderID());
	}

	public boolean isSet(quickfix.field.SecondaryOrderID field) {
		return isSetField(field);
	}

	public boolean isSetSecondaryOrderID() {
		return isSetField(198);
	}

}
