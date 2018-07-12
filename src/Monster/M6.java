package Monster;

import java.awt.*;

import Player.Music;
import Player.Player;

public class M6 extends Monster{
	private Ms2 m;
	private boolean mov;
    public M6(Point []roadn,Ms2 mn){
    	super(roadn);
    	m=mn;
		hp=150;
		atk=30;
		val=200;
		mov=true;
    }
    public void move(){
    	if((Math.abs(location.x-road[loc].x)<step)&&(Math.abs(location.y-road[loc].y)<step))
    		loc++;
    	if(loc==road.length-1){
    		Player.reduceblood(atk);
    		mov=false;
    		loc--;
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
    		mov=false;
    		Player.coinChange(val);
    	}
    }
    public void run(){
    	while(mov){
    		move();
	    	try{
	    		sleep(timer);
	    	}catch(Exception e){}
    	}
    	direct=8;
    	Music.play(7, 1);
    	try{
    		sleep(4000);
    	}catch(Exception e){}
    	alive=false;
		m.n1--;
		m.aliveChange();
    }
}