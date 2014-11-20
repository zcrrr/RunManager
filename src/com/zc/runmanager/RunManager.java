package com.zc.runmanager;

import java.util.List;
import java.util.Timer;

/**
 * @author zhangchi
 * 核心控制模块
 * 基本思想：
 * 1.一次跑步对应一个该类的实例（单例），在开始跑步的时候初始化该类，所以构造函数调用时间就是该次跑步的开始时间
 * 2.构造函数直接传递取gps间隔，因为认为gps是最主要的输入，一但初始化该类，则调用它自己的timer，定时取点，并刷新自己的值。
 * 3.数据的更新时机分两种：一种是和gps相关的，更新的时机是取得一个新的gps；一种是和时间相关的，需要使用的时候立刻取得。
 * 	 前者设置为成员变量，后者通过方法得到。原因就是时间需要取得一个实时的值。
 *   例如外界想获取跑步的距离：则调用manager.distance,外界想获取运动的总时间,则调用manager.runLast()函数
 * 4.主要逻辑在于timertask的实现，该task取gps来更新成员变量值。
 * 5.另外我觉得语音的逻辑不应该包含在该manager中。
 * 6.比赛和日常跑步本来想分开为两个manager，但是像输出记录这种想统一管理，最后还是觉得用一个，无非有些变量用不上
 */
public class RunManager {
	//一些自用的变量（供自己使用，不会直接被外部所用）
	private int runType;//跑步类型：0：平时，1：比赛
	private int runStatus;//跑步状态：0：运动，1：暂停 或者比赛时 0：赛道内，1：偏离赛道
	private int gpsIntervalSec;//取gps时间间隔，初始化的时候赋值，以后自己更新自己
	private Timer timerUpdate;//去一个gps，更新数据的timer
	private long startTimeStamp;//初始化RunManager的时间戳（毫秒），初始化意味着开始跑步
	private int pauseSecond;//总的暂停时间（秒）
	
	//下面这些变量是在timer中每次根据新的gps刷新值,外部可以访问
	public double distance;//个人距离(米)
	public int pspeed;//配速（秒）
	public int aver_speed;//平均速度（km/h）
	public List<Integer> pspeedOfEveryKm;//每公里的配速列表
	public List<Object> GPSList;//gps序列,每个点有状态，运动/暂停，赛道内/赛道外
	
	public double match_team_dis;//队伍总成绩
	public boolean match_is_in_track;//是否在赛道内
	public double match_next_dis;//距离下一交界区
	
	
	
	/**
	 * 默认构造函数，则创建一个平时跑步的实例manager
	 * @param second 取gps点的时间间隔（秒）
	 */
	public RunManager(int second){
		this.runType = 0;
		this.startTimeStamp = System.currentTimeMillis();
		//根据second启动timer。
	}
	/**
	 * 构造一个为比赛服务的manager实例
	 * @param match_team_dis 队伍已经跑的距离（米）
	 * @param second 取gps点的时间间隔（秒）
	 * @param match_map 赛道名称
	 */
	public RunManager(double match_team_dis,int second,String match_map){
		this.runType = 1;//比赛
		this.match_team_dis = match_team_dis;
		this.startTimeStamp = System.currentTimeMillis();
		//根据match_map初始化赛道
		//根据second启动timer。
	}
	/**
	 * 结束一次运动
	 */
	public void FinishOneRun(){
		//timer cancel
		//timertask cancel
	}
	/**
	 * 将这次的数据保存到数据库
	 */
	public void saveToDB(){
		
	}
	/**
	 * 返回总的运动时间
	 * @return
	 */
	public int runLast(){
		int second = (int)((System.currentTimeMillis()-this.startTimeStamp)/1000)-pauseSecond;
		return second >= 0 ? second : 0 ;
	}
	/**
	 * 改变运动状态
	 * @param status 状态
	 */
	public void changeRunStatus(int status){
		this.runStatus = status;
		//如果是日常跑步，计算pauseSecond；
	}
}
