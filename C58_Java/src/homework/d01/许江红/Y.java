package homework.d01.����;


public class Y {

	public static void main(String[] args) {
		int sum=0;
		for(int i=2;i<100;i++)
		{
			boolean iszhishu=true;//Ĭ��������
			for(int j=2;j<i/2;j++)
			{
				if(i%j==0)
				{
					iszhishu=false;
					break;
				} 
			}
			 
			sum+=iszhishu?i:0; 
		}
		System.out.println("100���ڵ�����֮����:"+sum);
	}

}
