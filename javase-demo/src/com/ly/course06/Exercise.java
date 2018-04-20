package com.ly.course06;

import java.util.Scanner;

public class Exercise {

	/**
	 	国王将金币作为工资，发放给忠诚的骑士。第一天，骑士收到一枚金币；之后两天（第二天和第三天）里，
	 	每天收到两枚金币；之后三天（第四、五、六天）里，每天收到三枚金币；之后四天（第七、八、九、十天）里，
	 	每天收到四枚金币……。请问：
		1、在第30天时，i骑士一共获得了多少金币？
		2、假设国王有 1000 个金币，可发多少天？
		3、骑士在第100天拿到的金币数是多少？
	 */
	public static void gold() {
		int sum = 0;
		int days = 0;

		a: for (int i = 1;; i++) {
			for (int j = 0; j < i; j++) {
				//10000个金币可以发几天，这个判断要在 days ++ 之前
				if (sum + i > 10000) {
					System.out.println(days + ":" + sum);
					break a; //推出 a 循环 ，注意第9行的for循环定义
				}
				days++;
				sum += i;
				//打印第100天，骑士当天可以拿的金币数
				if (days == 100) {
					System.out.println(days + ":" + sum + "==>" + i);
				}

				//第30天总共拿的金币
				if (days == 30) {
					System.out.println(days + ":" + sum + "==>" + i);
				}
			}

		}
	}

	/**
	 * 某电信公司的市内通话费计算标准如下：
		三分钟内0.2元，三分钟后每增加一分钟增加0.1元，不足一分钟的按一分钟计算。
		要求编写程序，计算通话 520 秒，电信公司应收费的金额。
	 */
	public static void CallPayment() {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个通话时间：（单位s）");
		int val = input.nextInt();
		double sum = 0;
		if (val <= 180)
			sum = 0.2;
		else if (val % 60 == 0) {
			sum = 0.2 + ((val - 180) / 60) * 0.1;
		} else {
			sum = 0.2 + ((val - 180) / 60 + 1) * 0.1;
		}
		System.out.println("该通话时长的消费金额为：" + sum);
		input.close();
	}
	
	public static void qian() {
		int count = 0;
		int qian = 0, bai = 0, shi = 0, ge = 0;
		for (int i = 1000; i <= 9999; i++) {
			qian = i / 1000;
			bai = i % 1000 / 100;
			shi = i % 100 / 10;
			ge = i % 10;
			if (qian > bai && bai > shi && shi > ge && qian == (bai + shi + ge)) {
				count++;
				System.out.println(i);
			}
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		qian();

	}

}
