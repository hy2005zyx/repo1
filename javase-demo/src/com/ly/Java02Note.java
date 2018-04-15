package com.ly;
public class Java02Note{

	public static void main(String[] args) {

		char c = '你';
		char c1 = '\''; //特殊符号，使用转义符 
		char c2 = '\130'; //八进制  1~3 位
		char c3 = '\u1111'; //十六进制 固定4位 char c4 = 65535;

		// alt + / 代码提示

		boolean b = true; //真 
		boolean b1 = false; //假
		boolean bool = 1 > 2;
		
		System.out.println(bool);

		// 关于字面值
		// 整数类型（4种）：默认：int
		// 浮点数（2种）：默认：double

		int i = 58;
		long l = i; //自动类型转换（隐式转换） 小转大  规则：1、类型匹配，2、目标类型大于源
		byte a = (byte) i; //强制类型的转换（显示转换）

		//报错：Type mismatch: cannot convert from double to float ，解释：无法将double转换成float

		//直接指定字面值类型，指定为单精度浮点数 ，后面跟上该类型的首字母，大小写都可以

		float f = 1.442354e3F;
		double d = 1.0D;
		long l1 = 1234576L;

		// ctrl + shift + / 多行注释
		// ctrl + shift + \ 取消多行注释
		// 关于进制，10、8、16

		int i2 = 01327724; //8进制 以 0 开头 
		int i3 = 0xffff; //十六进制 0x开头

		// 关于正数和负数的存储方式

		// 00000000 00000000 00000000 00000100 原码 4
		// 11111111 11111111 11111111 11111100 补码 -4
		int i4 = 4; //关于浮点数的精度问题
		i4 = -4; //关于浮点数的精度问题

		double d1 = 2.0 - 1.1;
		System.out.println(d1); //返回0.899999999999999999	
		System.out.println(0.999999999999999999 == 1f); //返回 true

		//引用数据类型 Object 
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = obj1;

		System.out.println(obj1 == obj2); //false	分别 new 出的对象 ，内存地址不同
		System.out.println(obj1 == obj3); //true	同一对象，同一内存地址

		//没有被变量引用的对象，会被虚拟机回收
		obj1 = null;
		obj2 = null;
		obj3 = null;

		// 字符串 char，属于引用数据类型
		String s = "这是字符串";
		String s1 = new String("这是字符串");

		// 关于字符串的比较

		System.out.println(s == s1);

		System.out.println(s.equals(s1));

		int x, y, z; //多个同类型变量，可以用一个语句定义，变量之间用 , 号分隔

		int i5 = 100;
		int i6 = 70;

		System.out.println("数学" + i5 + "分，语文" + i6 + "分");

		//字符串内出现的特殊字符，必须使用 \ 转义，如："号  \号
		System.out.println("数学\"100\\分，语文100分");

		// 格式化 ctrl + shift + f 一定把輸入法關閉

		int i7, i8 = 0;
		char c4 = '语';
		i7 = c4 + i8;
		System.out.println("结果是：" + i7);

		//打印全部char字符，65535是char的最大值
		for (int i9 = 0; i9 < 65535; i9++) {

			System.out.print((char) i9); //强制类型转换
			if (i9 % 1000 == 0) { //逢1000个字符换行
				System.out.println();
			}
		}

		Object obj4 = new Object();

		Object obj5 = i;

		System.out.println(obj4 == obj5);

		int i10 = 100;

		int i11 = 100;

		if (i10 == 100 && i11 == 100) {
			System.out.println("双百，奖励乐高玩具");
		} else if (i10 == 100 || i11 == 100) {
			System.out.println("单课100，奖励球鞋");
		} else if (i10 != 100 && i11 != 100) {
			System.out.println("两门都没有100，奖励步步高打火机");
		} else if (i10 < 60 || i11 < 60) {
			System.out.println("一门不及格，竹笋炒肉丝");
		}

		
		
	}

}
