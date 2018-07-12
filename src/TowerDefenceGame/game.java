package TowerDefenceGame;

import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import myPanel.*;


public class game extends JFrame {
	private static final long serialVersionUID = -1241301477059177002L;
	CardLayout cl=new CardLayout();    //��Ƭ����
	loadingPanel myLoadingPanel;
	playingPanel myPlayingPanel;
	playingPanel2 myPlayingPanel2;
	missionPanel myMissionPanel;
	helpPanel	 myHelpPanel;
	public game() {
		setLayout(cl);
		myPlayingPanel = new playingPanel();
		myPlayingPanel2 = new playingPanel2();
		myHelpPanel	   = new helpPanel();
		myMissionPanel = new missionPanel(myPlayingPanel,myPlayingPanel2);
		myLoadingPanel = new loadingPanel(myMissionPanel,myHelpPanel);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				System.exit(0);
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
		});
		add(myLoadingPanel);
		add(myMissionPanel);
		add(myPlayingPanel);
		add(myPlayingPanel2);
		add(myHelpPanel);
		setResizable(false);
		setSize(1000,650);
		
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		game myGame = new game();
		myGame.setTitle("������Ϸ");
		myGame.setVisible(true);
		
	}

}
