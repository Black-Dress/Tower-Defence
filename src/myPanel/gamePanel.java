package myPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Buildings.Buildings;
import Monster.Monster;
import Monster.Monsters;
import Player.*;
import prop.prop;

public abstract class gamePanel extends JPanel implements Runnable,MouseListener,MouseMotionListener {
	protected static final long serialVersionUID = -4855036208991755326L;
	//���Ͻǲ˵�
	private int reStartState=0;                                   //���¿�ʼͼ��״̬
	protected int musicState=0;                                   //����ͼ��״̬
	protected int stopState=0;                                    //��ͣͼ��״̬
	protected int backState=0;                                    //����ͼ��״̬
	protected int isStopState=0;                                  //�Ƿ�����ͣ
	//��Ϸ��
	protected Buildings towers[];								//�����ٿ�����ʵ����
	protected int towerNumbers = 8;								//����������
	protected int img[];										//�����±�仯�����ƿյ�ͼ��仯
	protected boolean []isBuiled;								//�жϿյ��Ƿ񱻽���
	protected Dimension groundSize;								//�յ�ͼƬ��С
	protected Map myMap;										//��ͼ�����˸���λ��
	protected buildPanel myBuildPanel;							//����panel
	protected Monsters myMonsters;								//����
	protected Music myMusic;									//����
	protected Player myPlayer;									//����������Ϸ
	//���½ǹ���
	private int monsterState=0;                                 //�������ͼ��״̬
	public int levelMap = 1;										//ѡ��Ĺؿ�
	
	//����
	protected prop p;
	private int bx,by;
	private boolean hover,hover1,flag;

