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
	//右上角菜单
	private int reStartState=0;                                   //重新开始图标状态
	protected int musicState=0;                                   //音乐图标状态
	protected int stopState=0;                                    //暂停图标状态
	protected int backState=0;                                    //返回图标状态
	protected int isStopState=0;                                  //是否已暂停
	//游戏内
	protected Buildings towers[];								//用来操控塔的实例化
	protected int towerNumbers = 8;								//塔的总数量
	protected int img[];										//数组下标变化，控制空地图像变化
	protected boolean []isBuiled;								//判断空地是否被建造
	protected Dimension groundSize;								//空地图片大小
	protected Map myMap;										//地图保存了各种位置
	protected buildPanel myBuildPanel;							//建造panel
	protected Monsters myMonsters;								//怪兽
	protected Music myMusic;									//音乐
	protected Player myPlayer;									//用于重启游戏
	//左下角怪兽
	private int monsterState=0;                                 //怪物出现图标状态
	public int levelMap = 1;										//选择的关卡
	
	//道具
	protected prop p;
	private int bx,by;
	private boolean hover,hover1,flag;

	public gamePanel() {
		//初始化player
		myPlayer = new Player();
		//初始化音乐
		myMusic = new Music();
		//初始化空地图片大小
		groundSize = new Dimension(88,68);
		//初始化图片控制int型数组
		img = new int[] {0,0,0,0,0,0,0,0};
		//初始化空地判断数组
		isBuiled =  new boolean[] {false,false,false,false,false,false,false,false};
		//初始化包含的父类塔
		towers = new Buildings[towerNumbers];
		//初始化建造panel
		myBuildPanel=new buildPanel(towers);
		add(myBuildPanel);
		//初始化playingPanel
		setLayout(null);
		setSize(1000,650);
		setVisible(false);
		//初始化道具
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
			
		//点击界面重新开始游戏
		gameOver(false);
		//防御塔响应的鼠标点击事件
		for(int i = 0;i < towerNumbers;i++) {
			if (e.getX() >= myMap.buildingsLeftTopPosition[i].x && e.getX() <= myMap.buildingsLeftTopPosition[i].x+groundSize.width) {
				if (e.getY() >= myMap.buildingsLeftTopPosition[i].y && e.getY() <= myMap.buildingsLeftTopPosition[i].y+groundSize.height) {
					//播放声音
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
		//音乐图标响应的鼠标点击事件
		if(e.getX()>850 && e.getX()<890 && e.getY()>10 && e.getY()<50) {
			if(musicState==1){
				musicState=3;  						//如果当前有音乐，点击后变为没有音乐时的移入图片
				myMusic.stop(Music.BGM[0]);
				Music.play(4, 1);					//点击音效
			}
			else{
				musicState = 1;  					//如果当前没有音乐，点击后变为有音乐时的移入图片
				myMusic.restart(Music.BGM[0]);
				Music.play(4, 1);					//点击音效
			}  
		}
		//暂停游戏
		if(e.getX()>900 && e.getX()<940 && e.getY()>10 && e.getY()<50) {
			stopGame(false);
		}else {
			stopGame(true);
		}
		//返回图标响应的鼠标点击事件
		if(e.getX()>950 && e.getX()<985 && e.getY()>10 && e.getY()<50) {
			returnLoadPanel();
		}
		//monsterCome图标响应的鼠标点击事件
		if(e.getX()>40 && e.getX()<140 && e.getY()>520 && e.getY()<610) {
			if (!myMonsters.getmonsters()[0].isAlive()) { 
				myMonsters.start();
				Music.play(11, 1);
			}
			Music.play(4, 1);
		}
		//reStart图标响应的鼠标点击事件
		if(e.getX()>720 && e.getX()<840 && e.getY()>10 && e.getY()<50) {
			init();
			Music.play(4, 1);
			myMusic.restart(Music.BGM[0]);
		}
		
		//道具按键
		if(e.getX()>=bx&&e.getX()<=(bx+75)&&e.getY()>=by&&e.getY()<=(by+85)) {
			Music.play(5, 1);
			if(p.k>0) {
				p.k--;
				//小即死令
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
				//万死狂杀阵
				p.f=true;
				if(p.flag)
					p.k++;
				p.flag=false;
			}
		}
		//释放道具
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
		//判断选择的关卡来绘制背景图片
		if(levelMap==1) {
			g.drawImage(imageGather.backgroundImg[2], 0, 0, size.width, size.height,this);
		}
		if(levelMap==2) {
			g.drawImage(imageGather.backgroundImg[3], 0, 0, size.width, size.height,this);
		}
		//绘制空地
		for(int i = 0;i < towerNumbers;i++) {
			if (!isBuiled[i]) {
				g.drawImage(imageGather.buildImg[img[i]], myMap.buildingsLeftTopPosition[i].x, myMap.buildingsLeftTopPosition[i].y,groundSize.width,groundSize.height,this);  
			}
		}
		//绘制防御塔和相应的子弹
		for(int i = 0;i < towerNumbers ;i++) { 
			if (towers[i] != null) {
					towers[i].paint(g, this); 
					towers[i].getMyBullet().paint(g, this);
			}	
		}
		//绘制攻击范围圈
		for (int i = 0; i < towerNumbers; i++) {
			//借用img来判断防御塔是否被点击
			if (isBuiled[i] && img[i] == 1 && towers[i]!=null) {
				int r = towers[i].getAttackRange()[towers[i].getNowLevel()] - 25;
				g.setColor(new Color(207, 231, 250, 60));
				g.fillOval(towers[i].getMainPoint().x - r-35, towers[i].getMainPoint().y - r+20, r*2+70, r*2-40);
			}
			
		}
		//绘制怪兽
		myMonsters.paint(g);
		//绘制音乐图标
		g.drawImage(imageGather.musicImg[musicState], 850, 10, 40, 40, this);
		//绘制暂停图标
		g.drawImage(imageGather.stopImg[stopState], 900, 10, 40, 40, this);
		g.drawImage(imageGather.isStopImg[isStopState], 350, 200, 296, 153, this);
		//绘制返回图标
		g.drawImage(imageGather.backImg[backState], 950, 10, 35, 40, this);
		//绘制怪物come图标
		g.drawImage(imageGather.monsterComeImg[monsterState], 40, 520, 100, 90, this);
		//绘制重新开始图标
		g.drawImage(imageGather.reStartImg[reStartState], 720, 10, 120, 40, this);
		//绘制血量，金币，怪物数量
		myPlayer.paint(g, this,myMonsters);
		//画道具
		p.paint(g);
		g.drawImage(hover?imageGather.btn[1]:imageGather.btn[0], bx, by, 75,85,null);
		g.drawImage(hover1?imageGather.btn[3]:imageGather.btn[2], bx+85, by,75,85, null);
		g.drawString(("即死符:"+new Integer(p.k).toString()), 20, 110);
		g.drawString(("万死狂杀阵："+new Integer(p.k2).toString()), 20, 130);
		//绘制死亡界面
		if (Player.getBlood()<=0) {
			g.drawImage(imageGather.resultImg[1],170 , 50, this);
		}
		//绘制胜利界面	
		if (myMonsters.n1<=0) {
			g.drawImage(imageGather.resultImg[0],170 , 50, this);
		}
		g.drawImage(Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\blackBB.png"),387,578,103,35,this);
		

}
	public void run() {
		// 只要血量不为零游戏就继续进行
		myPlayer.setBlood(10);
		new Thread(myMusic).start();					//启动BGM
		while(Player.getBlood()>0 && myMonsters.n1 >0) {
			if(isStopState == 0) this.repaint();
			try {Thread.sleep(60);} catch (Exception e) {	}
		}
		//死亡时重新绘制一遍界面
		gameOver(true);
}
	public void mouseMoved(MouseEvent e) {
		if(e.getX()>850 && e.getX()<890 && e.getY()>10 && e.getY()<50) {//鼠标移入music图标区域
			if(musicState==0) { musicState=1; }  //如果当前有音乐，变为有音乐时的移入图片
			if(musicState==2) { musicState=3; }  //如果当前没有音乐，变为没有音乐时的移入图片
			Music.play(3, 0);
		}else if(e.getX()>900 && e.getX()<940 && e.getY()>10 && e.getY()<50) {//鼠标移入stop图标区域
			stopState=1;
			Music.play(3, 0);
		}else if(e.getX()>950 && e.getX()<985 && e.getY()>10 && e.getY()<50) {//鼠标移入back图标区域
			backState=1; 
			Music.play(3, 0);
		}else if(e.getX()>40 && e.getX()<140 && e.getY()>520 && e.getY()<610) {//鼠标移入monsterCome图标区域
			monsterState=1;
			Music.play(3, 0);
		}else if(e.getX()>720 && e.getX()<840 && e.getY()>10 && e.getY()<50) {//鼠标移入reStart图标区域
			reStartState=1;
			Music.play(3, 0);
		}else if(e.getX()>=bx&&e.getX()<=(bx+75)&&e.getY()>=by&&e.getY()<=(by+85)){//鼠标移入道具一
			hover=true;	
			Music.play(3, 0);
		}else if(e.getX()>=(bx+85)&&e.getX()<=(bx+160)&&e.getY()>=by&&e.getY()<=(by+85)){//鼠标移入道具二
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
		//设置金币
		myPlayer.setCoins(500);
		//设置血量
		myPlayer.setBlood(10);
		//初始化音乐
		myMusic.stop(Music.BGM[0]);
		myMusic = null;
		myMusic = new Music();
		//初始化怪物
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
		//初始化图片控制int型数组
		img = new int[] {0,0,0,0,0,0,0,0};
		//初始化空地判断数组
		isBuiled =  new boolean[] {false,false,false,false,false,false,false,false};
		//初始化包含的父类塔
		for(int i = 0;i<towerNumbers;i++) {
			if (towers[i]!=null) towers[i].destroy();		//停止防御塔线程
			towers[i]=null;
		}
		//初始化道具
		p.k=5;
		p.k2=3;
	}
	public void stopGame(boolean flag) {
		//重新开始
		if (isStopState == 1 && flag) {
			//普通重新开始
			Music.play(4, 1);						//播放音效
			if (musicState != 2&&musicState != 3) myMusic.restart(Music.BGM[0]);
			isStopState=0;
			for(int i =0;i<towerNumbers;i++) {
				if (towers[i]!=null) towers[i].stop();
			}
			Monster.step=2;
			try {myMonsters.notify();}catch (Exception e) {}
		}else if (!flag) {							//暂停游戏
			Music.play(4, 1);						
			myMusic.stop(Music.BGM[0]);
			isStopState = 1;
			repaint();
			for(int i =0;i< towerNumbers;i++) {
				if (towers[i]!=null) towers[i].stop();
			}
			//步长设为0，变相暂停
			Monster.step=0;
			try {myMonsters.wait();	}catch (Exception e) {}
		}
	}
	public void returnLoadPanel() {
		Music.play(4, 1);						//播放音效
		myMusic.stop(Music.BGM[0]);				//停止BGM
		init();
		setVisible(false);            			//出现loadingPanel
	}
	public void gameOver(boolean flag) {
		if (flag) {
			//步长设为0，变相暂停
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
		//点击时将所有img设置好
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
		// TODO 自动生成的方法存根
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	

}
