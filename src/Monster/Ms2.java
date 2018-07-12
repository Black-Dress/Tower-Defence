package Monster;

import java.awt.*;

import Player.Player;
import Player.imageGather;
public class Ms2 extends Monsters
{
	private M4[] m1;
	private M5[] m2;
	private M6 m3;
	private Point []road1;
	private Point []road2;
	public int n1;
	private int N1,N2;
	private Monster []aliveM;
	public Ms2(Point []roadn1,Point []roadn2){
		N1=36;
		N2=15;
		n1=N1+N2+1;
		Player.reducemonsterNumber(n1);
		m1=new M4[N1];
		m2=new M5[N2];
		road1=roadn1;
		road2=roadn2;
		for(int i=0;i<N1;i++){
			if(i%2==0)
				m1[i]=new M4(road1,this);
			else
				m1[i]=new M4(road2,this);
		};
		for(int i=0;i<N2;i++){
			if(i%2==0)
				m2[i]=new M5(road1,this);
			else
				m2[i]=new M5(road2,this);
		}
		m3=new M6(road1,this);
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
				g.drawImage(imageGather.enemy4[temp.direct],temp.location.x-10,temp.location.y-10,null);
				g.setColor(Color.cyan);
 				g.drawRect(temp.location.x-11,temp.location.y-15, 22, 5);
 				g.setColor(Color.red);
 				g.fillRect(temp.location.x-10,temp.location.y-14, (int)(temp.hp*0.4), 3);
 			}
		}
		for(int i=0;i<N2;i++){
			temp=m2[i];
 			if(temp.alive) {
				g.drawImage(imageGather.enemy5[temp.direct],temp.location.x-10,temp.location.y-10,null);
				g.setColor(Color.cyan);
 				g.drawRect(temp.location.x-11,temp.location.y-15, 22, 5);
 				g.setColor(Color.red);
 				g.fillRect(temp.location.x-10,temp.location.y-14, (int)(temp.hp*0.286), 3);
 			}
		}
		temp=m3;
			if(temp.alive) {
			g.drawImage(imageGather.enemy6[temp.direct],temp.location.x-10,temp.location.y-50,null);
			g.setColor(Color.cyan);
				g.drawRect(temp.location.x-3,temp.location.y-55, 42, 5);
				g.setColor(Color.red);
				g.fillRect(temp.location.x-2,temp.location.y-54, (int)(temp.hp*0.267), 3);
			}
	}
	public void run(){
		for(int i=0;i<12;i++)
		{
			m1[i].start();
			try{
				sleep(1000);
			}catch(Exception e){}
		}
		try{
			sleep(10000);
		}catch(Exception e){}
		for(int i=12;i<24;i++)
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
			sleep(3000);
		}catch(Exception e){}
		m3.start();
		try{
			sleep(1000);
		}catch(Exception e){}
		for(int i=24;i<36;i++)
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
}