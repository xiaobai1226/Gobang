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
 * <p>Title: CheckerboardPanel</p>
 * <p>Description: 棋盘面板</p>
 * @author	白鹏飞
 * @date	2018年4月13日下午3:37:58
 * @version 1.0.0
 */
public class CheckerboardPanel extends JPanel{
	private static CheckerboardPanel checkerboardPanel = null;
	//当前棋盘信息
	private int[][] checkerboard_situation;
	//光标位置信息
	private int cursorX;
	private int cursorY;
	
	private CheckerboardPanel() {
		init();
	}
	
	//提供一个全局的静态方法
    public static CheckerboardPanel getCheckerboardPanel(){
        if(checkerboardPanel == null){
            synchronized(CheckerboardPanel.class){
                if(checkerboardPanel == null){
                	checkerboardPanel = new CheckerboardPanel();
                }
            }
        }
        return checkerboardPanel;
    }
    
    Checkerboard checkerboard = Checkerboard.getCheckerboard();
    
    public void init() {
    	this.addMouseListener(new CheckerboardMouseListener());
    	this.addMouseMotionListener(new CheckerboardMouseMotionListener());
    }

	@Override
	public void paint(Graphics g) {
		try {
			//利用双缓冲技术，防止屏幕闪烁
			BufferedImage bufferImage = new BufferedImage(800,830,BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferImage.createGraphics();
			
			//添加棋盘图片
			graphics.drawImage(ImageIO.read(new File(checkerboard.getCHECKERBOARD_IMAGE_URL())), 0, 0, this);
			//添加光标
			//只有在棋盘范围内时，才显示此选择框
			if(checkerboard.getCursor_position()[0] != -1 && checkerboard.getCursor_position()[1] != -1) {
				cursorX = CheckerboardAlgorithm.calculationPositionByCoordinate(checkerboard.getCursor_position()[0]);
				cursorY = CheckerboardAlgorithm.calculationPositionByCoordinate(checkerboard.getCursor_position()[1]);
				
				graphics.drawImage(ImageIO.read(new File(checkerboard.getCURSOR_IMAGE_URL())), cursorX, cursorY, this);
			}
			
			//获取当前棋盘信息
			checkerboard_situation = checkerboard.getCheckerboardSituation();
			//根据棋盘数组绘画棋盘上应有的棋子
			for(int i = 0; i < checkerboard_situation.length; i++) {
				for(int j = 0; j < checkerboard_situation.length; j++) {
					if(checkerboard_situation[i][j] == 1) {
						graphics.drawImage(ImageIO.read(new File(checkerboard.getBLACK_CHESS_PIECES_IMAGE_URL())), //棋子图片
								CheckerboardAlgorithm.calculationCoordinateByIndex(i), //棋子横坐标
								CheckerboardAlgorithm.calculationCoordinateByIndex(j), //棋子纵坐标
								this);
					}else if(checkerboard_situation[i][j] == 2) {
						graphics.drawImage(ImageIO.read(new File(checkerboard.getWHITE_CHESS_PIECES_IMAGE_URL())), 
								CheckerboardAlgorithm.calculationCoordinateByIndex(i), 
								CheckerboardAlgorithm.calculationCoordinateByIndex(j),
								this);
					}
				}
			}
			
			g.drawImage(bufferImage, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
