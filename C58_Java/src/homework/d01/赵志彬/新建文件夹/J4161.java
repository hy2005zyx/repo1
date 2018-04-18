package homework.d01.赵志彬.新建文件夹;

import java.util.Scanner;

public class J4161 {
	public static void main(String[] args) {
		//1.编写程序输出1--100之间所有能被7整除的偶数
		for(int i=2;i<100;i+=2) {
			if(i%7==0) {
				System.out.print(i+",");
			}
		}
		

		
		//2.编写程序输出1--100之间所有质数之和
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
		System.out.println("100以内的质数之和是："+sum);*/
		
		
		
//		3.在屏幕上输出一个n行的金字塔图案，n通过屏幕输入
		
		/*System.out.println("请输入金字塔的高度：");
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
