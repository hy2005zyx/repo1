package homework.d01.曹宇;

public class A {
	public static void main(String[] args) {
		
		int sum=0;
		for(int i=2; i<100;i++)
		{
			boolean b =true;
			for(int j=2;j<=i/2;j++)
			{

				if(i %j ==0)
				{
					b = false;
					break;
				}
			 }
			sum +=b ?i :0;
		}
		System.out.println("100以内的质数之和是："+sum);
	}
}
