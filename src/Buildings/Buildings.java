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
	//���ڽ��������޲�����monster
	protected Monsters monsters;
	//�����ж��Ƿ����ڻ��Ʒ�����
	protected boolean isStop;
	//��ǰ��õĵ������
	protected int nowMonster;
	//ͼƬ��С
	protected Dimension imgSize;
	//��������Ϸ�е�λ��
	protected Point mainPoint;
	//����Ӧ�ô���jpanel�е�λ��
	protected Point picPoint;
	//������������һ�ʼ
	protected int attackAnimation;
	//������
	protected int []attack;
	//������Χ
	protected int []attackRange;
	//�������
	protected double []attackGap;
	//��ǰ�ȼ�
	protected int nowLevel;
	//��������Ӧ���ڵ����ø����������
	protected Bullet myBullet;
	//���
	Image []images;
	//����ֹͣ�߳�
	protected boolean deadThread = false;
	//���캯��
	public Buildings(Point picPoint,Point mainPoint,Monsters monsters) {
		nowLevel = 0;							//��ʼ���ȼ�
		nowMonster = 0;							//��ʼ���ڼ�������
		this.picPoint = picPoint;				//��ʼ����������λ��
		this.mainPoint = mainPoint;				//��ʼ������������Ϸ�е�λ��
		this.monsters = monsters;				//��ʼ������
		isStop = false;
		imgSize = new Dimension(88, 68);
		Music.play(10, 1);						//������Ч
	}
	//��õ�ǰ�ȼ�
	public int  getNowLevel() {
		return nowLevel;
	}
	//����
	public int levelUp() {
		if(nowLevel<3) nowLevel++;
		myBullet.levelUp(nowLevel);
		return nowLevel;
	}
	//������
	public abstract void paint(Graphics graphics,JPanel panel) ;		
	//��������
	public abstract void animation(Monster monster) throws InterruptedException ;
	//�߳�
	public abstract void run() ;
	//����������
	public abstract int forSale();
	//��õ�ǰ�������쿪��
	public abstract int getCost();
	//�Ƿ��ܽ��й���
	public abstract boolean isAttack(Monsters monsters);
	//�����������λ��
	public Point getMainPoint() {
		return mainPoint;
	}
	//������Ĺ�����Χ
	public int[] getAttackRange() {
		return attackRange;
	}
	//����ӵ�
	public Bullet getMyBullet() {
		return myBullet;
	}
	//��ͣ��Ϸ
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
	//���߳�ֹͣ
	public void destroy() {
		if (deadThread)	deadThread = false;
		else deadThread = true;
		
	}
}
