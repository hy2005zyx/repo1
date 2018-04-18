package homework.d01.������.��������ҵ;

import java.util.Random;

public class K6_pk {

	public static void main(String[] args) {
		int[] poker=new int[52*2];
		String[] types={"����","����","÷��","����"};
		String[] flower={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		
		//���˿˸�ֵ
		for(int i=0;i<poker.length;i++){
			poker[i]=i%52;
		}

		//���52���ƣ�ÿ����ɫռһ��
		for(int i=0;i<poker.length;i++){
			//�õ��кţ������кű�ɻ�ɫ���ั�˿��������ȡģ4����%4��
			int type=poker[i]/13%4;
			//��ӡ�ƺ�
			System.out.print(types[type]+flower[poker[i]%13]+"\t");
			//����
			if((i+1)%13==0){
				System.out.println();
			}
			
		}
		
		
		/*
		//Java�����������Random();
		Random random=new Random();
		random.nextInt(100);//����һ���������
		*/
		
		//ʹ������������ƴ���
		Random random=new Random();
		for(int i=0;i<poker.length;i++){
		  //��ǰ�ƺţ�poker[i]. ��������ƺţ�
		  int newPoint=random.nextInt(poker.length);
		  //System.out.println(newPoint);
		  int t;
		  t=poker[i];
		  poker[i]=poker[newPoint];
		  poker[newPoint]=t;
		}
		
		System.out.println("===================================��=============��=============��===================================");
		
		//���52���ƣ�ÿ����ɫռһ��
		for(int i=0;i<poker.length;i++){
			//�õ��кţ������кű�ɻ�ɫ���ั�˿��������ȡģ4����%4��
			int type=poker[i]/13%4;
			//��ӡ�ƺ�
			System.out.print(types[type]+flower[poker[i]%13]+"\t");
			//����
			if((i+1)%13==0){
				System.out.println();
			}
					
		}
		
		System.out.println("===================================��================��================��===================================");
		
		//����
		//ʹ������������ĸ����,�ĸ����������1/4���˿˵���
		int[][] player=new int[4][poker.length/4];
		for(int i=0;i<poker.length;i++){
			player[i%4][i/4]=poker[i];
			}
		
		
		//��ӡ����������
		for(int i=0;i<player.length;i++){
			System.out.println("���"+i+"�������:");
			for(int j=0;j<player[i].length;j++){
					//�õ��кţ������кű�ɻ�ɫ���ั�˿��������ȡģ4����%4��
					int type=player[i][j]/13%4;
					//��ӡ�ƺ�
					System.out.print(types[type]+flower[player[i][j]%13]+"\t");
					//����
					if((j+1)%13==0){
						System.out.println();
					}
							
			}
			System.out.println();
		}
		

		
	}

}
