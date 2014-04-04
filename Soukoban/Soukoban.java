/**
 * 内定者アルバイトJava課題２ 倉庫番
 *
 * @version 1.0, 04 April, 2014
 * @author Ryota Ike
 */
 
public class Soukoban{
	//マップファイルの場所 
	private static final String MAP_1 = "map\\gamemap_1.txt";
	private static final String MAP_2 = "map\\gamemap_2.txt";
	private static final String MAP_3 = "map\\gamemap_3.txt";
	private static final String MAP_4 = "map\\gamemap_4.txt";
	
	public static void main(String[] args) {
		Game game = Game.getInstance(MAP_2);
		game.start();
	}
}
 
 
 
 