package com.bpf.gobang.function;

import java.util.ArrayList;
import java.util.List;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.frame.MenuFrame;
import com.bpf.gobang.function.runnable.ConnectedPiecesFlashRunnable;
import com.bpf.gobang.function.runnable.TimerRunnable;
import com.bpf.gobang.panel.CheckerboardPanel;
import com.bpf.gobang.panel.ToolbarPanel;
import com.bpf.gobang.panel.WinPanel;
import com.bpf.gobang.role.ChessPlayer;
import com.bpf.gobang.role.Robot;

/**
 * <p>Title: CheckerboardFunction</p>
 * <p>Description: 关于棋盘的一些功能</p>
 * @author	白鹏飞
 * @date	2018年4月20日下午2:00:20
 * @version 1.0.0
 */
public class CheckerboardFunction {
	/**
	 * <p>Title: restart</p>
	 * <p>Description: 重新开始游戏</p>
	 */
	public static void restart() {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		
		//清空棋盘
		checkerboard.setCheckerboardSituation(new int[19][19]);
		//清空棋盘下子记录
		checkerboard.setChessRecord(new ArrayList<int[]>());
		//当前棋子置为黑色
		checkerboard.setCurrent_chess_piece(false);
		//将计时器归0
		checkerboard.setGameTime(0);
		//将当前状态置为true
		Common.getCommon().setCurrent_status(true);
		//初始化获胜组合
		checkerboard.initWinCombination();
		//初始化
		checkerboard.setWin(new int[2][1020]);
		//
		checkerboard.setWinRecord(new ArrayList<int[][]>());
		//
		checkerboard.setPlayerTableRecord(new ArrayList<boolean[][][]>());
		//
		checkerboard.setRobotTableRecord(new ArrayList<boolean[][][]>());
		
		//初始化玩家与电脑得分
		checkerboard.setScores(new int[2][19][19]);
		//将计时器开关打开
		checkerboard.setTimerRun(true);
		//获取存储的计时器线程
		Thread timer = checkerboard.getTimerThread();
		if(!timer.isAlive()) {
			//创建并开启计时器线程
			timer = new Thread(new TimerRunnable());
			timer.start();
			//将计时器线程存储起来
			checkerboard.setTimerThread(timer);
		}
		//将胜利面板添加到窗体中
		CheckerboardFrame.getCheckerboardFrame().remove(WinPanel.getWinPanel());
		//重绘该窗体
		CheckerboardFrame.getCheckerboardFrame().repaint();
		
		//如果电脑先行，则电脑下子
		if(Common.getCommon().getCurrent_page().equals(Common.COMPUTER_VS_PLAYER) && 
				checkerboard.getFirst_player()) {
			ChessPlayer robot = new Robot();
			robot.put(9, 9);
		}
	}
	
	/**
	 * <p>Title: backToMenu</p>
	 * <p>Description: 返回到菜单</p>
	 */
	public static void backToMenu() {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		
		//关闭计时器线程
		checkerboard.setTimerRun(false);
		//点击此按钮关闭棋盘窗体，打开菜单窗体
		CheckerboardFrame.getCheckerboardFrame().dispose();
		MenuFrame.getMenuFrame().setVisible(true);
	}
	
	/**
	 * <p>Title: connectedPiecesFlash</p>
	 * <p>Description: 胜利一方的相连棋子闪烁</p>
	 */
	public static void connectedPiecesFlash() {
		//创建棋子闪烁线程
		Thread connectedPiecesFlashThread = new Thread(new ConnectedPiecesFlashRunnable());
		//开始线程
		connectedPiecesFlashThread.start();
	}
	
	/**
	 * <p>Title: addWinPanel</p>
	 * <p>Description: 增加胜利面板</p>
	 */
	public static void addWinPanel() {
		//新建一个胜利面板
		WinPanel winPanel = WinPanel.getWinPanel();
		//新建一个棋盘面板
		CheckerboardPanel checkerboardPanel = CheckerboardPanel.getCheckerboardPanel();
		//新建一个工具面板
		ToolbarPanel toolbarPanel = ToolbarPanel.getToolbarPanel();
		//将胜利面板添加到窗体中
		CheckerboardFrame.getCheckerboardFrame().add(winPanel);
		//将棋盘面板添加到窗体中
		CheckerboardFrame.getCheckerboardFrame().add(checkerboardPanel);
		//将工具面板添加到窗体中
		CheckerboardFrame.getCheckerboardFrame().add(toolbarPanel);
		
		CheckerboardFrame.getCheckerboardFrame().repaint();
	}
	
	/**
	 * <p>Title: copyWin</p>
	 * <p>Description: 备份所有能赢的情况</p>
	 */
	public static void copyWin() {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		int[][] win = new int[2][1020];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 1020; j++) {
				win[i][j] = checkerboard.getWin()[i][j];
			}
		}
		checkerboard.getWinRecord().add(win);
	}
	
	/**
	 * <p>Title: copyTable</p>
	 * <p>Description: 备份落子点获胜组合</p>
	 */
	public static void copyTable() {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		boolean[][][] playerTable = new boolean[19][19][1020];
		boolean[][][] robotTable = new boolean[19][19][1020];
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				for(int k = 0; k < 1020; k++) {
					playerTable[i][j][k] = checkerboard.getPlayerTable()[i][j][k];
					robotTable[i][j][k] = checkerboard.getRobotTable()[i][j][k];
				}
			}
		}
		
		checkerboard.getPlayerTableRecord().add(playerTable);
		checkerboard.getRobotTableRecord().add(robotTable);
		
	}
	
	/**
	 * <p>Title: restoreWin</p>
	 * <p>Description: 恢复上一次所有能赢的情况</p>
	 */
	public static void restoreWin() {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		int size = checkerboard.getWinRecord().size() - 1;
		checkerboard.setWin(checkerboard.getWinRecord().get(size));
		
		checkerboard.getWinRecord().remove(size);
	}
	
	/**
	 * <p>Title: restoreTable</p>
	 * <p>Description: 恢复上一次落子点获胜组合</p>
	 */
	public static void restoreTable() {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		
		int playerTableSize = checkerboard.getPlayerTableRecord().size() - 1;
		int robotTableSize = checkerboard.getRobotTableRecord().size() - 1;
		
		checkerboard.setPlayerTable(checkerboard.getPlayerTableRecord().get(playerTableSize));
		checkerboard.setRobotTable(checkerboard.getRobotTableRecord().get(robotTableSize));
		
		checkerboard.getPlayerTableRecord().remove(playerTableSize);
		checkerboard.getRobotTableRecord().remove(robotTableSize);
	}
}
