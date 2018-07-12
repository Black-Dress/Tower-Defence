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
	
	static int coins=500;//定义初始金钱
    static int blood=100;//定义玩家血量
    static int monsterNumber=100;//定义活着的怪物数量。
  //改变玩家的金币数
  	public static int coinChange(int coin) {
  		coins+=coin;
  		return coins;
  	}
  	//减少玩家血量
  	public static int reduceblood(int b) {	
  		blood -= b;
  		return blood;
  	}
  	//活着的怪物总数
  	public static void reducemonsterNumber(int b) {	
  		monsterNumber += b;
  		
  	}
  	//获得当前金币数
  	public static int getCoins() {
  		return coins;
  	}
  	//获得当前玩家血量
  	public static int getBlood() {
  		return blood;
  	}
  	//设置血量
  	public void setBlood(int b) {
		blood = b;
	}
  	//获得当前活着的怪物总数
  	public static int getmonsterNumber() {
  		
  		return monsterNumber;
  	}
  	//设置金币
  	public void setCoins(int c) {
		coins = c;
	}
public void paint(Graphics g,JPanel panel,Monsters myMonsters) {
	String changecoins=String.valueOf(coins);
	String changeblood=String.valueOf(blood);
	Font fnt=new Font("宋",Font.BOLD,15);
	g.setFont(fnt);
	g.setColor(new Color(251, 251, 21,250));
	g.drawString(changecoins, 60, 23);
	g.setColor(Color.RED);
	g.drawString(changeblood, 60, 53);
	g.setColor(Color.LIGHT_GRAY);
	g.drawString(String.valueOf(myMonsters.getmonsters().length), 60, 83);
}

}
