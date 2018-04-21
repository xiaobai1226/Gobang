package com.bpf.gobang.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.algorithm.CommonAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.listener.WinMouseListener;
import com.bpf.gobang.listener.WinMouseMotionListener;

/**
 * <p>Title: CheckerboardPanel</p>
 * <p>Description: 棋盘面板</p>
 * @author	白鹏飞
 * @date	2018年4月13日下午3:37:58
 * @version 1.0.0
 */
public class WinPanel extends JPanel{
	private static WinPanel winPanel = null;
	
	private WinPanel() {
		init();
	}
	
	//提供一个全局的静态方法
    public static WinPanel getWinPanel(){
        if(winPanel == null){
            synchronized(WinPanel.class){
                if(winPanel == null){
                	winPanel = new WinPanel();
                }
            }
        }
        return winPanel;
    }
    
    Checkerboard checkerboard = Checkerboard.getCheckerboard();
    
    /**
     * <p>Title: init</p>
     * <p>Description: 该面板的初始化方法</p>
     */
    public void init() {
    	//设置此面板出现的位置以及大小
    	this.setBounds(0, 0, 900, 800);
    	//加入监听器
		this.addMouseListener(new WinMouseListener());
		this.addMouseMotionListener(new WinMouseMotionListener());
    }

	@Override
	public void paint(Graphics g) {
		try {
			//利用双缓冲技术，防止屏幕闪烁
			BufferedImage bufferImage = new BufferedImage(900,830,BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferImage.createGraphics();
			
			//添加棋盘图片
			if(checkerboard.getGame_result() == 1) {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBLACK_WIN_IMAGE_URL())), 0, 0, this);
			}else if(checkerboard.getGame_result() == 2){
				graphics.drawImage(ImageIO.read(new File(checkerboard.getWHITE_WIN_IMAGE_URL())), 0, 0, this);
			}else if(checkerboard.getGame_result() == 0) {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getDEUCE_IMAGE_URL())), 0, 0, this);
			}
			//添加游戏时间与胜方游戏步数
			graphics.setFont(new Font("黑体",Font.BOLD,30));
			graphics.setColor(Color.BLACK);
			graphics.drawString(CommonAlgorithm.timeFormat(), 490, 418);
			if(checkerboard.getGame_result() == 0) {
				graphics.drawString(String.valueOf(19*19), 490, 459);
			}else {
				graphics.drawString(String.valueOf(CommonAlgorithm.stepCount()), 490, 459);
			}
			
			
			if(Toolbar.getToolbar().getCURRENT_BUTTON().equals("another_game")) {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBIG_ANOTHER_GAME_IMAGE_URL())), 285, 488, this);
			}else {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getANOTHER_GAME_IMAGE_URL())), 290, 490, this);
			}
			
			if(Toolbar.getToolbar().getCURRENT_BUTTON().equals("back_menu")) {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBIG_BACK_MENU_IMAGE_URL())), 475, 488, this);
			}else {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBACK_MENU_IMAGE_URL())), 480, 490, this);
			}
			
			g.drawImage(bufferImage, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
