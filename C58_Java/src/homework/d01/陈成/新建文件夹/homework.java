package homework.d01.�³�.�½��ļ���;

import java.util.Scanner;

public class homework {
	public static void main(String[] args) {
		/**
		 * 1.��д�������1-100֮��������ܱ�7������ż����
		 */
		System.out.println("1-100֮�������ܱ�7������ż��:");
		for(int i=2;i<100;i += 2) {
			if(i%7==0) {
				System.out.print(i+",");
			}
		}
		System.out.println();
		
		System.out.println("=================�����ķָ���==============");
		/**
		 * 2.��д�������1-100֮�����е�����֮��
		 */
		int sum=0;
		/**
		 * ��С��������2,���Դ�2��ʼѭ����
		 */
		for(int i=2;i<100;i++) {
			boolean isZs=true;
			/**
			 * �ؼ����ж���������ѭ����ע�������˳�����
			 * 1�����������жϣ�����2��ʼ
			 */
			for(int j=2;j<=i/2;j++) {
				if(i%j==0) {
					/**
					 * ����������������
					 */
					isZs=false;
					break;
				}
			}
			sum+=isZs?i:0;
			}
		System.out.println("100��������֮����:"+sum);
		
		System.out.println("=================�����ķָ���==============");
		
		/**
		 * 3.����Ļ�����һ��n�еĽ�����ͼ����nͨ����Ļ����
		 */
		System.out.println("������������ĸ߶�:");
		Scanner scanner = new Scanner(System.in);
		int n=scanner.nextInt();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n-i-1;j++) {
				System.out.print(" ");
			}
			for(int j=0;j<(i+1)*2-1;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
