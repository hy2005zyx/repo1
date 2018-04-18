package homework.d01.杨茜;
import java.net.StandardSocketOptions;
import java.util.Scanner;

public class Calendar {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
		System.out.println("\n请输入一个年份：");
		int year = scanner.nextInt();
		System.out.println("请输入一个月份：");
		int month = scanner.nextInt();
		
		//求闰年
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			
		}
		
		//求输入月份的天数
		//1,3,5,7,8,10,12=31    4,6,9,11=30  2=28/29
		int days ;
		if(month == 1 || month == 3|| month == 5|| month == 7|| month == 8|| month == 10|| month == 12) {
			days = 31;
		}
		else if( month == 4|| month == 6|| month == 9|| month == 11) {
			days = 30;
		}
		else if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			days = 29;
		}
		else {
			days = 28;
		}
		
		//过去的总天数
		//1、通过年份求出前n年的总天数
		//2、求之前的闰年年数，加上前面的总天数
		//3、加上本年已经过去的月份包含的天数
		int allDays;
		allDays = (year - 1900) * 365;
		
		for(int b = 1990; b < year; b++) {
			if(b % 4 == 0 && b % 100 != 0 || b % 400 == 0) {
				allDays ++;
			}
		}
		
		for(int i = 1; i < month; i++) {
			int d;
			if(i == 1 || i == 3|| i == 5|| i == 7|| i == 8|| i == 10|| i == 12) {
				d = 31;
			}
			else if( i == 4|| i == 6|| i == 9|| i == 11) {
				d = 30;
			}
			else if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				d = 29;
			}
			else {
				d = 28;
			}
			allDays += d;
		}
		
		//计算该月第一天为星期几
		int weekFirst = allDays % 7 + 2;
		System.out.println("=====================" + year + "年" + month + "月  =======================");
		System.out.println("星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期日\t");
		
		for(int i = 1 ; i < weekFirst ; i++) {
			System.out.print("\t");
		}
		for(int i = 1 ; i <= days ; i++, weekFirst++) {
			allDays ++;
			System.out.print(i + "\t");
			if(allDays % 7 == 6) {
				System.out.println();
				
			}
		}


		
	}
	}
		
}
		
		