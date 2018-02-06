package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;

public class TimestampTest {
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date time = new Date();
		System.out.println(df.format(time));
//		Timestamp ts = Timestamp.newBuilder().setSeconds(time.getSecond()).setNanos(time.getNano()).build();
//		System.out.println(Timestamps.toString(ts));
	}
}
