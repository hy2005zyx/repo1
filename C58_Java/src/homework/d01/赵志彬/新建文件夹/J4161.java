package homework.d01.��־��.�½��ļ���;

import java.util.Scanner;

public class J4161 {
	public static void main(String[] args) {
		//1.��д�������1--100֮�������ܱ�7������ż��
		for(int i=2;i<100;i+=2) {
			if(i%7==0) {
				System.out.print(i+",");
			}
		}
		

		
		//2.��д�������1--100֮����������֮��
		/*int sum=0;
		for(int i=2;i<100;i++) {
			boolean isZs =true;
			for(int j=2;j<i/2;j++) {
				if(i%j==0) {
					isZs=false;
					break;
				}
			}
			sum+=isZs?i:0;
		}
		System.out.println("100���ڵ�����֮���ǣ�"+sum);*/
		
		
		
//		3.����Ļ�����һ��n�еĽ�����ͼ����nͨ����Ļ����
		
		/*System.out.println("������������ĸ߶ȣ�");
		Scanner scanner=new Scanner(System.in);
		int n=scanner.nextInt();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n-i;j++) {
			System.out.println();
			}
			for(int j=0;j<n-i-1;j++) {
				System.out.print("");
				}
			for(int j=0;j<(i+2)*2-1;j++) {
				System.out.print("*");
				}
			System.out.println();
		}*/
	}
}
