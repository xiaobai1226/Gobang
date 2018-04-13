package com.bpf.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: Checkerboard</p>
 * <p>Description: 包含游戏关于棋盘的参数</p>
 * @author	白鹏飞
 * @date	2018年4月13日下午3:35:29
 * @version 1.0.0
 */
public class Checkerboard {
	private static Checkerboard checkerboard = null;
	
	private Checkerboard() {
		init();
	}
	
	//提供一个全局的静态方法
    public static Checkerboard getCheckerboard(){
        if(checkerboard == null){
            synchronized(Checkerboard.class){
                if(checkerboard == null){
                	checkerboard = new Checkerboard();
                }
            }
        }
        return checkerboard;
    }
    
    //棋盘窗体宽度
    private int CHECKERBOARD_WIDTH;
    //棋盘窗体高度
    private int CHECKERBOARD_HEIGHT;  
    //棋盘图片位置
    private String CHECKERBOARD_IMAGE_URL;  
    
    public int getCHECKERBOARD_WIDTH() {
		return CHECKERBOARD_WIDTH;
	}

	public int getCHECKERBOARD_HEIGHT() {
		return CHECKERBOARD_HEIGHT;
	}

	public String getCHECKERBOARD_IMAGE_URL() {
		return CHECKERBOARD_IMAGE_URL;
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
			
			CHECKERBOARD_WIDTH = Integer.valueOf(properties.getProperty("checkerboard_width"));
			CHECKERBOARD_HEIGHT = Integer.valueOf(properties.getProperty("checkerboard_height"));
			CHECKERBOARD_IMAGE_URL = properties.getProperty("checkerboard_image_url");
//			PLAY_ONLINE_IMAGE_URL = properties.getProperty("play_online_image_url");
//			PLAYER_VS_COMPUTER_IMAGE_URL = properties.getProperty("player_vs_computer_image_url");
//			TWO_PLAYER_GAME_IMAGE_URL = properties.getProperty("two_player_game_image_url");
//			ABOUT_IMAGE_URL = properties.getProperty("about_image_url");
//		    RECORD_IMAGE_URL = properties.getProperty("record_image_url");
//		    BIG_RECORD_IMAGE_URL = properties.getProperty("big_record_image_url");
//		    GAME_RULES_IMAGE_URL = properties.getProperty("game_rules_image_url");
//		    BIG_GAME_RULES_IMAGE_URL = properties.getProperty("big_game_rules_image_url");
			
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
