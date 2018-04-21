package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.function.CheckerboardFunction;

/**
 * <p>Title: WinMouseListener</p>
 * <p>Description: 胜利面板鼠标监听适配器</p>
 * @author	白鹏飞
 * @date	2018年4月20日下午2:07:27
 * @version 1.0.0
 */
public class WinMouseListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		//点击重玩按钮
		if(Toolbar.getToolbar().getCURRENT_BUTTON().equals("another_game")) {
			CheckerboardFunction.restart();
		}
		
		//点击返回按钮
		if(Toolbar.getToolbar().getCURRENT_BUTTON().equals("back_menu")) {
			//执行重玩方法
			CheckerboardFunction.restart();
			//执行返回方法
			CheckerboardFunction.backToMenu();
			//将计时器归0
			Checkerboard.getCheckerboard().setGameTime(0);
		}
	}
}
