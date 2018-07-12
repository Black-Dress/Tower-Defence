package Player;

import java.awt.Image;
import java.awt.Toolkit;

public class imageGather {
	/*
	 * 各种panel的背景图片
	 * 0					loadingPanel的背景图
	 * 1					missionMap的背景图
	 * 2					playingPanel的第一关背景图
	 * 3					playingPanel的第二关背景图
	 */
	public static Image backgroundImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\mainmenu.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\missionMap.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\level1.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\level2.png")
	};
	/*
	 * start图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image startImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\startOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\startOn.png")
	};
	/*
	 * help图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image helpImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\helpOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\helpOn.png")
	};
	/*
	 * mission图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image missionImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\missionOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\missionOn.png")
	};
	/*
	 * back图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image backImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\backOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\backOn.png")
	};
	/*
	 * reStart图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image reStartImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\reStartOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\reStartOn.png")
	};
	/*
	 * music图标的图片
	 * 0					有音乐时没有得到光标时的图片
	 * 1					有音乐时得到光标时的图片
	 * 2					没有音乐时没有得到光标时的图片
	 * 3					没有音乐时得到光标时的图片
	 */
	public static Image musicImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\haveMusicOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\haveMusicOn.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\noMusicOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\noMusicOn.png")
	};
	/*
	 * stop图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image stopImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\stopOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\stopOn.png")
	};
	/*
	 * isStop图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image isStopImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\noStop.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\stop.png")
	};
	/*
	 * monsterCome图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image monsterComeImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\monsterOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\monsterOn.png")
	};
	/*
	 * build图标的图片
	 * 0					没有点击时的图片
	 * 1					点击后时的图片
	 */
	public static Image buildImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\nullTower.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\fillBuildPosition.png")
	};
	/*
	 * lastPage图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image lastPageImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\lastPageOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\lastPageOn.png")
	};
	/*
	 * nextPage图标的图片
	 * 0					没有得到光标时的图片
	 * 1					得到光标时的图片
	 */
	public static Image nextPageImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\nextPageOff.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\nextPageOn.png")
	};
	/*
	 * help界面的图片
	 * 0					帮助第一页的图片
	 * 1					帮助第二页的图片
	 * 2					帮助第三页的图片
	 */
	public static Image helpPanelImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\help1.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\help2.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\help3.png")
	};
	/*
	 * 游戏结束图标的图片
	 * 0					胜利时的图片
	 * 1					失败时的图片
	 */
	public static Image resultImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\victory.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\panel\\defeat.png")
	};
	
	public static Image enemy1[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/r1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/r2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/l1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/l2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/f1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/f2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/b1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e4/b2.png"),
	};
	public static Image enemy2[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/r1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/r2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/l1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/l2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/f1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/f2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/b1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e2/b2.png"),
	};
	public static Image enemy3[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/r1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/r2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/l1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/l2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/f1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/f2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/b1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/b2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e3/die1.png"),
	};
	public static Image enemy4[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/r1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/r2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/l1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/l2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/f1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/f2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/b1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e6/b2.png"),
	};
	public static Image enemy5[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/r1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/r2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/l1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/l2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/f1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/f2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/b1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e1/b2.png"),
	};
	public static Image enemy6[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/r1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/r2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/l1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/l2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/f1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/f2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/b1.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/b2.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/enemy/e5/die1.png"),
	};
	public static Image prop[] = new Image[]{
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\si.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\si1.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\si2.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\si3.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\si4.png"),
	};
	public static Image btn[]=new Image[] {
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\fu.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\fu2.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\btn.png"),
			Toolkit.getDefaultToolkit().getImage(".\\pic\\prop\\btn2.png"),
	};
	/*
	 * 箭塔图片
	 */
	public static Image arrowTowerImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/arrowTower/tower01.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/arrowTower/tower02.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/arrowTower/tower03.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/arrowTower/archer01.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/arrowTower/archer02.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/arrowTower/archer03.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/arrowTower/archer01.png")	
	};
	/*
	 * 魔法塔图片
	 */
	public static Image magicTowerImg[] = new Image[] {
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/magicTower/tower01.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/magicTower/tower02.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/magicTower/tower03.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/magicTower/witch00.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/magicTower/witch01.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/magicTower/witch02.png"),
			Toolkit.getDefaultToolkit().getImage("./pic/Towers/magicTower/witch03.png")
	};
}
