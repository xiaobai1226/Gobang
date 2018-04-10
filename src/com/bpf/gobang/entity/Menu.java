package com.bpf.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: Menu</p>
 * <p>Description: 包含游戏关于菜单的参数</p>
 * @author	白鹏飞
 * @date	2018年4月9日下午3:07:59
 * @version 1.0.0
 */
public class Menu {
	private static Menu menu = null;
	
	private Menu() {
		init();
	}
	
	//提供一个全局的静态方法
    public static Menu getMenu(){
        if(menu == null){
            synchronized(Menu.class){
                if(menu == null){
                	menu = new Menu();
                }
            }
        }
        return menu;
    }
    
    //菜单宽度
    private int MENU_WIDTH;
    //菜单高度
    private int MENU_HEIGHT;
    //菜单背景图片位置
    private String MENU_BACKGROUND_IMAGE_URL = null;

	public int getMENU_WIDTH() {
		return MENU_WIDTH;
	}

	public int getMENU_HEIGHT() {
		return MENU_HEIGHT;
	}
	
	public String getMENU_BACKGROUND_IMAGE_URL() {
		return MENU_BACKGROUND_IMAGE_URL;
	}

	/**
	 * <p>Title: init</p>
	 * <p>Description: 该类初始化方法，创建该类实例时，从配置文件中获取值赋给成员变量</p>
	 */
	private void init() {
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			inputStream = new FileInputStream("cfg/cfg.properties");
			properties.load(inputStream);
			
			MENU_WIDTH = Integer.valueOf(properties.getProperty("menu_width"));
			MENU_HEIGHT = Integer.valueOf(properties.getProperty("menu_height"));
			MENU_BACKGROUND_IMAGE_URL = properties.getProperty("menu_background_image_url");
		}catch(Exception e) {
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
