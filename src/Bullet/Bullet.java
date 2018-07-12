package Bullet;
import javax.swing.*;
import java.awt.*;
import Monster.*;
public abstract class Bullet {
//属性
protected	int BaseDamage;                      //子弹攻击力。
protected	int FlyVelocity;                     //子弹飞行速度。
protected	boolean State;                       //子弹状态true：飞行。false：静止状态。
protected	char Level;                          //子弹等级
protected	Monster AttackMonster;               //目标怪物。
protected	Point MyPosition;                    //子弹当前的位置。
protected   Point EvePosition;                   //子弹初始的位置（炮塔位置）。
protected   Image bulletPicture;                 //子弹的图片

//方法
//确定目标怪物。
public void attackMonster(Monster MyMonster) {//防御塔传入在其攻击范围内的怪物，开始攻击
	if(this.State==false&&MyMonster!=null) {  //发射子弹。
	this.AttackMonster=MyMonster;
	this.setState(true);	
	}
}
//怪物扣血，子弹复原。
public void bulletAttack() {	            //攻击完毕后子弹回到原来的位置
	MyPosition.x=EvePosition.x;
	MyPosition.y=EvePosition.y;
	AttackMonster.injury(this.BaseDamage);                         //完成一次攻击。		
}
//获得当前状态。
public boolean getState() {	
	return State;
}
//获得当前速度。
public int getFlyVelocity() {
	return FlyVelocity;
}
//获得当前攻击力。
public int getBaseDamage() {
	return BaseDamage;
} 
//设置攻击力。
public void setBaseDamage(int MyBaseDamage) {
	this.BaseDamage=MyBaseDamage;	
}
//设置速度。
public void setFlyVelocity(int MyFlyVelocity) {
	this.BaseDamage=MyFlyVelocity;	
}
//设置子弹状态。
public void setState(boolean MyState) {
	this.State=MyState;
}
//子弹回到初始位置。
public void setEvenPosition() {
	this.MyPosition=this.EvePosition;
	this.State=false;
}
//子弹飞行
public abstract void paint(Graphics graphics,JPanel panel);
//子弹升级。
public abstract void levelUp(int highlevel);
}
