package homework.d01.����;
import java.net.StandardSocketOptions;
import java.util.Scanner;

public class Calendar {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
		System.out.println("\n������һ����ݣ�");
		int year = scanner.nextInt();
		System.out.println("������һ���·ݣ�");
		int month = scanner.nextInt();
		
		//������
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			
		}
		
		//�������·ݵ�����
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
		
		//��ȥ��������
		//1��ͨ��������ǰn���������
		//2����֮ǰ����������������ǰ���������
		//3�����ϱ����Ѿ���ȥ���·ݰ���������
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
		
		//������µ�һ��Ϊ���ڼ�
		int weekFirst = allDays % 7 + 2;
		System.out.println("=====================" + year + "��" + month + "��  =======================");
		System.out.println("����һ\t���ڶ�\t������\t������\t������\t������\t������\t");
		
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
		
		