package org.wsh.common.test.servlet;

public class TestB {
	static String zhongwen = "中文";
	static byte[] bytes = null;

	public static void main(String[] args) throws Exception {
		// GBK
		//main1();
		// utf-8
		//main2();
		// GBK-->utf-8-->GBK
		//main3();
		// GBK-->iso8859-1-->utf-8-->iso8859-1-->GBK
		main4();

	}
	// GBK
	public static void main1() throws Exception {
		bytes = zhongwen.getBytes("GBK");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}
		System.out.println(new String(bytes, "GBK"));
	}
	// utf-8
	public static void main2() throws Exception {
		bytes = zhongwen.getBytes("utf-8");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}
		System.out.println(new String(bytes, "utf-8"));
	}
	// GBK-->utf-8-->GBK
	public static void main3() throws Exception {

		bytes = zhongwen.getBytes("GBK");
		System.out.println("--------GBK----------");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}

		bytes = zhongwen.getBytes("utf-8");
		byte [] bytes_copy = bytes;
		System.out.println("-------utf-8--------");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}

		//错误的组装方法
		System.out.println("-------error GBK--------");
		zhongwen = new String(bytes, "GBK");
		System.out.println(zhongwen);
		bytes = zhongwen.getBytes("GBK");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}
		//正确的组装方法
		System.out.println("-------GBK--------");
		System.out.println(new String(bytes_copy, "utf-8"));
	}
	// GBK-->iso8859-1-->utf-8-->iso8859-1-->GBK
	public static void main4() throws Exception {

		//最开始是GBK
		bytes = zhongwen.getBytes("GBK");
		System.out.println("--------GBK----------");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}

		//被组装成了iso8859-1,new出的字符串对象有问题,但是字节内容没变
		System.out.println("-------iso8859-1--------");
		zhongwen = new String(zhongwen.getBytes("GBK"), "iso8859-1");
		System.out.println(zhongwen);
		bytes = zhongwen.getBytes("iso8859-1");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}
		//转换成utf-8在网络上传输,一个byte转换为两个字节,所以一共8个字节
		bytes = zhongwen.getBytes("utf-8");
		System.out.println("-------utf-8--------");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}

		//server端接收到utf-8,首先组装成iso8859-1,new出的字符串对象有问题,显示4个问号
		System.out.println("-------server--iso8859-1------");
		zhongwen = new String(bytes, "utf-8");
		System.out.println(zhongwen);
		bytes = zhongwen.getBytes("iso8859-1");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(Integer.toHexString(bytes[i]));
		}

		//以iso8859-1得到字节,并组装成GBK
		System.out.println("-------GBK--------");
		System.out.println(new String(bytes, "GBK"));
	}



	public static void ____________________main() throws Exception {
		System.out.println(System.getProperty("file.encoding"));
		System.out.println(System.getProperty("user.language"));
		System.out.println(System.getProperty("user.region"));
	}

}
