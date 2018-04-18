package homework.d01.黄大林;

import java.util.Scanner;

public class o {
	public static void main(String args[]) {
		int Y;
		int M;
		int alldays;
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入年份：");
		Y = scanner.nextInt();
		System.out.println("请输入月份：");
		M = scanner.nextInt();
		alldays = (Y - 1900) * 365;
		for (int i = 1900; i <= Y; i++) {
			if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
				alldays++;
			}
		}
		for (int i = 1; i < M; i++) {
			if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
				alldays += 31;
			} else if (i == 4 || i == 6 || i == 9 || i == 11) {
				alldays += 30;
			} else if (Y % 4 == 0 && Y % 100 != 0 || Y % 400 == 0) {
				alldays += 29;
			} else {
				alldays += 28;
			}
		}
		int empty = (alldays + 1) % 7;
		System.out.println("星期天\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
		int thismonthdays;
		if (Y == 1 || Y == 3 || Y == 5 || Y == 7 || Y == 8 || Y == 10 || Y == 12) {
			thismonthdays = 31;
		} else if (Y == 4 || Y == 6 || Y == 9 || Y == 11) {
			thismonthdays = 30;
		} else if (Y % 4 == 0 && Y % 100 != 0 || Y % 400 == 0) {
			thismonthdays = 29;
		} else {
			thismonthdays = 28;
		}
		for (int i = 1; i <= empty; i++) {
			System.out.print("\t");
		}
		for (int i = 1; i <= thismonthdays; i++) {
			System.out.print(i + "\t");
			if (empty % 7 == 6) {
				System.out.println();
			}
			empty++;

		}
		System.out.println();
	}

}
