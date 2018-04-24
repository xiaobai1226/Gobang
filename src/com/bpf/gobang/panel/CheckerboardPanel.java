package com.bpf.gobang.panel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.UniversalBoard;
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
    
    private Checkerboard checkerboard;
    private UniversalBoard universalBoard = new UniversalBoard();
    
    /**
     * <p>Title: init</p>
     * <p>Description: 该面板的初始化方法</p>
     */
    public void init() {
    	//设置此面板出现的位置以及大小
    	this.setBounds(0, 0, 800, 800);
    	//加入监听器
		this.addMouseListener(new CheckerboardMouseListener());
		this.addMouseMotionListener(new CheckerboardMouseMotionListener());
    }

	@Override
	public void paint(Graphics g) {
		try {
			//根据当前页面选择使用的棋盘属性
			checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
			
			//添加棋盘图片
			g.drawImage(ImageIO.read(new File(universalBoard.getCHECKERBOARD_IMAGE_URL())), 0, 0, this);
			//添加光标
			//只有在棋盘范围内时，才显示此选择框
			int x = CheckerboardAlgorithm.calculationIndexByCoordinate(checkerboard.getCursor_position()[0]);
			int y = CheckerboardAlgorithm.calculationIndexByCoordinate(checkerboard.getCursor_position()[1]);
			int num = (x == y && y == -1) ? 0 : checkerboard.getCheckerboardSituation()[x][y];
			if(num == 0 && checkerboard.getCursor_position()[0] != -1 && checkerboard.getCursor_position()[1] != -1) {
				cursorX = CheckerboardAlgorithm.calculationPositionByCoordinate(checkerboard.getCursor_position()[0]);
				cursorY = CheckerboardAlgorithm.calculationPositionByCoordinate(checkerboard.getCursor_position()[1]);
				
				g.drawImage(ImageIO.read(new File(universalBoard.getCURSOR_IMAGE_URL())), cursorX, cursorY, this);
			}
			
			//获取当前棋盘信息
			checkerboard_situation = checkerboard.getCheckerboardSituation();
			//根据棋盘数组绘画棋盘上应有的棋子
			for(int i = 0; i < checkerboard_situation.length; i++) {
				for(int j = 0; j < checkerboard_situation.length; j++) {
					if(checkerboard_situation[i][j] == 1) {
						g.drawImage(ImageIO.read(new File(universalBoard.getBLACK_CHESS_PIECES_IMAGE_URL())), //棋子图片
								CheckerboardAlgorithm.calculationCoordinateByIndex(i), //棋子横坐标
								CheckerboardAlgorithm.calculationCoordinateByIndex(j), //棋子纵坐标
								this);
					}else if(checkerboard_situation[i][j] == 2) {
						g.drawImage(ImageIO.read(new File(universalBoard.getWHITE_CHESS_PIECES_IMAGE_URL())), 
								CheckerboardAlgorithm.calculationCoordinateByIndex(i), 
								CheckerboardAlgorithm.calculationCoordinateByIndex(j),
								this);
					}
				}
			}
			
			//在最后下的棋子上画上一个红点
			int length = checkerboard.getChessRecord().size();
			if(length > 0) {
				int lastI = checkerboard.getChessRecord().get(length - 1)[0];
				int lastJ = checkerboard.getChessRecord().get(length - 1)[1];
				g.drawImage(ImageIO.read(new File(universalBoard.getPOINT_IMAGE_URL())), //红点图片
						CheckerboardAlgorithm.calculationCoordinateByIndex(lastI) + 13, //红点横坐标
						CheckerboardAlgorithm.calculationCoordinateByIndex(lastJ) + 13, //红点纵坐标
						this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
