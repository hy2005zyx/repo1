
package homework.d01.����;

import java.util.Scanner;

public class CalendarMonday {
	public static void main(String [] args)
	{
		int []dayOfMonth= {0,31,28,31,30,31,30,31,31,30,31,30,31};
		Scanner scanner=new Scanner(System.in);
		while(true)
		{
			System.out.print("���������");
			int year=scanner.nextInt();
			if(year<=0)
			{
				System.out.println("�ݰ�");
				break;
			}
			System.out.print("�������·�");
			int month=scanner.nextInt();
			if(month<=0)
			{
				System.out.println("�ݰ�");
				break;
			}
			
			int allDays=(year - 1900)*365;
			for(int i=1900;i<year;i++)
			{
				if(i%400==0||i%4==0&&i%100!=0)
				{
					allDays++;
				}
			}
			for(int i=1;i<month;i++)
			{
				allDays+=dayOfMonth[i];
				if(i==2)
				{
					if(year%400==0||year%4==0&&100!=0)
					{
						allDays++;
					}
				}
			}
			int weeks=allDays%7+1;
			int thisDays=dayOfMonth[month];
			if(month==2)
			{
				if(year%400==0||year%4==0&&100!=0)
				{
					thisDays++;
				}
			}
			System.out.println("=============="+year+"��"+month+"��=============");
			System.out.println("����һ\t���ڶ�\t������\t������\t������\t������\t������");
			
			for(int i=1;i<weeks;i++)
			{
				System.out.print("\t\t");
			}
			for(int i=1;i<=thisDays;i++,weeks++)
			{
				System.out.print(i+" \t\t");
				if(weeks%7==0)
					System.out.println();
			}
			System.out.println();
			System.out.println("=================================");
			System.out.println();
			
			
			
		}
	}
	

}



