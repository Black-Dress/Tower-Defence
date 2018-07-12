package myPanel;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Player.Music;
import Player.imageGather;

public class missionPanel extends JPanel implements MouseListener,MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	private playingPanel myPlayingPanel;
	private playingPanel2 myPlayingPanel2;
	int missionState1=0;            			//第一关关卡的图像状态，通过其变化控制图像变化
	int missionState2=0;            			//第二关关卡的图像状态，通过其变化控制图像变化
	int backState=0;                			//back图像状态
	int missionImageWidth=60;      				//设置关卡图像的大小
	int missionImageHeight=82;
	private Thread tt ;
	//int levelMap;
	
	public missionPanel(playingPanel pp1,playingPanel2 pp2) {
		myPlayingPanel = pp1;
		myPlayingPanel2= pp2;
		setLayout(null);
		setSize(1000,650);
		setVisible(false);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	@Override
	public void paint(Graphics g) {
		setOpaque(true);
		super.paint(g);
		Image image= imageGather.backgroundImg[1];
		g.drawImage(image, 0, 0, 1000, 650,this);
		//画关卡图像
		g.drawImage(imageGather.missionImg[missionState1], 150, 300,missionImageWidth,missionImageHeight, this);
		g.drawImage(imageGather.missionImg[missionState2], 300, 250,missionImageWidth,missionImageHeight,this);
		//画返回图像
		g.drawImage(imageGather.backImg[backState], 80, 550, 60, 60, this);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX()>150&&e.getX()<150+missionImageWidth&&e.getY()>300&&e.getY()<300+missionImageHeight-10) {
			Music.play(4, 1);
			setVisible(false);
			myPlayingPanel.levelMap = 1;
			myPlayingPanel.setVisible(true);
			if (tt == null) {
				tt = new Thread(myPlayingPanel);
				tt.start();
			}else {
				tt.stop();
				tt = new Thread(myPlayingPanel);
				tt.start();
			}
		}
		else if (e.getX()>300&&e.getX()<300+missionImageWidth&&e.getY()>250&&e.getY()<250+missionImageHeight-10) {
			Music.play(4, 1);
			setVisible(false);
			myPlayingPanel2.levelMap=2;
			myPlayingPanel2.setVisible(true);
			if (tt == null) {
				tt = new Thread(myPlayingPanel2);
				tt.start();
			}else {
				tt.stop();
				tt = new Thread(myPlayingPanel2);
				tt.start();
			}
		}
		else if(e.getX()>80&&e.getX()<140&&e.getY()>550&&e.getY()<610){
			Music.play(4, 1);
			setVisible(false);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//移入指定坐标范围，startState改变
		if(e.getX()>150&&e.getX()<150+missionImageWidth&&e.getY()>300&&e.getY()<300+missionImageHeight-10) {
			Music.play(3, 0);
			missionState1=1;
			repaint();
		}
		else if (e.getX()>300&&e.getX()<300+missionImageWidth&&e.getY()>250&&e.getY()<250+missionImageHeight-10) {
			Music.play(3, 0);
			missionState2=1;
			repaint();
		}
		else if(e.getX()>80&&e.getX()<140&&e.getY()>550&&e.getY()<610) {
			Music.play(3, 0);
			backState=1;
			repaint();
		}
		else {
			Music.play(3, 2);
			missionState1=0;
			missionState2=0;
			backState=0;
			repaint();
		}
	}
}
