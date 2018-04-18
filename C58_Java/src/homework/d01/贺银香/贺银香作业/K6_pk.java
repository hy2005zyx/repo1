package homework.d01.贺银香.贺银香作业;

import java.util.Random;

public class K6_pk {

	public static void main(String[] args) {
		int[] poker=new int[52*2];
		String[] types={"黑桃","红桃","梅花","方块"};
		String[] flower={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		
		//给扑克赋值
		for(int i=0;i<poker.length;i++){
			poker[i]=i%52;
		}

		//输出52张牌，每个花色占一行
		for(int i=0;i<poker.length;i++){
			//得到行号，根据行号变成花色，多副扑克情况下需取模4（即%4）
			int type=poker[i]/13%4;
			//打印牌号
			System.out.print(types[type]+flower[poker[i]%13]+"\t");
			//换行
			if((i+1)%13==0){
				System.out.println();
			}
			
		}
		
		
		/*
		//Java的随机函数：Random();
		Random random=new Random();
		random.nextInt(100);//返回一个随机整数
		*/
		
		//使用随机函数将牌打乱
		Random random=new Random();
		for(int i=0;i<poker.length;i++){
		  //当前牌号：poker[i]. 随机生成牌号：
		  int newPoint=random.nextInt(poker.length);
		  //System.out.println(newPoint);
		  int t;
		  t=poker[i];
		  poker[i]=poker[newPoint];
		  poker[newPoint]=t;
		}
		
		System.out.println("===================================分=============割=============线===================================");
		
		//输出52张牌，每个花色占一行
		for(int i=0;i<poker.length;i++){
			//得到行号，根据行号变成花色，多副扑克情况下需取模4（即%4）
			int type=poker[i]/13%4;
			//打印牌号
			System.out.print(types[type]+flower[poker[i]%13]+"\t");
			//换行
			if((i+1)%13==0){
				System.out.println();
			}
					
		}
		
		System.out.println("===================================分================割================线===================================");
		
		//发牌
		//使用数组来存放四个玩家,四个玩家手里有1/4的扑克的牌
		int[][] player=new int[4][poker.length/4];
		for(int i=0;i<poker.length;i++){
			player[i%4][i/4]=poker[i];
			}
		
		
		//打印玩家手里的牌
		for(int i=0;i<player.length;i++){
			System.out.println("玩家"+i+"手里的牌:");
			for(int j=0;j<player[i].length;j++){
					//得到行号，根据行号变成花色，多副扑克情况下需取模4（即%4）
					int type=player[i][j]/13%4;
					//打印牌号
					System.out.print(types[type]+flower[player[i][j]%13]+"\t");
					//换行
					if((j+1)%13==0){
						System.out.println();
					}
							
			}
			System.out.println();
		}
		

		
	}

}
