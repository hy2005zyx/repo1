package homework.d01.王钊驰.新建文件夹.Java06.src;

import java.util.Scanner;

public class Java06 {
   public static void main(String[] args) {
	   Scanner s = new Scanner(System.in);
	   while(true) {
		   System.out.println("请输入年份：");
		   int year = s.nextInt();
		   System.out.println("请输入月份：");
		   int month = s.nextInt(); 
	   
   
     int days = 0;
	 if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){
		 days = 31;
	 }else if(month == 4 || month == 6 || month == 9 || month == 11 ){
		 days = 30;
	 }else if(year%4==0 && year%100!=0 || year%400==0) {
		 days = 29;
	 }else {
	     days = 28;
	 }
	//通过年份求出前N年的总天数（不含闰年多出的天数）
	int allDays = (year - 1900) * 365;
	//求之前的闰年年数，加上前面的总天数
	for(int i = 1900 ; i<year; i++) {
		if(i%4==0 && i%100!=0 || i%400==0) {
			allDays ++;
		}
	}
	//加上本年已经过去的月份包含的天
	for(int i = 1;i<month;i++) {
		int d;
		 if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12 ){
			 d = 31;
		 }else if(i == 4 || i == 6 || i == 9 || i == 11 ){
			 d = 30;
		 }else if(year%4==0 && year%100!=0 || year%400==0) {
			 d = 29;
		 }else {
		     d = 28;
		 }
		 allDays += d;
		 }
	//计算星期几
	int week = (allDays+1)%7 ;
	System.out.println("============================" + year + "年" + month + "月============================");
	System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
	
	//打印空位
	for(int i = 0; i<week; i++) {
		System.out.print("\t");
	}
	
	for(int i = 1; i<=days; i++) {
		System.out.print(i+"\t");
		if( week %7==6) {
			System.out.println();
		}
		week++;
	}
	System.out.println();
}

}
}