package com.bpf.gobang.function;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.CheckerboardFrame;

/**
 * <p>Title: ToolbarFunction</p>
 * <p>Description: 计时器功能</p>
 * @author	白鹏飞
 * @date	2018年4月19日下午10:22:32
 * @version 1.0.0
 */
public class TimerFunction implements Runnable{
	Checkerboard checkerboard = Checkerboard.getCheckerboard();
	 
	@Override
	public void run() {
		while(checkerboard.getTimerRun()) {
			checkerboard.setGameTime(checkerboard.getGameTime() + 1);
			
			CheckerboardFrame.getCheckerboardFrame().repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
