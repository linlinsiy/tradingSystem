
package quickfix.fix42;

import quickfix.FieldNotFound;


public class CustomerCapitalStatusResponse extends Message {

	static final long serialVersionUID = 20050617;
	public static final String MSGTYPE = "UF023";
	

	public CustomerCapitalStatusResponse() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}
	
	public CustomerCapitalStatusResponse(quickfix.field.RequestID requestID, quickfix.field.ClientID clientID, quickfix.field.BuyMarginAmt buyMarginAmt, quickfix.field.SellMarginAmt sellMarginAmt, quickfix.field.SupplementalMarginAmt supplementalMarginAmt, quickfix.field.OccupyMarginAmt occupyMarginAmt, quickfix.field.TotalMarginAmt totalMarginAmt, quickfix.field.YesterdayStlAmt yesterdayStlAmt, quickfix.field.BuyFrozenAmt buyFrozenAmt, quickfix.field.SellFrozenAmt sellFrozenAmt, quickfix.field.UseableAmt useableAmt, quickfix.field.RiskLevel riskLevel, quickfix.field.ClientSecuType clientSecuType, quickfix.field.Riskratio riskratio) {
		this();
		setField(requestID);
		setField(clientID);
		setField(buyMarginAmt);
		setField(sellMarginAmt);
		setField(supplementalMarginAmt);
		setField(occupyMarginAmt);
		setField(totalMarginAmt);
		setField(yesterdayStlAmt);
		setField(buyFrozenAmt);
		setField(sellFrozenAmt);
		setField(useableAmt);
		setField(riskLevel);
		setField(clientSecuType);
		setField(riskratio);
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

	public void set(quickfix.field.BuyMarginAmt value) {
		setField(value);
	}

	public quickfix.field.BuyMarginAmt get(quickfix.field.BuyMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.BuyMarginAmt getBuyMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.BuyMarginAmt());
	}

	public boolean isSet(quickfix.field.BuyMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetBuyMarginAmt() {
		return isSetField(8045);
	}

	public void set(quickfix.field.SellMarginAmt value) {
		setField(value);
	}

	public quickfix.field.SellMarginAmt get(quickfix.field.SellMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SellMarginAmt getSellMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.SellMarginAmt());
	}

	public boolean isSet(quickfix.field.SellMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetSellMarginAmt() {
		return isSetField(8046);
	}

	public void set(quickfix.field.SupplementalMarginAmt value) {
		setField(value);
	}

	public quickfix.field.SupplementalMarginAmt get(quickfix.field.SupplementalMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SupplementalMarginAmt getSupplementalMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.SupplementalMarginAmt());
	}

	public boolean isSet(quickfix.field.SupplementalMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetSupplementalMarginAmt() {
		return isSetField(8047);
	}

	public void set(quickfix.field.OccupyMarginAmt value) {
		setField(value);
	}

	public quickfix.field.OccupyMarginAmt get(quickfix.field.OccupyMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.OccupyMarginAmt getOccupyMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.OccupyMarginAmt());
	}

	public boolean isSet(quickfix.field.OccupyMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetOccupyMarginAmt() {
		return isSetField(8048);
	}

	public void set(quickfix.field.TotalMarginAmt value) {
		setField(value);
	}

	public quickfix.field.TotalMarginAmt get(quickfix.field.TotalMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TotalMarginAmt getTotalMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.TotalMarginAmt());
	}

	public boolean isSet(quickfix.field.TotalMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetTotalMarginAmt() {
		return isSetField(8049);
	}

	public void set(quickfix.field.TotalExMarginAmt value) {
		setField(value);
	}

	public quickfix.field.TotalExMarginAmt get(quickfix.field.TotalExMarginAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TotalExMarginAmt getTotalExMarginAmt() throws FieldNotFound {
		return get(new quickfix.field.TotalExMarginAmt());
	}

	public boolean isSet(quickfix.field.TotalExMarginAmt field) {
		return isSetField(field);
	}

	public boolean isSetTotalExMarginAmt() {
		return isSetField(8014);
	}

	public void set(quickfix.field.YesterdayStlAmt value) {
		setField(value);
	}

	public quickfix.field.YesterdayStlAmt get(quickfix.field.YesterdayStlAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.YesterdayStlAmt getYesterdayStlAmt() throws FieldNotFound {
		return get(new quickfix.field.YesterdayStlAmt());
	}

	public boolean isSet(quickfix.field.YesterdayStlAmt field) {
		return isSetField(field);
	}

	public boolean isSetYesterdayStlAmt() {
		return isSetField(8081);
	}

	public void set(quickfix.field.BuyFrozenAmt value) {
		setField(value);
	}

	public quickfix.field.BuyFrozenAmt get(quickfix.field.BuyFrozenAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.BuyFrozenAmt getBuyFrozenAmt() throws FieldNotFound {
		return get(new quickfix.field.BuyFrozenAmt());
	}

	public boolean isSet(quickfix.field.BuyFrozenAmt field) {
		return isSetField(field);
	}

	public boolean isSetBuyFrozenAmt() {
		return isSetField(8082);
	}

	public void set(quickfix.field.SellFrozenAmt value) {
		setField(value);
	}

	public quickfix.field.SellFrozenAmt get(quickfix.field.SellFrozenAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.SellFrozenAmt getSellFrozenAmt() throws FieldNotFound {
		return get(new quickfix.field.SellFrozenAmt());
	}

	public boolean isSet(quickfix.field.SellFrozenAmt field) {
		return isSetField(field);
	}

	public boolean isSetSellFrozenAmt() {
		return isSetField(8083);
	}

	public void set(quickfix.field.FrozenCommision value) {
		setField(value);
	}

	public quickfix.field.FrozenCommision get(quickfix.field.FrozenCommision value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.FrozenCommision getFrozenCommision() throws FieldNotFound {
		return get(new quickfix.field.FrozenCommision());
	}

	public boolean isSet(quickfix.field.FrozenCommision field) {
		return isSetField(field);
	}

	public boolean isSetFrozenCommision() {
		return isSetField(8020);
	}

	public void set(quickfix.field.TotalFrozenAmt value) {
		setField(value);
	}

	public quickfix.field.TotalFrozenAmt get(quickfix.field.TotalFrozenAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.TotalFrozenAmt getTotalFrozenAmt() throws FieldNotFound {
		return get(new quickfix.field.TotalFrozenAmt());
	}

	public boolean isSet(quickfix.field.TotalFrozenAmt field) {
		return isSetField(field);
	}

	public boolean isSetTotalFrozenAmt() {
		return isSetField(8074);
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

	public void set(quickfix.field.FetchAmt value) {
		setField(value);
	}

	public quickfix.field.FetchAmt get(quickfix.field.FetchAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.FetchAmt getFetchAmt() throws FieldNotFound {
		return get(new quickfix.field.FetchAmt());
	}

	public boolean isSet(quickfix.field.FetchAmt field) {
		return isSetField(field);
	}

	public boolean isSetFetchAmt() {
		return isSetField(8098);
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

	public void set(quickfix.field.FloatProfit value) {
		setField(value);
	}

	public quickfix.field.FloatProfit get(quickfix.field.FloatProfit value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.FloatProfit getFloatProfit() throws FieldNotFound {
		return get(new quickfix.field.FloatProfit());
	}

	public boolean isSet(quickfix.field.FloatProfit field) {
		return isSetField(field);
	}

	public boolean isSetFloatProfit() {
		return isSetField(8085);
	}

	public void set(quickfix.field.CloseProfit value) {
		setField(value);
	}

	public quickfix.field.CloseProfit get(quickfix.field.CloseProfit value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.CloseProfit getCloseProfit() throws FieldNotFound {
		return get(new quickfix.field.CloseProfit());
	}

	public boolean isSet(quickfix.field.CloseProfit field) {
		return isSetField(field);
	}

	public boolean isSetCloseProfit() {
		return isSetField(8086);
	}

	public void set(quickfix.field.DayFolatProfit value) {
		setField(value);
	}

	public quickfix.field.DayFolatProfit get(quickfix.field.DayFolatProfit value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.DayFolatProfit getDayFolatProfit() throws FieldNotFound {
		return get(new quickfix.field.DayFolatProfit());
	}

	public boolean isSet(quickfix.field.DayFolatProfit field) {
		return isSetField(field);
	}

	public boolean isSetDayFolatProfit() {
		return isSetField(8087);
	}

	public void set(quickfix.field.DayPaymentAmt value) {
		setField(value);
	}

	public quickfix.field.DayPaymentAmt get(quickfix.field.DayPaymentAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.DayPaymentAmt getDayPaymentAmt() throws FieldNotFound {
		return get(new quickfix.field.DayPaymentAmt());
	}

	public boolean isSet(quickfix.field.DayPaymentAmt field) {
		return isSetField(field);
	}

	public boolean isSetDayPaymentAmt() {
		return isSetField(8099);
	}

	public void set(quickfix.field.DayIncomeAmt value) {
		setField(value);
	}

	public quickfix.field.DayIncomeAmt get(quickfix.field.DayIncomeAmt value) throws FieldNotFound {
		getField(value);
		return value;
	}

	public quickfix.field.DayIncomeAmt getDayIncomeAmt() throws FieldNotFound {
		return get(new quickfix.field.DayIncomeAmt());
	}

	public boolean isSet(quickfix.field.DayIncomeAmt field) {
		return isSetField(field);
	}

	public boolean isSetDayIncomeAmt() {
		return isSetField(8100);
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
