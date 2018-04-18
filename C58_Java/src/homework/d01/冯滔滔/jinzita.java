package homework.d01.·ëÌÏÌÏ;

import java.util.Scanner;

public class jinzita {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ÇëÊäÈë½ğ×ÖËşµÄ²ãÊı£º");
		Scanner sc = new Scanner(System.in);
		int a=sc.nextInt();
		for(int i=0; i<a; i++){
			for(int j=0; j<a-i-1; j++){
			System.out.print(" ");
		}
		for(int j=0;j<(i+1)*2-1; j++){
			System.out.print("*");
		}
		System.out.println();
		
		}
	}

}
