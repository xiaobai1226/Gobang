package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.frame.MenuFrame;
import com.bpf.gobang.function.runnable.TimerRunnable;

public class MenuMouseListener extends MouseAdapter{
	private Checkerboard checkerboard;
	@Override
	public void mouseClicked(MouseEvent e) {
		//判断光标在哪个按钮上
		//点击战绩按钮
		if(e.getX() >= 85 && e.getX() <= 215 && e.getY() >= 20 && e.getY() <= 120) {
		}
		//点击规则按钮
		if(e.getX() >= 315 && e.getX() <= 455 && e.getY() >= 30 && e.getY() <= 120) {
//			menu.setCURRENT_BUTTON("rules");
		}
		
		//点击人机对弈按钮
		if(Common.getCommon().getCurrent_button().equals(Common.COMPUTER_VS_PLAYER_BUTTON)){
			//将当前页面置为人机对战
			Common.getCommon().setCurrent_page(Common.COMPUTER_VS_PLAYER);
			
			//根据当前页面选择使用的棋盘属性
			checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
			
			//将计时器线程运行标志位打开
			checkerboard.setTimerRun(true);
			//创建并开启计时器线程
			Thread timer = new Thread(new TimerRunnable());
			timer.start();
			//将计时器线程存储起来
			checkerboard.setTimerThread(timer);
			//点击此按钮关闭菜单窗体，打开棋盘窗体
			MenuFrame.getMenuFrame().dispose();
			CheckerboardFrame.getCheckerboardFrame().setVisible(true);
		}
		//点击双人对弈按钮
		else if(Common.getCommon().getCurrent_button().equals(Common.TWO_PLAYER_BUTTON)){
			//将当前页面置为双人对弈
			Common.getCommon().setCurrent_page(Common.TWOPLAYER);
			
			//根据当前页面选择使用的棋盘属性
			checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
			
			//将计时器线程运行标志位打开
			checkerboard.setTimerRun(true);
			//创建并开启计时器线程
			Thread timer = new Thread(new TimerRunnable());
			timer.start();
			//将计时器线程存储起来
			checkerboard.setTimerThread(timer);
			
			//点击此按钮关闭菜单窗体，打开棋盘窗体
			MenuFrame.getMenuFrame().dispose();
			CheckerboardFrame.getCheckerboardFrame().setVisible(true);
		}
		
		if(e.getX() >= 167 && e.getX() <= 367) {
			//点击联机模式按钮
			if(e.getY() >= 250 && e.getY() <= 305) {
//				menu.setCURRENT_BUTTON("play_online");
			}
			//点击关于按钮
			else if(e.getY() >= 580 && e.getY() <= 635){
//				menu.setCURRENT_BUTTON("about");
			}
		}
	}
	
}
