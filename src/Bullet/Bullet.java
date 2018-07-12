package Bullet;
import javax.swing.*;
import java.awt.*;
import Monster.*;
public abstract class Bullet {
//����
protected	int BaseDamage;                      //�ӵ���������
protected	int FlyVelocity;                     //�ӵ������ٶȡ�
protected	boolean State;                       //�ӵ�״̬true�����С�false����ֹ״̬��
protected	char Level;                          //�ӵ��ȼ�
protected	Monster AttackMonster;               //Ŀ����
protected	Point MyPosition;                    //�ӵ���ǰ��λ�á�
protected   Point EvePosition;                   //�ӵ���ʼ��λ�ã�����λ�ã���
protected   Image bulletPicture;                 //�ӵ���ͼƬ

//����
//ȷ��Ŀ����
public void attackMonster(Monster MyMonster) {//�������������乥����Χ�ڵĹ����ʼ����
	if(this.State==false&&MyMonster!=null) {  //�����ӵ���
	this.AttackMonster=MyMonster;
	this.setState(true);	
	}
}
//�����Ѫ���ӵ���ԭ��
public void bulletAttack() {	            //������Ϻ��ӵ��ص�ԭ����λ��
	MyPosition.x=EvePosition.x;
	MyPosition.y=EvePosition.y;
	AttackMonster.injury(this.BaseDamage);                         //���һ�ι�����		
}
//��õ�ǰ״̬��
public boolean getState() {	
	return State;
}
//��õ�ǰ�ٶȡ�
public int getFlyVelocity() {
	return FlyVelocity;
}
//��õ�ǰ��������
public int getBaseDamage() {
	return BaseDamage;
} 
//���ù�������
public void setBaseDamage(int MyBaseDamage) {
	this.BaseDamage=MyBaseDamage;	
}
//�����ٶȡ�
public void setFlyVelocity(int MyFlyVelocity) {
	this.BaseDamage=MyFlyVelocity;	
}
//�����ӵ�״̬��
public void setState(boolean MyState) {
	this.State=MyState;
}
//�ӵ��ص���ʼλ�á�
public void setEvenPosition() {
	this.MyPosition=this.EvePosition;
	this.State=false;
}
//�ӵ�����
public abstract void paint(Graphics graphics,JPanel panel);
//�ӵ�������
public abstract void levelUp(int highlevel);
}
