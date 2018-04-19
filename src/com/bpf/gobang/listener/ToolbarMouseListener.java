package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.frame.MenuFrame;

public class ToolbarMouseListener extends MouseAdapter{
	Toolbar toolbar = Toolbar.getToolbar();
	Checkerboard checkerboard = Checkerboard.getCheckerboard();
	@Override
	public void mouseClicked(MouseEvent e) {
		//当前状态为true才可以操作
		if(Common.getCommon().getCurrent_status()) {
			//点击返回按钮
			if(toolbar.getCURRENT_BUTTON().equals("back")) {
				//点击此按钮关闭棋盘窗体，打开菜单窗体
				CheckerboardFrame.getCheckerboardFrame().dispose();
				MenuFrame.getMenuFrame().setVisible(true);;
			}
			
			//点击重玩按钮
			if(toolbar.getCURRENT_BUTTON().equals("restart")) {
				//清空棋盘
				checkerboard.setCheckerboardSituation(new int[19][19]);
				//清空棋盘下子记录
				checkerboard.setChessRecord(new ArrayList<int[]>());
				//当前棋子置为黑色
				checkerboard.setCurrent_chess_piece(false);
				//重绘该窗体
				CheckerboardFrame.getCheckerboardFrame().repaint();
			}
			
			//点击悔棋按钮
			if(toolbar.getCURRENT_BUTTON().equals("regret")) {
				//将当前状态置为false
				Common.getCommon().setCurrent_status(false);
				
				Thread t = new Thread() {
					public void run() {
						//获取当期棋局记录的长度
						int length = checkerboard.getChessRecord().size();
						int i = 0;
						while(i < 2 && length > 0) {
							//获取当期棋局，最后下的棋子的坐标
							int[] last = checkerboard.getChessRecord().get(length - 1);
							//清除当期棋局，最后下的棋子
							checkerboard.getCheckerboardSituation()[last[0]][last[1]] = 0;
							//清除当前棋局记录，最后下的棋子
							checkerboard.getChessRecord().remove(length - 1);
							
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
		}
	}
	
}
