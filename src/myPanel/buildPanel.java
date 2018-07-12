package myPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import Buildings.*;
import Monster.Monsters;
import Player.Player;

public class buildPanel extends JPanel  implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private JButton arrowButton,magicButton,boomButton;                 //建造三种防御塔的按钮
	private JButton levelUpButton,saleButton;				            //升级和贩卖按钮
	int width=55; int height=80;                                        //防御塔建造按钮的大小
	
	Buildings []towers ;												//tower指针
	private Point picPoint;												//防御塔的建立位置
	private Point mainPoint;											//防御塔的实际位置
	private boolean []isBuiled;											//判断空地是否继续存在
	private int pos = 0;												//判断目前操控的哪个空地或者防御塔
	private Monsters myMonsters;										//用于传递给防御塔
	
	public buildPanel(Buildings towers[]) {
		 //设置buildPanel的位置和大小
		 setBounds(640, 520, width*5, height-5);
		 //初始化防御塔位置
		 picPoint = new Point(0, 0);
		 mainPoint = new Point(0, 0);
		 //初始化panel
		 setLayout(null);
		 //初始化防御塔
		 this.towers = towers;
		 //初始化按钮
		 arrowButton=new JButton();
		 magicButton=new JButton();
		 boomButton=new JButton();
		 levelUpButton=new JButton();
		 saleButton=new JButton();
		 
		 //添加组件
		 add(arrowButton);
		 add(magicButton);
		 add(boomButton);
		 add(levelUpButton);
		 add(saleButton);
		 
		 //设置button位置及大小
		 arrowButton.setBounds(new Rectangle(0,0,width,height));
		 magicButton.setBounds(new Rectangle(width*2,0,width,height));
		 boomButton.setBounds(new Rectangle(width*4,0,width,height));
		 levelUpButton.setBounds(new Rectangle(width+3, 7, 50, 56));
		 saleButton.setBounds(new Rectangle(width*3+3, 7, 50, 56));
		 
		 //button添加图片
		 arrowButton.setIcon(new ImageIcon(".\\pic\\panel\\arrowTowerOff80.png"));
		 magicButton.setIcon(new ImageIcon(".\\pic\\panel\\magicTowerOff100.png"));
		 boomButton.setIcon(new ImageIcon(".\\pic\\panel\\boomTowerOff130.png"));
		 //levelUpButton,saleButton根据所点击的 塔的 类型和等级来设置不同的图片
		 levelUpButton.setIcon(new ImageIcon(".\\pic\\panel\\levelUpButtonOff160.png"));
		 saleButton.setIcon(new ImageIcon(".\\pic\\panel\\saleButtonOff60.png"));
		 
		 //设置button属性
		 levelUpButton.setVisible(false);
		 saleButton.setVisible(false);
		 arrowButton.setBorderPainted(false);
		 magicButton.setBorderPainted(false);
		 boomButton.setBorderPainted(false);
		 levelUpButton.setBorderPainted(false);
		 saleButton.setBorderPainted(false);
		 arrowButton.setBackground(new Color(111, 147, 37));
		 magicButton.setBackground(new Color(135, 159, 56));
		 boomButton.setBackground(new Color(135, 159, 56));
		 levelUpButton.setBackground(Color.BLACK);
		 saleButton.setBackground(Color.BLACK);
		 //添加button事件
		 arrowButton.addActionListener(new ActionListener() {			
			 public void actionPerformed(ActionEvent arg0) {
					//调用魔法塔的静态变量
					if (Player.getCoins() >= arrowTower.cost[0] && isBuiled[pos] == false) {
						Player.coinChange(-arrowTower.cost[0]);								//改变金钱
						towers[pos] = new arrowTower(picPoint, mainPoint,myMonsters);		//新建防御塔
						isBuiled[pos] = true;												//让空地不再绘制
						setVisible(false);													//build panel消失
					}
				}
			});
		 magicButton.addActionListener(new ActionListener() {			
			 public void actionPerformed(ActionEvent arg0) {
					if(Player.getCoins() >= magicTower.cost[0] && isBuiled[pos] == false) {
						Player.coinChange(-magicTower.cost[0]);								//改变金钱
						towers[pos] = new magicTower(picPoint, mainPoint,myMonsters);		//新建防御塔
						isBuiled[pos] = true;												//让空地不再绘制
						setVisible(false);													//build panel消失
					}
				}
		  });
		 boomButton.addActionListener(new ActionListener() {			
			 public void actionPerformed(ActionEvent arg0) {
					if (Player.getCoins() >= boomTower.cost[0] && isBuiled[pos] == false) {
						Player.coinChange(-boomTower.cost[0]);								//改变金钱
						towers[pos] = new boomTower(picPoint, mainPoint,myMonsters);		//新建防御塔
						isBuiled[pos] = true;												//让空地不再绘制
						setVisible(false);													//build panel消失
					}
				}
			});
		 saleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.coinChange(towers[pos].forSale());
				towers[pos].destroy();
				towers[pos] = null;
				isBuiled[pos] = false;
				setVisible(false);
			}
		});
		 levelUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (towers[pos].getCost()<=Player.getCoins() && towers[pos].getNowLevel()<2) {
					Player.coinChange(-towers[pos].getCost());
					towers[pos].levelUp();
				}
			}	
		});
		 arrowButton.addMouseListener(this); 
		 magicButton.addMouseListener(this); 
		 boomButton.addMouseListener(this); 
		 saleButton.addMouseListener(this); 
		 levelUpButton.addMouseListener(this);
		
		
		 //初始化buildPanel
		 setVisible(false);
		 setOpaque(false);    //panel背景透明
	}
	/*绘制buildPanel*/
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
        super.paint(g);
        arrowButton.repaint();
        magicButton.repaint();
        boomButton.repaint();
		levelUpButton.repaint();
		saleButton.repaint();
		
	}
	/*从build panel类中更改外界数值的方法*/
	public void transform(int i ,boolean isBuiled[],Point picPoint,Point mainPoint,Monsters monsters) {
		pos = i;
		this.isBuiled = isBuiled;
		this.picPoint = picPoint;
		this.mainPoint = mainPoint;
		myMonsters = monsters;
	}
	//获得升级按钮
	public JButton getLevelUpButton() {
		return levelUpButton;
	}
	//获得出售按钮
	public JButton getSaleButton() {
		return saleButton;
	}
	//获得箭塔按钮
	public JButton getArrowButton() {
		return arrowButton;
	}
	//获得魔法塔按钮
	public JButton getMagicButton() {
		return magicButton;
	}
	//获得爆炸塔
	public JButton getBoomButton() {
		return boomButton;
	}
	
	
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseEntered(MouseEvent e) {
		
		//移入不同button触发不同事件，改变button的图片
		if(e.getSource()==arrowButton) {
			 arrowButton.setIcon(new ImageIcon(".\\pic\\panel\\arrowTowerOn80.png"));
		}
		else if(e.getSource()==magicButton) {
			 magicButton.setIcon(new ImageIcon(".\\pic\\panel\\magicTowerOn100.png"));
		}
		else if(e.getSource()==boomButton) {
			 boomButton.setIcon(new ImageIcon(".\\pic\\panel\\boomTowerOn130.png"));
		}
		//移入levelUpButton,saleButton是根据所点击的 塔的 类型和等级来设置不同的图片
		if (isBuiled[pos]) {
			String path[] = new String[2];
			path[0] = "./pic/panel/levelUpButtonOn"+String.valueOf(towers[pos].getCost())+".png";
			path[1] = "./pic/panel/saleButtonOn"+String.valueOf(towers[pos].forSale())+".png";
			if(e.getSource() == levelUpButton)	levelUpButton.setIcon(new ImageIcon(path[0]));
			if(e.getSource() == saleButton)		saleButton.setIcon(new ImageIcon(path[1]));
		}
	}
	public void mouseExited(MouseEvent e) {
		//移出不同button触发不同事件，改变button的图片
		if(e.getSource()==arrowButton) {
			 arrowButton.setIcon(new ImageIcon(".\\pic\\panel\\arrowTowerOff80.png"));
		}
		else if(e.getSource()==magicButton) {
			 magicButton.setIcon(new ImageIcon(".\\pic\\panel\\magicTowerOff100.png"));
		}
		else if(e.getSource()==boomButton) {
			 boomButton.setIcon(new ImageIcon(".\\pic\\panel\\boomTowerOff130.png"));
		}
		//移出levelUpButton,saleButton是根据所点击的 塔的 类型和等级来设置不同的图片
		if (isBuiled[pos]) {
			String path[] = new String[2];
			path[0] = "./pic/panel/levelUpButtonOff"+String.valueOf(towers[pos].getCost())+".png";
			path[1] = "./pic/panel/saleButtonOff"+String.valueOf(towers[pos].forSale())+".png";
			if(e.getSource() == levelUpButton)	levelUpButton.setIcon(new ImageIcon(path[0]));
			if(e.getSource() == saleButton)		saleButton.setIcon(new ImageIcon(path[1]));
		}
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseReleased(MouseEvent arg0) {
	}

}