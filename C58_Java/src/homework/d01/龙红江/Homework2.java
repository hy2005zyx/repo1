package homework.d01.龙红江;

import java.util.Scanner;

public class Homework2 {
	public static void main (String [] args) 
	{
		/*int j;
		int sum= 0;
        for (int i = 2; i <= 100; i++) // 1不是素数，所以直接从2开始循环
        {
            j = 2;
            while (i % j != 0) {
                j++; // 测试2至i的数字是否能被i整除，如不能就自加
            }
            if (j == i) // 当有被整除的数字时，判断它是不是自身
            {
                sum+=i;
            }
        }
        System.out.println("和为："+sum);*/
        
        System.out.println("请输入行数:");
		Scanner scanner = new Scanner (System .in);
		int line =scanner .nextInt();
		for (int i = 1; i <= line; i++) {  
            // 控制打印空格  
            for (int j = 1; j <= line - i; j++) {  
                System.out.print(" ");  
            }  
            // 控制打印*  
            for (int k = 1; k <= 2 * i - 1; k++) {  
                System.out.print("*");  
            }  
            System.out.println();
		}
    }
}
		
			
			
			
		
	