	public gamePanel() {
		//��ʼ��player
		myPlayer = new Player();
		//��ʼ������
		myMusic = new Music();
		//��ʼ���յ�ͼƬ��С
		groundSize = new Dimension(88,68);
		//��ʼ��ͼƬ����int������
		img = new int[] {0,0,0,0,0,0,0,0};
		//��ʼ���յ��ж�����
		isBuiled =  new boolean[] {false,false,false,false,false,false,false,false};
		//��ʼ�������ĸ�����
		towers = new Buildings[towerNumbers];
		//��ʼ������panel
		myBuildPanel=new buildPanel(towers);
		add(myBuildPanel);
		//��ʼ��playingPanel
		setLayout(null);
		setSize(1000,650);
		setVisible(false);
		//��ʼ������
		p=new prop();
		p.start();
		bx=150;
		by=500;
		hover=false;
		hover1=false;
		this.addMouseMotionListener(this);
		p.k=3;
		p.k2=1;
		flag=true;
	}
	public void mouseClicked(MouseEvent e) {
			
		//����������¿�ʼ��Ϸ
		gameOver(false);
		//��������Ӧ��������¼�
		for(int i = 0;i < towerNumbers;i++) {
			if (e.getX() >= myMap.buildingsLeftTopPosition[i].x && e.getX() <= myMap.buildingsLeftTopPosition[i].x+groundSize.width) {
				if (e.getY() >= myMap.buildingsLeftTopPosition[i].y && e.getY() <= myMap.buildingsLeftTopPosition[i].y+groundSize.height) {
					//��������
					Music.play(5, 1);
					showBuildPanel(isBuiled[i],i);
					break;
				}else {
					myBuildPanel.setVisible(false);
					img[i] = 0;
				}
			}else {
				myBuildPanel.setVisible(false);
				img[i] = 0;
			}
		}
		//����ͼ����Ӧ��������¼�
		if(e.getX()>850 && e.getX()<890 && e.getY()>10 && e.getY()<50) {
			if(musicState==1){
				musicState=3;  						//�����ǰ�����֣�������Ϊû������ʱ������ͼƬ
				myMusic.stop(Music.BGM[0]);
				Music.play(4, 1);					//�����Ч
			}
			else{
				musicState = 1;  					//�����ǰû�����֣�������Ϊ������ʱ������ͼƬ
				myMusic.restart(Music.BGM[0]);
				Music.play(4, 1);					//�����Ч
			}  
		}
		//��ͣ��Ϸ
		if(e.getX()>900 && e.getX()<940 && e.getY()>10 && e.getY()<50) {
			stopGame(false);
		}else {
			stopGame(true);
		}
		//����ͼ����Ӧ��������¼�
		if(e.getX()>950 && e.getX()<985 && e.getY()>10 && e.getY()<50) {
			returnLoadPanel();
		}
		//monsterComeͼ����Ӧ��������¼�
		if(e.getX()>40 && e.getX()<140 && e.getY()>520 && e.getY()<610) {
			if (!myMonsters.getmonsters()[0].isAlive()) { 
				myMonsters.start();
				Music.play(11, 1);
			}
			Music.play(4, 1);
		}
		//reStartͼ����Ӧ��������¼�
		if(e.getX()>720 && e.getX()<840 && e.getY()>10 && e.getY()<50) {
			init();
			Music.play(4, 1);
			myMusic.restart(Music.BGM[0]);
		}
		
		//���߰���
		if(e.getX()>=bx&&e.getX()<=(bx+75)&&e.getY()>=by&&e.getY()<=(by+85)) {
			Music.play(5, 1);
			if(p.k>0) {
				p.k--;
				//С������
				p.flag=true;
				if(p.f)
					p.k2++;
				p.f=false;
			}
		}
			
		if(e.getX()>=(bx+85)&&e.getX()<=(bx+160)&&e.getY()>=by&&e.getY()<=(by+85)) {
			Music.play(5, 1);
			if(p.k2>0) {
				p.k2--;
				//������ɱ��
				p.f=true;
				if(p.flag)
					p.k++;
				p.flag=false;
			}
		}
		//�ͷŵ���
		if(flag) {
			if(p.flag){
				p.setXY(e.getX(),e.getY());
				p.flag1=true;
				return;
			}
			if(p.f) {
				p.setXY(e.getX(),e.getY());
				p.f2=true;
				return;
			}
		}
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		myBuildPanel.paint(g);
		Dimension size=this.getParent().getSize();
		//�ж�ѡ��Ĺؿ������Ʊ���ͼƬ
		if(levelMap==1) {
			g.drawImage(imageGather.backgroundImg[2], 0, 0, size.width, size.height,this);
		}
		if(levelMap==2) {
			g.drawImage(imageGather.backgroundImg[3], 0, 0, size.width, size.height,this);
		}
		//���ƿյ�
		for(int i = 0;i < towerNumbers;i++) {
			if (!isBuiled[i]) {
				g.drawImage(imageGather.buildImg[img[i]], myMap.buildingsLeftTopPosition[i].x, myMap.buildingsLeftTopPosition[i].y,groundSize.width,groundSize.height,this);  
			}
		}
		//���Ʒ���������Ӧ���ӵ�
		for(int i = 0;i < towerNumbers ;i++) { 
			if (towers[i] != null) {
					towers[i].paint(g, this); 
					towers[i].getMyBullet().paint(g, this);
			}	
		}
		//���ƹ�����ΧȦ
		for (int i = 0; i < towerNumbers; i++) {
			//����img���жϷ������Ƿ񱻵��
			if (isBuiled[i] && img[i] == 1 && towers[i]!=null) {
				int r = towers[i].getAttackRange()[towers[i].getNowLevel()] - 25;
				g.setColor(new Color(207, 231, 250, 60));
				g.fillOval(towers[i].getMainPoint().x - r-35, towers[i].getMainPoint().y - r+20, r*2+70, r*2-40);
			}
			
		}
		//���ƹ���
		myMonsters.paint(g);
		//��������ͼ��
		g.drawImage(imageGather.musicImg[musicState], 850, 10, 40, 40, this);
		//������ͣͼ��
		g.drawImage(imageGather.stopImg[stopState], 900, 10, 40, 40, this);
		g.drawImage(imageGather.isStopImg[isStopState], 350, 200, 296, 153, this);
		//���Ʒ���ͼ��
		g.drawImage(imageGather.backImg[backState], 950, 10, 35, 40, this);
		//���ƹ���comeͼ��
		g.drawImage(imageGather.monsterComeImg[monsterState], 40, 520, 100, 90, this);
		//�������¿�ʼͼ��
		g.drawImage(imageGather.reStartImg[reStartState], 720, 10, 120, 40, this);
		//����Ѫ������ң���������
		myPlayer.paint(g, this,myMonsters);
		//������
		p.paint(g);
		g.drawImage(hover?imageGather.btn[1]:imageGather.btn[0], bx, by, 75,85,null);
		g.drawImage(hover1?imageGather.btn[3]:imageGather.btn[2], bx+85, by,75,85, null);
		g.drawString(("������:"+new Integer(p.k).toString()), 20, 110);
		g.drawString(("������ɱ��"+new Integer(p.k2).toString()), 20, 130);
		//������������
		if (Player.getBlood()<=0) {
			g.drawImage(imageGather.resultImg[1],170 , 50, this);
		}
		//����ʤ������	
		if (myMonsters.n1<=0) {
			g.drawImage(imageGather.resultImg[0],170 , 50, this);
		}
		g.drawImage(Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\blackBB.png"),387,578,103,35,this);
		

}
	public void run() {
		// ֻҪѪ����Ϊ����Ϸ�ͼ�������
		myPlayer.setBlood(10);
		new Thread(myMusic).start();					//����BGM
		while(Player.getBlood()>0 && myMonsters.n1 >0) {
			if(isStopState == 0) this.repaint();
			try {Thread.sleep(60);} catch (Exception e) {	}
		}
		//����ʱ���»���һ�����
		gameOver(true);
}
	public void mouseMoved(MouseEvent e) {
		if(e.getX()>850 && e.getX()<890 && e.getY()>10 && e.getY()<50) {//�������musicͼ������
			if(musicState==0) { musicState=1; }  //�����ǰ�����֣���Ϊ������ʱ������ͼƬ
			if(musicState==2) { musicState=3; }  //�����ǰû�����֣���Ϊû������ʱ������ͼƬ
			Music.play(3, 0);
		}else if(e.getX()>900 && e.getX()<940 && e.getY()>10 && e.getY()<50) {//�������stopͼ������
			stopState=1;
			Music.play(3, 0);
		}else if(e.getX()>950 && e.getX()<985 && e.getY()>10 && e.getY()<50) {//�������backͼ������
			backState=1; 
			Music.play(3, 0);
		}else if(e.getX()>40 && e.getX()<140 && e.getY()>520 && e.getY()<610) {//�������monsterComeͼ������
			monsterState=1;
			Music.play(3, 0);
		}else if(e.getX()>720 && e.getX()<840 && e.getY()>10 && e.getY()<50) {//�������reStartͼ������
			reStartState=1;
			Music.play(3, 0);
		}else if(e.getX()>=bx&&e.getX()<=(bx+75)&&e.getY()>=by&&e.getY()<=(by+85)){//����������һ
			hover=true;	
			Music.play(3, 0);
		}else if(e.getX()>=(bx+85)&&e.getX()<=(bx+160)&&e.getY()>=by&&e.getY()<=(by+85)){//���������߶�
			hover1=true; 
			Music.play(3, 0);
		}else {
			if(musicState==1) musicState=0; 
			if(musicState==3) musicState=2;
			stopState=0;
			backState=0;
			monsterState=0;
			reStartState=0;
			hover1=false;
			hover =false;
			Music.play(3, 2);
		}
	}
	@SuppressWarnings("deprecation")
	public void init() {
		//���ý��
		myPlayer.setCoins(500);
		//����Ѫ��
		myPlayer.setBlood(10);
		//��ʼ������
		myMusic.stop(Music.BGM[0]);
		myMusic = null;
		myMusic = new Music();
		//��ʼ������
		if (myMonsters.getmonsters() != null) {
			for(int i = 0;i<myMonsters.getmonsters().length;i++) {
				myMonsters.getmonsters()[i].stop();
				myMonsters.getmonsters()[i]=null;
			}
		}
		Monster.step=2;
		myMonsters = null;
		myMonsters = new Monsters(myMap.turningPoint1,myMap.turningPoint2);
		p.setMonsters(myMonsters);
		//��ʼ��ͼƬ����int������
		img = new int[] {0,0,0,0,0,0,0,0};
		//��ʼ���յ��ж�����
		isBuiled =  new boolean[] {false,false,false,false,false,false,false,false};
		//��ʼ�������ĸ�����
		for(int i = 0;i<towerNumbers;i++) {
			if (towers[i]!=null) towers[i].destroy();		//ֹͣ�������߳�
			towers[i]=null;
		}
		//��ʼ������
		p.k=5;
		p.k2=3;
	}
	public void stopGame(boolean flag) {
		//���¿�ʼ
		if (isStopState == 1 && flag) {
			//��ͨ���¿�ʼ
			Music.play(4, 1);						//������Ч
			if (musicState != 2&&musicState != 3) myMusic.restart(Music.BGM[0]);
			isStopState=0;
			for(int i =0;i<towerNumbers;i++) {
				if (towers[i]!=null) towers[i].stop();
			}
			Monster.step=2;
			try {myMonsters.notify();}catch (Exception e) {}
		}else if (!flag) {							//��ͣ��Ϸ
			Music.play(4, 1);						
			myMusic.stop(Music.BGM[0]);
			isStopState = 1;
			repaint();
			for(int i =0;i< towerNumbers;i++) {
				if (towers[i]!=null) towers[i].stop();
			}
			//������Ϊ0��������ͣ
			Monster.step=0;
			try {myMonsters.wait();	}catch (Exception e) {}
		}
	}
	public void returnLoadPanel() {
		Music.play(4, 1);						//������Ч
		myMusic.stop(Music.BGM[0]);				//ֹͣBGM
		init();
		setVisible(false);            			//����loadingPanel
	}
	public void gameOver(boolean flag) {
		if (flag) {
			//������Ϊ0��������ͣ
			Monster.step=0;
			try {myMonsters.wait();	}catch (Exception e) {}
			for(int i =0;i< towerNumbers;i++) {
				if (towers[i]!=null) towers[i].stop();
			}
			myMusic.stop(Music.BGM[0]);
			if (Player.getBlood()<=0) Music.play(2, 1);
			else if(myMonsters.n1<=0) Music.play(1, 1);	
			flag=false;
		}else {
			if (Player.getBlood()<=0 || myMonsters.n1 <= 0) {
				init();
				new Thread(this).start();
			}
			flag=true;
		}
		repaint();
	}
	public void showBuildPanel(boolean flag , int pos) {
		//���ʱ������img���ú�
		for(int j = 0;j<towerNumbers;j++) {
			img[j] = 0;
		}
		if (flag) {
			String path[] = new String[2];
			path[0] = "./pic/panel/levelUpButtonOff"+String.valueOf(towers[pos].getCost())+".png";
			path[1] = "./pic/panel/saleButtonOff"+String.valueOf(towers[pos].forSale())+".png";
			myBuildPanel.getLevelUpButton().setIcon(new ImageIcon(path[0]));
			myBuildPanel.getSaleButton().setIcon(new ImageIcon(path[1]));						
			myBuildPanel.getSaleButton().setVisible(true);
			myBuildPanel.getLevelUpButton().setVisible(true);
			myBuildPanel.getArrowButton().setVisible(false);
			myBuildPanel.getMagicButton().setVisible(false);
			myBuildPanel.getBoomButton().setVisible(false);
		}else {
			myBuildPanel.getSaleButton().setVisible(false);
			myBuildPanel.getLevelUpButton().setVisible(false);
			myBuildPanel.getArrowButton().setVisible(true);
			myBuildPanel.getMagicButton().setVisible(true);
			myBuildPanel.getBoomButton().setVisible(true);
		}
		myBuildPanel.setVisible(true);
		myBuildPanel.transform(pos, isBuiled, myMap.buildingsLeftTopPosition[pos], myMap.buildingsMidPosition[pos],myMonsters);
		img[pos] = 1;
	}

	
	
	
	
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	public void mouseDragged(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	

}
