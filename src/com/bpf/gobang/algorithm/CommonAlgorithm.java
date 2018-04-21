package com.bpf.gobang.algorithm;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;

/**
 * <p>Title: CommonAlgorithm</p>
 * <p>Description: 通用的算法</p>
 * @author	白鹏飞
 * @date	2018年4月9日下午3:52:46
 * @version 1.0.0
 */
public class CommonAlgorithm {
	/**
	 * <p>Title: calculateFramePosition</p>
	 * <p>Description: 计算窗体位置</p>
	 * @param frameWidth 窗体宽度
	 * @param frameHeight 窗体高度
	 * @return 返回窗体所在位置横纵坐标
	 */
	public static int[] calculateFramePosition(int frameWidth, int frameHeight) {
		//获得通用属性
		Common common = Common.getCommon();
		
		int frameLocation[] = new int[2];
		//计算窗体位置横坐标
		frameLocation[0] = (common.getSCREEN_WIDTH() - frameWidth) / 2;
		//计算窗体位置纵坐标
		frameLocation[1] = (common.getSCREEN_HEIGHT() - frameHeight) / 2;
		
		return frameLocation;
	}
	
	/**
	 * <p>Title: timeFormat</p>
	 * <p>Description: 将描述转换成相应格式的字符串</p>
	 * @return
	 */
	public static String timeFormat() {
		//获取游戏时间
		int gameTime = Checkerboard.getCheckerboard().getGameTime();
		//进行格式转换
		String minute = gameTime/60 < 10 ? "0" + (gameTime/60) : "" + (gameTime/60);
		String second = gameTime%60 < 10 ? "0" + (gameTime%60) : "" + (gameTime%60);
		
		return minute + ":" + second;
	}
	
	/**
	 * <p>Title: stepCount</p>
	 * <p>Description: 计算胜利方所走棋数</p>
	 * @return
	 */
	public static int stepCount() {
		//获取双方总步数
		int totalStepCount = Checkerboard.getCheckerboard().getChessRecord().size();
		
		return totalStepCount % 2 == 0 ? totalStepCount / 2 : (totalStepCount - 1) / 2 + 1;
	}
}
