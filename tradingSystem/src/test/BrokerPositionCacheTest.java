package test;

import org.junit.Test;

import com.sanjin.cache.BrokerPositionCache;

public class BrokerPositionCacheTest {
	private BrokerPositionCache brokerPositionCache = BrokerPositionCache.getInstance();
	@Test
	public void refreshPosFromDbTest() throws Exception {
		brokerPositionCache.refreshPosFromDb("123", "123");
		
	}
}
