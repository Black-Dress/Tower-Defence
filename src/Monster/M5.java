package Monster;

import java.awt.*;

import Player.Player;

public class M5 extends Monster{
	private Ms2 m;
    public M5(Point []roadn,Ms2 mn){
    	super(roadn);
    	m=mn;
		hp=70;
		atk=5;
		val=40;
    }
    public void move(){
    	if((Math.abs(location.x-road[loc].x)<step)&&(Math.abs(location.y-road[loc].y)<step))
    		loc++;
    	if(loc==road.length-1){
    		Player.reduceblood(atk);
    		alive=false;
    		loc--;
    		m.n1--;
    		m.aliveChange();
    		return;
    	}
    	int dx=road[loc].x-location.x;
    	int dy=road[loc].y-location.y;
    	if(dx>-dy){
    		if(dx>dy){
    			if(direct==0)
    				direct=1;
    			else
    				direct=0;
    		}
    		else{
    			if(direct==6)
    				direct=7;
    			else
    				direct=6;
    		}
    	}
    	else{
    		if(dx>dy){
    			if(direct==4)
    				direct=5;
    			else
    				direct=4;
    		}
    		else{
    			if(direct==2)
    				direct=3;
    			else
    				direct=2;
    		}
    	}
    	if(dx>0)
    		location.x+=step;
    	else if(dx<0)
    		location.x-=step;
    	if(dy>0)
    		location.y+=step;
    	else if(dy<0)
    		location.y-=step;
    }
    public void injury(int x){
    	hp-=x;
    	if(hp<=0&&alive) {
    		alive=false;
    		Player.coinChange(val);
    		m.n1--;
    		m.aliveChange();
    	}
    }
    public void run(){
    	while(alive){
    		move();
	    	try{
	    		sleep(timer);
	    	}catch(Exception e){}
    	}
    }
}