package Player;

import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Music implements Runnable {

	public static int musicNumbers = 12;
	public static AudioStream []BGM = new AudioStream[musicNumbers];				
	/*
	 * 0    		游戏进行中的BGM
	 * 1  			游戏胜利BGM
	 * 2 			游戏失败BGM
	 * 3			进入点击范围时音效
	 * 4			点击音效
	 * 5			点击空地和防御塔时音效
	 * 6 			明明打完这场战
	 * 7			我和哥哥有个约定
	 * 8			男死亡
	 * 9   			女死亡
	 * 10			锯木头
	 * 11			出怪
	 */
	//判断是否循环播放音乐
	boolean flag;
	private static FileInputStream fileInputStream[] = new FileInputStream[musicNumbers];
	private static String path[] = new String[musicNumbers];
	
	public Music() {
		flag = true;
		try {
			path[0] = "./music/游戏进行BGM.wav";
			path[1] = "./music/胜利BGM.wav";
			path[2] = "./music/失败BGM.wav";
			path[3] = "./music/进入点击区域.wav";
			path[4] = "./music/点击.wav";
			path[5] = "./music/点击空地.wav";
			path[6] = "./music/明明打完这场战.wav";
			path[7] = "./music/对不起哥哥.wav";
			path[8] = "./music/男死亡.wav";
			path[9] = "./music/女死亡.wav";
			path[10] = "./music/锯木头.wav";
			path[11] = "./music/出怪.wav";
			for(int i =0;i<path.length;i++) {
				fileInputStream[i] = new FileInputStream(path[i]);
				BGM[i] = new AudioStream(fileInputStream[i]);
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	//播放特定音乐
	public static void play(int i ,int isIn) {
		try {
			if (isIn == 0) {
				AudioPlayer.player.start(BGM[i]);
			}else if (isIn == 1) {
				AudioPlayer.player.start(BGM[i]);
				fileInputStream[i] = new FileInputStream(path[i]);
				BGM[i] = new AudioStream(fileInputStream[i]);
			}else {
				fileInputStream[i] = new FileInputStream(path[i]);
				BGM[i] = new AudioStream(fileInputStream[i]);
			}
		} catch (Exception e) {e.printStackTrace();}
		
	}
	//暂停
	public void stop(AudioStream music) {
		AudioPlayer.player.stop(music);
		flag = false;
	}
	//重新开始(仅供playingBGM使用)
	public void restart(AudioStream music) {
			flag = true;
			new Thread(this).start();
	}
	//循环播放游戏音乐
	public void run() { 
		// TODO 自动生成的方法存根
		//用来停止线程
		while(flag) {
			try {
				if (BGM[0].available()==0) {
					fileInputStream[0] = new FileInputStream("./music/游戏进行BGM.wav");
					BGM[0] = new AudioStream(fileInputStream[0]);
					play(0,0);
				}else {
					play(0,0);
					Thread.sleep(289300);				//BGM时长为289秒
				}
			} catch (IOException | InterruptedException e) {e.printStackTrace();}
		}
	}
}
