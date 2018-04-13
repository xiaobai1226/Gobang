package com.bpf.gobang.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.bpf.gobang.algorithm.CommonAlgorithm;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Menu;
import com.bpf.gobang.panel.MenuPanel;
/**
 * <p>Title: MenuFrame</p>
 * <p>Description: 菜单窗体</p>
 * @author	白鹏飞
 * @date	2018年4月9日下午2:40:57
 * @version 1.0.0
 */
public class MenuFrame extends JFrame{
private static MenuFrame menuFrame = null;
	
	private MenuFrame() {
		init();
	}
	
	//提供一个全局的静态方法
    public static MenuFrame getMenuFrame(){
        if(menuFrame == null){
            synchronized(MenuFrame.class){
                if(menuFrame == null){
                	menuFrame = new MenuFrame();
                }
            }
        }
        return menuFrame;
    }
	
	
	Common common = Common.getCommon();
	Menu menu = Menu.getMenu();
	
	public void init(){
		//获得菜单宽度
		int menu_width = menu.getMENU_WIDTH();
		//获得菜单高度
		int menu_height = menu.getMENU_HEIGHT();
		//获得菜单窗体坐标位置
		int[] menuCoordinate = CommonAlgorithm.calculateFramePosition(menu_width, menu_height);
		
		//设置窗体标题
		this.setTitle("五子棋");
		//设置窗体图标
		this.setIconImage((new ImageIcon(common.getICON_IMAGE_URL())).getImage());
		//设置窗体大小位置
		this.setBounds(menuCoordinate[0], menuCoordinate[1], menu_width, menu_height);
		//设置关闭窗体后，程序结束
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//新建一个菜单面板
		MenuPanel menuPanel = MenuPanel.getMenuPanel();
		//将菜单面板添加到窗体中
		this.add(menuPanel);
		
		//使当前窗体显示
		this.setVisible(true);
		//禁止当前窗体大小改变
		this.setResizable(false);
	}
}
