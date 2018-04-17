package com.ly.course06;

import java.util.Random;
/**
 * 扑克牌洗牌、整排、发牌、计算对子、同花顺的算法例子
 * @author 廖彦
 */
public class Poker {
	public static void main(String[] args) {

		//花色数组，省了判断花色的 switch 或 if 判断
		String[] flowerNames = { "红桃", "黑桃", "方块", "梅花" };
		//牌字数组，省了判断牌字的 switch 或 if 判断
		String[] numberNames = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "J", "Q", "K" };

		//多副扑克牌的数量
		int pokerCount = 4;
		//扑克牌的的总牌数
		int[] cards = new int[52 * pokerCount];

		/**
		 * 为每一张赋初值，一副牌的花色用0~51的数字表示
		 */
		for (int i = 0; i < cards.length; i++) {
			//多副扑克牌
			cards[i] = i % 52;
		}

		/**
		 * 打印新牌
		 */
		for (int i = 0; i < cards.length; i++) {
			//取花色
			int flower = cards[i] / 13;
			//取牌号 1~9 A~K
			int number = cards[i] % 13;
			System.out.print(flowerNames[flower] + numberNames[number] + ",");
			//每个花色结束后换行
			if ((i + 1) % 13 == 0) {
				System.out.println();
			}
			//每副牌之间加入空行
			if ((i + 1) % 52 == 0) {
				System.out.println();
			}
		}

		/**
		 * 洗牌
		 */
		Random r = new Random();
		for (int i = 0; i < cards.length - 1; i++) {
			int index = r.nextInt(cards.length - i);
			int temp = cards[i];
			cards[i] = cards[index];
			cards[index] = temp;
		}

		/**
		 * 发牌
		 */
		int[][] p = new int[4][cards.length / 4];
		for (int i = 0; i < cards.length; i++) {
			//计算拿牌的玩家
			p[i % 4][i / 4] = cards[i];
		}

		/**
		 * 整牌（冒泡排序）
		 */
		//遍历玩家
		for (int j = 0; j < p.length; j++) {
			//下面是冒泡排序法
			for (int i = p[0].length; i > 0; i--) { //注意：外循环是逆向循环
				for (int k = 0; k < i - 1; k++) { //注意：内循环的写法 k < i - 1
					if (p[j][k] > p[j][k + 1]) {
						//如果前面的牌大于后面的牌，则交换2张牌
						int tmp = p[j][k];
						p[j][k] = p[j][k + 1];
						p[j][k + 1] = tmp;
					}
				}
			}
		}

		/**
		 * 打印牌
		 */
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

		System.out.println();

		/**
		 * 显示可出的对子
		 */
		int cnt = 3;
		for (int z = 0; z < p.length; z++) {
			System.out.print("玩家" + (z + 1) + "可以出的" + cnt + "对子：");
			for (int j = 0; j < p[z].length - cnt + 1; j++) { //注意：p[0].length - cnt + 1
				//定义牌的计数器，默认为1，也就当前有的这张牌
				int count = 1;
				for (int k = j + 1; k < p[z].length; k++) {
					if (p[z][j] == p[z][k]) {
						//如果后面的牌与当前牌一样，则+1
						count++;
					} else {
						//如果后面的牌与当前牌不一样，则退出循环，说明没有相同牌了
						break;
					}
				}
				if (count >= cnt) {
					//如果统计想同牌的张数比要求的张数多，则打印 cnt 次该牌
					int flower = p[z][j] / 13;
					int number = p[z][j] % 13;
					String s = flowerNames[flower] + numberNames[number];
					for (int v = 0; v < cnt; v++) {
						System.out.print(" " + s);
					}
					System.out.print(",");
					//将循环变量向前推进
					j += count - 1;
				}

			}
			System.out.println();
			/**
			 * 请思考：如果允许不同花色的牌可以成对，请问要如何实现，例如：红桃K 黑桃K
			 */
		}

		System.out.println();

		/**
		 * 显示可出的同花顺
		 */
		//顺子的花色数，这里设置为起码5花色，即五连顺
		int orderCnt = 5;
		for (int z = 0; z < p.length; z++) {
			System.out.print("玩家" + (z + 1) + "可以出的" + orderCnt + "张牌的同花顺：");
			for (int j = 0; j < p[z].length - orderCnt + 1; j++) { //注意：p[0].length - orderCnt + 1
				//定义牌的计数器，默认为1，也就当前有的这张牌
				int count = 1;
				//count1包括相同牌的数量，因为多副牌同花色的牌有多张，因此：count1 >= count
				int count1 = 1; 
				//设置起始的牌的花色，表示顺子从这张牌开始计算
				int curFlower = p[z][j];
				for (int k = j + 1; k < p[z].length; k++) {
					if (p[z][k] == curFlower) {
						//如果当前牌与起始牌一样，则直接跳过，这里只是总牌数+1
						count1++;
						continue;
					} else if (p[z][k]/13 == (curFlower + 1) / 13 && p[z][k] == curFlower + 1) {
						/**
						 * 注意判断条件：
						 * p[z][k]/13 == (curFlower + 1) / 13	===> 花色相同，表示是同花
						 * p[z][k] == curFlower + 1 			===> 当前牌比前一张牌大1，表示顺子
						 */
						//总牌数+1
						count1++;
						//起始牌+1，顺到一张牌了
						curFlower++;
						//顺子牌数+1
						count++;
					} else {
						break;
					}
				}
				if (count >= orderCnt) {
					//如果统计想同牌的张数比要求的张数多，则打印 orderCnt 张牌
					for (int v = j; v < j + count1; v++) {
						//如果当前牌不是第一张牌 并且 当前牌与前一张牌花色一样，则不需要在重复显示了，直接 continue
						if (v > j && p[z][v] == p[z][v - 1]) {
							continue;
						}
						int flower = p[z][v] / 13;
						int number = p[z][v] % 13;
						String s = flowerNames[flower] + numberNames[number];
						System.out.print(" " + s);
					}
					System.out.print(",");
					//将循环变量向前推进
					j += count - 1;
				}

			}
			System.out.println();
		}
	}
}
