package homework.d01.����;
import java.net.StandardSocketOptions;
import java.util.Scanner;

public class Java05 {
	public static void main(String[] args) {
		
		//1-100֮�������ܱ�7������ż��
		
		for(int i = 1; i < 100; i++) {
			if (i % 7 == 0 && i % 2 == 0) {
				System.out.println(i);
			}
		}
		

		//1-100֮�������֮��
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
		
		
		
		//����Ļ�����һ��n�еĽ�����ͼ����nͨ����Ļ����
		
		/*System.out.println("������������ĸ߶ȣ�");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for(int i = 0; i < n ; i++) {
			//��ӡ�ո�
			for(int j = 0; j < n - i - 1; j++) {
				System.out.print(" ");
			}
			//��ӡ�Ǻ�
			for(int j = 0; j < (i + 1) * 2 - 1; j++) {
				System.out.print("*");
			}
			//����
			System.out.println();
		}*/
			
	}
}
