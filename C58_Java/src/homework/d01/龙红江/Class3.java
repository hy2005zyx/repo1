package homework.d01.龙红江;

import java.util.Scanner;

public class Class3 {
	public static void main (String [] args )
	{
		Scanner scanner = new Scanner (System .in);
		while (true) {
			System.out.println("请输入年份：");
		int year=scanner .nextInt();
			System.out.println("请输入月份：");
		int month=scanner .nextInt();
		int day;
			if (month ==1||month ==3||month ==5||month ==7||month ==8||month ==10||month ==12)
			{
				day =31;
			}
			else if(month ==4||month ==6||month ==9||month ==11)
			{
				day=30;
			}
			else if(year%100!=0&year%4==0|year%400==0)
			{
				day=29;
			}
			else 
			{
				day = 28;
			}
			int alldays=(year- 1900)*365 ;
			for (int i=1900;i<year;i++) {
				if(i%100!=0&i%4==0|i%400==0)
					alldays++;
			}
			int m=month;
			int days=day;
			while (m>0) {
				if (m ==1||m ==3||m ==5||m ==7||m ==8||m ==10||m ==12)
				{
					days =31;
				}
				else if(m ==4||m ==6||m ==9||m ==11)
				{
					days=30;
				}
				else if(year%100!=0&year%4==0|year%400==0)
				{
					days=29;
				}
				else 
				{
					days = 28;
				}
				alldays+=days;
				m--;
			}			
			int week= alldays % 7+1;//计算星期数
			System.out.println("======================="+year+"年"+month+"月"+"=======================");
			System.out.println("星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期日");
			for (int i =0;i<week;i++) 
			{
				System.out.print("\t");
			}
			for (int d =1;d<=day;d++,week++)
			{
				System.out.print(d+"\t");
				if (week %7==6) 
					System.out.println();
				}
				System.out.println();
				System.out.println("=======================================================");
			}
	}
}

	

