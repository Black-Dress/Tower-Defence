package myPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Player.Music;
import Player.imageGather;

public class loadingPanel extends JPanel implements MouseListener,MouseMotionListener{
	private static final long serialVersionUID = 1L;
	private missionPanel myMissionPanel;
	private helpPanel myHelpPanel;
	int helpState=0;                            //helpͼ��״̬
	int startState=0;            				//��ʼ��״̬��ͨ����仯���ƿ�ʼͼ��仯

	public loadingPanel(missionPanel mp,helpPanel hp) {
		myMissionPanel = mp;
		myHelpPanel=hp;
		setLayout(null);
		setSize(1000,650);	
		setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	@Override
	public void paint(Graphics g) {
		setOpaque(true);
		super.paint(g);
		Image image= imageGather.backgroundImg[0];
		g.drawImage(image, 0, 0, 1000, 650,this);
		//����Ϸ��ʼͼ��
		g.drawImage(imageGather.startImg[startState], 390, 340, 200, 200, this);
		//����Ϸ����ͼ��
		g.drawImage(imageGather.helpImg[helpState], 900, 20, 50, 45, this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX()>390&&e.getX()<590&&e.getY()>440&&e.getY()<540) {
			Music.play(4, 1);
			setVisible(false);
			myMissionPanel.setVisible(true);
		}
		else if(e.getX()>900 && e.getX()<950 && e.getY()>20 && e.getY()<65){//���helpͼ��
			Music.play(4, 1);
			setVisible(false);
			myHelpPanel.setVisible(true);
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
		if(e.getX()>390&&e.getX()<590&&e.getY()>440&&e.getY()<540) {//����startͼ��
			Music.play(3, 0);
			startState=1;
			repaint();
		}
		else if(e.getX()>900 && e.getX()<950 && e.getY()>20 && e.getY()<65) {//����helpͼ��
			Music.play(3, 0);
			helpState=1;
			repaint();
		}
		else {
			Music.play(3, 2);
			startState=0;
			helpState=0;
			repaint();
		}
	}
}