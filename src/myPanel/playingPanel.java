package myPanel;

import Monster.Monsters;
import Player.*;
public class playingPanel extends gamePanel  {

	private static final long serialVersionUID = 715696295887839417L;
	public playingPanel() {
		super();
		//��ʼ����ͼ
		myMap = new Map1();
		//��ʼ������
		myMonsters = new Monsters(myMap.turningPoint1,myMap.turningPoint2);
		p.setMonsters(myMonsters);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
}
