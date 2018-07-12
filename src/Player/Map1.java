package Player;

import java.awt.Point;

public class Map1 extends Map{
	void setBuildingsLeftTopPosition() {
 		buildingsLeftTopPosition = new Point[buildingsNum];
		buildingsLeftTopPosition[0]=new Point(520, 65); 
		buildingsLeftTopPosition[1]=new Point(280, 140);
		buildingsLeftTopPosition[2]=new Point(130, 300);
		buildingsLeftTopPosition[3]=new Point(225, 425);
		buildingsLeftTopPosition[4]=new Point(445, 440);
		buildingsLeftTopPosition[5]=new Point(330, 295);
		buildingsLeftTopPosition[6]=new Point(550, 320);
		buildingsLeftTopPosition[7]=new Point(675, 260);
	}
	void setBuildingsMidPosition() {
		buildingsMidPosition = new Point[buildingsNum];
		for(int i=0;i<buildingsNum;i++) {
			buildingsMidPosition[i]=new Point( buildingsLeftTopPosition[i].x+width/2,
				                               buildingsLeftTopPosition[i].y+height/2 );
		}
	}
	void setTurningPoint1(){
		turningPoint1 = new Point[13];
		turningPoint1[0] = new Point(455, -50);
		turningPoint1[1]= new Point(455,0);
		turningPoint1[2]= new Point(445,195);
		turningPoint1[3]= new Point(420,220);
		turningPoint1[4]= new Point(360,240);
		turningPoint1[5]= new Point(300,260);
		turningPoint1[6]= new Point(240,390);
		turningPoint1[7]= new Point(320,430);
		turningPoint1[8]= new Point(550,430);
		turningPoint1[9]= new Point(690,450);
		turningPoint1[10]= new Point(780,380);
		turningPoint1[11]= new Point(1000,365);
		turningPoint1[12]= new Point(1000, 1000);
	}
	void setTurningPoint2(){
		turningPoint2 = new Point[11];
		turningPoint2[0]= new Point(482,-50);
		turningPoint2[1]= new Point(482,0);
		turningPoint2[2]= new Point(469,200);
		turningPoint2[3]= new Point(440,240);
		turningPoint2[4]= new Point(390,262);
		turningPoint2[5]= new Point(315,275);
		turningPoint2[6]= new Point(300,400);
		turningPoint2[7]= new Point(671,420);
		turningPoint2[8]= new Point(760,360);
		turningPoint2[9]= new Point(1000,345);
		turningPoint2[10]= new Point(1000,1000);
	}
	public Map1() {
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
