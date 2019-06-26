public class Practice {
	// 利用递归方法求解 n 的阶乘
	public static long factorial1(int n) {
		// 终止条件
		// 1! == 1		0! == 1		1 和 0 的阶乘都是 1
		if (n == 0 || n == 1) {
			return 1;
		}
		// 递推公式
		// n! == (n - 1)! * n
		return factorial1(n - 1) * n;
	}
	
	// 利用循环方法求解 n 的阶乘
	public static long factorial2(int n) {
		// n! = 1 * 2 * 3 * ... * n
		// 一共要循环多少次		n 次
		long fact = 1;
		for (int i = 1; i <= n; i++) {
			fact = fact * i;
		}
		
		return fact;
	}
	
	/**
     * 利用公式
     * e = 1 + 1/1! + 1/2! + 1/3! + 1/4! + ...
     * 计算自然对数的底 e
     * @param n 一共需要计算 n 项
     * @return
     */
	public static double calcE(int n) {
		double e = 0;
		for (int i = 0; i < n; i++) {
			// 需要把 1 类型转换为 double 或者 写成 1.0，否则除法的结果只保留整数部分
			e = e + ((double)1 / factorial1(i));
		}
		
		return e;
	}
	
	/**
     * 利用公式
     * e = 1 + 1/1! + 1/2! + 1/3! + 1/4! + ...
     * 计算自然对数的底 e
     * @param precision 最后一项的精度 <= precision
     * @return
     */
	public static double calcE(double precision) {
		double e = 0;
		int n = 0;
		while (true) {
			double item = 1.0 / factorial1(n++);
			e += item;
			if (item < precision) {
				break;
			}
		}
		
		return e;
	}
	
	/**
     * 利用公式
     * Pi / 4 = 1 - 1/3 + 1/5 - 1/7 + 1/9 - ...
     * 计算 pi 的值
     * @param n 一共计算 n 项
     * @return
     */
	public static double calcPi(int n) {
		double pi4 = 0;
		
		for (int i = 0; i < n; i++) {
			int m = 2 * i + 1;
			if (i % 2 == 0) {
				// 加
				pi4 = pi4 + 1.0 / m;
			} else {
				// 减
				pi4 = pi4 - 1.0 / m;
			}
		}
		
		return pi4 * 4;
	}
	
	public static String reverse(String s) {
		int length = s.length();
		String r = "";
		// 0, 1, 2 ... length - 1
		for (int i = length - 1; i >= 0; i--) {
			char ch = s.charAt(i);
			r = r + ch;
		}
		
		return r;
	}
	
	/**
     * 10 进制转 16 进制，计算公式如下例
     * 1958 转 16 进制
     * 1958 % 16 == 6   1958 / 16 == 122
     * 122 % 16 == 10 也就是 A   122 / 16 == 7
     * 7 < 16
     * 1958 的 16 进制 为 7A6
     * @param n
     * @return
     */
	public static String decToHex(int dec) {
		String hex = "";
		while (dec >= 16) {
			int remainder = dec % 16;
			dec = dec / 16;
			
			if (remainder < 10) {
				hex += remainder;	// 字符串 + int 类型 仍是字符串
			} else {
				char ch = (char)('A' + (remainder - 10));
				hex += ch;
			}
		}
		
		if (dec != 0) {
			char ch = (char)('A' + (dec - 10));
			hex += ch;
		}
		
		return reverse(hex);
	}
	
	public static void main(String[] args) {
		int n = 12;
		
		// 求阶乘的方法
		// 通过递归
		long fact1 = factorial1(n);
		System.out.printf("阶乘是 %d%n", fact1);
		// 通过循环
		long fact2 = factorial2(n);
		System.out.printf("阶乘是 %d%n", fact2);
		
		// 求 e
		// 计算多少项
		double e = calcE(10);
		System.out.printf("e = %f%n", e);
		// 给定精度去求，要求最后一项的结果 < 精度
		e = calcE(1E-4);	// e = calcE(0.0001);
		System.out.printf("e = %f%n", e);
		
		double pi = calcPi(5000);
		System.out.printf("pi = %f%n", pi);
		
		// 逆置字符串
		System.out.println(reverse("Hello"));
		
		// 10 进制转换 16 进制
		System.out.println(decToHex(240));
	}
}










