package homework.d01.������.��������ҵ;

import java.util.Scanner;

public class Xt4 {

	public static void main(String[] args) {
		
		//1����д�������1-100֮�������ܱ�7������ż����
		for(int i=2;i<100;i+=2){
			if(i%7==0){
				System.out.print(i+"��");
			}
		}
		
		
		
		System.out.println();
		
		//2����д�������1-100֮����������֮�͡�
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
		System.out.println("1-100����֮��Ϊ"+s);
		
		
		
		
		//3������Ļ�����һ��n�еĽ�����ͼ����nͨ����Ļ����
		Scanner scanner =new Scanner(System.in);
		System.out.println("������һ������:");
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
		//4.�����1 1 2 3 5 8 13 21
		Scanner scanner =new Scanner(System.in);
		System.out.println("������n:");
		int n=scanner.nextInt();
		int i=1,j=1,sum=0,s=i+j;
		for(int k=2;k<=n;k++){
			s+=sum;
			sum=i+j;
			i=j;
			j=sum;
		}
		System.out.println("ǰn��֮��Ϊ��"+s);
		
		*/
		
		
		
		
		
		
		
		
		
	}

}
