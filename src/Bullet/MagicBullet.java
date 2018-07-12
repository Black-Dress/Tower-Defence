package Bullet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;

public final class MagicBullet extends Bullet{
	//方法
	public MagicBullet(Point MyPoint){		
		State=false;                             //初始化子弹静止。
		Level='E';                               //子弹等级为0级（初始等级）。
		MyPosition=new Point(MyPoint);           //子弹位置为炮塔位置。
		BaseDamage=10;                           //子弹的初始攻击力为5。
		FlyVelocity=20;                          //子弹的初始速度。
		EvePosition=new Point(MyPoint);          //子弹每次飞出的位置（防御塔位置的附近）。
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bulletmagic.png");//子弹的图片	。	
	}
	//子弹飞行
	public void paint(Graphics graphics,JPanel jpanel) {
		//当子弹正在飞行且等级为0级时。
	 if(this.State==true&&Level=='E') {	
		//子弹足够接近怪物时的爆炸效果。
	    if(Math.abs(MyPosition.x-AttackMonster.location.x)<25 && Math.abs(MyPosition.y-AttackMonster.location.y)<25)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet9.png");	
	    //子弹位置实时更新。
		this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.AttackMonster.location.x-this.MyPosition.x));
		this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.AttackMonster.location.y-this.MyPosition.y));
		//画子弹
		graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 20, 20,jpanel);
		//子弹完成攻击时。
		if((Math.abs(MyPosition.x-AttackMonster.location.x)<20 && Math.abs(MyPosition.y-AttackMonster.location.y)<20)) {		
		//子弹变为静止状态。
		this.State=false;
		//子弹变回原图。
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bulletmagic.png");
		//子弹攻击完毕，归回原位。
		this.bulletAttack();		
	    }
	 }
		//当子弹正在飞行且等级为1级时。
		else if(this.State==true&&Level=='F') {
			//子弹足够接近怪物时的爆炸效果。
			   if(Math.abs(MyPosition.x-AttackMonster.location.x)<25 && Math.abs(MyPosition.y-AttackMonster.location.y)<25)
				   bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet9.png");			
				this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.AttackMonster.location.x-this.MyPosition.x));
				this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.AttackMonster.location.y-this.MyPosition.y));		
				graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 20, 20,jpanel);
				   if((Math.abs(MyPosition.x-AttackMonster.location.x)<20 && Math.abs(MyPosition.y-AttackMonster.location.y)<20)) {		
					this.State=false;
					bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet.png");
					this.bulletAttack();		
			     }									
		}
		//当子弹正在飞行且等级为2级时
		else if(this.State==true&&Level=='S'){			   				
			this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.AttackMonster.location.x-this.MyPosition.x));
			this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.AttackMonster.location.y-this.MyPosition.y));
			graphics.drawImage(bulletPicture, AttackMonster.location.x,AttackMonster.location.y, 40, 40,jpanel);
			   if((Math.abs(MyPosition.x-AttackMonster.location.x)<20 && Math.abs(MyPosition.y-AttackMonster.location.y)<20)) {		
				this.State=false;
				this.bulletAttack();		
		     }    									
		}
	 }
	//子弹升级。
	public void levelUp(int highlevel) {	
		if(highlevel==1) {
			this.BaseDamage=18;
			this.Level='F';
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet.png");	
		}			
		else if(highlevel==2) {
			this.BaseDamage=26;	
			this.Level='S';
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/reign.png");
		}					
	}		
}
