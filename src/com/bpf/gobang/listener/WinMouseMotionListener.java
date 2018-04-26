package com.bpf.gobang.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.frame.CheckerboardFrame;

/**
 * <p>Title: WinMouseMotionListener</p>
 * <p>Description: 胜利面板鼠标移动监听适配器</p>
 * @author	白鹏飞
 * @date	2018年4月20日下午1:14:51
 * @version 1.0.0
 */
public class WinMouseMotionListener extends MouseMotionAdapter{
	Toolbar toolbar = Toolbar.getToolbar();
	
	@Override
	public void mouseMoved(MouseEvent e) {
		toolbar.setCURRENT_BUTTON("");
		if(e.getX() >= 290 && e.getX() <= 427 && e.getY() >= 490 && e.getY() <= 537) {
			toolbar.setCURRENT_BUTTON("another_game");
		}else if(e.getX() >= 480 && e.getX() <= 617 && e.getY() >= 490 && e.getY() <= 537){
			toolbar.setCURRENT_BUTTON("back_menu");
		}
		CheckerboardFrame.getCheckerboardFrame().repaint();
	}
}
