package com.bpf.gobang.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.CheckerboardFrame;

/**
 * <p>Title: CheckerboardMouseMotionListener</p>
 * <p>Description: 棋盘面板鼠标移动监听适配器</p>
 * @author	白鹏飞
 * @date	2018年4月15日下午9:21:50
 * @version 1.0.0
 */
public class CheckerboardMouseMotionListener extends MouseMotionAdapter{
	Checkerboard checkerboard = Checkerboard.getCheckerboard();
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//当前状态为true才可以操作
		if(e.getX() >= 35 && e.getX() <= 765 && e.getY() >= 35 && e.getY() <= 765
				&& Common.getCommon().getCurrent_status()) {
			checkerboard.getCursor_position()[0] = e.getX();
			checkerboard.getCursor_position()[1] = e.getY();
		}else {
			checkerboard.getCursor_position()[0] = -1;
			checkerboard.getCursor_position()[1] = -1;
		}
		CheckerboardFrame.getCheckerboardFrame().repaint();
	}
}
