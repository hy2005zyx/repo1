package com.ly.course07.guessing;

import java.util.Scanner;

public class Person {

	private String name;
	private int score;
	private Scanner scanner = new Scanner(System.in);

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toString() {
		return name + "的得分：" + score;
	}

	public int showFist() {
		System.out.println(name + "出拳：");
		int ret = scanner.nextInt();
		return ret;
	}

}
