package Bullet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;
public final class ArrowBullet extends Bullet{
    private Point MiddlePosition;       //�����Ƹߵ����ꡣ
    private boolean YesNO;              //������ߵ㡣
    //�����Ĺ��캯����
	public ArrowBullet(Point MyPoint) {
		MiddlePosition=new Point(MyPoint);       //�Ƹߵ�λ�á�
		YesNO=true;		                         //�����Ƹߵ�
		State=false;                             //��ʼ���ӵ���ֹ��
		Level='E';                               //�ӵ��ȼ�Ϊ0������ʼ�ȼ�����
		MyPosition=new Point(MyPoint);           //�ӵ�λ��Ϊ����λ�á�
		BaseDamage=8;                            //�ӵ��ĳ�ʼ������Ϊ5��
		FlyVelocity=20;
		EvePosition=new Point(MyPoint);          //�ӵ�ÿ�ηɳ���λ�ã�������λ�õĸ�����
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom.png");//�ӵ���ͼƬ��		
	}
	//�ӵ�����
	public void paint(Graphics graphics,JPanel panel) {
		if(this.State==true&&Level=='E') {
		//ʵʱ��̬������ߵ����ꡣ	
		MiddlePosition.x=AttackMonster.location.x+(int)(0.7*(EvePosition.x-AttackMonster.location.x));
		MiddlePosition.y=EvePosition.y-80;	
		//�ӵ����Ϸɣ�ȥ����ߵ㡣
		if((Math.abs(MyPosition.x-MiddlePosition.x)>=15 && Math.abs(MyPosition.y-MiddlePosition.y)>=15)&&YesNO==true) {		
		this.MyPosition.x=this.MyPosition.x+(int)(0.18*(this.MiddlePosition.x-this.MyPosition.x));
		this.MyPosition.y=this.MyPosition.y+(int)(0.18*(this.MiddlePosition.y-this.MyPosition.y));
		//ȷ����ͼ�ĽǶȺͻ�ʲôͼ��
		this.changeAngleFlyToMiddlePosition();		
		graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 40, 40,panel);
	    }
		//�ﵽ��ߵ㡣
		else {
		YesNO=false;
		}
		//�ﵽ��ߵ㿪ʼ���䣬�������
		if(YesNO==false) {
			this.MyPosition.x=this.MyPosition.x+(int)(0.25*(this.AttackMonster.location.x-this.MyPosition.x));
			this.MyPosition.y=this.MyPosition.y+(int)(0.25*(this.AttackMonster.location.y-this.MyPosition.y));
			//ȷ����ͼ�ĽǶȺͻ�ʲôͼ��
			this.changeAngleFlyToAttackMonster();
			//���ӵ���
			graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 40, 40,panel);
		}
		//����;���ж��Ƿ񵽴����λ�á�
		if((Math.abs(MyPosition.x-AttackMonster.location.x)<15&& Math.abs(MyPosition.y-AttackMonster.location.y)<15)) {		
			this.State=false;
			this.bulletAttack();
			YesNO=true;
	     }	
	}
		
}
	//�ӵ�������
	public void levelUp(int highlevel) {	
		if(highlevel==1)
			this.BaseDamage=12;
		else if(highlevel==2)
			this.BaseDamage=16;		
	}
	//�ı䷢��Ƕȡ�
public void changeAngleFlyToMiddlePosition() {
		double father=0;
		double propotion=0;	 
	 int x=MyPosition.x-MiddlePosition.x;
	 int y=MyPosition.y-MiddlePosition.y;		  
     father=Math.sqrt(y*y+x*x);		
		if(father!=0)
		propotion=y/father;     
     //���¡�
	if(x>=0&&y<=0){
		if(propotion<=-0.985||x==0)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");		   
		else if(propotion>-0.985&&propotion<=-0.939)	   
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
		else if(propotion>-0.643&&propotion<=-0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
	    else if(propotion>-0.500&&propotion<=-0.342)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
		else if(propotion>-0.342&&propotion<=-0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
		else if(propotion>-0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");			
		}
		//���ϡ�
	if(x>=0&&y>=0){
		if(propotion>=0.985||x==0)				
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����10.png");							
		}
		//���ϡ�
	if(x<=0&&y>=0){
		if(propotion>=0.985||x==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");						   
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");				
		}
		//���½ǡ�
	if(x<=0&&y<=0){
		if(propotion<=-0.985||x==0)					
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");
		else if(propotion>-0.985&&propotion<=-0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
	    else if(propotion>-0.643&&propotion<=-0.500)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
		else if(propotion>-0.500&&propotion<=-0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
	    else if(propotion>-0.342&&propotion<=-0.174)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
	    else if(propotion>-0.174||y==0)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����10.png");				
		}				
	}
	//�ı乥������ĽǶȡ�
	public void changeAngleFlyToAttackMonster() {
		double father=0;
		double propotion=0;
	 
	 int x=MyPosition.x-AttackMonster.location.x;
	 int y=MyPosition.y-AttackMonster.location.y;		  
     father=Math.sqrt(y*y+x*x);		
		if(father!=0)
		propotion=y/father;	     
	     //���¡�
		if(x>=0&&y<=0){
			if(propotion<=-0.985||x==0)
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");		   
			else if(propotion>-0.985&&propotion<=-0.939)	   
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
			else if(propotion>-0.939&&propotion<=-0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
			else if(propotion>-0.866&&propotion<=-0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
			else if(propotion>-0.766&&propotion<=-0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
			else if(propotion>-0.643&&propotion<=-0.500)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
		    else if(propotion>-0.500&&propotion<=-0.342)
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
			else if(propotion>-0.342&&propotion<=-0.174)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
			else if(propotion>-0.174||y==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");			
			}
			//���ϡ�
		if(x>=0&&y>=0){
			if(propotion>=0.985||x==0)				
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");
			else if(propotion<0.985&&propotion>=0.939)	   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
			else if(propotion<0.939&&propotion>=0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
			else if(propotion<0.866&&propotion>=0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
			else if(propotion<0.766&&propotion>=0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
			else if(propotion<0.643&&propotion>=0.500)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
			else if(propotion<0.500&&propotion>=0.342)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
			else if(propotion<0.342&&propotion>=0.174)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
			else if(propotion<0.174||y==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����10.png");							
			}
			//���ϡ�
		if(x<=0&&y>=0){
			if(propotion>=0.985||x==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");						   
			else if(propotion<0.985&&propotion>=0.939)	   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
			else if(propotion<0.939&&propotion>=0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
			else if(propotion<0.866&&propotion>=0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
			else if(propotion<0.766&&propotion>=0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
			else if(propotion<0.643&&propotion>=0.500)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
			else if(propotion<0.500&&propotion>=0.342)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
			else if(propotion<0.342&&propotion>=0.174)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
			else if(propotion<0.174||y==0)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");				
			}
			//���½ǡ�
		if(x<=0&&y<=0){
			if(propotion<=-0.985||x==0)					
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic��.png");
			else if(propotion>-0.985&&propotion<=-0.939)	   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����80.png");
			else if(propotion>-0.939&&propotion<=-0.866)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����70.png");
			else if(propotion>-0.866&&propotion<=-0.766)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����60.png");
			else if(propotion>-0.766&&propotion<=-0.643)   
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����50.png");
		    else if(propotion>-0.643&&propotion<=-0.500)
		    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����40.png");
			else if(propotion>-0.500&&propotion<=-0.342)
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����30.png");
		    else if(propotion>-0.342&&propotion<=-0.174)
			 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����20.png");
		    else if(propotion>-0.174||y==0)
			 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/magic����10.png");				
			}	
	}
}
