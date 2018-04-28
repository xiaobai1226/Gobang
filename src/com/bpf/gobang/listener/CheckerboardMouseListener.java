package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.algorithm.RobotAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.function.CheckerboardFunction;
import com.bpf.gobang.role.ChessPlayer;
import com.bpf.gobang.role.Player;
import com.bpf.gobang.role.Robot;

public class CheckerboardMouseListener extends MouseAdapter{
	//存储当前点击点在数组中的索引i
	private int i;
	//存储当前点击点在数组中的索引j
	private int j;
	private ChessPlayer player = new Player();
	private ChessPlayer robot = new Robot();
	RobotAlgorithm robotAlgorithm = new RobotAlgorithm();
	
	@Override
	public void mousePressed(MouseEvent e) {
		//点击在棋盘范围内才执行操作
		if(e.getX() >= 35 && e.getX() <= 765 && e.getY() >= 35 && e.getY() <= 765) {
			//判断当前页面，根据页面执行不同的操作，双人对弈，轮流执行玩家下子动作
			if(Common.getCommon().getCurrent_page().equals(Common.TWOPLAYER)) {
				//根据算法计算出当前点击点在数组中的索引
				i = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getX());
				j = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getY());
				
				if(Checkerboard.getCheckerboard(Common.TWOPLAYER).getCurrent_chess_piece()) {
					robot.put(i,j);
				}else {
					player.put(i, j);
				}
			}
			
			//判断当前页面，根据页面执行不同的操作，人机对战，玩家与机器人轮流执行下子动作
			if(Common.getCommon().getCurrent_page().equals(Common.COMPUTER_VS_PLAYER)) {
				//判断是否是电脑先行，是的话先执行电脑下子动作
				
				//根据算法计算出当前点击点在数组中的索引
				i = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getX());
				j = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getY());
				
				//根据当前页面选择使用的棋盘属性
				Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
				//判断当前索引位置是否有子，无子继续执行操作
				if(checkerboard.getCheckerboardSituation()[i][j] == 0) {
					player.put(i, j);
					
					int[] bestFallingPoint = robotAlgorithm.bestFallingPoint();
					robot.put(bestFallingPoint[0], bestFallingPoint[1]);
				}
			}
		}
	}
}
