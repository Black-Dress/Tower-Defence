package Monster;

import java.awt.*;
public abstract class Monster extends Thread
{
	public double hp;
	public int atk;
	public int val;
	protected Point []road;
	public int direct;
    public Point location;
    protected int loc;
    public boolean alive;
	public static int step=2;
	public static int timer=100;
	public Monster(Point []roadn){
		direct=0;
		road=roadn;
		location=new Point(road[0]);
		loc=0;
		alive=true;
	}
	public abstract void move();
	public abstract void injury(int x);
}
