package com.bpf.gobang.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.bpf.gobang.algorithm.CommonAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.panel.CheckerboardPanel;

/**
 * <p>Title: CheckerboardFrame</p>
 * <p>Description: 棋盘窗体</p>
 * @author	白鹏飞
 * @date	2018年4月13日下午3:20:20
 * @version 1.0.0
 */
public class CheckerboardFrame extends JFrame{
	private static CheckerboardFrame checkerboardFrame = null;
	
	private CheckerboardFrame() {
		init();
	}
	
	//提供一个全局的静态方法
    public static CheckerboardFrame getCheckerboardFrame(){
        if(checkerboardFrame == null){
            synchronized(CheckerboardFrame.class){
                if(checkerboardFrame == null){
                	checkerboardFrame = new CheckerboardFrame();
                }
            }
        }
        return checkerboardFrame;
    }
    
    Common common = Common.getCommon();
    Checkerboard checkerboard = Checkerboard.getCheckerboard();
    
    public void init() {
    	//获得棋盘窗体宽度
		int checkerboard_width = checkerboard.getCHECKERBOARD_WIDTH();
		//获得棋盘窗体高度
		int checkerboard_height = checkerboard.getCHECKERBOARD_HEIGHT();
		//获得棋盘窗体坐标位置
		int[] checkerboardCoordinate = CommonAlgorithm.calculateFramePosition(checkerboard_width, checkerboard_height);
		
		//设置窗体标题
		this.setTitle("五子棋");
		//设置窗体图标
		this.setIconImage((new ImageIcon(common.getICON_IMAGE_URL())).getImage());
		//设置窗体大小位置
		this.setBounds(checkerboardCoordinate[0], checkerboardCoordinate[1], checkerboard_width, checkerboard_height);
		//设置关闭窗体后，程序结束
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//新建一个菜单面板
		CheckerboardPanel checkerboardPanel = CheckerboardPanel.getCheckerboardPanel();
		//将菜单面板添加到窗体中
		this.add(checkerboardPanel);
		
		//使当前窗体显示
		this.setVisible(true);
		//禁止当前窗体大小改变
		this.setResizable(false);
    }
}
