
package Buildings;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import Bullet.MagicBullet;
import Monster.*;
import Player.Player;
import Player.imageGather;

/**
 * @author ASUS
 *
 */

public final class magicTower extends Buildings  {
	//开销与售价
	public static int cost[] = new int[] {100,160,200};
	public static int sale[] = new int[] {70,140,210};
	/**
	 * 魔法塔设定
	 * 魔法塔，单体攻击，总共三级.
	 * 攻击力：10, 18, 26
	 * 攻击距离：100，160，200
	 * 攻击间隔：3s，2s，1s（实际上是要比这个快的）
	 * 购买价格：160，200，250
	 * 出售价格：70，140，210
	 */
	//构造函数
	public magicTower(Point picPoint , Point mainPoint,Monsters monsters) {
		super(picPoint, mainPoint, monsters);
		//初始化相应的炮弹
		myBullet = new MagicBullet(new Point(picPoint.x+20, picPoint.y-20));
		//攻击画面从第三张开始
		attackAnimation = 3;
		//设置攻击力，攻击范围，攻击间隔
		attack = new int[] {10,18,26};
		attackGap = new double[] {3,2,1};
		attackRange = new int[] {140,160,180};
		//启动自我线程
		new Thread(this).start();
	}
	//重写paint方法，在巫师攻击时以及升级时会进行调用
	public void paint(Graphics graphics,JPanel panel) {
		graphics.drawImage(imageGather.magicTowerImg[nowLevel],picPoint.x,picPoint.y,imgSize.width,imgSize.height, panel);
		graphics.drawImage(imageGather.magicTowerImg[attackAnimation], picPoint.x, picPoint.y,imgSize.width,imgSize.height, panel);
	}
	//执行一次完整的攻击动画，并调用bullet的射击方法
	public void animation(Monster monster) {
			attackAnimation++;
			try {	Thread.sleep(100);	} catch (Exception e) {			}
			if (attackAnimation == 6 ) {
				//一次攻击动画完成之后休息一下
				myBullet.attackMonster(monster);
				try {	Thread.sleep((long) (attackGap[nowLevel]*500));	} catch (Exception e) {		}
				attackAnimation = 3;
			}
	}
	//判断接受点是否为空
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
				if(monsters.getmonsters()[i]!=null) {
					radius = getDistance(mainPoint, monsters.getmonsters()[i].location);
					if(radius <= attackRange[nowLevel]) {
						nowMonster = i;
						flag = true;
						break;
					}
				}
			}
		}else {
			flag = false;
		}
		return flag;
	}
	//完成一次攻击动画
	public void run() {
		// 只要怪物一直在攻击范围内就一直调用攻击动画
		while(Player.getBlood()>0 && !deadThread) {
			if (isAttack(monsters) && !isStop) animation(monsters.getmonsters()[nowMonster]);
		}
	}
	//贩卖防御塔
	public int forSale() {
		return sale[nowLevel];
	}
	//获得当前等级升级开销
	public int getCost() {
		if (nowLevel+1<=2) return cost[nowLevel+1];
		else return cost[nowLevel];	
	}
}

