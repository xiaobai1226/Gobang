package com.bpf.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: UniversalBoard</p>
 * <p>Description: 通用棋盘属性</p>
 * @author	白鹏飞
 * @date	2018年4月24日上午10:24:54
 * @version 1.0.0
 */
public class UniversalBoard {
	public UniversalBoard() {
		init();
	}
	
	//棋盘窗体宽度
    private int CHECKERBOARD_WIDTH;
    //棋盘窗体高度
    private int CHECKERBOARD_HEIGHT;  
    //棋盘边界宽度
    private int CHECKERBOARD_BORDER_WIDTH;  
    //棋盘图片位置
    private String CHECKERBOARD_IMAGE_URL;  
    //光标图片位置
    private String CURSOR_IMAGE_URL; 
    //黑色棋子图片位置
    private String BLACK_CHESS_PIECES_IMAGE_URL; 
    //白色棋子图片位置
    private String WHITE_CHESS_PIECES_IMAGE_URL; 
    //黑色棋子图片(大)位置
    private String BIG_BLACK_CHESS_PIECES_IMAGE_URL; 
    //白色棋子图片(大)位置
    private String BIG_WHITE_CHESS_PIECES_IMAGE_URL; 
    //黑色棋子胜利图片
    private String BLACK_WIN_IMAGE_URL;
    //白色棋子胜利图片
    private String WHITE_WIN_IMAGE_URL;
    //和棋图片
    private String DEUCE_IMAGE_URL;
    //胜利图片
    private String WIN_IMAGE_URL;
    //失败图片
    private String FAIL_IMAGE_URL;
    //再来一局按钮图片
    private String ANOTHER_GAME_IMAGE_URL;
    //返回菜单按钮图片
    private String BACK_MENU_IMAGE_URL;
    //再来一局按钮图片（大）
    private String BIG_ANOTHER_GAME_IMAGE_URL;
    //返回菜单按钮图片（大）
    private String BIG_BACK_MENU_IMAGE_URL;
    //红点图片位置
    private String POINT_IMAGE_URL; 
   

	public int getCHECKERBOARD_WIDTH() {
		return CHECKERBOARD_WIDTH;
	}

	public int getCHECKERBOARD_HEIGHT() {
		return CHECKERBOARD_HEIGHT;
	}

	public int getCHECKERBOARD_BORDER_WIDTH() {
		return CHECKERBOARD_BORDER_WIDTH;
	}

	public String getCHECKERBOARD_IMAGE_URL() {
		return CHECKERBOARD_IMAGE_URL;
	}
	
	public String getCURSOR_IMAGE_URL() {
		return CURSOR_IMAGE_URL;
	}

	public String getPOINT_IMAGE_URL() {
		return POINT_IMAGE_URL;
	}

	public String getBLACK_CHESS_PIECES_IMAGE_URL() {
		return BLACK_CHESS_PIECES_IMAGE_URL;
	}

	public String getWHITE_CHESS_PIECES_IMAGE_URL() {
		return WHITE_CHESS_PIECES_IMAGE_URL;
	}

	public String getBIG_BLACK_CHESS_PIECES_IMAGE_URL() {
		return BIG_BLACK_CHESS_PIECES_IMAGE_URL;
	}

	public String getBIG_WHITE_CHESS_PIECES_IMAGE_URL() {
		return BIG_WHITE_CHESS_PIECES_IMAGE_URL;
	}
	
	public String getBLACK_WIN_IMAGE_URL() {
		return BLACK_WIN_IMAGE_URL;
	}

	public String getWHITE_WIN_IMAGE_URL() {
		return WHITE_WIN_IMAGE_URL;
	}

	public String getANOTHER_GAME_IMAGE_URL() {
		return ANOTHER_GAME_IMAGE_URL;
	}

	public String getBACK_MENU_IMAGE_URL() {
		return BACK_MENU_IMAGE_URL;
	}

	public String getBIG_ANOTHER_GAME_IMAGE_URL() {
		return BIG_ANOTHER_GAME_IMAGE_URL;
	}

	public String getBIG_BACK_MENU_IMAGE_URL() {
		return BIG_BACK_MENU_IMAGE_URL;
	}

	public String getDEUCE_IMAGE_URL() {
		return DEUCE_IMAGE_URL;
	}

	public String getWIN_IMAGE_URL() {
		return WIN_IMAGE_URL;
	}

	public String getFAIL_IMAGE_URL() {
		return FAIL_IMAGE_URL;
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
			CHECKERBOARD_BORDER_WIDTH = Integer.valueOf(properties.getProperty("checkerboard_border_width"));
			CHECKERBOARD_IMAGE_URL = properties.getProperty("checkerboard_image_url");
			CURSOR_IMAGE_URL = properties.getProperty("cursor_image_url");
			BLACK_CHESS_PIECES_IMAGE_URL = properties.getProperty("black_chess_pieces_image_url");
			WHITE_CHESS_PIECES_IMAGE_URL = properties.getProperty("white_chess_pieces_image_url");
			BIG_BLACK_CHESS_PIECES_IMAGE_URL = properties.getProperty("big_black_chess_pieces_image_url");
			BIG_WHITE_CHESS_PIECES_IMAGE_URL = properties.getProperty("big_white_chess_pieces_image_url");
			POINT_IMAGE_URL = properties.getProperty("point_image_url");
			BLACK_WIN_IMAGE_URL = properties.getProperty("black_win_image_url");
			WHITE_WIN_IMAGE_URL = properties.getProperty("white_win_image_url");
			DEUCE_IMAGE_URL = properties.getProperty("deuce_image_url");
			WIN_IMAGE_URL = properties.getProperty("win_image_url");
			FAIL_IMAGE_URL = properties.getProperty("fail_image_url");
			ANOTHER_GAME_IMAGE_URL = properties.getProperty("another_game_image_url");
			BACK_MENU_IMAGE_URL = properties.getProperty("back_menu_image_url");
			BIG_ANOTHER_GAME_IMAGE_URL = properties.getProperty("big_another_game_image_url");
			BIG_BACK_MENU_IMAGE_URL = properties.getProperty("big_back_menu_image_url");
			
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
