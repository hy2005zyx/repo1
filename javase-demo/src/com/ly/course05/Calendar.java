package com.ly.course05;

import java.util.Scanner;

/**
 * 日历计算，给出一个年份和月份，生成对应的日期
 * @author hy2005zyx
 *
 */
public class Calendar {

	public static void main(String[] args) {
		//定义月份的天数数组，可以替代if语句或switch语句
		int[] dayOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		//创建屏幕录入对象
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("请输入年份：");
			int year = scanner.nextInt();
			if(year <= 0) {
				System.out.println("谢谢使用，再见！");
				break;
			}
			System.out.print("请输入月份：");
			int month = scanner.nextInt();
			if(month <= 0) {
				System.out.println("谢谢使用，再见！");
				break;
			}

			/**
			 * 获取1900.1.1到指定月前一天的天数 = 365 * 年数 + 闰年数 + 本年已过天数
			 */
			//365 * 年数
			int allDays = (year - 1900) * 365;
			// + 闰年数 
			for (int i = 1900; i < year; i++) {
				if (i % 400 == 0 || i % 4 == 0 && i % 100 != 0) {
					allDays++;
				}
			}
			// + 本年已过天数
			for (int i = 1; i < month; i++) {
				allDays += dayOfMonth[i];
				if (month == 2) {
					if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
						allDays++;
					}
				}
			}

			/**
			 * 计算该月第一天的星期数，所有天数 模 7 + 1
			 */
			int weeks = allDays % 7 + 1;

			/**
			 * 获取该月天数
			 */
			int thisDays = dayOfMonth[month];
			//如果是2月，又是闰年，则该月天数 + 1
			if (month == 2) {
				if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
					thisDays++;
				}
			}

			System.out.println("=======================" + year + "年" + month + "月=====================");
			System.out.println("星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期天");
			/**
			 * 打印月前空的星期
			 */
			for (int i = 1; i < weeks; i++) {
				System.out.print("\t");
			}

			/**
			 * 打印日历
			 */
			for (int i = 1; i <= thisDays; i++, weeks++) {
				System.out.print(i);
				System.out.print("\t");
				if (weeks % 7 == 0) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println("====================================================");
			System.out.println();
		}
		//关闭屏幕录入对象
		scanner.close();
	}

}
