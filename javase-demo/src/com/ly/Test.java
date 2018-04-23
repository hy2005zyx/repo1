package com.ly;

import java.util.Random;
import java.util.Scanner;

public class Test {
	public static final int SINGLE = 1;
	public static final int MARRIED = 2;

	private static final double RATE1 = 0.15;
	private static final double RATE2 = 0.28;
	private static final double RATE3 = 0.31;

	private static final double SINGLE_BRACKET1 = 21450;
	private static final double SINGLE_BRACKET2 = 51900;

	private static final double MARRIED_BRACKET1 = 35800;
	private static final double MARRIED_BRACKET2 = 86500;

	public static void maxMinAvg() {
		int[] a = { 468, 116, 221, 767, 478, 914, 47, 842, 460, 891, 252, 62, 966, 516, 699, 650, 701, 109, 874, 507,
				41, 401, 330, 968, 282, 734, 268, 385, 795, 893, 76, 148, 618, 519, 764, 115, 524, 713, 844, 749, 870,
				558, 972, 775, 543, 952, 12, 903, 449, 907, 699, 413, 918, 342, 491, 223, 47, 150, 670, 55, 343, 113,
				114, 715, 614, 492, 647, 744, 345, 994, 306, 620, 447, 449, 666, 695, 967, 874, 23, 917, 173, 909, 530,
				355, 179, 863, 751, 579, 604, 473, 400, 253, 252, 270, 732, 292, 555, 113, 532, 323 };
		int max = 0, min = 1000, sum = 0;
		for (int i : a) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
			sum += i;
		}
		int avg = sum / a.length;
		System.out.println(String.format("最大值：%s，最小值：%s，平均值：%s", max, min, avg));
	}

	public static void chenji() {
		int[] a = { 60, 80, 86, 93, 76, 74, 87, 71, 56, 68, 50, 71, 87, 79, 78, 64, 50, 81, 62, 92, 93, 80, 84, 76, 96,
				50, 95, 58, 82, 57, 99, 78, 80, 52, 50, 55, 52, 67, 75, 81, 89, 59, 56, 51, 60, 93, 52, 90, 61 };
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		int avg = sum / a.length;
		int countJg = 0;
		int countPj = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < avg) {
				countPj++;
			}
			if (a[i] >= 60) {
				countJg++;
			}
		}
		System.out.println(String.format("平均分：%s，及格人数：%s，低于平均分人数：%s", avg, countJg, countPj));
	}

	public static void tax() {
		Scanner in = new Scanner(System.in);

		System.out.print("请输入您的收入: ");
		double income = in.nextDouble();

		System.out.print("请输入婚姻状态(S 单身 / M 已婚): ");
		String input = in.next();
		int status = 0;

		if (input.equalsIgnoreCase("S"))
			status = SINGLE;
		else if (input.equalsIgnoreCase("M"))
			status = MARRIED;
		else {
			System.out.println("Bad input.");
			return;
		}

		double tax = 0;

		if (status == SINGLE) {
			if (income <= SINGLE_BRACKET1)
				tax = RATE1 * income;
			else if (income <= SINGLE_BRACKET2)
				tax = RATE1 * SINGLE_BRACKET1 + RATE2 * (income - SINGLE_BRACKET1);
			else
				tax = RATE1 * SINGLE_BRACKET1 + RATE2 * (SINGLE_BRACKET2 - SINGLE_BRACKET1)
						+ RATE3 * (income - SINGLE_BRACKET2);
		} else {
			if (income <= MARRIED_BRACKET1)
				tax = RATE1 * income;
			else if (income <= MARRIED_BRACKET2)
				tax = RATE1 * MARRIED_BRACKET1 + RATE2 * (income - MARRIED_BRACKET1);
			else
				tax = RATE1 * MARRIED_BRACKET1 + RATE2 * (MARRIED_BRACKET2 - MARRIED_BRACKET1)
						+ RATE3 * (income - MARRIED_BRACKET2);
		}

		System.out.println("所应缴的税款为:" + tax);

	}

	public static void main(String[] args) {
		/*Random r = new Random();
		for (int i = 0; i < 50; i++) {
			System.out.print(50 + r.nextInt(51) + ", ");
		}*/

		tax();
	}
}
