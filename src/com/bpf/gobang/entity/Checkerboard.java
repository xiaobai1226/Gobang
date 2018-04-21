package com.bpf.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
    private Thread timerThread;
	public Thread getTimerThread() {
		return timerThread;
	}

	public void setTimerThread(Thread timerThread) {
		this.timerThread = timerThread;
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
    //棋盘落子情况 0为无子，1为黑子，2为白子
    private int[][] checkerboardSituation;
    //下子点记录
    private List<int[]> chessRecord;
    //下子相连点记录
    private List<int[]> chessConnectedRecord;
    //光标位置
    private int[] cursor_position;
    //当前棋子 false是黑色，true是白色
    private boolean current_chess_piece;
    //游戏时间
    private int gameTime;
    //计时器线程是否运行标志位
    private boolean timerRun;

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

	public int[][] getCheckerboardSituation() {
		return checkerboardSituation;
	}

	public void setCheckerboardSituation(int[][] checkerboardSituation) {
		this.checkerboardSituation = checkerboardSituation;
	}

	public int[] getCursor_position() {
		return cursor_position;
	}
	
	public boolean getCurrent_chess_piece() {
		return current_chess_piece;
	}

	public void setCurrent_chess_piece(boolean current_chess_piece) {
		this.current_chess_piece = current_chess_piece;
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

	public List<int[]> getChessRecord() {
		return chessRecord;
	}

	public void setChessRecord(List<int[]> chessRecord) {
		this.chessRecord = chessRecord;
	}

	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
	
	public boolean getTimerRun() {
		return timerRun;
	}

	public void setTimerRun(boolean timerRun) {
		this.timerRun = timerRun;
	}

	public List<int[]> getChessConnectedRecord() {
		return chessConnectedRecord;
	}

	public void setChessConnectedRecord(List<int[]> chessConnectedRecord) {
		this.chessConnectedRecord = chessConnectedRecord;
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
			
			timerRun = true;
			gameTime = 0;
			chessRecord = new ArrayList<int[]>();
			checkerboardSituation = new int[19][19];
			cursor_position = new int[2];
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
