package homework.d01.许江红;

import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("请输入年份：");
			int year=scanner.nextInt();
			if(year==0){
				System.out.println("退出程序！谢谢使用！");
				break;
			}
			System.out.println("请输入月份：");
			int month=scanner.nextInt();
			
			//求输入月份的天数
			int days=0;
			if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				days=31;
			}else if(month==4||month==6||month==9||month==11){
				days=30;
			}else if(year%4==0&&year%100!=0||year%400==0){
				days=29;
			}else{
				days=28;
			}
			
			//求年总天数
			int allDays=(year-1900)*365;
			for(int i=1900;i<year;i++){
				if(i%4==0&&i%100!=0||i%400==0){
					allDays++;
				}
			}
			//求年加月总天数
			for(int i=1;i<month;i++){
				int d;
				if(i==1||i==3||i==5||i==7||i==8||i==10||i==12){
					d=31;
				}else if(i==4||i==6||i==9||i==11){
					d=30;
				}else if(year%4==0&&year%100!=0||year%400==0){
					d=29;
				}else{
					d=28;
				}
				allDays+=d;
			}
			
			//计算输入的月份的一号是星期几
			int week=(allDays+1)%7;
			System.out.println("======================"+year+"年"+month+"月"+"======================");
			System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
			//打印空位
			for(int i=0;i<week;i++){
				System.out.print("\t");
			}
			//打印日历
			for(int i=1;i<=days;i++){
				System.out.print(i+"\t");
				
				if(week%7==6){
					System.out.println();
				}
				week++;
			}
			System.out.println();
		}

	}

}
