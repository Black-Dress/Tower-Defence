package Player;

import java.awt.Point;
import myPanel.playingPanel;

public class Map {
	//防御塔的大小
	int width=88;
	int height=68;
	int buildingsNum=8;
	playingPanel myPlayingPanel;
	public Point buildingsLeftTopPosition[];  //防御塔左上角位置数组
	public Point buildingsMidPosition[];      //防御塔中间位置数组
	public Point turningPoint1[];             //第一条路线的转折点数组
	public Point turningPoint2[];             //第二条路线的转折点数组
	

	//防御塔左上角位置数组
	void setBuildingsLeftTopPosition(){};
	//防御塔中间位置数组
	void setBuildingsMidPosition(){};
	//第一条路线的转折点数组
	void setTurningPoint1(){};
	//第二条路线的转折点数组
	void setTurningPoint2(){};
	
	//对各个数组进行赋值的方法
	public Map() {
		//防御塔左上角位置数组
		setBuildingsLeftTopPosition();
		//防御塔中间位置数组
		setBuildingsMidPosition();
		//第一条路线的转折点数组
		setTurningPoint1();
		//第二条路线的转折点数组
		setTurningPoint2();
	}
}