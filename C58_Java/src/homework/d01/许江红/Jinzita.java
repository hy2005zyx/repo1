package homework.d01.许江红;

import java.util.Scanner;

public class Jinzita {

	public static void main(String[] args) {
		System.out.println("请输入金字塔的高度： ");
		Scanner scanner=new Scanner(System.in);
		int n=scanner.nextInt();
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-i-1;j++)
			{
				System.out.print(" ");
			}
			for(int j=0;j<(i+1)*2-1;j++)
			{
				System.out.print("*");
			}
			System.out.println();
		}

	}

}
