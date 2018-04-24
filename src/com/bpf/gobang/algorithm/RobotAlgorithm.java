package com.bpf.gobang.algorithm;

import java.util.List;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Computer_vs_player;

public class RobotAlgorithm {
	//棋盘属性
	private Checkerboard checkerboard = null;
	//玩家得分
	private int[][] playerScore = null;
	//机器人得分
	private int[][] robotScore = null;  
	//玩家落子点得分
	private boolean[][][] playerTable = Computer_vs_player.getComputer_vs_player(Common.getCommon().getCurrent_page()).getPlayerTable();  
	//机器人落子点得分
	private boolean[][][] robotTable = Computer_vs_player.getComputer_vs_player(Common.getCommon().getCurrent_page()).getRobotTable(); 
	//所有能赢的情况
	private int[][] win = null;
	//最佳落子点坐标
	private int[] location = new int[2];

	/**
	 * <p>Title: bestFallingPoint</p>
	 * <p>Description: 计算出电脑最佳落子点</p>
	 * @return
	 */
	public int[] bestFallingPoint() {
		//根据当前页面选择使用的棋盘属性
		checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		//初始化玩家得分
		playerScore = Computer_vs_player.getComputer_vs_player(Common.getCommon().getCurrent_page()).getScores()[0];
		//初始化机器人得分
		robotScore = Computer_vs_player.getComputer_vs_player(Common.getCommon().getCurrent_page()).getScores()[1];
		//初始化所有能赢的情况
		win = Computer_vs_player.getComputer_vs_player(Common.getCommon().getCurrent_page()).getWin();
		
		//获取棋盘
		int[][] boardCondition = checkerboard.getCheckerboardSituation();
		List<int[]> chessRecord = checkerboard.getChessRecord();
		
		if(chessRecord.size() == 0) {
			location[0] = 9;
			location[1] = 9;
		}else if(chessRecord.size() == 1) {
			if(boardCondition[9][9] == 0) {
				location[0] = 9;
				location[1] = 9;
			}else {
				location[0] = 8;
				location[1] = 8;
			}
		}else {
			for(int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++) {
					//该坐标的玩家得分清零
					playerScore[i][j] = 0;
					//在还没下棋子的地方遍历
					if(boardCondition[i][j] == 0) {
						//遍历该棋盘可落子点上的黑子所有权值的连子情况，并给该落子点加上相应奖励分
						for(int k = 0; k < 1020; k++) {
							if(playerTable[i][j][k]){  
								switch(this.win[0][k]){     
								case 1: //一连子  
									playerScore[i][j] += 5;  
									break;  
								case 2: //两连子  
									playerScore[i][j] += 100;  
									break;  
								case 3: //三连子  
									playerScore[i][j] += 500;  
									break;  
								case 4: //四连子  
									playerScore[i][j] += 5000;  
								}  
							} 
						}
					}

					//该坐标的机器人得分清零
					robotScore[i][j] = 0;
					//在还没下棋子的地方遍历
					if(boardCondition[i][j] == 0) {
						//遍历该棋盘可落子点上的白子所有权值的连子情况，并给该落子点加上相应奖励分
						for(int k = 0; k < 1020; k++) {
							if(robotTable[i][j][k]){  
								switch(this.win[1][k]){    
								case 1:  //一连子  
									robotScore[i][j] += 5;  
									break;  
								case 2:  //两连子  
									robotScore[i][j] += 50;  
									break;  
								case 3: //三连子  
									robotScore[i][j] += 500;  
									break;  
								case 4:  //四连子  
									robotScore[i][j] += 10000;  
									break;  
								}  
							}  
						}
					}
				}
			}

			int playerScoreTemp = 0;
			int robotScoreTemp = 0;
			int pI = 0;
			int pJ = 0;
			int rI = 0;
			int rJ = 0;

			for(int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++) {
					//找出空位中玩家最大分值点
					if(playerScore[i][j] > playerScoreTemp) {
						playerScoreTemp = playerScore[i][j];
						pI = i;
						pJ = j;
					}
					//找出空位中电脑最大分值点
					if(robotScore[i][j] > robotScoreTemp) {
						robotScoreTemp = robotScore[i][j];
						rI = i;
						rJ = j;
					}
				}
			}

			if(playerScoreTemp > robotScoreTemp) {
				location[0] = pI;
				location[1] = pJ;
			}else {
				location[0] = rI;
				location[1] = rJ;
			}

			for(int i = 0; i < 1020; i++){  
	            if(robotTable[location[0]][location[1]][i] && this.win[1][i] != 7)  
	                win[1][i]++;     //给白子的所有五连子可能的加载当前连子数  
	            if(playerTable[location[0]][location[1]][i]){  
	                playerTable[location[0]][location[1]][i] = false;  
	                win[0][i]=7;  
	            }  
	        }  
		}
		
		return location;
	}

	/**
	 * <p>Title: score</p>
	 * <p>Description: 获取该点分数</p>
	 * @param i 该点在数组中索引
	 * @param j 该点在数组中索引
	 * @param color 棋子颜色
	 * @return
	 */
	public int score(int m, int n, int color) {
		
		return 0;
	}
}
