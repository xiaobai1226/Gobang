package com.bpf.gobang.function.runnable;

import java.util.List;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.function.CheckerboardFunction;

public class ConnectedPiecesFlashRunnable implements Runnable{
	@Override
	public void run() {
		//将光标位置置为无效
		Checkerboard.getCheckerboard().getCursor_position()[0] = -1;
		Checkerboard.getCheckerboard().getCursor_position()[1] = -1;
		//获取五子连珠位置记录
		List<int[]> chessConnectedRecord = Checkerboard.getCheckerboard().getChessConnectedRecord();
		//获取棋盘记录
		int[][] checkerboardSituation = Checkerboard.getCheckerboard().getCheckerboardSituation();
		//初始化当前状态为true
		boolean flag = true;
		//判断闪烁棋子颜色
		int color= Checkerboard.getCheckerboard().getCurrent_chess_piece() ? 1 : 2;
		//五子连珠反复闪烁三次
		for(int i = 0; i < 6; i++) {
			for(int[] record : chessConnectedRecord) {
				checkerboardSituation[record[0]][record[1]] = flag ? 0 : color;
			}
			flag = !flag;
			
			CheckerboardFrame.getCheckerboardFrame().repaint();
			
			try {
				if(i != 5) {
					Thread.sleep(250);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//闪烁完毕后，增加胜利面板
		CheckerboardFunction.addWinPanel();
	}

}
