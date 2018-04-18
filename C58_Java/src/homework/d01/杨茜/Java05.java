package homework.d01.杨茜;
import java.net.StandardSocketOptions;
import java.util.Scanner;

public class Java05 {
	public static void main(String[] args) {
		
		//1-100之间所有能被7整除的偶数
		
		for(int i = 1; i < 100; i++) {
			if (i % 7 == 0 && i % 2 == 0) {
				System.out.println(i);
			}
		}
		

		//1-100之间的质数之和
		int j = 2;
		int k ;
		int i;
		for( i = 2; i < 100; i++) {
			while( i % j != 0) {
				j++;
			}
			if(j == i) {	
			}
		}
		k = i + j;
		System.out.println(k);
		
		
		
		//在屏幕上输出一个n行的金字塔图案，n通过屏幕输入
		
		/*System.out.println("请输入金字塔的高度：");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for(int i = 0; i < n ; i++) {
			//打印空格
			for(int j = 0; j < n - i - 1; j++) {
				System.out.print(" ");
			}
			//打印星号
			for(int j = 0; j < (i + 1) * 2 - 1; j++) {
				System.out.print("*");
			}
			//换行
			System.out.println();
		}*/
			
	}
}
