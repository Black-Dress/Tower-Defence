/**
 * 
 */
package Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import Monster.Monsters;

/**
 * @author 1
 *
 */
public class Player {
	
	static int coins=500;//�����ʼ��Ǯ
    static int blood=100;//�������Ѫ��
    static int monsterNumber=100;//������ŵĹ���������
  //�ı���ҵĽ����
  	public static int coinChange(int coin) {
  		coins+=coin;
  		return coins;
  	}
  	//�������Ѫ��
  	public static int reduceblood(int b) {	
  		blood -= b;
  		return blood;
  	}
  	//���ŵĹ�������
  	public static void reducemonsterNumber(int b) {	
  		monsterNumber += b;
  		
  	}
  	//��õ�ǰ�����
  	public static int getCoins() {
  		return coins;
  	}
  	//��õ�ǰ���Ѫ��
  	public static int getBlood() {
  		return blood;
  	}
  	//����Ѫ��
  	public void setBlood(int b) {
		blood = b;
	}
  	//��õ�ǰ���ŵĹ�������
  	public static int getmonsterNumber() {
  		
  		return monsterNumber;
  	}
  	//���ý��
  	public void setCoins(int c) {
		coins = c;
	}
public void paint(Graphics g,JPanel panel,Monsters myMonsters) {
	String changecoins=String.valueOf(coins);
	String changeblood=String.valueOf(blood);
	Font fnt=new Font("��",Font.BOLD,15);
	g.setFont(fnt);
	g.setColor(new Color(251, 251, 21,250));
	g.drawString(changecoins, 60, 23);
	g.setColor(Color.RED);
	g.drawString(changeblood, 60, 53);
	g.setColor(Color.LIGHT_GRAY);
	g.drawString(String.valueOf(myMonsters.getmonsters().length), 60, 83);
}

}
