package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.function.CheckerboardFunction;
import com.bpf.gobang.function.ToolbarFunction;
import com.bpf.gobang.role.ChessPlayer;
import com.bpf.gobang.role.Robot;

public class ToolbarMouseListener extends MouseAdapter{
	private Toolbar toolbar = Toolbar.getToolbar();
	private Checkerboard checkerboard;
	
	@Override
	public void mousePressed(MouseEvent e) {
		//根据当前页面选择使用的棋盘属性
		checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		
		//当前状态为true才可以操作
		if(Common.getCommon().getCurrent_status()) {
			//点击返回按钮
			if(toolbar.getCURRENT_BUTTON().equals("back")) {
				//执行返回方法
				CheckerboardFunction.backToMenu();
			}
			
			//点击重玩按钮
			else if(toolbar.getCURRENT_BUTTON().equals("restart")) {
				//执行重新开始方法
				CheckerboardFunction.restart();
			}
			
			//点击悔棋按钮
			else if(toolbar.getCURRENT_BUTTON().equals("regret")) {
				//执行悔棋方法
				ToolbarFunction.regret();
			}
			
			//点击提示按钮
			else if(toolbar.getCURRENT_BUTTON().equals("prompt")) {
				//执行提示方法
				ToolbarFunction.prompt();
			}
			
			//点击声音按钮
			else if(toolbar.getCURRENT_BUTTON().equals("sound")) {
				//修改声音打开标志位
				toolbar.setSound(!toolbar.getSound());
				//重绘棋盘窗体
				CheckerboardFrame.getCheckerboardFrame().repaint();
			}
			
			//点击先行按钮
			else if(Common.getCommon().getCurrent_page().equals(Common.COMPUTER_VS_PLAYER) 
					&& toolbar.getCURRENT_BUTTON().equals("first")) {
				//将电脑玩家标志位置反
				checkerboard.setFirst_player(!checkerboard.getFirst_player());
				//执行重新开始方法
				CheckerboardFunction.restart();
			}
		}
	}
	
}
