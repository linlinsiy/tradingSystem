package com.sanjin.business.orderManage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.TextFormat;
import com.sanjin.bean.StockPoolClientProtos.Exchange;
import com.sanjin.bean.StockPoolGatewayProtos.BrokerPosition;
import com.sanjin.business.gateway.FixMessageManager;
import com.sanjin.cache.BrokerCache;
import com.sanjin.cache.BrokerPositionCache;

public class PositionManagement implements Runnable {
	private static Logger logger = LogManager.getLogger("position");
	
	@Override
	public void run() {
		logger.info("Position Management已经开启");
		while (true) {
			BrokerPosition pos = FixMessageManager.getInstance().popPosition();
			if(pos !=null) {
				try {
					logger.info(pos);
					if(pos.getSymbol().equals("000000"))
						continue;
					BrokerPosition.Builder posBuilder = pos.toBuilder();
					if(BrokerCache.getInstance().isHkTrade(pos.getBrokerId()))
						if(pos.getExchange() == Exchange.SH)
							posBuilder.setExchange(Exchange.HKSH);
						else if(pos.getExchange() == Exchange.SZ)
							posBuilder.setExchange(Exchange.HKSZ);
					pos = posBuilder.build();
					
					BrokerPosition oldpos = BrokerPositionCache.getInstance().getPosition(pos.getBrokerId(), pos.getSymbol());
					if(oldpos == null && pos.getPosition() == pos.getLeavesQty()) {
						BrokerPositionCache.getInstance().putPosition(pos);
						logger.info("持仓更新成功");
					} else if(oldpos == null){
						System.out.println("Broker持仓出现错误，原持仓不存在而broker返回持仓不为初始数据");
						System.out.println(TextFormat.shortDebugString(pos));
						logger.error("Broker持仓出现错误，原持仓不存在而broker返回持仓不为初始数据");
						logger.error(TextFormat.shortDebugString(pos));
					} else {
						if(oldpos.getPosition()+oldpos.getPosLong()-oldpos.getPosShort()!=pos.getPosition() ||
								oldpos.getPosition() - oldpos.getPosShort() -oldpos.getPosShortPending() != pos.getLeavesQty()) {
							System.out.println("Broker持仓数据不匹配");
							logger.warn("Broker持仓数据不匹配");
							System.out.println("现有仓位："+TextFormat.shortDebugString(oldpos));
							logger.warn("现有仓位："+TextFormat.shortDebugString(oldpos));
							System.out.println("新仓位："+TextFormat.shortDebugString(pos));
							logger.warn("新仓位："+TextFormat.shortDebugString(pos));
						}
						BrokerPositionCache.getInstance().putPosition(oldpos.toBuilder().setLeavesQty(pos.getLeavesQty()).build());
						
					}
				} catch(Throwable e) {
					logger.error("出异常啦",e);
				}
			}
			
		}

	}
}
