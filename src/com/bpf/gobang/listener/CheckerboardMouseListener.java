package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.bpf.gobang.algorithm.RobotAlgorithm;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.role.ChessPlayer;
import com.bpf.gobang.role.Player;
import com.bpf.gobang.role.Robot;

public class CheckerboardMouseListener extends MouseAdapter{
	private ChessPlayer player = new Player();
	private ChessPlayer robot = new Robot();
	private boolean order = false;
	RobotAlgorithm robotAlgorithm = new RobotAlgorithm();
	@Override
	public void mouseClicked(MouseEvent e) {
		if(Common.getCommon().getCurrent_page().equals(Common.TWOPLAYER)) {
			player.put(e.getX(), e.getY());
		}
		
		if(Common.getCommon().getCurrent_page().equals(Common.COMPUTER_VS_PLAYER)) {
			player.put(e.getX(), e.getY());
			order = !order;
			int[] bestFallingPoint = robotAlgorithm.bestFallingPoint();
			robot.put(bestFallingPoint[0], bestFallingPoint[1]);
			order = !order;
		}
	}
}
