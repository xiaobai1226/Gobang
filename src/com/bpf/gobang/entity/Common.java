package com.bpf.gobang.entity;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: Common</p>
 * <p>Description: 包含游戏的一些通用的参数</p>
 * @author	白鹏飞
 * @date	2018年4月9日下午3:07:37
 * @version 1.0.0
 */
public class Common {
	private static Common common = null;
	
	private Common() {
		init();
	}
	
	//提供一个全局的静态方法
    public static Common getCommon(){
        if(common == null){
            synchronized(Common.class){
                if(common == null){
                	common = new Common();
                }
            }
        }
        return common;
    }
    
	//屏幕宽度
	private Integer SCREEN_WIDTH = null;
	//屏幕高度
	private Integer SCREEN_HEIGHT = null;
	//图标路径
	private String ICON_IMAGE_URL = null;
	//当前界面按钮状态
	private boolean current_status;
	
	//当前页面
	private String current_page;
	//菜单页面标识
	public static final String MENU = "menu";
	//人机对战难度菜单页面标识
	public static final String DIFFICULTY_MENU = "difficulty_menu";
	//人机对战页面标识
	public static final String COMPUTER_VS_PLAYER = "computer_vs_player";
	//双人对弈页面标识
	public static final String TWOPLAYER = "twoplayer";
	
	//当前按钮
    private String current_button;
	
    //人机模式按钮标识
    public static final String COMPUTER_VS_PLAYER_BUTTON = "computer_vs_player_button";
    //双人对弈按钮标识
    public static final String TWO_PLAYER_BUTTON = "two_player_button";
	
	
	
	public int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}
	public int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}
	
	public String getICON_IMAGE_URL() {
		return ICON_IMAGE_URL;
	}

	public boolean getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(boolean current_status) {
		this.current_status = current_status;
	}

	public String getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(String current_page) {
		this.current_page = current_page;
	}
	
	public String getCurrent_button() {
		return current_button;
	}

	public void setCurrent_button(String current_button) {
		this.current_button = current_button;
	}

	/**
	 * <p>Title: init</p>
	 * <p>Description: 该类初始化方法，创建该类实例时，从配置文件中获取值赋给成员变量</p>
	 */
	public void init() {
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			inputStream = new FileInputStream("cfg/cfg.properties");
			properties.load(inputStream);
			
			current_button = "";
			current_page = "menu";
			current_status = true;
			SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
			SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
			ICON_IMAGE_URL = properties.getProperty("icon_image_url");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
