package test;

import static com.sanjin.cache.ClientOrderCache.DATEDF;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.sanjin.cache.bean.DbBrokerOrder;
import com.sanjin.cache.bean.DbBrokerPosition;
import com.sanjin.cache.bean.DbClientOrder;
import com.sanjin.cache.bean.DbUserAccount;
import com.sanjin.cache.mapper.BrokerInfoMapper;
import com.sanjin.cache.mapper.BrokerOrderMapper;
import com.sanjin.cache.mapper.BrokerPositionMapper;
import com.sanjin.cache.mapper.ClientOrderMapper;
import com.sanjin.cache.mapper.ClientPositionMapper;
import com.sanjin.cache.mapper.StockMapper;
import com.sanjin.cache.mapper.UserAccountMapper;
import com.sanjin.cache.mapper.UserInfoMapper;

public class MybatisTest {
	private static String mybatisResource = "mybatis-config.xml";
	private BrokerInfoMapper brokerInfoMapper;
	private BrokerPositionMapper brokerPositionMapper;
	private ClientPositionMapper clientPositionMapper;
	private StockMapper stockMapper;
	private UserInfoMapper userInfoMapper;
	private SqlSession session;
	
	@Before
	public void before() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream(mybatisResource);
		SqlSessionFactoryBuilder facBuilder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory = facBuilder.build(inputStream);
	    session = sqlSessionFactory.openSession(); 
	}
                   
	@Test
	public void testQuery() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream(mybatisResource);
		SqlSessionFactoryBuilder facBuilder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory = facBuilder.build(inputStream);
	    SqlSession session = sqlSessionFactory.openSession();  
//	    brokerInfoMapper = session.getMapper(BrokerInfoMapper.class);
	    brokerPositionMapper = session.getMapper(BrokerPositionMapper.class);

	}
	@Test
	public void testBrokerPosition() throws Exception {

	    brokerPositionMapper = session.getMapper(BrokerPositionMapper.class);
	    
	    DbBrokerPosition brokerPosition = new DbBrokerPosition("123","234",500,200,100,200,200);
	    int n = brokerPositionMapper.insertBrokerPosition(brokerPosition);
	    System.out.println(n);
	}
	
	@Test
	public void testBrokerOrder() throws Exception {
		BrokerOrderMapper brokerOrderMapper = session.getMapper(BrokerOrderMapper.class);
		DbBrokerOrder dbBrokerOrder = new DbBrokerOrder();
		dbBrokerOrder.setOrderId("111");
		dbBrokerOrder.setClientOrderId("201801181347300000000");
		dbBrokerOrder.setDate(DATEDF.format(new Date()).toString());
		dbBrokerOrder.setCancelTime(20180120L);
		brokerOrderMapper.insertBrokerOrder(dbBrokerOrder);
	}
	@Test
	public void testBrokerPosMapper() throws Exception {
		BrokerPositionMapper brokerPositionMapper = session.getMapper(BrokerPositionMapper.class);
		DbBrokerPosition brokerPosition = new DbBrokerPosition();
		
		//		DbBrokerPosition brokerPosition = brokerPositionMapper.getBrokerPositionById("123", "123");
//		System.out.println(brokerPosition);
		brokerPositionMapper.insertBrokerPosition(brokerPosition );
	}
	@Test
	public void testClientOrder() throws Exception {
		ClientOrderMapper clientOrderMapper = session.getMapper(ClientOrderMapper.class);
		DbClientOrder dbClientOrder = new DbClientOrder();
		dbClientOrder.setOrderId("201801181347300000000");
		dbClientOrder.setDate(DATEDF.format(new Date()).toString());
		dbClientOrder.setCancelTime(20180119L);
		clientOrderMapper.insertClientOrder(dbClientOrder);

	}
	@Test
	public void testUserAccount() throws Exception {
		UserAccountMapper userAccountMapper = session.getMapper(UserAccountMapper.class);

		DbUserAccount dbUserAccount = new DbUserAccount("12345",7000.0,10000.0,3000.0);
		int i = userAccountMapper.insertUserAccount(dbUserAccount);
		System.out.println(i);
		List<DbUserAccount> list = userAccountMapper.getAllUserAccount();
		for (DbUserAccount userAccount : list) {
			System.out.println(userAccount);
		}
	}

}
