package homework.d01.杨敏.作业和日历;

import java.util.Scanner;

public class J {
	public static void main(String[] args) {
		
		

		System.out.println("请输入年份：" + "");// 判断闰年
		Scanner scanner1 = new Scanner(System.in);
		int year = scanner1.nextInt();
		

		System.out.println("请输入月份：" + " ");// 输入月份的天数
		Scanner scanner2 = new Scanner(System.in);
		int month = scanner2.nextInt();
		int days;
		if ((month < 8 && month % 2 != 0) || ((7 < month && month < 13) && month % 2 == 0))
			days=31;
		else if (((2 < month && month < 7) && month % 2 == 0) || ((8 < month && month < 12) && month % 2 != 0))
			days=30;
		else if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && month == 2)
			days=29;
		else
			days=28;

		int day = (year - 1900) * 365;

		for (int d = 1900; d < year; d++) {
			if ((d % 4 == 0 && d % 100 != 0) || d % 400 == 0)
				day++;
		}

		for (int y = 1; y < month; y++) {
			if ((y < 8 && y % 2 != 0) || ((7 < y && y < 13) && y % 2 == 0))
				day = day + 31;
			else if (((2 < y && y < 7) && y % 2 == 0) || ((8 < y && y < 12) && y % 2 != 0))
				day = day + 30;
			else if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && y == 2)
				day = day + 29;
			else
				day = day + 28;
		}
		System.out.println("总天数：" + day);
		int thisday;
		if ((month < 8 && month % 2 != 0) || ((7 < month && month < 13) && month % 2 == 0))
			thisday=31;
		else if (((2 < month && month < 7) && month % 2 == 0) || ((8 < month && month < 12) && month % 2 != 0))
			thisday=30;
		else if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && month == 2)
			thisday=29;
		else
			thisday=28;
		
		int  week=(day+1)%7;
		System.out.println("=============================="+year+"年"+month+"月"+"=============================");
		System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
		for(int i=0;i<week;i++)
		{	
			System.out.print("\t\t");
		}
			for(int w=1;w<=days;w++)
			{	
				System.out.print(w+"\t\t");
					if(week%7==6)
						System.out.println();
					week++;
			}
			System.out.println();
		}
	}

