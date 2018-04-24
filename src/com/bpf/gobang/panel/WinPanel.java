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
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.entity.UniversalBoard;
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
    
    private Checkerboard checkerboard;
    private UniversalBoard universalBoard = new UniversalBoard();
    
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
			//根据当前页面选择使用的棋盘属性
			checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
			
			//添加棋盘图片
			if(checkerboard.getGame_result() == 1) {
				g.drawImage(ImageIO.read(new File(universalBoard.getBLACK_WIN_IMAGE_URL())), 0, 0, this);
			}else if(checkerboard.getGame_result() == 2){
				g.drawImage(ImageIO.read(new File(universalBoard.getWHITE_WIN_IMAGE_URL())), 0, 0, this);
			}else if(checkerboard.getGame_result() == 0) {
				g.drawImage(ImageIO.read(new File(universalBoard.getDEUCE_IMAGE_URL())), 0, 0, this);
			}
			//添加游戏时间与胜方游戏步数
			g.setFont(new Font("黑体",Font.BOLD,30));
			g.setColor(Color.BLACK);
			g.drawString(CommonAlgorithm.timeFormat(), 490, 418);
			if(checkerboard.getGame_result() == 0) {
				g.drawString(String.valueOf(19*19), 490, 459);
			}else {
				g.drawString(String.valueOf(CommonAlgorithm.stepCount()), 490, 459);
			}
			
			
			if(Toolbar.getToolbar().getCURRENT_BUTTON().equals("another_game")) {
				g.drawImage(ImageIO.read(new File(universalBoard.getBIG_ANOTHER_GAME_IMAGE_URL())), 285, 488, this);
			}else {
				g.drawImage(ImageIO.read(new File(universalBoard.getANOTHER_GAME_IMAGE_URL())), 290, 490, this);
			}
			
			if(Toolbar.getToolbar().getCURRENT_BUTTON().equals("back_menu")) {
				g.drawImage(ImageIO.read(new File(universalBoard.getBIG_BACK_MENU_IMAGE_URL())), 475, 488, this);
			}else {
				g.drawImage(ImageIO.read(new File(universalBoard.getBACK_MENU_IMAGE_URL())), 480, 490, this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
