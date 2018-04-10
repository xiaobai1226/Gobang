package com.bpf.gobang.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.entity.Menu;

/**
 * <p>Title: MenuPanel</p>
 * <p>Description: 菜单面板</p>
 * @author	白鹏飞
 * @date	2018年4月8日4:53:27
 * @version 1.0.0
 */
public class MenuPanel extends JPanel{
	Menu menu = Menu.getMenu();
	
	public MenuPanel() {
		init();
	}
	
	public void init() {
		this.repaint();
	}

	@Override
	public void print(Graphics g) {
		try {
			g.drawImage(ImageIO.read(new File(menu.getMENU_BACKGROUND_IMAGE_URL())), 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
