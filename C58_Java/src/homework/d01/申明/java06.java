package homework.d01.申明;

import java.util.Random;

public class java06 {
        public static void main(String[] args) {
			int[] poker = new int[52*2];
			String[] types={"黑桃","红桃","梅花","方块"};
			String[] flowernames={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
			for(int i=0;i<poker.length;i++){
				poker[i]=i%52;
			}
			for(int i=0;i<poker.length;i++){				
				int t=poker[i]/13%4;
                if(t==0){
                	System.out.print(types[t]+flowernames[poker[i]%13]+",");
                }else if(t==1){
                	System.out.print(types[t]+flowernames[poker[i]%13]+",");
                }else{
                	System.out.print(types[t]+flowernames[poker[i]%13]+",");}
                if((i+1)%13==0){
					System.out.println();//????????
				}
			}
			
			System.out.println("----------------------------------------------------------------------------------------");
			
			Random random = new Random();
			for(int i=0;i<poker.length;i++){
			int newPoint =random.nextInt(poker.length);
			int j=poker[i];
			poker[i]=poker[newPoint];
			poker[newPoint]=j;
			
			}
			for(int i=0;i<poker.length;i++){				
				int t=poker[i]/13%4;
                	System.out.print(types[t]+flowernames[poker[i]%13]+",");
                if((i+1)%13==0){
					System.out.println();//????????
				}
			}
			
			
			int[][] player=new int[4][poker.length/4];
			for(int i=0;i<poker.length;i++){
				player[i%4][i/4]=poker[i];
			}
			
			System.out.println("----------------------------------------------------------------------------------------");
			
			for(int i=0;i<player.length;i++){
				System.out.println("玩家"+i+"手中的牌:::::");
				for(int d=0;d<player[i].length;d++){
					int t=player[i][d]/13%4;
                	System.out.print(types[t]+flowernames[player[i][d]%13]+",");
                if((d+1)%13==0){
					System.out.println();//????????
				}
			    }
			}

			
			
			
			
			
			
			
			
			
		}
}
