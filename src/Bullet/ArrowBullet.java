package Bullet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;
public final class ArrowBullet extends Bullet{
    private Point MiddlePosition;       //飞行制高点坐标。
    private boolean YesNO;              //飞向最高点。
    //箭塔的构造函数。
	public ArrowBullet(Point MyPoint) {
		MiddlePosition=new Point(MyPoint);       //制高点位置。
		YesNO=true;		                         //飞向制高点
		State=false;                             //初始化子弹静止。
		Level='E';                               //子弹等级为0级（初始等级）。
		MyPosition=new Point(MyPoint);           //子弹位置为炮塔位置。
		BaseDamage=8;                            //子弹的初始攻击力为5。
		FlyVelocity=20;
		EvePosition=new Point(MyPoint);          //子弹每次飞出的位置（防御塔位置的附近）
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom.png");//子弹的图片。		
	}
	//子弹飞行
	public void paint(Graphics graphics,JPanel panel) {
		if(this.State==true&&Level=='E') {
		//实时动态更新最高点坐标。	
		MiddlePosition.x=AttackMonster.location.x+(int)(0.7*(EvePosition.x-AttackMonster.location.x));
		MiddlePosition.y=EvePosition.y-80;	
		//子弹向上飞，去往最高点。
		if((Math.abs(MyPosition.x-MiddlePosition.x)>=15 && Math.abs(MyPosition.y-MiddlePosition.y)>=15)&&YesNO==true) {		
		this.MyPosition.x=this.MyPosition.x+(int)(0.18*(this.MiddlePosition.x-this.MyPosition.x));
		this.MyPosition.y=this.MyPosition.y+(int)(0.18*(this.MiddlePosition.y-this.MyPosition.y));
		//确定画图的角度和画什么图。
		this.changeAngleFlyToMiddlePosition();		
		graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 40, 40,panel);
	    }
		//达到最高点。
		else {
		YesNO=false;
		}
		//达到最高点开始下落，攻击怪物。
		if(YesNO==false) {
			this.MyPosition.x=this.MyPosition.x+(int)(0.25*(this.AttackMonster.location.x-this.MyPosition.x));
			this.MyPosition.y=this.MyPosition.y+(int)(0.25*(this.AttackMonster.location.y-this.MyPosition.y));
			//确定画图的角度和画什么图。
			this.changeAngleFlyToAttackMonster();
			//画子弹。
			graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 40, 40,panel);
		}
		//下落途中判断是否到达怪物位置。
		if((Math.abs(MyPosition.x-AttackMonster.location.x)<15&& Math.abs(MyPosition.y-AttackMonster.location.y)<15)) {		
			this.State=false;
			this.bulletAttack();
			YesNO=true;
	     }	
	}
		
}
	//子弹升级。
	public void levelUp(int highlevel) {	
		if(highlevel==1)
			this.BaseDamage=12;
		else if(highlevel==2)
			this.BaseDamage=16;		
	}
	//改变发射角度。
