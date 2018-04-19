package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.CheckerboardFrame;

public class CheckerboardMouseListener extends MouseAdapter{
	//获取棋盘通用属性
	Checkerboard checkerboard = Checkerboard.getCheckerboard();
	//存储当前棋子颜色信息，false为黑色，true为白色
	private boolean current_chess_piece;
	//存储当前点击点在数组中的索引i
	private int i;
	//存储当前点击点在数组中的索引j
	private int j;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//只有在规定范围内点击，才有效
		//当前状态为true才可以操作
		if(e.getX() >= 35 && e.getX() <= 765 && e.getY() >= 35 && e.getY() <= 765
				&& Common.getCommon().getCurrent_status()) {
			//根据算法计算出当前点击点在数组中的索引
			i = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getX());
			j = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getY());
			
			//存储当前点击点在数组中的索引
			int[] chessRecord = new int[2];
			chessRecord[0] = i;
			chessRecord[1] = j;
			//将下子位置添加进下子记录中
			checkerboard.getChessRecord().add(chessRecord);
			
			//获取当前棋子颜色信息，false为黑色，true为白色
			current_chess_piece = checkerboard.getCurrent_chess_piece();
			
			//判断当前棋子颜色与当前位置是否有棋子，如果为黑色，数组相应位置置为1，如果是白色，数组相应位置置为2
			if(checkerboard.getCheckerboardSituation()[i][j] == 0) {
				if(current_chess_piece) {
					checkerboard.getCheckerboardSituation()[i][j] = 2;
				}else {
					checkerboard.getCheckerboardSituation()[i][j] = 1;
				}
				
				//将当前棋子颜色置为另一种
				Checkerboard.getCheckerboard().setCurrent_chess_piece(!current_chess_piece);
				
				//重绘棋盘窗体
				CheckerboardFrame.getCheckerboardFrame().repaint();
				
				if(CheckerboardAlgorithm.judge(i,j)) {
					JOptionPane.showMessageDialog(CheckerboardFrame.getCheckerboardFrame(), current_chess_piece ? "白棋胜利,游戏结束!!!" : "黑棋胜利,游戏结束!!!");
				}
			}
		}
	}
}
