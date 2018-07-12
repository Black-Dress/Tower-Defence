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
	
	//开销与售价
	public static int cost[] = new int[] {130,200,260};
	public static int sale[] = new int[] {100,180,210};
	/*
	 * 爆炸塔设定
	 * 爆炸塔，单体攻击，三级，射速慢，攻击高
	 * 攻击力		 	 12 20 30
	 * 攻击间隔 		 4s 3s 2s
	 * 攻击距离  		 130 150 210
	 * 花销 			 130 200 260
	 * 售价			 100 180 210
	 */
	public boomTower(Point picPoint, Point mainPoint, Monsters monsters) {
		super(picPoint, mainPoint, monsters);
		//初始化炮弹
		myBullet = new BoomBullet(new Point(picPoint.x+40, picPoint.y+10));
		//初始化img
		images = new Image[15];
		int count = 0; 						//用于images数组赋值的简单计数
		for(int i = 1; i < 4; i++) {
			for(int j = 1; j < 6 ;j++) {
				String path = "./pic/Towers/boomTower/炮塔"+String.valueOf(i)+"动作"+String.valueOf(j)+".png";
				images[count++] = Toolkit.getDefaultToolkit().getImage(path);
			}
		}
		//初始化攻击动画开始点
		attackAnimation = 0;
		//初始化攻击距离，攻击间隔，攻击力
		attack = new int[] {12,20,24};
		attackGap = new double[] {4,3,2};
		attackRange = new int[] {130,150,210};
		//启动自我线程
		new Thread(this).start();	
	}

	//重写paint方法，在巫师攻击时以及升级时会进行调用
	public void paint(Graphics graphics,JPanel panel) {
		//适应图片顺序
		int level[] = new int[] {0,5,10};
		graphics.drawImage(images[level[nowLevel]+attackAnimation],picPoint.x,picPoint.y,imgSize.width,imgSize.height, panel);
	}

	//执行一次完整的攻击动画
	public void animation(Monster monster) {
		attackAnimation++;
		try {Thread.sleep(150);} catch (InterruptedException e) {e.printStackTrace();}
		if (attackAnimation == 2) myBullet.attackMonster(monster);
		if (attackAnimation == 4) {
			attackAnimation = 0;
			try {Thread.sleep((long) (attackGap[nowLevel]*600));} catch (InterruptedException e) {e.printStackTrace();	}
		}
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
	//防御塔线程，玩家不死就一直执行
	public void run() {
		// 只要怪物一直在攻击范围内就一直调用攻击动画
		while(Player.getBlood()>0 && !deadThread) {
			if (isAttack(monsters) && !isStop) animation(monsters.getmonsters()[nowMonster]);
		}
	}

	@Override
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
