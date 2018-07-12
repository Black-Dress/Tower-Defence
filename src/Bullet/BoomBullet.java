package Bullet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;
public final class BoomBullet extends Bullet{
    private Point MiddlePosition;       //飞行制高点坐标。
    private boolean YesNO;              //飞向最高点。
    //箭塔的构造函数。
	public BoomBullet(Point MyPoint) {
		MiddlePosition=new Point(MyPoint);       //制高点位置。
		YesNO=true;		                         //飞向制高点
		State=false;                             //初始化子弹静止。
		Level='E';                               //子弹等级为0级（初始等级）。
		MyPosition=new Point(MyPoint);           //子弹位置为炮塔位置。
		BaseDamage=12;                            //子弹的初始攻击力为5。
		FlyVelocity=20;
		EvePosition=new Point(MyPoint);          //子弹每次飞出的位置（防御塔位置的附近）
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom.png");//子弹的图片。		
	}
	//子弹飞行
	public void paint(Graphics graphics,JPanel panel) {
		if(this.State==true) {
		//实时动态更新最高点坐标。	
			MiddlePosition.x=AttackMonster.location.x+(int)(0.2*(EvePosition.x-AttackMonster.location.x));
			MiddlePosition.y=EvePosition.y-80;
		//子弹向上飞，去往最高点。
		if((Math.abs(MyPosition.x-MiddlePosition.x)>=15 && Math.abs(MyPosition.y-MiddlePosition.y)>=15)&&YesNO==true) {		
		this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.MiddlePosition.x-this.MyPosition.x));
		this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.MiddlePosition.y-this.MyPosition.y));
		//二级塔三级塔的特效。
		if(Level=='F')
		this.changeAngleFlyToMiddlePosition();
		graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 20, 20,panel);
	    }
		//达到最高点。
		else {
		YesNO=false;
		}
		//是否可以进入爆炸图像。
		if(Math.abs(MyPosition.x-AttackMonster.location.x)<30 && Math.abs(MyPosition.y-AttackMonster.location.y)<30)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet9.png");
		//达到最高点开始下落，攻击怪物。
		if(YesNO==false) {
			this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.AttackMonster.location.x-this.MyPosition.x));
			this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.AttackMonster.location.y-this.MyPosition.y));	
			//二级塔三级塔的特效。
			if(Level=='F')
			this.changeAngleFlyToAttackMonster();
			graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 20, 20,panel);
		}
		//下落途中判断是否到达怪物位置。	
		if((Math.abs(MyPosition.x-AttackMonster.location.x)<25&& Math.abs(MyPosition.y-AttackMonster.location.y)<25)) {		
			this.State=false;
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom.png");
			this.bulletAttack();
			YesNO=true;
	     }
		
	  }		
	}	
	//子弹升级。
	public void levelUp(int highlevel) {	
		if(highlevel==1) {
		this.BaseDamage=20;
		this.Level='F';
		}
		else if(highlevel==2) {
		this.BaseDamage=30;
		this.Level='F';
		}
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
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom下.png");		   
		else if(propotion>-0.985&&propotion<=-0.939)	   
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下50.png");
		else if(propotion>-0.643&&propotion<=-0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下40.png");
	    else if(propotion>-0.500&&propotion<=-0.342)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下30.png");
		else if(propotion>-0.342&&propotion<=-0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下20.png");
		else if(propotion>-0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom横.png");			
		}
		//左上。
	if(x>=0&&y>=0){
		if(propotion>=0.985||x==0)				
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom上.png");
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上10.png");							
		}
		//右上。
	if(x<=0&&y>=0){
		if(propotion>=0.985||x==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom上.png");						   
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右.png");				
		}
		//右下角。
	if(x<=0&&y<=0){
		if(propotion<=-0.985||x==0)					
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom下.png");
		else if(propotion>-0.985&&propotion<=-0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下50.png");
	    else if(propotion>-0.643&&propotion<=-0.500)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下40.png");
		else if(propotion>-0.500&&propotion<=-0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下30.png");
	    else if(propotion>-0.342&&propotion<=-0.174)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下20.png");
	    else if(propotion>-0.174||y==0)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下10.png");				
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
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom下.png");		   
		else if(propotion>-0.985&&propotion<=-0.939)	   
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下50.png");
		else if(propotion>-0.643&&propotion<=-0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下40.png");
	    else if(propotion>-0.500&&propotion<=-0.342)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下30.png");
		else if(propotion>-0.342&&propotion<=-0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左下20.png");
		else if(propotion>-0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom横.png");			
		}
		//左上。
	if(x>=0&&y>=0){
		if(propotion>=0.985||x==0)				
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom上.png");
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom左上10.png");							
		}
		//右上。
	if(x<=0&&y>=0){
		if(propotion>=0.985||x==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom上.png");						   
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右上20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右.png");				
		}
		//右下角。
	if(x<=0&&y<=0){
		if(propotion<=-0.985||x==0)					
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom下.png");
		else if(propotion>-0.985&&propotion<=-0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下50.png");
	    else if(propotion>-0.643&&propotion<=-0.500)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下40.png");
		else if(propotion>-0.500&&propotion<=-0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下30.png");
	    else if(propotion>-0.342&&propotion<=-0.174)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下20.png");
	    else if(propotion>-0.174||y==0)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom右下10.png");				
		}
	if((Math.abs(x)<25&&Math.abs(y)<25))
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet9.png");
   }
}
