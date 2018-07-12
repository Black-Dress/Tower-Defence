package Buildings;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import Bullet.ArrowBullet;
import Monster.*;
import Player.Player;
import Player.imageGather;

public class arrowTower extends Buildings {
	//�������ۼ�
	public static int cost[] = new int[] {80,160,240};
	public static int sale[] = new int[] {70,140,210};
	/*
	 * �����趨
	 * ���������幥�������������ٿ�
	 * ��������8, 12, 16
	 * �������룺120, 145, 185
	 * ���������2s�� 1s�� 0.6s
	 * ����۸�80��160�� 240
	 * ���ۼ۸�60��  140�� 220
	 */
	//��ʼ������
	public arrowTower(Point picPoint,Point mainPoint,Monsters monsters) {
		//���๹�캯��
		super(picPoint, mainPoint, monsters);
		//��ʼ����Ӧ���ڵ�	
		myBullet = new ArrowBullet(new Point(picPoint.x+40, picPoint.y+10));
		//��������ӵ����ſ�ʼ
		attackAnimation = 3;
		//���ù�������������Χ��������������컨�ѣ�����ӯ��
		attack = new int[] {8,12,16};
		attackRange = new int[] {120,145,185};
		attackGap = new double[] {2,1,0.6};		
		//���������߳�
		new Thread(this).start();
	}
	//ִ��һ�������Ĺ�������
	public void animation(Monster monster) {
		attackAnimation++;
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		if (attackAnimation == 6) {
			myBullet.attackMonster(monster);
			try {Thread.sleep((long) (attackGap[nowLevel]*500));} catch (InterruptedException e) {e.printStackTrace();	}
			attackAnimation = 3;
		}
	}
	//�жϹ����Ƿ��ѵ���
	public boolean isMonsterNull() {
		if(monsters == null) return true;
		else return false;
	}
	//�ж��Ƿ��ڹ�����Χ��
	public boolean isAttack(Monsters monsters) {
		this.monsters = monsters;
		//�ж��Ƿ��й����ڹ�����Χ��
		boolean flag = false;
		//�ж��Ƿ��л��ŵĹ���
		if(monsters.getmonsters() != null) {
			for(int i = 0;i < monsters.n1;i++) {
				double radius=0;
				if(monsters.getmonsters()[i]!=null)
				radius = getDistance(mainPoint, monsters.getmonsters()[i].location);
				if(radius <= attackRange[nowLevel]) {
					nowMonster = i;
					flag = true;
					break;
				}
			}
		}else {
			flag = false;
		}
		return flag;
	}
	public void paint(Graphics graphics,JPanel panel) {
		graphics.drawImage(imageGather.arrowTowerImg[nowLevel],picPoint.x,picPoint.y,imgSize.width,imgSize.height, panel);
		graphics.drawImage(imageGather.arrowTowerImg[attackAnimation], picPoint.x, picPoint.y,imgSize.width,imgSize.height, panel);
	}
	//�������̣߳���Ҳ�����һֱִ��
	public void run() {
		// ֻҪ����һֱ�ڹ�����Χ�ھ�һֱ���ù�������
		while(Player.getBlood()>0 && !deadThread) {
			if (isAttack(monsters) && !isStop) animation(monsters.getmonsters()[nowMonster]);
		}
	}
	//����������
	public int forSale() {
		// TODO �Զ����ɵķ������
		return sale[nowLevel];
	}
	//�����������
	public int getCost() {
		if (nowLevel+1<=2) return cost[nowLevel+1];
		else return cost[nowLevel];	
	}

	
}
