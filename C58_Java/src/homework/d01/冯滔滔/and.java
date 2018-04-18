package homework.d01.冯滔滔;

public class and {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=0;
		for(int i=0; i<=100;i++){
			boolean isZs =true;
			for(int j=2;j<i/2;j++){
				if(i %j ==0){
					isZs = false;
					break;
				}
			}
			a +=isZs ?i :0;
		}
		System.out.println("100以内的质数之和是："+a);

	}

}
