package Buildings;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Bullet.BoomBullet;
import Monster.Monster;
import Monster.Monsters;
import Player.Player;

import java.awt.Image;

public class boomTower extends Buildings {
	
	//�������ۼ�
	public static int cost[] = new int[] {130,200,260};
	public static int sale[] = new int[] {100,180,210};
	/*
	 * ��ը���趨
	 * ��ը�������幥������������������������
	 * ������		 	 12 20 30
	 * ������� 		 4s 3s 2s
	 * ��������  		 130 150 210
	 * ���� 			 130 200 260
	 * �ۼ�			 100 180 210
	 */
	public boomTower(Point picPoint, Point mainPoint, Monsters monsters) {
		super(picPoint, mainPoint, monsters);
		//��ʼ���ڵ�
		myBullet = new BoomBullet(new Point(picPoint.x+40, picPoint.y+10));
		//��ʼ��img
		images = new Image[15];
		int count = 0; 						//����images���鸳ֵ�ļ򵥼���
		for(int i = 1; i < 4; i++) {
			for(int j = 1; j < 6 ;j++) {
				String path = "./pic/Towers/boomTower/����"+String.valueOf(i)+"����"+String.valueOf(j)+".png";
				images[count++] = Toolkit.getDefaultToolkit().getImage(path);
			}
		}
		//��ʼ������������ʼ��
		attackAnimation = 0;
		//��ʼ���������룬���������������
		attack = new int[] {12,20,24};
		attackGap = new double[] {4,3,2};
		attackRange = new int[] {130,150,210};
		//���������߳�
		new Thread(this).start();	
	}

	//��дpaint����������ʦ����ʱ�Լ�����ʱ����е���
	public void paint(Graphics graphics,JPanel panel) {
		//��ӦͼƬ˳��
		int level[] = new int[] {0,5,10};
		graphics.drawImage(images[level[nowLevel]+attackAnimation],picPoint.x,picPoint.y,imgSize.width,imgSize.height, panel);
	}

	//ִ��һ�������Ĺ�������
	public void animation(Monster monster) {
		attackAnimation++;
		try {Thread.sleep(150);} catch (InterruptedException e) {e.printStackTrace();}
		if (attackAnimation == 2) myBullet.attackMonster(monster);
		if (attackAnimation == 4) {
			attackAnimation = 0;
			try {Thread.sleep((long) (attackGap[nowLevel]*600));} catch (InterruptedException e) {e.printStackTrace();	}
		}
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
				radius = Math.sqrt(Math.pow(Math.abs(mainPoint.x - monsters.getmonsters()[i].location.x), 2)+Math.pow(Math.abs(mainPoint.y - monsters.getmonsters()[i].location.y), 2));
				if(radius<= attackRange[nowLevel]) {
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
	//�������̣߳���Ҳ�����һֱִ��
	public void run() {
		// ֻҪ����һֱ�ڹ�����Χ�ھ�һֱ���ù�������
		while(Player.getBlood()>0 && !deadThread) {
			if (isAttack(monsters) && !isStop) animation(monsters.getmonsters()[nowMonster]);
		}
	}

	@Override
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
