package com.ly.course06;

import java.util.Random;

public class Poker {
	public static void main(String[] args) {

		String[] flowerNames = { "红桃", "黑桃", "方块", "梅花" };
		String[] numberNames = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "J", "Q", "K" };

		//两副扑克牌
		int[] cards = new int[52 * 2];

		for (int i = 0; i < cards.length; i++) {
			//多副扑克牌
			cards[i] = i % 52;
		}

		//打印新牌
		for (int i = 0; i < cards.length; i++) {
			//取花色
			int flower = cards[i] / 13;
			//取牌号 1~9 A~K
			int number = cards[i] % 13;
			System.out.print(flowerNames[flower] + numberNames[number] + ",");
			if ((i + 1) % 13 == 0) {
				System.out.println();
			}
		}
		System.out.println();

		//洗牌
		Random r = new Random();
		for (int i = 0; i < cards.length - 1; i++) {
			int index = r.nextInt(cards.length - i);
			int temp = cards[i];
			cards[i] = cards[index];
			cards[index] = temp;
		}

		//发牌
		int[][] p = new int[4][cards.length / 4];
		for (int i = 0; i < cards.length; i++) {
			//计算拿牌的玩家
			p[i % 4][i / 4] = cards[i];
		}

		//整牌（冒泡排序）
		//遍历玩家
		for (int j = 0; j < p.length; j++) {
			//下面是冒泡排序法
			for (int i = p[0].length; i > 0; i--) {
				for (int k = 0; k < i - 1; k++) { 	//注意：内循环的写法 k < i - 1
					if (p[j][k] > p[j][k + 1]) {
						//如果前面的牌大于后面的牌，则交换2张牌
						int tmp = p[j][k];
						p[j][k] = p[j][k + 1];
						p[j][k + 1] = tmp;
					}
				}
			}
		}

		//打印牌
		for (int j = 0; j < p.length; j++) {
			System.out.print("玩家" + (j + 1) + "的牌：");
			for (int i = 0; i < p[j].length; i++) {
				//取花色
				int flower = p[j][i] / 13;
				//取牌号 1~9 A~K
				int number = p[j][i] % 13;
				System.out.print(flowerNames[flower] + numberNames[number] + ",");
			}
			System.out.println();
		}

		//显示可出的对子
		int cnt = 2;
		for (int z = 0; z < p.length; z++) {
			System.out.print("玩家" + (z + 1) + "可以出的" + cnt + "对子：");
			for (int j = 0; j < p[z].length - cnt + 1; j++) { //注意：p[0].length - cnt + 1
				//定义牌的计数器
				int count = 1;
				//对当前牌的后 cnt 张牌进行对比（注意前提是牌是排好顺序的）
				for (int k = j + 1; k < j + cnt; k++) { //注意：k < j + cnt
					if (p[z][j] == p[z][k]) {
						//如果后面的牌与当前牌一样，则+1
						count++;
					}
				}
				if (count == cnt) {
					//如果统计想同牌的张数与要求的张数一致，则打印 cnt 次该牌
					int flower = p[z][j] / 13;
					int number = p[z][j] % 13;
					String s = flowerNames[flower] + numberNames[number];
					for (int v = 0; v < cnt; v++) {
						System.out.print(" " + s);
					}
					System.out.print(",");
				} else if (count > cnt) {
					//如果统计想同牌的张数大于要求的张数，则将循环变量向前推进
					j += count - 1;
				} else {
					//否则，童鞋们想想是什么情况？要不要处理
				}

			}
			System.out.println();
			/**
			 * 这个算法还有优化的空间，例如：如果是2副以上的牌，在统计2对子时，
			 * 如果某个牌有2张以上的，则会出现多次，例如：红桃K,红桃K,红桃K
			 * 统计会出现：玩家1可以出的2对子：红桃K 红桃K ,红桃K 红桃K ,
			 * 请思考，什么原因造成的？如何解决该问题？
			 */
		}
	}

}
