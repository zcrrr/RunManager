package com.zc.runmanager;

import java.util.List;
import java.util.Timer;

/**
 * @author zhangchi
 * ���Ŀ���ģ��
 * ����˼�룺
 * 1.һ���ܲ���Ӧһ�������ʵ�������������ڿ�ʼ�ܲ���ʱ���ʼ�����࣬���Թ��캯������ʱ����Ǹô��ܲ��Ŀ�ʼʱ��
 * 2.���캯��ֱ�Ӵ���ȡgps�������Ϊ��Ϊgps������Ҫ�����룬һ����ʼ�����࣬��������Լ���timer����ʱȡ�㣬��ˢ���Լ���ֵ��
 * 3.���ݵĸ���ʱ�������֣�һ���Ǻ�gps��صģ����µ�ʱ����ȡ��һ���µ�gps��һ���Ǻ�ʱ����صģ���Ҫʹ�õ�ʱ������ȡ�á�
 * 	 ǰ������Ϊ��Ա����������ͨ�������õ���ԭ�����ʱ����Ҫȡ��һ��ʵʱ��ֵ��
 *   ����������ȡ�ܲ��ľ��룺�����manager.distance,������ȡ�˶�����ʱ��,�����manager.runLast()����
 * 4.��Ҫ�߼�����timertask��ʵ�֣���taskȡgps�����³�Ա����ֵ��
 * 5.�����Ҿ����������߼���Ӧ�ð����ڸ�manager�С�
 * 6.�������ճ��ܲ�������ֿ�Ϊ����manager�������������¼������ͳһ��������Ǿ�����һ�����޷���Щ�����ò���
 */
public class RunManager {
	//һЩ���õı��������Լ�ʹ�ã�����ֱ�ӱ��ⲿ���ã�
	private int runType;//�ܲ����ͣ�0��ƽʱ��1������
	private int runStatus;//�ܲ�״̬��0���˶���1����ͣ ���߱���ʱ 0�������ڣ�1��ƫ������
	private int gpsIntervalSec;//ȡgpsʱ��������ʼ����ʱ��ֵ���Ժ��Լ������Լ�
	private Timer timerUpdate;//ȥһ��gps���������ݵ�timer
	private long startTimeStamp;//��ʼ��RunManager��ʱ��������룩����ʼ����ζ�ſ�ʼ�ܲ�
	private int pauseSecond;//�ܵ���ͣʱ�䣨�룩
	
	//������Щ��������timer��ÿ�θ����µ�gpsˢ��ֵ,�ⲿ���Է���
	public double distance;//���˾���(��)
	public int pspeed;//���٣��룩
	public int aver_speed;//ƽ���ٶȣ�km/h��
	public List<Integer> pspeedOfEveryKm;//ÿ����������б�
	public List<Object> GPSList;//gps����,ÿ������״̬���˶�/��ͣ��������/������
	
	public double match_team_dis;//�����ܳɼ�
	public boolean match_is_in_track;//�Ƿ���������
	public double match_next_dis;//������һ������
	
	
	
	/**
	 * Ĭ�Ϲ��캯�����򴴽�һ��ƽʱ�ܲ���ʵ��manager
	 * @param second ȡgps���ʱ�������룩
	 */
	public RunManager(int second){
		this.runType = 0;
		this.startTimeStamp = System.currentTimeMillis();
		//����second����timer��
	}
	/**
	 * ����һ��Ϊ���������managerʵ��
	 * @param match_team_dis �����Ѿ��ܵľ��루�ף�
	 * @param second ȡgps���ʱ�������룩
	 * @param match_map ��������
	 */
	public RunManager(double match_team_dis,int second,String match_map){
		this.runType = 1;//����
		this.match_team_dis = match_team_dis;
		this.startTimeStamp = System.currentTimeMillis();
		//����match_map��ʼ������
		//����second����timer��
	}
	/**
	 * ����һ���˶�
	 */
	public void FinishOneRun(){
		//timer cancel
		//timertask cancel
	}
	/**
	 * ����ε����ݱ��浽���ݿ�
	 */
	public void saveToDB(){
		
	}
	/**
	 * �����ܵ��˶�ʱ��
	 * @return
	 */
	public int runLast(){
		int second = (int)((System.currentTimeMillis()-this.startTimeStamp)/1000)-pauseSecond;
		return second >= 0 ? second : 0 ;
	}
	/**
	 * �ı��˶�״̬
	 * @param status ״̬
	 */
	public void changeRunStatus(int status){
		this.runStatus = status;
		//������ճ��ܲ�������pauseSecond��
	}
}
