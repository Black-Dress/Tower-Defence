package Bullet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;

public final class MagicBullet extends Bullet{
	//����
	public MagicBullet(Point MyPoint){		
		State=false;                             //��ʼ���ӵ���ֹ��
		Level='E';                               //�ӵ��ȼ�Ϊ0������ʼ�ȼ�����
		MyPosition=new Point(MyPoint);           //�ӵ�λ��Ϊ����λ�á�
		BaseDamage=10;                           //�ӵ��ĳ�ʼ������Ϊ5��
		FlyVelocity=20;                          //�ӵ��ĳ�ʼ�ٶȡ�
		EvePosition=new Point(MyPoint);          //�ӵ�ÿ�ηɳ���λ�ã�������λ�õĸ�������
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bulletmagic.png");//�ӵ���ͼƬ	��	
	}
	//�ӵ�����
	public void paint(Graphics graphics,JPanel jpanel) {
		//���ӵ����ڷ����ҵȼ�Ϊ0��ʱ��
	 if(this.State==true&&Level=='E') {	
		//�ӵ��㹻�ӽ�����ʱ�ı�ըЧ����
	    if(Math.abs(MyPosition.x-AttackMonster.location.x)<25 && Math.abs(MyPosition.y-AttackMonster.location.y)<25)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet9.png");	
	    //�ӵ�λ��ʵʱ���¡�
		this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.AttackMonster.location.x-this.MyPosition.x));
		this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.AttackMonster.location.y-this.MyPosition.y));
		//���ӵ�
		graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 20, 20,jpanel);
		//�ӵ���ɹ���ʱ��
		if((Math.abs(MyPosition.x-AttackMonster.location.x)<20 && Math.abs(MyPosition.y-AttackMonster.location.y)<20)) {		
		//�ӵ���Ϊ��ֹ״̬��
		this.State=false;
		//�ӵ����ԭͼ��
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bulletmagic.png");
		//�ӵ�������ϣ����ԭλ��
		this.bulletAttack();		
	    }
	 }
		//���ӵ����ڷ����ҵȼ�Ϊ1��ʱ��
		else if(this.State==true&&Level=='F') {
			//�ӵ��㹻�ӽ�����ʱ�ı�ըЧ����
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
		//���ӵ����ڷ����ҵȼ�Ϊ2��ʱ
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
	//�ӵ�������
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
