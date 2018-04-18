package homework.d01.许江红;


public class X {

	public static void main(String[] args) {
		System.out.println("1-100之间所有能被7整除的偶数：");
		for(int i=2;i<100;i+=2)
		{
			if(i%7==0)
			{
				System.out.print(i+",");
			}			
		}
		System.out.println();

	}

}
