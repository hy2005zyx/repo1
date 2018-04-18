package homework.d01.冯滔滔;

import java.util.Scanner;

public class riqi {

	public static void main(String[] args) {
		// 求闰年
		Scanner m = new Scanner(System.in);
		System.out.println("请输入年份：");
		System.out.println("请输入月份：");
	    while (true){
	    	int year = m.nextInt();
	    	int month = m.nextInt();
		int days;

		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {

		}
		// 球输入的月份的天数
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			days = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			days = 30;
		} else if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			days = 29;
		} else {
			days = 28;
		}

		int a = (year - 1900) * 365;
		for (int i = 1900; i < year; i++);
		{
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				a++;
			}
			for(int i=1;i<month;i++){
				int d;
			if(i == 1 || i ==3|| i ==5|| i ==7|| i ==8|| i ==10|| i ==12){
		    	   d=31;
		       }else if( i == 4 || i ==6|| i ==9|| i ==11 ){
		    	   d=30;
		       }else if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
		    	   d=29;
		       }else{
		    	   d=28;
		       }
			a +=d;
		}
		
			int j=a%7+2;
			System.out.println("星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期日");
			
			for (int i=1;i<j;i++){

				System.out.print("\t");
			}
			
			//打印日期
			for(int i=1;i<=days;i++,j++){
				    System.out.print(i+"\t");
				if (j % 7  ==  0){
					System.out.println();
					}  
				}
			System.out.println();
			System.out.println("================================");
			System.out.println();
			}
			}

	
		
		
	}

}
