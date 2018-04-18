package homework.d01.王钊驰.新建文件夹.Java05.src;

import java.util.Scanner;

public class Java05 {
public static void main(String[] args) {
	for(int i=1;i<101;i++) {
		if(i%7==0 && i%2==0) {
			System.out.print("\t"+i);
		}
	}
	
	int i,j;
	int sum=0;
	for(i = 2; i<=100; i++) {
		boolean isZs=true;
		for(j = 2;j<=i/2 ;j++) {
			if(i%j == 0) {
				isZs=false;
				break;
			}
				
		}
			sum += isZs ? i : 0;
			
	}
	System.out.println("100以内质数和是:"+sum);
	System.out.println("===================");
	/*
		System.out.print("请输入金字塔高度：");
		Scanner s =new Scanner(System.in);
		int n = s.nextInt();
		for(int i = 0;i<n; i++){
			for(int j = 0;j < n - i - 1;j++) {
				System.out.print(" ");
			}
			for(int j = 0; j<(i + 1)*2 - 1;j++) {
				System.out.print("*");
				}
			System.out.println();
		}*/
}
}
