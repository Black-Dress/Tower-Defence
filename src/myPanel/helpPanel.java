package myPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Player.Music;
import Player.imageGather;

public class helpPanel extends JPanel implements MouseListener,MouseMotionListener{
	private static final long serialVersionUID = 5954251956593269725L;
	private int backState=0;          //backÍ¼Ïñ×´Ì¬
	private int lastPageState=0;       //ÉÏÒ»Ò³Í¼Ïñ×´Ì¬
	private int nextPageState=0;       //ÏÂÒ»Ò³Í¼Ïñ×´Ì¬
	private int page=0;                //helpµÄÒ³Êı

	
	public helpPanel() {
		// TODO Auto-generated constructor stub
		setVisible(false);
		setBackground(Color.black);
		setBounds(new Rectangle(1000, 650, 0, 0));
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		if(page==0)g.drawImage(imageGather.helpPanelImg[0], 0, 0, 995, 613,this);
		if(page==1)g.drawImage(imageGather.helpPanelImg[1], 0, 0, 995, 613,this);
		if(page==2)g.drawImage(imageGather.helpPanelImg[2], 0, 0, 995, 613,this);
		//»­·µ»ØÍ¼Ïñ
		g.drawImage(imageGather.backImg[backState], 80, 530, 60, 60, this);
		//»­ÉÏÒ»Ò³¡¢ÏÂÒ»Ò³Í¼±ê
		g.drawImage(imageGather.lastPageImg[lastPageState], 130, 530, 60, 60, this);
		g.drawImage(imageGather.nextPageImg[nextPageState], 200, 530, 60, 60, this);
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX()>80&&e.getX()<140&&e.getY()>550&&e.getY()<610) {//ÒÆµ½back
			Music.play(3, 0);
			backState=1;
			repaint();
		}
		else if(e.getX()>130 && e.getX()<190 && e.getY()>550 && e.getY()<610) {//ÒÆµ½lastPage
			Music.play(3, 0);
			lastPageState=1;
			repaint();
		}
		else if(e.getX()>200 && e.getX()<260 && e.getY()>550 && e.getY()<610) {//ÒÆµ½nextPage
			Music.play(3, 0);
			nextPageState=1;
			repaint();
		}
		else {
			Music.play(3, 2);
			backState=0;
			lastPageState=0;
			nextPageState=0;
			repaint();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX()>80&&e.getX()<140&&e.getY()>550&&e.getY()<610){//µã»÷back
			Music.play(4, 1);
			setVisible(false);
			}
		else if(e.getX()>130 && e.getX()<190 && e.getY()>550 && e.getY()<610) {//µã»÷lastPage
			Music.play(4, 1);
			if(page==1) page=0;
			else if(page==2) page=1;
			else if(page==0) page=2;
			repaint();
		}
		else if(e.getX()>200 && e.getX()<260 && e.getY()>550 && e.getY()<610) {//µã»÷nextPage
			Music.play(4, 1);
			if(page==1) page=2;
			else if(page==2) page=0;
			else if(page==0) page=1;
			repaint();
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
}
