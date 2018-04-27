package homework.d01.王菅物;
import java.util.Scanner;

public class lianxi4 {
          public static void main(String[] args) {
			for(int i = 1;i<101;i++) {
				if(i%7==0&&i%2==0) {System.out.println(i);}
			}
        	  
        	int sum=0;
            for(int i=2;i<101;i++) {
            	
            		if(i%2!=0|i==2) {sum +=i;
            			System.out.println(i+"");System.out.println("质数合为："+sum);
            		 
            	}
            }//2501
        	  
        	Scanner scanner =new Scanner(System.in);
  		    System.out.println("请输入行数：");
  	    	int  n= scanner.nextInt();
  	    	int i,j;
			for(i=0;i<n;i++) {
			         for(j=0;j<n-1-i;j++){
			        	 System.out.print(" ");
			        	 }
			         for(j=0;j<(i+1)*2-1;j++){			        	 
		    
			        	 			        	 			        	 			        	 			        	 			        	 			 			        	
			        	 System.out.print("*");
			        	 }
			         System.out.println();
			}
		         }
        	  
        	  

          }  

