package Player;

import java.awt.Point;
import myPanel.playingPanel;

public class Map {
	//�������Ĵ�С
	int width=88;
	int height=68;
	int buildingsNum=8;
	playingPanel myPlayingPanel;
	public Point buildingsLeftTopPosition[];  //���������Ͻ�λ������
	public Point buildingsMidPosition[];      //�������м�λ������
	public Point turningPoint1[];             //��һ��·�ߵ�ת�۵�����
	public Point turningPoint2[];             //�ڶ���·�ߵ�ת�۵�����
	

	//���������Ͻ�λ������
	void setBuildingsLeftTopPosition(){};
	//�������м�λ������
	void setBuildingsMidPosition(){};
	//��һ��·�ߵ�ת�۵�����
	void setTurningPoint1(){};
	//�ڶ���·�ߵ�ת�۵�����
	void setTurningPoint2(){};
	
	//�Ը���������и�ֵ�ķ���
	public Map() {
		//���������Ͻ�λ������
		setBuildingsLeftTopPosition();
		//�������м�λ������
		setBuildingsMidPosition();
		//��һ��·�ߵ�ת�۵�����
		setTurningPoint1();
		//�ڶ���·�ߵ�ת�۵�����
		setTurningPoint2();
	}
}