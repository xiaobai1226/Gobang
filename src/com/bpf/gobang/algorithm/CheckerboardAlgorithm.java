package com.bpf.gobang.algorithm;

import com.bpf.gobang.entity.Checkerboard;

/**
 * <p>Title: CheckerboardAlgorithm</p>
 * <p>Description: 关于棋盘的算法</p>
 * @author	白鹏飞
 * @date	2018年4月15日下午10:18:47
 * @version 1.0.0
 */
public class CheckerboardAlgorithm {
	/**
	 * <p>Title: calculationPositionByCoordinate</p>
	 * <p>Description: 根据坐标计算绘图位置</p>
	 * @param calculation 坐标点位置
	 * @return
	 */
	public static int calculationPositionByCoordinate(int calculation) {
		int width = Checkerboard.getCheckerboard().getCHECKERBOARD_BORDER_WIDTH();
		
		return calculation % width > width/2 ? calculation - calculation % width + width/2 : calculation - calculation % width - width/2;
	}
	
	/**
	 * <p>Title: calculationCoordinateByIndex</p>
	 * <p>Description: 根据索引计算绘图位置</p>
	 * @param index
	 * @return
	 */
	public static int calculationCoordinateByIndex(int index) {
		int width = Checkerboard.getCheckerboard().getCHECKERBOARD_BORDER_WIDTH();
		
		return width * index + width - width/2;
	}
	
	/**
	 * <p>Title: calculationIndexByCoordinate</p>
	 * <p>Description: 根据坐标计算数组索引</p>
	 * @param calculation
	 * @return
	 */
	public static int calculationIndexByCoordinate(int calculation) {
		int width = Checkerboard.getCheckerboard().getCHECKERBOARD_BORDER_WIDTH();
		
		return calculation % width > width/2 ? (calculation - calculation % width + width) / width - 1 : (calculation - calculation % width) / width - 1;
	}
}
