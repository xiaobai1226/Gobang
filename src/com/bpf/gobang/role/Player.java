package com.bpf.gobang.role;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Computer_vs_player;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.function.CheckerboardFunction;

/**
 * <p>Title: Player</p>
 * <p>Description: 游戏玩家</p>
 * @author	白鹏飞
 * @date	2018年4月22日下午6:03:30
 * @version 1.0.0
 */
public class Player implements ChessPlayer{
	//存储当前点击点在数组中的索引i
	private int i;
	//存储当前点击点在数组中的索引j
	private int j;
	//存储当前棋子颜色信息，false为黑色，true为白色
	private boolean current_chess_piece;
	//获取棋盘通用属性
	Checkerboard checkerboard = Checkerboard.getCheckerboard();
	//玩家落子点得分
	private boolean[][][] playerTable = Computer_vs_player.getComputer_vs_player().getPlayerTable();  
	//机器人落子点得分
	private boolean[][][] robotTable = Computer_vs_player.getComputer_vs_player().getRobotTable(); 
	//所有能赢的情况
	private int[][] win = null;

	/**
	 * <p>Title: put</p>
	 * <p>Description: 下棋方法</p>
	 * @param row  玩家所点击横坐标
	 * @param coll 玩家所点击纵坐标
	 * @see com.bpf.gobang.role.ChessPlayer#put(int, int)
	 */
	@Override
	public void put(int row, int coll) {
		//只有在规定范围内点击，才有效
		//当前状态为true才可以操作
		if(row >= 35 && row <= 765 && coll >= 35 && coll <= 765
				&& Common.getCommon().getCurrent_status()) {
			//根据算法计算出当前点击点在数组中的索引
			i = CheckerboardAlgorithm.calculationIndexByCoordinate(row);
			j = CheckerboardAlgorithm.calculationIndexByCoordinate(coll);

			//获取当前棋子颜色信息，false为黑色，true为白色
			current_chess_piece = checkerboard.getCurrent_chess_piece();

			//判断当前棋子颜色与当前位置是否有棋子，如果为黑色，数组相应位置置为1，如果是白色，数组相应位置置为2
			if(checkerboard.getCheckerboardSituation()[i][j] == 0) {

				if(current_chess_piece) {
					checkerboard.getCheckerboardSituation()[i][j] = 2;
				}else {
					checkerboard.getCheckerboardSituation()[i][j] = 1;
				}

				//存储当前点击点在数组中的索引
				int[] chessRecord = new int[2];
				chessRecord[0] = i;
				chessRecord[1] = j;
				//将下子位置添加进下子记录中
				checkerboard.getChessRecord().add(chessRecord);

				//将当前棋子颜色置为另一种
				Checkerboard.getCheckerboard().setCurrent_chess_piece(!current_chess_piece);

				//初始化所有能赢的情况
				win = Computer_vs_player.getComputer_vs_player().getWin();
				for(int k = 0; k < 1020; k++){  
					if(playerTable[i][j][k] && this.win[0][k] != 7)  
						win[0][k]++;     //给黑子的所有五连子可能的加载当前连子数  
					if(robotTable[i][j][k]){  
						robotTable[i][j][k] = false;  
						win[1][k]=7;  
					}  
				} 

				//重绘棋盘窗体
				CheckerboardFrame.getCheckerboardFrame().repaint();

				//检测出有胜利一方执行的操作
				if(CheckerboardAlgorithm.judge(i,j)) {
					Common.getCommon().setCurrent_status(false);
					checkerboard.setTimerRun(false);
					//存储胜利方
					if(current_chess_piece) {
						checkerboard.setGame_result(2);
					}else {
						checkerboard.setGame_result(1);
					}
					//执行五子连珠闪烁
					CheckerboardFunction.connectedPiecesFlash();
				}
				//检测出和棋执行的操作
				else if(Checkerboard.getCheckerboard().getChessRecord().size() == 19*19){
					Common.getCommon().setCurrent_status(false);
					checkerboard.setTimerRun(false);
					//存储比赛结果为和棋
					checkerboard.setGame_result(0);
					//增加平局面板
					CheckerboardFunction.addWinPanel();
				}
			}
		}
	}
}
