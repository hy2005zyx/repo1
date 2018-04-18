package homework.d01.申明;

import java.util.Scanner;

public class java05 {
   public static void main(String[] args) {
	 /*int i=2,j=2,sum=0;
	 for(i=2;i<=99;i+=2){
		 boolean isZs=true;	 
	     for(j=2;j<=i/2;j++){
	         if(i%j==0){
	         isZs = false;
	         break;}
	     }
	     sum+=isZs ? i : 0;
	  }
	 System.out.println(sum);*/
	   
	   
	   /*int i=0;
	   for(i=0;i<=100;i+=2){
		   if(i % 7 == 0){
			   System.out.println(i);
		   }
	   }*/
	   
	   /*Scanner scanner=new Scanner(System.in);
	   int i=scanner.nextInt();
	   for(int j=0;j<i;j++){
		   for(int k=0;k<j-i-1<k++){
			   System.out.println(" ");
		   }
		   for(int k=0;k<(j+1)*2-1;k++){
			   System.out.println("*");
		   }
		   System.out.println();
	   }*/
	   while(true){
	   Scanner scanner=new Scanner(System.in);
	   int i=scanner.nextInt();
	   int m=scanner.nextInt();
       int days=0,j=1900;
           if(m == 1 || m== 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12){
			   days=31;
		   }else if(m == 4 || m== 6 || m == 9 || m == 11 ){
			   days=30;
		   }else if(i % 4 == 0 && i % 100 !=0 || i % 400 == 0){
			   days=29;
		   }else{
			   days=28;
		   }
           int sum=(i-1900)*365;
           for(j=1900;j<i;j++){
        	   if(j % 4 == 0 && j % 100 !=0 || j % 400 == 0){
            	   sum += 1;
               }
           }
	   /*}*/
           
           for(int b=1;b<m;b++)//b为当年月份
           {
        	   if(b == 1 || b== 3 || b == 5 || b == 7 || b == 8 || b == 10 || b == 12){
    			   days=31;
    		   }else if(b == 4 || b== 6 || b == 9 || b == 11 ){
    			   days=30;  
    		   }else if(b % 4 == 0 && b % 100 !=0 || b % 400 == 0){
    			   days=29; 
    		   }else{
    			   days=28; 
    		   } 
        	   sum+=days;
           }
         int week =(sum+1) % 7;
         System.out.println("周一\t周二\t周三\t周四\t周五\t周六\t周日\t");
          for(int d = 0;d< week;d++){
        	  System.out.println("\t");
          }
         for(int d = 1;d <= days;d++){
        	 System.out.print(d+"\t");         
         if(week % 7 == 6){
             System.out.println();}
             week++;
         }
         System.out.println();
        
          	   
	    }	   
}
}
