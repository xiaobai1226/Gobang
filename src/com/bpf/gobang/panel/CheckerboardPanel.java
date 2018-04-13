package com.bpf.gobang.panel;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.entity.Checkerboard;

/**
 * <p>Title: CheckerboardPanel</p>
 * <p>Description: 棋盘面板</p>
 * @author	白鹏飞
 * @date	2018年4月13日下午3:37:58
 * @version 1.0.0
 */
public class CheckerboardPanel extends JPanel{
	private static CheckerboardPanel checkerboardPanel = null;
	
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
    	
    }

	@Override
	public void paint(Graphics g) {
		try {
			//添加棋盘图片
			g.drawImage(ImageIO.read(new File(checkerboard.getCHECKERBOARD_IMAGE_URL())), 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
