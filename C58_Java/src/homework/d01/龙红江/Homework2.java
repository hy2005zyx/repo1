package homework.d01.���콭;

import java.util.Scanner;

public class Homework2 {
	public static void main (String [] args) 
	{
		/*int j;
		int sum= 0;
        for (int i = 2; i <= 100; i++) // 1��������������ֱ�Ӵ�2��ʼѭ��
        {
            j = 2;
            while (i % j != 0) {
                j++; // ����2��i�������Ƿ��ܱ�i�������粻�ܾ��Լ�
            }
            if (j == i) // ���б�����������ʱ���ж����ǲ�������
            {
                sum+=i;
            }
        }
        System.out.println("��Ϊ��"+sum);*/
        
        System.out.println("����������:");
		Scanner scanner = new Scanner (System .in);
		int line =scanner .nextInt();
		for (int i = 1; i <= line; i++) {  
            // ���ƴ�ӡ�ո�  
            for (int j = 1; j <= line - i; j++) {  
                System.out.print(" ");  
            }  
            // ���ƴ�ӡ*  
            for (int k = 1; k <= 2 * i - 1; k++) {  
                System.out.print("*");  
            }  
            System.out.println();
		}
    }
}
		
			
			
			
		
	

