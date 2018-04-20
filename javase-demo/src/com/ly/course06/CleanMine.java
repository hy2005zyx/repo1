package com.ly.course06;

import java.util.Random;
public class CleanMine
{

	//String[] args表示入门 是一个字符串数组
	public static void main(String[] args)
	{
		//定义雷区
		int[][] minefield = null;  //引用类型的默认值都是null， 表示在堆中开辟空间
		int mineCount = 0; //雷的数量
		String  level = args.length == 0 ? "1" : args[0];  //取到雷区的级别

		switch(level) //字符支持是在jdk 1.7之上
		{
			case "1":
				System.out.println("进入简单雷区");
				minefield = new int[9][9];
				mineCount = 10;
				break;
			case "2":
				System.out.println("进入中级雷区");
				minefield = new int[16][16];
				mineCount = 40;
				break;
			case "3":
				System.out.println("进入高级雷区");
				minefield = new int[16][30];
				mineCount = 99;
				break;
		}

		//埋雷
		for (int i = 0; i < mineCount;)  //埋多少雷
		{
			Random rand = new Random(); //生成随机数对象
			int row = rand.nextInt(minefield.length);  //生成随机数是 0 ~ minefield.length - 1
			int col = rand.nextInt(minefield[0].length);  //生成随机数是 0 ~ minefield[0].length - 1 
			if ((minefield[row][col] & 0b0000001) == 0) //判断是否有雷
			{
				minefield[row][col] += 0b0000001;

				//统计雷的数量(有八个方向)
				//上面
				if (row - 1 >= 0)
				{
					minefield[row - 1][col] += 0b0001000; //统计加上一个雷
					//上面左边
					if (col - 1 >= 0)
					{
						minefield[row - 1][col - 1] += 0b0001000; //统计加上一个雷
					}
					//上面右边
					if (col + 1 < minefield[row].length)
					{
						minefield[row - 1][col + 1] += 0b0001000; //统计加上一个雷
					}
				}

				//下面
				if (row + 1 < minefield.length)
				{
					minefield[row + 1][col] += 0b0001000; //统计加上一个雷
					//下面左边
					if (col - 1 >= 0)
					{
						minefield[row + 1][col - 1] += 0b0001000; //统计加上一个雷
					}
					//下面右边
					if (col + 1 < minefield[row].length)
					{
						minefield[row + 1][col + 1] += 0b0001000; //统计加上一个雷
					}
				}

				//左边
				if (col - 1 >= 0)
				{
					minefield[row][col - 1] += 0b0001000; //统计加上一个雷
				}
				//右边
				if (col + 1 < minefield[row].length)
				{
					minefield[row][col + 1] += 0b0001000; //统计加上一个雷
				}				
				i++; //埋了雷才++
			}
			
		}

		//测试统计雷的数量
		for (int i = 0; i < minefield.length; i++)
		{
			for (int j = 0; j < minefield[i].length; j++)
			{
				if ((minefield[i][j] >>> 3) != 0 && (minefield[i][j] & 0b0000001) != 0b0000001)
				{
					minefield[i][j] += 0b0000010;
				}
			}
		}


		for (int i = 0; i < minefield.length; i++)
		{
			for (int j = 0; j < minefield[i].length; j++)
			{

				//显示分三种情况
				if ( (minefield[i][j] & 0b0000110) == 0)
				{
					//1、没开
					System.out.print(" □ "); //每一行的字符
				}
				else if ( (minefield[i][j] & 0b0000110) == 0b0000010)
				{
					//2、开了
					
					if ((minefield[i][j] & 0b0000001) == 0b0000001) //判断是否是雷
					{
						//显示雷
						System.out.print(" ◎ "); //每一行的字符
					}
					else if ((minefield[i][j] >>> 3) == 0) //判断是否是雷
					{
						//显示空白
						System.out.print(" ■ ");
					}
					else
					{
						//显示统计雷的数量
						System.out.print(" " + (minefield[i][j] >>> 3) + " "); //每一行的字符
					}					
				}
				else if ( (minefield[i][j] & 0b0000110) == 0b0000100)
				{
					//3、插旗
					System.out.print(" ▲ ");
				}
			}
			System.out.println(); //控制行
		}
		

		//00000000   
		//00000001 表示有雷还没有雷  1有雷, 0 没雷
		//00000110 表示格子的状态 00 表示没开， 01 表示开了， 10表示插旗
		//01111000 表示有几个雷

	}
}
