package com.bpf.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title: Checkerboard</p>
 * <p>Description: 包含游戏关于棋盘的参数</p>
 * @author	白鹏飞
 * @date	2018年4月13日下午3:35:29
 * @version 1.0.0
 */
public class Checkerboard {
	private static Map<String, Checkerboard> checkerboardMap = new Hashtable<String, Checkerboard>();
	
	private Checkerboard() {
		init();
	}
	
	//提供一个全局的静态方法
    public static Checkerboard getCheckerboard(String key){
        if(checkerboardMap.get(key) == null){
            synchronized(Checkerboard.class){
                if(checkerboardMap.get(key) == null){
                	checkerboardMap.put(key, new Checkerboard());
                }
            }
        }
        return checkerboardMap.get(key);
    }
    
    private Thread timerThread;
	public Thread getTimerThread() {
		return timerThread;
	}

	public void setTimerThread(Thread timerThread) {
		this.timerThread = timerThread;
	}
	
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
    //游戏结果 0为和棋，1为黑棋胜，2为白棋胜
    private int game_result;
    //游戏时间
    private int gameTime;
    //计时器线程是否运行标志位
    private boolean timerRun;

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

	public int getGame_result() {
		return game_result;
	}

	public void setGame_result(int game_result) {
		this.game_result = game_result;
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
