package Bullet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;
public final class BoomBullet extends Bullet{
    private Point MiddlePosition;       //�����Ƹߵ����ꡣ
    private boolean YesNO;              //������ߵ㡣
    //�����Ĺ��캯����
	public BoomBullet(Point MyPoint) {
		MiddlePosition=new Point(MyPoint);       //�Ƹߵ�λ�á�
		YesNO=true;		                         //�����Ƹߵ�
		State=false;                             //��ʼ���ӵ���ֹ��
		Level='E';                               //�ӵ��ȼ�Ϊ0������ʼ�ȼ�����
		MyPosition=new Point(MyPoint);           //�ӵ�λ��Ϊ����λ�á�
		BaseDamage=12;                            //�ӵ��ĳ�ʼ������Ϊ5��
		FlyVelocity=20;
		EvePosition=new Point(MyPoint);          //�ӵ�ÿ�ηɳ���λ�ã�������λ�õĸ�����
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom.png");//�ӵ���ͼƬ��		
	}
	//�ӵ�����
	public void paint(Graphics graphics,JPanel panel) {
		if(this.State==true) {
		//ʵʱ��̬������ߵ����ꡣ	
			MiddlePosition.x=AttackMonster.location.x+(int)(0.2*(EvePosition.x-AttackMonster.location.x));
			MiddlePosition.y=EvePosition.y-80;
		//�ӵ����Ϸɣ�ȥ����ߵ㡣
		if((Math.abs(MyPosition.x-MiddlePosition.x)>=15 && Math.abs(MyPosition.y-MiddlePosition.y)>=15)&&YesNO==true) {		
		this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.MiddlePosition.x-this.MyPosition.x));
		this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.MiddlePosition.y-this.MyPosition.y));
		//����������������Ч��
		if(Level=='F')
		this.changeAngleFlyToMiddlePosition();
		graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 20, 20,panel);
	    }
		//�ﵽ��ߵ㡣
		else {
		YesNO=false;
		}
		//�Ƿ���Խ��뱬ըͼ��
		if(Math.abs(MyPosition.x-AttackMonster.location.x)<30 && Math.abs(MyPosition.y-AttackMonster.location.y)<30)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet9.png");
		//�ﵽ��ߵ㿪ʼ���䣬�������
		if(YesNO==false) {
			this.MyPosition.x=this.MyPosition.x+(int)(0.20*(this.AttackMonster.location.x-this.MyPosition.x));
			this.MyPosition.y=this.MyPosition.y+(int)(0.20*(this.AttackMonster.location.y-this.MyPosition.y));	
			//����������������Ч��
			if(Level=='F')
			this.changeAngleFlyToAttackMonster();
			graphics.drawImage(bulletPicture, MyPosition.x, MyPosition.y, 20, 20,panel);
		}
		//����;���ж��Ƿ񵽴����λ�á�	
		if((Math.abs(MyPosition.x-AttackMonster.location.x)<25&& Math.abs(MyPosition.y-AttackMonster.location.y)<25)) {		
			this.State=false;
			bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom.png");
			this.bulletAttack();
			YesNO=true;
	     }
		
	  }		
	}	
	//�ӵ�������
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
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");		   
		else if(propotion>-0.985&&propotion<=-0.939)	   
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
		else if(propotion>-0.643&&propotion<=-0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
	    else if(propotion>-0.500&&propotion<=-0.342)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
		else if(propotion>-0.342&&propotion<=-0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
		else if(propotion>-0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");			
		}
		//���ϡ�
	if(x>=0&&y>=0){
		if(propotion>=0.985||x==0)				
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����10.png");							
		}
		//���ϡ�
	if(x<=0&&y>=0){
		if(propotion>=0.985||x==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");						   
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");				
		}
		//���½ǡ�
	if(x<=0&&y<=0){
		if(propotion<=-0.985||x==0)					
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");
		else if(propotion>-0.985&&propotion<=-0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
	    else if(propotion>-0.643&&propotion<=-0.500)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
		else if(propotion>-0.500&&propotion<=-0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
	    else if(propotion>-0.342&&propotion<=-0.174)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
	    else if(propotion>-0.174||y==0)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����10.png");				
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
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");		   
		else if(propotion>-0.985&&propotion<=-0.939)	   
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
		else if(propotion>-0.643&&propotion<=-0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
	    else if(propotion>-0.500&&propotion<=-0.342)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
		else if(propotion>-0.342&&propotion<=-0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
		else if(propotion>-0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");			
		}
		//���ϡ�
	if(x>=0&&y>=0){
		if(propotion>=0.985||x==0)				
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����10.png");							
		}
		//���ϡ�
	if(x<=0&&y>=0){
		if(propotion>=0.985||x==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");						   
		else if(propotion<0.985&&propotion>=0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion<0.939&&propotion>=0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion<0.866&&propotion>=0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion<0.766&&propotion>=0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
		else if(propotion<0.643&&propotion>=0.500)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
		else if(propotion<0.500&&propotion>=0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
		else if(propotion<0.342&&propotion>=0.174)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
		else if(propotion<0.174||y==0)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");				
		}
		//���½ǡ�
	if(x<=0&&y<=0){
		if(propotion<=-0.985||x==0)					
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom��.png");
		else if(propotion>-0.985&&propotion<=-0.939)	   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����80.png");
		else if(propotion>-0.939&&propotion<=-0.866)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����70.png");
		else if(propotion>-0.866&&propotion<=-0.766)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����60.png");
		else if(propotion>-0.766&&propotion<=-0.643)   
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����50.png");
	    else if(propotion>-0.643&&propotion<=-0.500)
	    bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����40.png");
		else if(propotion>-0.500&&propotion<=-0.342)
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����30.png");
	    else if(propotion>-0.342&&propotion<=-0.174)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����20.png");
	    else if(propotion>-0.174||y==0)
		 bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/boom����10.png");				
		}
	if((Math.abs(x)<25&&Math.abs(y)<25))
		bulletPicture=Toolkit.getDefaultToolkit().getImage("./pic/Bullets/bullet9.png");
   }
}
