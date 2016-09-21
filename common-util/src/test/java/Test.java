import com.alibaba.fastjson.JSON;


public class Test {

	public static void main(String[] args) {
		long l = 9223372036854775807l;
		long a = (long) Math.pow(2, 62);
		boolean b = l > a;
		System.out.println(b);
		
	}
}

