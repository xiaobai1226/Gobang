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
    
    private Checkerboard checkerboard;
    private Toolbar toolbar = Toolbar.getToolbar();
    private UniversalBoard universalBoard = new UniversalBoard();
    
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
			//根据当前页面选择使用的棋盘属性
			checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
			
			//添加工具栏背景图片
			g.drawImage(ImageIO.read(new File(toolbar.getTOOLBAR_BACKGROUND_IMAGE_URL())), 0, 0, this);
			
			//添加当前棋子提示图片
			if(checkerboard.getCurrent_chess_piece()) {
				g.drawImage(ImageIO.read(new File(universalBoard.getBIG_WHITE_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
			}else {
				g.drawImage(ImageIO.read(new File(universalBoard.getBIG_BLACK_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
			}
			
			g.setFont(new Font("Algerian",Font.BOLD,32));
			g.setColor(Color.BLACK);
			g.drawString(CommonAlgorithm.timeFormat(), 0, 110);
			
			//添加返回按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("back")) {
				g.drawImage(ImageIO.read(new File(toolbar.getBIG_BACK_IMAGE_URL())), 15, 137, this);
			}else {
				g.drawImage(ImageIO.read(new File(toolbar.getBACK_IMAGE_URL())), 18, 140, this);
			}
			
			//添加重玩按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("restart")) {
				g.drawImage(ImageIO.read(new File(toolbar.getBIG_RESTART_IMAGE_URL())), 17, 237, this);
			}else {
				g.drawImage(ImageIO.read(new File(toolbar.getRESTART_IMAGE_URL())), 20, 240, this);
			}
			
			//添加悔棋按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("regret")) {
				g.drawImage(ImageIO.read(new File(toolbar.getBIG_REGRET_IMAGE_URL())), 14, 337, this);
			}else {
				g.drawImage(ImageIO.read(new File(toolbar.getREGRET_IMAGE_URL())), 17, 340, this);
			}
			
			//添加提示按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("prompt")) {
				g.drawImage(ImageIO.read(new File(toolbar.getBIG_PROMPT_IMAGE_URL())), 15, 437, this);
			}else {
				g.drawImage(ImageIO.read(new File(toolbar.getPROMPT_IMAGE_URL())), 18, 440, this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
