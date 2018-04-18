package homework.d01.贺银香.贺银香作业;

import java.util.Scanner;

public class Xt4 {

	public static void main(String[] args) {
		
		//1、编写程序输出1-100之间所有能被7整除的偶数。
		for(int i=2;i<100;i+=2){
			if(i%7==0){
				System.out.print(i+"、");
			}
		}
		
		
		
		System.out.println();
		
		//2、编写程序输出1-100之间所有质数之和。
		int s=0;
		for(int i=2;i<100;i++){
			boolean t=true;
			for(int j=2;j<i;j++){
				if(i%j==0){
					t=false;
					break;
					}
				}
			s+=t?i:0;
			}
		System.out.println("1-100质数之和为"+s);
		
		
		
		
		//3、在屏幕上输出一个n行的金字塔图案，n通过屏幕输入
		Scanner scanner =new Scanner(System.in);
		System.out.println("请输入一个数字:");
		int n=scanner.nextInt();
		for(int i=1;i<=n;i++){
			for(int k=i;k<=n-1;k++){
				System.out.print(" ");
			}
				for(int j=1;j<=2*i-1;j++){
				    System.out.print("*");
			}
			System.out.println();
		}                      
		
		
		
	/*	
		//4.斐数列1 1 2 3 5 8 13 21
		Scanner scanner =new Scanner(System.in);
		System.out.println("请输入n:");
		int n=scanner.nextInt();
		int i=1,j=1,sum=0,s=i+j;
		for(int k=2;k<=n;k++){
			s+=sum;
			sum=i+j;
			i=j;
			j=sum;
		}
		System.out.println("前n项之和为："+s);
		
		*/
		
		
		
		
		
		
		
		
		
	}

}
