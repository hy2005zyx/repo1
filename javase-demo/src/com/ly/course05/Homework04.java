package com.ly.course05;

import java.util.Scanner;

public class Homework04 {
	/**
	   1、编写程序输出1-100之间所有能被7整除的偶数。
	   2、编写程序输出1-100之间所有质数之和。
	   3、在屏幕上输出一个n行的金字塔图案，n通过屏幕输入
	 */
	public static void main(String[] args) {

		/**
		 * 1、编写程序输出1-100之间所有能被7整除的偶数。
		 */
		System.out.println("1-100之间所有能被7整除的偶数：");
		for (int i = 2; i < 100; i += 2) {
			if (i % 7 == 0) {
				System.out.print(i + ",");
			}
		}
		System.out.println();
		
		System.out.println("==================华丽的分割线==================");
		
		/**
		 * 2、编写程序输出1-100之间所有质数之和。
		 */
		int sum = 0;
		/**
		 * 最小的质数是2，所以从2开始循环
		 */
		for (int i = 2; i < 100; i++) {
			//质数标记变量，默认是质数
			boolean isZs = true;
			/**
			 * 关键是判断质数的内循环，注意它的退出条件
			 * 1不用做整除判断，所以从2开始
			 */
			for (int j = 2; j <= i / 2; j++) {
				if (i % j == 0) {
					/**
					 * 被整除，不是质数
					 */
					isZs = false;
					break;
				}
			}
			sum += isZs ? i : 0;
		}
		System.out.println("100以内质数之和是：" + sum);
		
		System.out.println("==================华丽的分割线==================");

		/**
		 * 3、在屏幕上输出一个n行的金字塔图案，n通过屏幕输入
		 */
		System.out.println("请输入金字塔的高度（莫手贱，数字别输得太大）：");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			//打印空格
			for (int j = 0; j < n - i - 1; j++) {
				System.out.print(" ");
			}
			//打印星号
			for (int j = 0; j < (i + 1) * 2 - 1; j++) {
				System.out.print("*");
			}
			//换行
			System.out.println();
		}
	}

}
