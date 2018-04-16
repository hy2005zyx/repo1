package com.ly.course07.guessing;

import java.util.Random;

public class Android {

	private String name;
	private int score;
	
	public Android(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		Random r = new Random();
		return r.nextInt(3) + 1;
	}

}
