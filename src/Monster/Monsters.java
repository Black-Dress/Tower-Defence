package Monster;

import java.awt.*;

import Player.Player;
import Player.imageGather;
public class Monsters extends Thread
{
	private M1[] m1;
	private M2[] m2;
	private M3 m3;
	private Point []road1;
	private Point []road2;
	public int n1;
	private int N1;
	private int N2;
	private Monster []aliveM;
	public Monsters(Point []roadn1,Point []roadn2){
		N1=30;
		N2=45;
		n1=N1+N2+1;
		Player.reducemonsterNumber(n1);
		m1=new M1[N1];
		m2=new M2[N2];
		road1=roadn1;
		road2=roadn2;
		for(int i=0;i<N1;i++){
			if(i%2==0)
				m1[i]=new M1(road1,this);
			else
				m1[i]=new M1(road2,this);
		};
		for(int i=0;i<N2;i++){
			if(i%2==0)
				m2[i]=new M2(road1,this);
			else
				m2[i]=new M2(road2,this);
		}
		m3=new M3(road1,this);
		aliveM=new Monster[n1];
		int j=0;
		for(int i=0;i<N1;i++){
			if(m1[i].alive){
				aliveM[j]=m1[i];
				j++;
			}
		}
		for(int i=0;i<N2;i++){
			if(m2[i].alive){
				aliveM[j]=m2[i];
				j++;
			}
		}
		if(m3.alive)
			aliveM[j]=m3;
	}
	public void aliveChange() {
		aliveM=new Monster[n1];
		Player.reducemonsterNumber(-(Player.getmonsterNumber()));
		Player.reducemonsterNumber(n1);
		int j=0;
		for(int i=0;i<N1;i++){
			if(m1[i].alive){
				aliveM[j]=m1[i];
				j++;
			}
		}
		for(int i=0;i<N2;i++){
			if(m2[i].alive){
				aliveM[j]=m2[i];
				j++;
			}
		}
		if(m3.alive)
			aliveM[j]=m3;
	}
	public void paint(Graphics g){
		Monster temp;
		for(int i=0;i<N1;i++){
			temp=m1[i];
 			if(temp.alive) {
				g.drawImage(imageGather.enemy1[temp.direct],temp.location.x-10,temp.location.y-10,null);
				g.setColor(Color.cyan);
 				g.drawRect(temp.location.x-11,temp.location.y-15, 22, 5);
 				g.setColor(Color.red);
 				g.fillRect(temp.location.x-10,temp.location.y-14, (int)(temp.hp*0.4), 3);
 			}
		}
		for(int i=0;i<N2;i++){
			temp=m2[i];
 			if(temp.alive) {
				g.drawImage(imageGather.enemy2[temp.direct],temp.location.x-10,temp.location.y-10,null);
				g.setColor(Color.cyan);
 				g.drawRect(temp.location.x-11,temp.location.y-15, 22, 5);
 				g.setColor(Color.red);
 				g.fillRect(temp.location.x-10,temp.location.y-14, (int)(temp.hp*0.333), 3);
 			}
		}
		temp=m3;
			if(temp.alive) {
			g.drawImage(imageGather.enemy3[temp.direct],temp.location.x-10,temp.location.y-50,null);
			g.setColor(Color.cyan);
				g.drawRect(temp.location.x-3,temp.location.y-55, 42, 5);
				g.setColor(Color.red);
				g.fillRect(temp.location.x-2,temp.location.y-54, (int)(temp.hp*0.286), 3);
			}
	}
	public void run(){
		for(int i=0;i<10;i++)
		{
			m1[i].start();
			try{
				sleep(1000);
			}catch(Exception e){}
		}
		try{
			sleep(10000);
		}catch(Exception e){}
		for(int i=10;i<20;i++)
		{
			m1[i].start();
			try{
				sleep(1000);
			}catch(Exception e){}
		}
		try{
			sleep(10000);
		}catch(Exception e){}
		for(int i=0;i<15;i++)
		{
			m2[i].start();
			try{
				sleep(1000);
			}catch(Exception e){}
		}
		try{
			sleep(10000);
		}catch(Exception e){}
		for(int i=15;i<30;i++)
		{
			m2[i].start();
			try{
				sleep(1000);
			}catch(Exception e){}
		}
		try{
			sleep(3000);
		}catch(Exception e){}
		m3.start();
		try{
			sleep(10000);
		}catch(Exception e){}
		for(int i=30;i<45;i++)
		{
			m2[i].start();
			try{
				sleep(1000);
			}catch(Exception e){}
		}
		try{
			sleep(10000);
		}catch(Exception e){}
		for(int i=20;i<30;i++)
		{
			m1[i].start();
			try{
				sleep(1000);
			}catch(Exception e){}
		}
	}
	public Monster[] getmonsters(){
		if(n1>0) {
			return aliveM;
		}else
			return null;
	}
	protected Monsters() {}
}