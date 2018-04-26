package com.bpf.gobang.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.frame.CheckerboardFrame;

/**
 * <p>Title: ToolbarMouseMotionListener</p>
 * <p>Description: 工具栏面板鼠标移动监听适配器</p>
 * @author	白鹏飞
 * @date	2018年4月18日下午9:42:52
 * @version 1.0.0
 */
public class ToolbarMouseMotionListener extends MouseMotionAdapter{
	Toolbar toolbar = Toolbar.getToolbar();
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//当前状态为true才可以操作
		if(Common.getCommon().getCurrent_status()) {
			toolbar.setCURRENT_BUTTON("");
			//判断光标在哪个按钮上
			//在返回按钮上
			if(e.getX() >= 18 && e.getX() <= 78 && e.getY() >= 140 && e.getY() <= 202) {
				toolbar.setCURRENT_BUTTON("back");
			}
			//在重玩按钮上
			else if(e.getX() >= 20 && e.getX() <= 75 && e.getY() >= 240 && e.getY() <= 320) {
				toolbar.setCURRENT_BUTTON("restart");
			}
			//在悔棋按钮上
			else if(e.getX() >= 17 && e.getX() <= 77 && e.getY() >= 340 && e.getY() <= 418) {
				toolbar.setCURRENT_BUTTON("regret");
			}
			CheckerboardFrame.getCheckerboardFrame().repaint();
		}
	}
}
