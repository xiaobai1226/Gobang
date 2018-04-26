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
    //玩家落子点获胜组合
  	private boolean[][][] playerTable = new boolean[19][19][1020];  
  	//机器人落子点获胜组合
  	private boolean[][][] robotTable = new boolean[19][19][1020];
  	//玩家落子点获胜组合（备份记录，用与悔棋）
  	private List<boolean[][][]> playerTableRecord = new ArrayList<boolean[][][]>();
  	//机器人落子点获胜组合（备份记录，用与悔棋）
  	private List<boolean[][][]> robotTableRecord = new ArrayList<boolean[][][]>();
  	//所有能赢的情况
  	private int[][] win = new int[2][1020];
  	//所有能赢的情况（备份记录，用与悔棋）
  	private List<int[][]> winRecord = new ArrayList<int[][]>();
  	//玩家与电脑得分
  	private int[][][] scores = new int[2][19][19];
  	//先下子一方
  	private boolean first_player; 

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

	public boolean[][][] getPlayerTable() {
		return playerTable;
	}

	public void setPlayerTable(boolean[][][] playerTable) {
		this.playerTable = playerTable;
	}

	public boolean[][][] getRobotTable() {
		return robotTable;
	}

	public void setRobotTable(boolean[][][] robotTable) {
		this.robotTable = robotTable;
	}

	public int[][] getWin() {
		return win;
	}

	public void setWin(int[][] win) {
		this.win = win;
	}
	
	public int[][][] getScores() {
		return scores;
	}

	public void setScores(int[][][] scores) {
		this.scores = scores;
	}

	public boolean isFirst_player() {
		return first_player;
	}

	public void setFirst_player(boolean first_player) {
		this.first_player = first_player;
	}
	
	public Thread getTimerThread() {
		return timerThread;
	}

	public void setTimerThread(Thread timerThread) {
		this.timerThread = timerThread;
	}
	
	public List<boolean[][][]> getPlayerTableRecord() {
		return playerTableRecord;
	}

	public void setPlayerTableRecord(List<boolean[][][]> playerTableRecord) {
		this.playerTableRecord = playerTableRecord;
	}

	public List<boolean[][][]> getRobotTableRecord() {
		return robotTableRecord;
	}

	public void setRobotTableRecord(List<boolean[][][]> robotTableRecord) {
		this.robotTableRecord = robotTableRecord;
	}

	public List<int[][]> getWinRecord() {
		return winRecord;
	}

	public void setWinRecord(List<int[][]> winRecord) {
		this.winRecord = winRecord;
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
			
			initWinCombination();
			
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
    
    public void initWinCombination() {
		//遍历所有的五连子可能情况的权值  
        //横 
		int icount = 0;
        for(int i=0; i<19; i++)  
            for(int j=0;j<15;j++){  
                for(int k=0;k<5;k++){  
                    playerTable[j+k][i][icount] = true;  
                    robotTable[j+k][i][icount] = true;  
                }  
                icount++;  
            }  
        //竖  
        for(int i=0;i<19;i++)  
            for(int j=0;j<15;j++){  
                for(int k=0;k<5;k++){  
                    playerTable[i][j+k][icount] = true;  
                    robotTable[i][j+k][icount] = true;  
                }  
                icount++;  
            }  
        //右斜  
        for(int i=0;i<15;i++)  
            for(int j=0;j<15;j++){  
                for(int k=0;k<5;k++){  
                    playerTable[j+k][i+k][icount] = true;  
                    robotTable[j+k][i+k][icount] = true;  
                }  
                icount++;  
            }  
        //左斜  
        for(int i=0;i<15;i++) {
        	for(int j=18;j>=4;j--){  
                for(int k=0;k<5;k++){  
                    playerTable[j-k][i+k][icount] = true;  
                    robotTable[j-k][i+k][icount] = true;  
                }  
                icount++;  
            }  
        }
	}
}