public void changeAngleFlyToMiddlePosition() {
		double father=0;
		double propotion=0;	 
	 int x=MyPosition.x-MiddlePosition.x;
	 int y=MyPosition.y-MiddlePosition.y;		  
     father=Math.sqrt(y*y+x*x);		
		if(father!=0)
		propotion=y/father;     
     //左下。
	if(x>=0&&y<=0){
		if(propotion<=-0.985||x==0)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic下.png");		   
		else if(propotion>-0.985&&propotion<=-0.939)	   
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下50.png");
		else if(propotion>-0.643&&propotion<=-0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下40.png");
	    else if(propotion>-0.500&&propotion<=-0.342)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下30.png");
		else if(propotion>-0.342&&propotion<=-0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下20.png");
		else if(propotion>-0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic横.png");			
		}
		//左上。
	if(x>=0&&y>=0){
		if(propotion>=0.985||x==0)				
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic上.png");
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上10.png");							
		}
		//右上。
	if(x<=0&&y>=0){
		if(propotion>=0.985||x==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic上.png");						   
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右.png");				
		}
		//右下角。
	if(x<=0&&y<=0){
		if(propotion<=-0.985||x==0)					
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic下.png");
		else if(propotion>-0.985&&propotion<=-0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下50.png");
	    else if(propotion>-0.643&&propotion<=-0.500)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下40.png");
		else if(propotion>-0.500&&propotion<=-0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下30.png");
	    else if(propotion>-0.342&&propotion<=-0.174)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下20.png");
	    else if(propotion>-0.174||y==0)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下10.png");				
		}				
	}
	//改变攻击怪物的角度。
	public void changeAngleFlyToAttackMonster() {
		double father=0;
		double propotion=0;
	 
	 int x=MyPosition.x-AttackMonster.location.x;
	 int y=MyPosition.y-AttackMonster.location.y;		  
     father=Math.sqrt(y*y+x*x);		
		if(father!=0)
		propotion=y/father;	     
	     //左下。
		if(x>=0&&y<=0){
			if(propotion<=-0.985||x==0)
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic下.png");		   
			else if(propotion>-0.985&&propotion<=-0.939)	   
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下80.png");
			else if(propotion>-0.939&&propotion<=-0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下70.png");
			else if(propotion>-0.866&&propotion<=-0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下60.png");
			else if(propotion>-0.766&&propotion<=-0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下50.png");
			else if(propotion>-0.643&&propotion<=-0.500)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下40.png");
		    else if(propotion>-0.500&&propotion<=-0.342)
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下30.png");
			else if(propotion>-0.342&&propotion<=-0.174)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左下20.png");
			else if(propotion>-0.174||y==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic横.png");			
			}
			//左上。
		if(x>=0&&y>=0){
			if(propotion>=0.985||x==0)				
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic上.png");
			else if(propotion<0.985&&propotion>=0.939)	   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上80.png");
			else if(propotion<0.939&&propotion>=0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上70.png");
			else if(propotion<0.866&&propotion>=0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上60.png");
			else if(propotion<0.766&&propotion>=0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上50.png");
			else if(propotion<0.643&&propotion>=0.500)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上40.png");
			else if(propotion<0.500&&propotion>=0.342)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上30.png");
			else if(propotion<0.342&&propotion>=0.174)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上20.png");
			else if(propotion<0.174||y==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic左上10.png");							
			}
			//右上。
		if(x<=0&&y>=0){
			if(propotion>=0.985||x==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic上.png");						   
			else if(propotion<0.985&&propotion>=0.939)	   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上80.png");
			else if(propotion<0.939&&propotion>=0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上70.png");
			else if(propotion<0.866&&propotion>=0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上60.png");
			else if(propotion<0.766&&propotion>=0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上50.png");
			else if(propotion<0.643&&propotion>=0.500)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上40.png");
			else if(propotion<0.500&&propotion>=0.342)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上30.png");
			else if(propotion<0.342&&propotion>=0.174)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右上20.png");
			else if(propotion<0.174||y==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右.png");				
			}
			//右下角。
		if(x<=0&&y<=0){
			if(propotion<=-0.985||x==0)					
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic下.png");
			else if(propotion>-0.985&&propotion<=-0.939)	   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下80.png");
			else if(propotion>-0.939&&propotion<=-0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下70.png");
			else if(propotion>-0.866&&propotion<=-0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下60.png");
			else if(propotion>-0.766&&propotion<=-0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下50.png");
		    else if(propotion>-0.643&&propotion<=-0.500)
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下40.png");
			else if(propotion>-0.500&&propotion<=-0.342)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下30.png");
		    else if(propotion>-0.342&&propotion<=-0.174)
			 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下20.png");
		    else if(propotion>-0.174||y==0)
			 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic右下10.png");				
			}	
	}
}
