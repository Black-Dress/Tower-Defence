package prop;

import java.awt.*;

import Monster.Monsters;
import Player.imageGather;
public class prop extends Thread{
	public boolean flag,flag1,f,f2;
	private int x,y,x2,y2;
	private int ex,ey;
	private Monsters m;
	private int z;
	public int k,k2;
	public prop(){

		x=-200;
		y=-200;

		x2=-200;
		y2=-200;

		flag=false;
		flag1=false;
		f=false;
		f2=false;
		z=1;
	}
	public void setMonsters(Monsters mn){
		m=mn;
	}
	public void paint(Graphics g){
		g.drawImage(imageGather.prop[0],x,y,null);
		g.drawImage(imageGather.prop[z], x2-40, y2-40,null);
	}
	public void setXY(int kx,int ky) {
		ex=kx;
		ey=ky;
	}
	public void run(){
		while(true) {
			if(flag1) {
				if(m.getmonsters() != null) {
					//n1是怪物数monsters.getmonsters()返回从0到n1-1共n1个活着的怪物
					for(int i = 0;i < m.n1;i++) {
						double radius=0;
						if(m.getmonsters()[i]!=null) {
							radius = Math.sqrt(Math.pow(Math.abs(ex - m.getmonsters()[i].location.x), 2)+Math.pow(Math.abs(ey - m.getmonsters()[i].location.y), 2));
							if(radius <= 20) {
								x=m.getmonsters()[i].location.x-20;
								y=m.getmonsters()[i].location.y-20;
								m.getmonsters()[i].injury(200);
								flag1=false;
								flag=false;
								try {
									sleep(1500);
								}catch(Exception e) {}
								break;
							}
						}
					}
				}
				
				x=-200;
				y=-200;
			}



			if(f2) {
				if(m.getmonsters() != null) {
					//n1是怪物数monsters.getmonsters()返回从0到n1-1共n1个活着的怪物
					for(int i = 0;i < m.n1;i++) {
						double radius=0;
						if(m.getmonsters()[i]!=null) {
							radius = Math.sqrt(Math.pow(Math.abs(ex - m.getmonsters()[i].location.x), 2)+Math.pow(Math.abs(ey - m.getmonsters()[i].location.y), 2));
							if(radius <= 80) {
								m.getmonsters()[i].injury(200);
								f2=false;
								f=false;
								x2=ex;
								y2=ey;
							}
						}

					}
				}
				for(int i=0;i<10;i++) {
					z=1+i%4;
					try {
						sleep(100);
					}catch(Exception e) {}
				}
				x2=-200;
				y2=-200;
			}

			try {
				sleep(1);
			}catch(Exception e) {}
		}

	}
}