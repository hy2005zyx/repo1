package com.ly.util;

import java.util.Random;

public class RandomUtils {

	private static final Random r = new Random();

	/**
	 * 随机生成 0 ~ bound-1 之间的随机整数值
	 * @param bound
	 * @return
	 */
	public static int nextInt(int bound) {
		return r.nextInt(bound);
	}

	/**
	 * 随机生成 start ~ end（含 end） 之间的随机整数值，
	 * 如：输入 3 和 8，  输出值最大值是 3，最大值是 8
	 * @param start
	 * @param end
	 * @return
	 */
	public static int nextInt(int start, int end) {
		return r.nextInt(end - start + 1) + start;
	}

	/**
	 * 返回权重随机数，如输入参数：6，1，3，那么 60% 的可能输出 0， 10% 的可能输出 1， 30% 的可能输出 2
	 * @param nums
	 * @return
	 */
	public static int nextInt(int... nums) {
		int[] quan = new int[nums.length + 1];
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			count += nums[i];
			quan[i + 1] = quan[i] + nums[i];
		}
		int value = r.nextInt(count);
		for (int i = 1; i < quan.length; i++) {
			if (quan[i - 1] <= value && quan[i] > value) {
				return i - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int a = 0, b = 0, c = 0;
		for (int i = 0; i < 1000; i++) {
			int j = nextInt(5, 5, 5);
			System.out.println(j);
			a += j == 0 ? 1 : 0;
			b += j == 1 ? 1 : 0;
			c += j == 2 ? 1 : 0;
		}
		System.out.println(String.format("%s,%s,%s", a, b, c));
	}
}
