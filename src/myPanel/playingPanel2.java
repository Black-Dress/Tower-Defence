package myPanel;

import Monster.Monsters;
import Player.*;;
public class playingPanel2 extends gamePanel  {

	private static final long serialVersionUID = 715696295887839417L;
	public playingPanel2() {
		super();
		//初始化地图
		myMap = new Map2();
		//初始化怪物
		myMonsters = new Monsters(myMap.turningPoint1,myMap.turningPoint2);
		p.setMonsters(myMonsters);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
}
