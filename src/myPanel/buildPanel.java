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
	
	private JButton arrowButton,magicButton,boomButton;                 //�������ַ������İ�ť
	private JButton levelUpButton,saleButton;				            //�����ͷ�����ť
	int width=55; int height=80;                                        //���������찴ť�Ĵ�С
	
	Buildings []towers ;												//towerָ��
	private Point picPoint;												//�������Ľ���λ��
	private Point mainPoint;											//��������ʵ��λ��
	private boolean []isBuiled;											//�жϿյ��Ƿ��������
	private int pos = 0;												//�ж�Ŀǰ�ٿص��ĸ��յػ��߷�����
	private Monsters myMonsters;										//���ڴ��ݸ�������
	
	public buildPanel(Buildings towers[]) {
		 //����buildPanel��λ�úʹ�С
		 setBounds(640, 520, width*5, height-5);
		 //��ʼ��������λ��
		 picPoint = new Point(0, 0);
		 mainPoint = new Point(0, 0);
		 //��ʼ��panel
		 setLayout(null);
		 //��ʼ��������
		 this.towers = towers;
		 //��ʼ����ť
		 arrowButton=new JButton();
		 magicButton=new JButton();
		 boomButton=new JButton();
		 levelUpButton=new JButton();
		 saleButton=new JButton();
		 
		 //������
		 add(arrowButton);
		 add(magicButton);
		 add(boomButton);
		 add(levelUpButton);
		 add(saleButton);
		 
		 //����buttonλ�ü���С
		 arrowButton.setBounds(new Rectangle(0,0,width,height));
		 magicButton.setBounds(new Rectangle(width*2,0,width,height));
		 boomButton.setBounds(new Rectangle(width*4,0,width,height));
		 levelUpButton.setBounds(new Rectangle(width+3, 7, 50, 56));
		 saleButton.setBounds(new Rectangle(width*3+3, 7, 50, 56));
		 
		 //button���ͼƬ
		 arrowButton.setIcon(new ImageIcon(".\\pic\\panel\\arrowTowerOff80.png"));
		 magicButton.setIcon(new ImageIcon(".\\pic\\panel\\magicTowerOff100.png"));
		 boomButton.setIcon(new ImageIcon(".\\pic\\panel\\boomTowerOff130.png"));
		 //levelUpButton,saleButton����������� ���� ���ͺ͵ȼ������ò�ͬ��ͼƬ
		 levelUpButton.setIcon(new ImageIcon(".\\pic\\panel\\levelUpButtonOff160.png"));
		 saleButton.setIcon(new ImageIcon(".\\pic\\panel\\saleButtonOff60.png"));
		 
		 //����button����
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
		 //���button�¼�
		 arrowButton.addActionListener(new ActionListener() {			
			 public void actionPerformed(ActionEvent arg0) {
					//����ħ�����ľ�̬����
					if (Player.getCoins() >= arrowTower.cost[0] && isBuiled[pos] == false) {
						Player.coinChange(-arrowTower.cost[0]);								//�ı��Ǯ
						towers[pos] = new arrowTower(picPoint, mainPoint,myMonsters);		//�½�������
						isBuiled[pos] = true;												//�ÿյز��ٻ���
						setVisible(false);													//build panel��ʧ
					}
				}
			});
		 magicButton.addActionListener(new ActionListener() {			
			 public void actionPerformed(ActionEvent arg0) {
					if(Player.getCoins() >= magicTower.cost[0] && isBuiled[pos] == false) {
						Player.coinChange(-magicTower.cost[0]);								//�ı��Ǯ
						towers[pos] = new magicTower(picPoint, mainPoint,myMonsters);		//�½�������
						isBuiled[pos] = true;												//�ÿյز��ٻ���
						setVisible(false);													//build panel��ʧ
					}
				}
		  });
		 boomButton.addActionListener(new ActionListener() {			
			 public void actionPerformed(ActionEvent arg0) {
					if (Player.getCoins() >= boomTower.cost[0] && isBuiled[pos] == false) {
						Player.coinChange(-boomTower.cost[0]);								//�ı��Ǯ
						towers[pos] = new boomTower(picPoint, mainPoint,myMonsters);		//�½�������
						isBuiled[pos] = true;												//�ÿյز��ٻ���
						setVisible(false);													//build panel��ʧ
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
		
		
		 //��ʼ��buildPanel
		 setVisible(false);
		 setOpaque(false);    //panel����͸��
	}
	/*����buildPanel*/
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
        super.paint(g);
        arrowButton.repaint();
        magicButton.repaint();
        boomButton.repaint();
		levelUpButton.repaint();
		saleButton.repaint();
		
	}
	/*��build panel���и��������ֵ�ķ���*/
	public void transform(int i ,boolean isBuiled[],Point picPoint,Point mainPoint,Monsters monsters) {
		pos = i;
		this.isBuiled = isBuiled;
		this.picPoint = picPoint;
		this.mainPoint = mainPoint;
		myMonsters = monsters;
	}
	//���������ť
	public JButton getLevelUpButton() {
		return levelUpButton;
	}
	//��ó��۰�ť
	public JButton getSaleButton() {
		return saleButton;
	}
	//��ü�����ť
	public JButton getArrowButton() {
		return arrowButton;
	}
	//���ħ������ť
	public JButton getMagicButton() {
		return magicButton;
	}
	//��ñ�ը��
	public JButton getBoomButton() {
		return boomButton;
	}
	
	
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseEntered(MouseEvent e) {
		
		//���벻ͬbutton������ͬ�¼����ı�button��ͼƬ
		if(e.getSource()==arrowButton) {
			 arrowButton.setIcon(new ImageIcon(".\\pic\\panel\\arrowTowerOn80.png"));
		}
		else if(e.getSource()==magicButton) {
			 magicButton.setIcon(new ImageIcon(".\\pic\\panel\\magicTowerOn100.png"));
		}
		else if(e.getSource()==boomButton) {
			 boomButton.setIcon(new ImageIcon(".\\pic\\panel\\boomTowerOn130.png"));
		}
		//����levelUpButton,saleButton�Ǹ���������� ���� ���ͺ͵ȼ������ò�ͬ��ͼƬ
		if (isBuiled[pos]) {
			String path[] = new String[2];
			path[0] = "./pic/panel/levelUpButtonOn"+String.valueOf(towers[pos].getCost())+".png";
			path[1] = "./pic/panel/saleButtonOn"+String.valueOf(towers[pos].forSale())+".png";
			if(e.getSource() == levelUpButton)	levelUpButton.setIcon(new ImageIcon(path[0]));
			if(e.getSource() == saleButton)		saleButton.setIcon(new ImageIcon(path[1]));
		}
	}
	public void mouseExited(MouseEvent e) {
		//�Ƴ���ͬbutton������ͬ�¼����ı�button��ͼƬ
		if(e.getSource()==arrowButton) {
			 arrowButton.setIcon(new ImageIcon(".\\pic\\panel\\arrowTowerOff80.png"));
		}
		else if(e.getSource()==magicButton) {
			 magicButton.setIcon(new ImageIcon(".\\pic\\panel\\magicTowerOff100.png"));
		}
		else if(e.getSource()==boomButton) {
			 boomButton.setIcon(new ImageIcon(".\\pic\\panel\\boomTowerOff130.png"));
		}
		//�Ƴ�levelUpButton,saleButton�Ǹ���������� ���� ���ͺ͵ȼ������ò�ͬ��ͼƬ
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