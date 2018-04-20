package com.bpf.gobang.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.listener.CheckerboardMouseListener;
import com.bpf.gobang.listener.CheckerboardMouseMotionListener;
import com.bpf.gobang.listener.ToolbarMouseListener;
import com.bpf.gobang.listener.ToolbarMouseMotionListener;

/**
 * <p>Title: ToolbarPanel</p>
 * <p>Description: 工具栏面板</p>
 * @author	白鹏飞
 * @date	2018年4月17日下午1:22:20
 * @version 1.0.0
 */
public class ToolbarPanel extends JPanel{
	private static ToolbarPanel toolbarPanel = null;
	
	private ToolbarPanel() {
		init();
	}
	
	//提供一个全局的静态方法
    public static ToolbarPanel getToolbarPanel(){
        if(toolbarPanel == null){
            synchronized(ToolbarPanel.class){
                if(toolbarPanel == null){
                	toolbarPanel = new ToolbarPanel();
                }
            }
        }
        return toolbarPanel;
    }
    
    Checkerboard checkerboard = Checkerboard.getCheckerboard();
    Toolbar toolbar = Toolbar.getToolbar();
    
    /**
     * <p>Title: init</p>
     * <p>Description: 该面板的初始化方法</p>
     */
    public void init() {
    	//设置此面板出现的位置以及大小
    	this.setBounds(800, 0, 100, 800);
    	//加入监听器
		this.addMouseListener(new ToolbarMouseListener());
    	this.addMouseMotionListener(new ToolbarMouseMotionListener());
    }

	@Override
	public void paint(Graphics g) {
		try {
			//利用双缓冲技术，防止屏幕闪烁
			BufferedImage bufferImage = new BufferedImage(800,830,BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferImage.createGraphics();
			
			//添加工具栏背景图片
			graphics.drawImage(ImageIO.read(new File(toolbar.getTOOLBAR_BACKGROUND_IMAGE_URL())), 0, 0, this);
			
			//添加当前棋子提示图片
			if(checkerboard.getCurrent_chess_piece()) {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBIG_WHITE_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
			}else {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBIG_BLACK_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
			}
			
			int gameTime = checkerboard.getGameTime();
			
			String minute = gameTime/60 < 10 ? "0" + (gameTime/60) : "" + (gameTime/60);
			String second = gameTime%60 < 10 ? "0" + (gameTime%60) : "" + (gameTime%60);
			
			graphics.setFont(new Font("Algerian",Font.BOLD,32));
			graphics.setColor(Color.BLACK);
			graphics.drawString(minute + ":" + second, 0, 110);
			
			//添加返回按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("back")) {
				graphics.drawImage(ImageIO.read(new File(toolbar.getBIG_BACK_IMAGE_URL())), 15, 137, this);
			}else {
				graphics.drawImage(ImageIO.read(new File(toolbar.getBACK_IMAGE_URL())), 18, 140, this);
			}
			
			//添加重玩按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("restart")) {
				graphics.drawImage(ImageIO.read(new File(toolbar.getBIG_RESTART_IMAGE_URL())), 17, 237, this);
			}else {
				graphics.drawImage(ImageIO.read(new File(toolbar.getRESTART_IMAGE_URL())), 20, 240, this);
			}
			
			//添加重玩按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("regret")) {
				graphics.drawImage(ImageIO.read(new File(toolbar.getBIG_REGRET_IMAGE_URL())), 14, 337, this);
			}else {
				graphics.drawImage(ImageIO.read(new File(toolbar.getREGRET_IMAGE_URL())), 17, 340, this);
			}
			
			g.drawImage(bufferImage, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
