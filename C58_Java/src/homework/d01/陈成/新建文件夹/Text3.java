package homework.d01.陈成.新建文件夹;

import java.util.Scanner;

public class Text3 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner (System.in);
				
		while(true) {
		System.out.println("请输入年份:");
		int year = scanner.nextInt();
		System.out.println("请输入月份:");
		int month = scanner.nextInt();
		
		//求闰年
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
		
		}
		
		//求输入的月份的天数
		 int days = 0;
		 if(month == 1||month == 3||month == 5||month == 7
				 ||month == 8||month == 10||month == 12) {days = 31;}
		 else if(month == 4||month == 6||month == 9||month == 11) {days = 30;}
		 else if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){days = 29;}
		 else {days = 28;}
		 
		 //求总的过去的天数
		 
		 //1.通过年份求出前N年的年数
		 
		 int allDays = (year - 1990)*365;
		 
		 //2.求之间的闰年天数加上前N年的总天数
		 
		 for(int i=1900;i<year;i++)
		 {
			 if(i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
				 allDays ++;
			 }
		 }
		 
		 //3.加上本年已经过去的月份包含的天数
		 
		 for(int i=1;i<month;i++) {
			 int d;
			 if(i == 1||i == 3||i == 5||i == 7
					 ||i == 8||i == 10||i == 12) {d = 31;}
			 else if(i == 4||i == 6||i == 9||i == 11) {d = 30;}
			 else if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){d = 29;}
			 else {d = 28;}
			 allDays += d;
		 }
		 
		 //计算星期几
		 int week = allDays % 7;
		 
		 System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
		 System.out.println("");
		 
		 //打印空位
		 for(int i=0;i<week;i++) {
			 System.out.print("\t");
		 }
		 
		 //打印
		 for(int i=1;i<=days;i++,week++) {
			 System.out.print(i+"\t");
			 if(week % 7 == 6) {
				 System.out.println();
			 }
		 }
	  }
	}
}
