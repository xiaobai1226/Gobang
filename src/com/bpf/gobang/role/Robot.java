package com.bpf.gobang.role;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.function.CheckerboardFunction;

/**
 * <p>Title: Robot</p>
 * <p>Description: 电脑机器人</p>
 * @author	白鹏飞
 * @date	2018年4月22日下午6:04:29
 * @version 1.0.0
 */
public class Robot implements ChessPlayer{
	//存储当前棋子颜色信息，false为黑色，true为白色
	private boolean current_chess_piece;
	//获取棋盘通用属性
	Checkerboard checkerboard = Checkerboard.getCheckerboard();
	
	/**
	 * <p>Title: put</p>
	 * <p>Description: 下棋方法</p>
	 */
	@Override
	public void put(int row, int coll) {
		//当前状态为true才可以操作
		if(Common.getCommon().getCurrent_status()) {
			//获取当前棋子颜色信息，false为黑色，true为白色
			current_chess_piece = checkerboard.getCurrent_chess_piece();
			
			//判断当前棋子颜色与当前位置是否有棋子，如果为黑色，数组相应位置置为1，如果是白色，数组相应位置置为2
			if(checkerboard.getCheckerboardSituation()[row][coll] == 0) {
				if(current_chess_piece) {
					checkerboard.getCheckerboardSituation()[row][coll] = 2;
				}else {
					checkerboard.getCheckerboardSituation()[row][coll] = 1;
				}
				
				//存储当前点击点在数组中的索引
				int[] chessRecord = new int[2];
				chessRecord[0] = row;
				chessRecord[1] = coll;
				//将下子位置添加进下子记录中
				checkerboard.getChessRecord().add(chessRecord);
				
				//将当前棋子颜色置为另一种
				Checkerboard.getCheckerboard().setCurrent_chess_piece(!current_chess_piece);
				
				//重绘棋盘窗体
				CheckerboardFrame.getCheckerboardFrame().repaint();
				
				//检测出有胜利一方执行的操作
				if(CheckerboardAlgorithm.judge(row,coll)) {
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
