package Buildings;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import Bullet.ArrowBullet;
import Monster.*;
import Player.Player;
import Player.imageGather;

public class arrowTower extends Buildings {
	//开销与售价
	public static int cost[] = new int[] {80,160,240};
	public static int sale[] = new int[] {70,140,210};
	/*
	 * 箭塔设定
	 * 箭塔，单体攻击，三级，射速快
	 * 攻击力：8, 12, 16
	 * 攻击距离：120, 145, 185
	 * 攻击间隔：2s， 1s， 0.6s
	 * 购买价格：80，160， 240
	 * 出售价格：60，  140， 220
	 */
	//初始化函数
	public arrowTower(Point picPoint,Point mainPoint,Monsters monsters) {
		//父类构造函数
		super(picPoint, mainPoint, monsters);
		//初始化相应的炮弹	
		myBullet = new ArrowBullet(new Point(picPoint.x+40, picPoint.y+10));
		//攻击画面从第三张开始
		attackAnimation = 3;
		//设置攻击力，攻击范围，攻击间隔，建造花费，贩卖盈利
		attack = new int[] {8,12,16};
		attackRange = new int[] {120,145,185};
		attackGap = new double[] {2,1,0.6};		
		//启动自我线程
		new Thread(this).start();
	}
	//执行一次完整的攻击动画
	public void animation(Monster monster) {
		attackAnimation++;
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		if (attackAnimation == 6) {
			myBullet.attackMonster(monster);
			try {Thread.sleep((long) (attackGap[nowLevel]*500));} catch (InterruptedException e) {e.printStackTrace();	}
			attackAnimation = 3;
		}
	}
	//判断怪兽是否已导入
	public boolean isMonsterNull() {
		if(monsters == null) return true;
		else return false;
	}
	//判断是否处在攻击范围内
	public boolean isAttack(Monsters monsters) {
		this.monsters = monsters;
		//判断是否有怪兽在攻击范围内
		boolean flag = false;
		//判断是否还有活着的怪物
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
	//防御塔线程，玩家不死就一直执行
	public void run() {
		// 只要怪物一直在攻击范围内就一直调用攻击动画
		while(Player.getBlood()>0 && !deadThread) {
			if (isAttack(monsters) && !isStop) animation(monsters.getmonsters()[nowMonster]);
		}
	}
	//贩卖防御塔
	public int forSale() {
		// TODO 自动生成的方法存根
		return sale[nowLevel];
	}
	//获得升级花销
	public int getCost() {
		if (nowLevel+1<=2) return cost[nowLevel+1];
		else return cost[nowLevel];	
	}

	
}
