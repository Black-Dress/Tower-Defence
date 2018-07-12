package Player;

import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Music implements Runnable {

	public static int musicNumbers = 12;
	public static AudioStream []BGM = new AudioStream[musicNumbers];				
	/*
	 * 0    		��Ϸ�����е�BGM
	 * 1  			��Ϸʤ��BGM
	 * 2 			��Ϸʧ��BGM
	 * 3			��������Χʱ��Ч
	 * 4			�����Ч
	 * 5			����յغͷ�����ʱ��Ч
	 * 6 			���������ⳡս
	 * 7			�Һ͸���и�Լ��
	 * 8			������
	 * 9   			Ů����
	 * 10			��ľͷ
	 * 11			����
	 */
	//�ж��Ƿ�ѭ����������
	boolean flag;
	private static FileInputStream fileInputStream[] = new FileInputStream[musicNumbers];
	private static String path[] = new String[musicNumbers];
	
	public Music() {
		flag = true;
		try {
			path[0] = "./music/��Ϸ����BGM.wav";
			path[1] = "./music/ʤ��BGM.wav";
			path[2] = "./music/ʧ��BGM.wav";
			path[3] = "./music/����������.wav";
			path[4] = "./music/���.wav";
			path[5] = "./music/����յ�.wav";
			path[6] = "./music/���������ⳡս.wav";
			path[7] = "./music/�Բ�����.wav";
			path[8] = "./music/������.wav";
			path[9] = "./music/Ů����.wav";
			path[10] = "./music/��ľͷ.wav";
			path[11] = "./music/����.wav";
			for(int i =0;i<path.length;i++) {
				fileInputStream[i] = new FileInputStream(path[i]);
				BGM[i] = new AudioStream(fileInputStream[i]);
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	//�����ض�����
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
	//��ͣ
	public void stop(AudioStream music) {
		AudioPlayer.player.stop(music);
		flag = false;
	}
	//���¿�ʼ(����playingBGMʹ��)
	public void restart(AudioStream music) {
			flag = true;
			new Thread(this).start();
	}
	//ѭ��������Ϸ����
	public void run() { 
		// TODO �Զ����ɵķ������
		//����ֹͣ�߳�
		while(flag) {
			try {
				if (BGM[0].available()==0) {
					fileInputStream[0] = new FileInputStream("./music/��Ϸ����BGM.wav");
					BGM[0] = new AudioStream(fileInputStream[0]);
					play(0,0);
				}else {
					play(0,0);
					Thread.sleep(289300);				//BGMʱ��Ϊ289��
				}
			} catch (IOException | InterruptedException e) {e.printStackTrace();}
		}
	}
}
