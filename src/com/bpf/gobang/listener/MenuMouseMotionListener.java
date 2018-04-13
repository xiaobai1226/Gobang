package com.bpf.gobang.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.bpf.gobang.entity.Menu;
import com.bpf.gobang.frame.MenuFrame;

/**
 * <p>Title: MenuMouseMotionListener</p>
 * <p>Description: 菜单面板鼠标移动监听适配器</p>
 * @author	白鹏飞
 * @date	2018年4月13日下午3:02:24
 * @version 1.0.0
 */
public class MenuMouseMotionListener extends MouseMotionAdapter{
	Menu menu = Menu.getMenu();
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//将CURRENT_BUTTON置空
		menu.setCURRENT_BUTTON("");
		
		//判断光标在哪个按钮上
		//在战绩按钮上
		if(e.getX() >= 85 && e.getX() <= 215 && e.getY() >= 20 && e.getY() <= 120) {
			menu.setCURRENT_BUTTON("record");
		}
		//在规则按钮上
		if(e.getX() >= 315 && e.getX() <= 455 && e.getY() >= 30 && e.getY() <= 120) {
			menu.setCURRENT_BUTTON("rules");
		}
		
		if(e.getX() >= 167 && e.getX() <= 367) {
			//在联机模式按钮上
			if(e.getY() >= 250 && e.getY() <= 305) {
				menu.setCURRENT_BUTTON("play_online");
			}
			//在人机对弈按钮上
			else if(e.getY() >= 360 && e.getY() <= 415){
				menu.setCURRENT_BUTTON("player_vs_computer");
			}
			//在双人对弈按钮上
			else if(e.getY() >= 470 && e.getY() <= 525){
				menu.setCURRENT_BUTTON("two_player_game");
			}
			//在关于按钮上
			else if(e.getY() >= 580 && e.getY() <= 635){
				menu.setCURRENT_BUTTON("about");
			}
		}
		
		//重绘窗体
		MenuFrame.getMenuFrame().repaint();
	}
}
