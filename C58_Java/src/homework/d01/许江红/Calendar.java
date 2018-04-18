package homework.d01.����;

import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("��������ݣ�");
			int year=scanner.nextInt();
			if(year==0){
				System.out.println("�˳�����ллʹ�ã�");
				break;
			}
			System.out.println("�������·ݣ�");
			int month=scanner.nextInt();
			
			//�������·ݵ�����
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
			
			//����������
			int allDays=(year-1900)*365;
			for(int i=1900;i<year;i++){
				if(i%4==0&&i%100!=0||i%400==0){
					allDays++;
				}
			}
			//�������������
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
			
			//����������·ݵ�һ�������ڼ�
			int week=(allDays+1)%7;
			System.out.println("======================"+year+"��"+month+"��"+"======================");
			System.out.println("������\t����һ\t���ڶ�\t������\t������\t������\t������");
			//��ӡ��λ
			for(int i=0;i<week;i++){
				System.out.print("\t");
			}
			//��ӡ����
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
