package homework.d01.������;
import java.util.Scanner;

public class java5 {
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
	    System.out.println("���������");
	    System.out.println("�������·�");
	    int year =scanner.nextInt();
		
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			System.out.println(year + "��������");
		} else {
			System.out.println(year + "�겻������");
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
		System.out.println(month+"����" + day + "��");

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
		System.out.println("����"+ ++week);

		System.out.println("==================="+year+"��"+month+"��===================");
	System.out.println("������\t����һ\t���ڶ�\t������\t������\t������\t������");
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
