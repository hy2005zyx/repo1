package homework.d01.王菅物;
import java.util.Scanner;

public class java5 {
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
	    System.out.println("请输入年份");
	    System.out.println("请输入月份");
	    int year =scanner.nextInt();
		
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			System.out.println(year + "年是闰年");
		} else {
			System.out.println(year + "年不是闰年");
		}

		Scanner scanner1 =new Scanner(System.in);
	     
	    int month =scanner1.nextInt();
		
		int d = 0;
		int day = 0;
		if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
			day = 31;
		} else if (month == 4 | month == 6 | month == 9 | month == 11) {
			day = 30;
		} else if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			day = 29;
		} else {
			day = 28;
		}
		System.out.println(month+"月有" + day + "天");

		for (int i = 0; i < month; i++) {
			if (i == 1 | i == 3 | i == 5 | i == 7 | i == 8 | i == 10 | i == 12) {
				day = 31;
			} else if (i == 4 | i == 6 | i == 9 | i == 11) {
				day = 30;
			} else if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				day = 29;
			} else {
				day = 28;
			}
			d += day;
		}

		int oneday = 1900;
		int aday;
		aday = (year - oneday) * 365 + ((year - 1 - oneday) / 4) + d;
		System.out.println(aday);

		int week = aday % 7;
		System.out.println("星期"+ ++week);

		System.out.println("==================="+year+"年"+month+"月===================");
	System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
	for (int i=0;i<(week%7);i++) {
		System.out.print("\t");}
	
	for(int i=1;i<=day;i++) {
		System.out.print(i+"\t");
		if(week%7==6) {
			System.out.println();
		}
		week++;
	}
	    System.out.println();
	}
}
