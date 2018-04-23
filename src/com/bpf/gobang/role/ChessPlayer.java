package com.bpf.gobang.role;

/**
 * <p>Title: ChessPlayer</p>
 * <p>Description: 棋手接口，玩家与电脑的父接口</p>
 * @author	白鹏飞
 * @date	2018年4月22日下午6:05:17
 * @version 1.0.0
 */
public interface ChessPlayer {
	/**
	 * <p>Title: put</p>
	 * <p>Description: 下棋方法</p>
	 * @param row
	 * @param coll
	 */
	public void put(int row, int coll);
}
