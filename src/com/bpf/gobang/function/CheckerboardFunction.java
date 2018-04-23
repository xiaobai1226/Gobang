package com.bpf.gobang.function;

import java.util.ArrayList;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Computer_vs_player;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.frame.MenuFrame;
import com.bpf.gobang.function.runnable.ConnectedPiecesFlashRunnable;
import com.bpf.gobang.function.runnable.TimerRunnable;
import com.bpf.gobang.panel.CheckerboardPanel;
import com.bpf.gobang.panel.ToolbarPanel;
import com.bpf.gobang.panel.WinPanel;

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
		//清空棋盘
		Checkerboard.getCheckerboard().setCheckerboardSituation(new int[19][19]);
		//清空棋盘下子记录
		Checkerboard.getCheckerboard().setChessRecord(new ArrayList<int[]>());
		//当前棋子置为黑色
		Checkerboard.getCheckerboard().setCurrent_chess_piece(false);
		//将计时器归0
		Checkerboard.getCheckerboard().setGameTime(0);
		//将当前状态置为true
		Common.getCommon().setCurrent_status(true);
		//初始化获胜组合
		Computer_vs_player.getComputer_vs_player().initWinCombination();
		//初始化
		Computer_vs_player.getComputer_vs_player().setWin(new int[2][1020]);
		//初始化玩家与电脑得分
		Computer_vs_player.getComputer_vs_player().setScores(new int[2][19][19]);
		//将计时器开关打开
		Checkerboard.getCheckerboard().setTimerRun(true);
		//获取存储的计时器线程
		Thread timer = Checkerboard.getCheckerboard().getTimerThread();
		if(!timer.isAlive()) {
			//创建并开启计时器线程
			timer = new Thread(new TimerRunnable());
			timer.start();
			//将计时器线程存储起来
			Checkerboard.getCheckerboard().setTimerThread(timer);
		}
		//将胜利面板添加到窗体中
		CheckerboardFrame.getCheckerboardFrame().remove(WinPanel.getWinPanel());
		//重绘该窗体
		CheckerboardFrame.getCheckerboardFrame().repaint();
	}
	
	/**
	 * <p>Title: backToMenu</p>
	 * <p>Description: 返回到菜单</p>
	 */
	public static void backToMenu() {
		//关闭计时器线程
		Checkerboard.getCheckerboard().setTimerRun(false);
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
}
