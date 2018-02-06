package test;

public class StringFormatTest {
	public static void main(String[] args) {
		int num = 123532;
		String str = String.format("%07d", num);
		System.out.println(str);
	}
}
