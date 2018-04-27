package homework.d01.杨敏.作业和日历;

import java.util.Scanner;

public class S {
	public static void main(String[] args) {
		 for(int i=1;i<=100;i++) 
		  { 
		  	if(i%7==0 && i%2==0) 
		  		System.out.print(i+"  "); 
		  } 
		 System.out.println();
		  int sum=0;
		  for(int i=2;i<100;i++)
		   { 
		   		boolean isZs = true;
		   		for(int j=2;j<=i/2;j++)
		   		{
		   			if(i%j==0)
		   				{
		   					isZs = false;
		   					break;
		   				}
		   			}
		   		sum+=isZs? i : 0;
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
