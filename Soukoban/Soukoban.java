/**
 * ����҃A���o�C�gJava�ۑ�Q �q�ɔ�
 *
 * @version 1.0, 04 April, 2014
 * @author Ryota Ike
 */
 
public class Soukoban{
	//�}�b�v�t�@�C���̏ꏊ 
	private static final String MAP_1 = "map\\gamemap_1.txt";
	private static final String MAP_2 = "map\\gamemap_2.txt";
	private static final String MAP_3 = "map\\gamemap_3.txt";
	private static final String MAP_4 = "map\\gamemap_4.txt";
	
	public static void main(String[] args) {
		Game game = Game.getInstance(MAP_2);
		game.start();
	}
}
 
 
 
 