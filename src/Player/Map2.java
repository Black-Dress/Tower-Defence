package Player;

import java.awt.Point;

public class Map2 extends Map{
	void setBuildingsLeftTopPosition() {
		buildingsLeftTopPosition = new Point[buildingsNum];
		buildingsLeftTopPosition[0]=new Point(470, 100);
		buildingsLeftTopPosition[1]=new Point(280, 140);
		buildingsLeftTopPosition[2]=new Point(115, 300);
		buildingsLeftTopPosition[3]=new Point(500, 465);
		buildingsLeftTopPosition[4]=new Point(665, 200);
		buildingsLeftTopPosition[5]=new Point(285, 250);
		buildingsLeftTopPosition[6]=new Point(445, 350);
		buildingsLeftTopPosition[7]=new Point(660, 340);
	}
	void setBuildingsMidPosition() {
		buildingsMidPosition = new Point[buildingsNum];
		for(int i=0;i<buildingsNum;i++) {
			buildingsMidPosition[i]=new Point( buildingsLeftTopPosition[i].x+width/2,
				                               buildingsLeftTopPosition[i].y+height/2 );
		}
	}
	void setTurningPoint1(){
		turningPoint1 = new Point[9];
		turningPoint1[0] = new Point(413, 680);
		turningPoint1[1]= new Point(413,612);
		turningPoint1[2]= new Point(417,481);
		turningPoint1[3]= new Point(226,349);
		turningPoint1[4]= new Point(207,206);
		turningPoint1[5]= new Point(308,110);
		turningPoint1[6]= new Point(539,69);
		turningPoint1[7]= new Point(542,0);
		turningPoint1[8] = new Point(545, -30);
	}
	void setTurningPoint2(){
		turningPoint2 = new Point[9];
		turningPoint2[0]= new Point(447,680);
		turningPoint2[1]= new Point(447,611);
		turningPoint2[2]= new Point(459,486);
		turningPoint2[3]= new Point(713,476);
		turningPoint2[4]= new Point(823,401);
		turningPoint2[5]= new Point(816,245);
		turningPoint2[6]= new Point(575,72);
		turningPoint2[7]= new Point(576,0);
		turningPoint2[8]= new Point(576,-30);
	}
	public Map2() {
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