package homework.d01.����;

import java.util.Scanner;

public class C {
	public static void main(String[] args) {

		System.out.print("������������߶ȣ�");
		Scanner hao = new Scanner(System.in);
		int n = hao.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				System.out.print("  ");
			}
			for (int j = 0; j < (i + 1) * 2 - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
