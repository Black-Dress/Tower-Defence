/**
 * 
 */
package Buildings;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;
import Bullet.Bullet;
import Monster.*;
import Player.Music;


/**
 * @author ASUS
 *
 */
public abstract class Buildings implements Runnable  {
	//用于接受外界怪兽参数的monster
	protected Monsters monsters;
	//用于判断是否正在绘制防御塔
	protected boolean isStop;
	//当前获得的单体怪兽
	protected int nowMonster;
	//图片大小
	protected Dimension imgSize;
	//自身在游戏中的位置
	protected Point mainPoint;
	//自身应该处在jpanel中的位置
	protected Point picPoint;
	//攻击动画从哪一项开始
	protected int attackAnimation;
	//攻击力
	protected int []attack;
	//攻击范围
	protected int []attackRange;
	//攻击间隔
	protected double []attackGap;
	//当前等级
	protected int nowLevel;
	//防御塔相应的炮弹，用父类调用子类
	protected Bullet myBullet;
	//外观
	Image []images;
	//用于停止线程
	protected boolean deadThread = false;
	//构造函数
	public Buildings(Point picPoint,Point mainPoint,Monsters monsters) {
		nowLevel = 0;							//初始化等级
		nowMonster = 0;							//初始化第几个怪兽
		this.picPoint = picPoint;				//初始化防御塔的位置
		this.mainPoint = mainPoint;				//初始化防御塔在游戏中的位置
		this.monsters = monsters;				//初始化怪兽
		isStop = false;
		imgSize = new Dimension(88, 68);
		Music.play(10, 1);						//播放音效
	}
	//获得当前等级
	public int  getNowLevel() {
		return nowLevel;
	}
	//升级
	public int levelUp() {
		if(nowLevel<3) nowLevel++;
		myBullet.levelUp(nowLevel);
		return nowLevel;
	}
	//绘制塔
	public abstract void paint(Graphics graphics,JPanel panel) ;		
	//攻击动画
	public abstract void animation(Monster monster) throws InterruptedException ;
	//线程
	public abstract void run() ;
	//贩卖防御塔
	public abstract int forSale();
	//获得当前升级建造开销
	public abstract int getCost();
	//是否能进行攻击
	public abstract boolean isAttack(Monsters monsters);
	//获得塔的中心位置
	public Point getMainPoint() {
		return mainPoint;
	}
	//获得塔的攻击范围
	public int[] getAttackRange() {
		return attackRange;
	}
	//获得子弹
	public Bullet getMyBullet() {
		return myBullet;
	}
	//暂停游戏
	public void stop() {
		if (isStop) isStop = false;
		else isStop = true;
	}
	//distance
	public double getDistance(Point xPoint,Point yPoint) {
		double radius = 0;
		radius = Math.sqrt(Math.pow(xPoint.y-yPoint.y, 2)+Math.pow(xPoint.x - yPoint.x, 2));
		return radius;
	}
	//让线程停止
	public void destroy() {
		if (deadThread)	deadThread = false;
		else deadThread = true;
		
	}
}
