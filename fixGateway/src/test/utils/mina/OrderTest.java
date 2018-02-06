package test.utils.mina;

import java.util.Date;

import org.junit.Test;

import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;

public class OrderTest {
	@Test
	public void testTime() throws Exception {
//		long millis = System.currentTimeMillis();
////		Timestamp timestamp = Timestamp.newBuilder().setSeconds(millis / 1000)
////				.build();
//		Timestamp timestamp = Timestamp.parseFrom(System.in);
//		System.out.println(timestamp);
		Timestamp a = Timestamps.fromMillis(new Date().getTime());
		System.out.println(a);
//		com.google.protobuf.Timestamp转化为Date：
//		Timestamp timestamp = null;
		Date date = new Date(a.getSeconds() * 1000);
		System.out.println(date);
	}
}
