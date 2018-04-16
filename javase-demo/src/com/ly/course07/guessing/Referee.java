package com.ly.course07.guessing;

import java.util.Scanner;

public class Referee {

	private String[] fistName = { null, "布", "剪刀", "石头" };
	private Person person;
	private Android android;
	private Android[] androids = { new Android("喜洋洋"), new Android("灰太狼"), new Android("懒洋洋") };
	private Scanner scanner = new Scanner(System.in);
	private int count;

	public Referee() {
		System.out.println("请输入您的名字：");
		String name = scanner.next();
		person = new Person(name);
		/**
		 * 输出全部对手
		 */
		for (int i = 0; i < androids.length; i++) {
			System.out.print(i + 1 + "：" + androids[i].getName() + "  ");
		}
		System.out.println();
		System.out.println("请选择您的对手（请输入1~3）：");
		/**
		 * 根据用户的输入选择对手
		 */
		android = androids[scanner.nextInt() - 1];
	}

	public void start() {
		while (true) {
			int pFist = person.showFist();
			int aFist = android.showFist();
			System.out.println(android.getName() + "出“" + fistName[aFist] + "”，" + person.getName() + "出“"
					+ fistName[pFist] + "”");
			int result = calc(pFist, aFist);
			switch (result) {
			case 1:
				System.out.println(person.getName() + "赢了！得1分");
				person.setScore(person.getScore() + 1);
				break;
			case -1:
				System.out.println(android.getName() + "赢了！得1分");
				android.setScore(android.getScore() + 1);
				break;
			default:
				System.out.println("平局！双方不得分");
			}
			count++;
			System.out.println("当前局数：" + count + "，双方得分：" + person + "，" + android);
		}
	}

	/**
	 * 计算结果：如果fist1 比 fist2 大，则返回 1，小返回 -1 ，相同返回0
	 * @param fist1
	 * @param fist2
	 * @return
	 */
	public int calc(int fist1, int fist2) {
		if (fist1 == fist2) {
			//相同返回0
			return 0;
		} else if (fist1 + fist2 == 4) {
			if (fist1 < fist2) {
				return 1;
			} else {
				return -1;
			}
		} else {
			if (fist1 > fist2) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public static void main(String[] args) {
		new Referee().start();
	}

}
