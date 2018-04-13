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
    //联机模式按钮图片位置
    private String PLAY_ONLINE_IMAGE_URL = null;
    //人机对弈图片位置
    private String PLAYER_VS_COMPUTER_IMAGE_URL = null;
    //双人对弈图片位置
    private String TWO_PLAYER_GAME_IMAGE_URL = null;
    //关于图片位置
    private String ABOUT_IMAGE_URL = null;
    //战绩图片位置
    private String RECORD_IMAGE_URL = null;
    //战绩（大）图片位置
    private String BIG_RECORD_IMAGE_URL = null;
    //规则图片位置
    private String GAME_RULES_IMAGE_URL = null;
    //规则（大）图片位置
    private String BIG_GAME_RULES_IMAGE_URL = null;
    
    //当前按钮
    private String CURRENT_BUTTON;

	public int getMENU_WIDTH() {
		return MENU_WIDTH;
	}

	public int getMENU_HEIGHT() {
		return MENU_HEIGHT;
	}
	
	public String getMENU_BACKGROUND_IMAGE_URL() {
		return MENU_BACKGROUND_IMAGE_URL;
	}

	public String getPLAY_ONLINE_IMAGE_URL() {
		return PLAY_ONLINE_IMAGE_URL;
	}
	
	public String getPLAYER_VS_COMPUTER_IMAGE_URL() {
		return PLAYER_VS_COMPUTER_IMAGE_URL;
	}

	public String getTWO_PLAYER_GAME_IMAGE_URL() {
		return TWO_PLAYER_GAME_IMAGE_URL;
	}

	public String getABOUT_IMAGE_URL() {
		return ABOUT_IMAGE_URL;
	}
	
	public String getRECORD_IMAGE_URL() {
		return RECORD_IMAGE_URL;
	}

	public String getBIG_RECORD_IMAGE_URL() {
		return BIG_RECORD_IMAGE_URL;
	}

	public String getGAME_RULES_IMAGE_URL() {
		return GAME_RULES_IMAGE_URL;
	}

	public String getBIG_GAME_RULES_IMAGE_URL() {
		return BIG_GAME_RULES_IMAGE_URL;
	}

	
	
	public String getCURRENT_BUTTON() {
		return CURRENT_BUTTON;
	}

	public void setCURRENT_BUTTON(String CURRENT_BUTTON) {
		this.CURRENT_BUTTON = CURRENT_BUTTON;
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
			PLAY_ONLINE_IMAGE_URL = properties.getProperty("play_online_image_url");
			PLAYER_VS_COMPUTER_IMAGE_URL = properties.getProperty("player_vs_computer_image_url");
			TWO_PLAYER_GAME_IMAGE_URL = properties.getProperty("two_player_game_image_url");
			ABOUT_IMAGE_URL = properties.getProperty("about_image_url");
		    RECORD_IMAGE_URL = properties.getProperty("record_image_url");
		    BIG_RECORD_IMAGE_URL = properties.getProperty("big_record_image_url");
		    GAME_RULES_IMAGE_URL = properties.getProperty("game_rules_image_url");
		    BIG_GAME_RULES_IMAGE_URL = properties.getProperty("big_game_rules_image_url");
			
			CURRENT_BUTTON = "";
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
