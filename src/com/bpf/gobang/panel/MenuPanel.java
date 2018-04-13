package com.bpf.gobang.panel;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.entity.Menu;
import com.bpf.gobang.listener.MenuMouseListener;
import com.bpf.gobang.listener.MenuMouseMotionListener;

/**
 * <p>Title: MenuPanel</p>
 * <p>Description: 菜单面板</p>
 * @author	白鹏飞
 * @date	2018年4月8日4:53:27
 * @version 1.0.0
 */
public class MenuPanel extends JPanel{
	private static MenuPanel menuPanel = null;
	
	private MenuPanel() {
		init();
	}
	
	//提供一个全局的静态方法
    public static MenuPanel getMenuPanel(){
        if(menuPanel == null){
            synchronized(MenuPanel.class){
                if(menuPanel == null){
                	menuPanel = new MenuPanel();
                }
            }
        }
        return menuPanel;
    }
	
	
	Menu menu = Menu.getMenu();
	
	public void init() {
		this.addMouseListener(new MenuMouseListener());
		this.addMouseMotionListener(new MenuMouseMotionListener());
	}
	
	@Override
	public void paint(Graphics g) {
		try {
			//添加菜单背景
			g.drawImage(ImageIO.read(new File(menu.getMENU_BACKGROUND_IMAGE_URL())), 0, 0, this);
			
			//添加联机模式按钮
			if(menu.getCURRENT_BUTTON().equals("play_online")) {
				g.drawImage(ImageIO.read(new File(menu.getPLAY_ONLINE_IMAGE_URL())), 157, 247, this);
			}else {
				g.drawImage(ImageIO.read(new File(menu.getPLAY_ONLINE_IMAGE_URL())), 167, 250, 200, 55, this);
			}
			//添加人机对弈按钮
			if(menu.getCURRENT_BUTTON().equals("player_vs_computer")) {
				g.drawImage(ImageIO.read(new File(menu.getPLAYER_VS_COMPUTER_IMAGE_URL())), 157, 357, this);
			}else {
				g.drawImage(ImageIO.read(new File(menu.getPLAYER_VS_COMPUTER_IMAGE_URL())), 167, 360, 200, 55, this);
			}
			//添加双人对弈按钮
			if(menu.getCURRENT_BUTTON().equals("two_player_game")) {
				g.drawImage(ImageIO.read(new File(menu.getTWO_PLAYER_GAME_IMAGE_URL())), 157, 467, this);
			}else {
				g.drawImage(ImageIO.read(new File(menu.getTWO_PLAYER_GAME_IMAGE_URL())), 167, 470, 200, 55, this);
			}
			//添加关于按钮
			if(menu.getCURRENT_BUTTON().equals("about")) {
				g.drawImage(ImageIO.read(new File(menu.getABOUT_IMAGE_URL())), 157, 577, this);
			}else {
				g.drawImage(ImageIO.read(new File(menu.getABOUT_IMAGE_URL())), 167, 580, 200, 55, this);
			}
			//添加战绩按钮
			if(menu.getCURRENT_BUTTON().equals("record")) {
				g.drawImage(ImageIO.read(new File(menu.getBIG_RECORD_IMAGE_URL())), 80, 16, this);
			}else {
				g.drawImage(ImageIO.read(new File(menu.getRECORD_IMAGE_URL())), 85, 20, this);
			}
			//添加规则按钮
			if(menu.getCURRENT_BUTTON().equals("rules")) {
				g.drawImage(ImageIO.read(new File(menu.getBIG_GAME_RULES_IMAGE_URL())), 310, 27, this);
			}else {
				g.drawImage(ImageIO.read(new File(menu.getGAME_RULES_IMAGE_URL())), 315, 30, this);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
