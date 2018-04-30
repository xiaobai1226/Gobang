package com.bpf.gobang.function;

import com.bpf.gobang.algorithm.RobotAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.role.ChessPlayer;
import com.bpf.gobang.role.Player;
import com.bpf.gobang.role.Robot;

/**
 * <p>Title: ToolbarFunction</p>
 * <p>Description: 工具栏方法</p>
 * @author	白鹏飞
 * @date	2018年4月24日下午4:07:11
 * @version 1.0.0
 */
public class ToolbarFunction {
	/**
	 * <p>Title: regret</p>
	 * <p>Description: 悔棋方法</p>
	 */
	public static void regret() {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		//将当前状态置为false
		Common.getCommon().setCurrent_status(false);
		
		Thread t = new Thread() {
			public void run() {
				//获取当期棋局记录的长度
				int length = checkerboard.getChessRecord().size();
				int max = 0;
				if(Common.getCommon().getCurrent_page().equals(Common.COMPUTER_VS_PLAYER)) {
					max = 2;
				}else if(Common.getCommon().getCurrent_page().equals(Common.TWOPLAYER)) {
					max = 1;
				}
				int i = 0;
				while(i < max && length > 0) {
					//获取当期棋局，最后下的棋子的坐标
					int[] last = checkerboard.getChessRecord().get(length - 1);
					//清除当期棋局，最后下的棋子
					checkerboard.getCheckerboardSituation()[last[0]][last[1]] = 0;
					//清除当前棋局记录，最后下的棋子
					checkerboard.getChessRecord().remove(length - 1);
					//
					checkerboard.setCurrent_chess_piece(!checkerboard.getCurrent_chess_piece());
					
					//
					CheckerboardFunction.restoreWin();
					//
					CheckerboardFunction.restoreTable();
					
					//重绘该窗体
					CheckerboardFrame.getCheckerboardFrame().repaint();
					
					i++;
					length--;
					//睡眠500毫秒
					try {
						if(length > 0 && i < 2) {
							Thread.sleep(500);
						}
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				
				//如果棋子记录数为0，将当前棋子颜色置为黑色
				if(length == 0) {
					checkerboard.setCurrent_chess_piece(false);
				}
				//将当前状态置为true
				Common.getCommon().setCurrent_status(true);
			}
		};
		t.start();
	}
	
	/**
	 * <p>Title: prompt</p>
	 * <p>Description: 提示方法</p>
	 */
	public static void prompt() {
		RobotAlgorithm robotAlgorithm = new RobotAlgorithm();
		ChessPlayer player = new Player();
		ChessPlayer robot = new Robot();
		int[] bestFallingPoint = null;
		
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		
		if(Common.getCommon().getCurrent_page().equals(Common.TWOPLAYER)) {
			bestFallingPoint = robotAlgorithm.bestFallingPoint();
			if(checkerboard.getCurrent_chess_piece()) {
				robot.put(bestFallingPoint[0], bestFallingPoint[1]);
			}else {
				player.put(bestFallingPoint[0], bestFallingPoint[1]);
			}
		}else if(Common.getCommon().getCurrent_page().equals(Common.COMPUTER_VS_PLAYER)) {
			bestFallingPoint = robotAlgorithm.bestFallingPoint();
			player.put(bestFallingPoint[0], bestFallingPoint[1]);
			
			bestFallingPoint = robotAlgorithm.bestFallingPoint();
			robot.put(bestFallingPoint[0], bestFallingPoint[1]);
		}
	}
}
