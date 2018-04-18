package homework.d01.杨敏.作业和日历;

import java.util.Scanner;

public class S {
	public static void main(String[] args) {
		 for(int i=1;i<=100;i++) 
		  { 
		  	if(i%7==0) 
		  		System.out.println(i); 
		  } 
		  int sum=0;
		  for(int j=1;j<=100;j++)
		   { 
		   	for(int k=2;k<j/2;k++) 
		   		if(j%1==0&&j%k!=0)
		   		 {
		 				sum=sum+j; 
		  		} 
		  } 
		  System.out.println(sum);
		 
		  System.out.println("请输入金字塔行数："+ "");
		 Scanner scanner = new Scanner(System.in);
		  int n= scanner.nextInt(); 
		 for(int g=0;g<n;g++) 
		  { 
			 for(int a=0;a<n-g-1;a++)
		 				System.out.print("    ");
		  	for(int b=0;b<(g+1)*2-1;b++) 
		 				System.out.print(" * ");
		  	System.out.println();
		   }
		 
	}
	
		
	
}
