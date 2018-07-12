
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
	//�������ۼ�
	public static int cost[] = new int[] {100,160,200};
	public static int sale[] = new int[] {70,140,210};
	/**
	 * ħ�����趨
	 * ħ���������幥�����ܹ�����.
	 * ��������10, 18, 26
	 * �������룺100��160��200
	 * ���������3s��2s��1s��ʵ������Ҫ�������ģ�
	 * ����۸�160��200��250
	 * ���ۼ۸�70��140��210
	 */
	//���캯��
	public magicTower(Point picPoint , Point mainPoint,Monsters monsters) {
		super(picPoint, mainPoint, monsters);
		//��ʼ����Ӧ���ڵ�
		myBullet = new MagicBullet(new Point(picPoint.x+20, picPoint.y-20));
		//��������ӵ����ſ�ʼ
		attackAnimation = 3;
		//���ù�������������Χ���������
		attack = new int[] {10,18,26};
		attackGap = new double[] {3,2,1};
		attackRange = new int[] {140,160,180};
		//���������߳�
		new Thread(this).start();
	}
	//��дpaint����������ʦ����ʱ�Լ�����ʱ����е���
	public void paint(Graphics graphics,JPanel panel) {
		graphics.drawImage(imageGather.magicTowerImg[nowLevel],picPoint.x,picPoint.y,imgSize.width,imgSize.height, panel);
		graphics.drawImage(imageGather.magicTowerImg[attackAnimation], picPoint.x, picPoint.y,imgSize.width,imgSize.height, panel);
	}
	//ִ��һ�������Ĺ���������������bullet���������
	public void animation(Monster monster) {
			attackAnimation++;
			try {	Thread.sleep(100);	} catch (Exception e) {			}
			if (attackAnimation == 6 ) {
				//һ�ι����������֮����Ϣһ��
				myBullet.attackMonster(monster);
				try {	Thread.sleep((long) (attackGap[nowLevel]*500));	} catch (Exception e) {		}
				attackAnimation = 3;
			}
	}
	//�жϽ��ܵ��Ƿ�Ϊ��
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
	//���һ�ι�������
	public void run() {
		// ֻҪ����һֱ�ڹ�����Χ�ھ�һֱ���ù�������
		while(Player.getBlood()>0 && !deadThread) {
			if (isAttack(monsters) && !isStop) animation(monsters.getmonsters()[nowMonster]);
		}
	}
	//����������
	public int forSale() {
		return sale[nowLevel];
	}
	//��õ�ǰ�ȼ���������
	public int getCost() {
		if (nowLevel+1<=2) return cost[nowLevel+1];
		else return cost[nowLevel];	
	}
}

