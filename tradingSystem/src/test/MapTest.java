package test;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.sanjin.bean.StockPoolClientProtos.ClientAccount;

public class MapTest {
	public static void main(String[] args) {
		ConcurrentHashMap<Integer,ClientAccount> orderMap = new ConcurrentHashMap<Integer,ClientAccount>();
		new Thread(new Runnable() {
			int index = 1;
			Random r1 = new Random(1000);
			public void run() {
				while(true) {
					ClientAccount.Builder builder = ClientAccount.newBuilder();
					builder.setClientId(index+"");
					builder.setInitBalance(r1.nextDouble());
					builder.setUsableBalance(r1.nextDouble());
					builder.setFrozenBalance(r1.nextDouble());
					orderMap.put(index,builder.build());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		while(true) {
			for(int id:orderMap.keySet()) {
				System.out.println(orderMap.get(id));
			}
			
		}
	}
}
