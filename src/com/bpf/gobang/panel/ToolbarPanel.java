package com.bpf.gobang.panel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.listener.CheckerboardMouseListener;
import com.bpf.gobang.listener.CheckerboardMouseMotionListener;

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
    
    public void init() {
    	this.setBounds(800, 0, 100, 800);
//    	this.addMouseListener(new CheckerboardMouseListener());
//    	this.addMouseMotionListener(new CheckerboardMouseMotionListener());
    }

	@Override
	public void paint(Graphics g) {
		try {
			//利用双缓冲技术，防止屏幕闪烁
			BufferedImage bufferImage = new BufferedImage(800,830,BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferImage.createGraphics();
			
			//添加棋盘图片
//			graphics.drawImage(ImageIO.read(new File(checkerboard.getCHECKERBOARD_IMAGE_URL())), 0, 0, this);
			
			//添加棋盘图片
			if(checkerboard.getCurrent_chess_piece()) {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBIG_WHITE_CHESS_PIECES_IMAGE_URL())), 0, 0, this);
			}else {
				graphics.drawImage(ImageIO.read(new File(checkerboard.getBIG_BLACK_CHESS_PIECES_IMAGE_URL())), 0, 0, this);
			}
			
			g.drawImage(bufferImage, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
