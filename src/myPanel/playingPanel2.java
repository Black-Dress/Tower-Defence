package myPanel;

import Monster.Monsters;
import Player.*;;
public class playingPanel2 extends gamePanel  {

	private static final long serialVersionUID = 715696295887839417L;
	public playingPanel2() {
		super();
		//��ʼ����ͼ
		myMap = new Map2();
		//��ʼ������
		myMonsters = new Monsters(myMap.turningPoint1,myMap.turningPoint2);
		p.setMonsters(myMonsters);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
}
