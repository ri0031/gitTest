import java.util.Scanner;
import java.util.Stack;

/**
 * �Q�[���̓���Ɋւ��郁�\�b�h���܂Ƃ߂��N���X
 */
public class Game {
	
	private static final String MOVE_UP    = "w";
	private static final String MOVE_DOWN  = "s";
	private static final String MOVE_LEFT  = "a";
	private static final String MOVE_RIGHT = "d";
	private static final String MOVE_UNDO  = "u";
	private static final String MOVE_RESET = "@";
	
	private MapMaker mapMaker;
	private MapManager mapManager;
	private CharacterController characterController;
	
	private static Game instance;
	
	//�����}�b�v�ۊǗp
	private static int[][] initialMap;
	//undo�p�̃}�b�v��ۊǂ���X�^�b�N
	private static Stack<int[][]> undo = new Stack<int[][]>();
	
	private Game(){}
	private Game(String mapFileAddress) {
		mapMaker = MapMaker.getInstance(mapFileAddress);
		mapManager = MapManager.getInstance(mapFileAddress);
		characterController = CharacterController.getInstance(mapFileAddress);
		//�����}�b�v�̕ۊ�
		initialMap = new int[mapMaker.y][mapMaker.x];
		copyMap(MapMaker.map, initialMap);
	}
	
	public static Game getInstance(String mapFileAddress) {
		if(instance == null) {
			instance = new Game(mapFileAddress);
		}
		return instance;
	}
	
	/**
     * �^�C�g���̕\��
	 */
	private void printTitle() {
		System.out.println("�Q�Q�Q�Q�������Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�Q�����Q�Q");
		System.out.println("�Q�Q�Q���Q�Q�Q���Q�Q�Q�Q�Q���������������������Q�Q�Q�����������Q���Q�Q");
		System.out.println("�Q�Q���Q�������Q���Q�Q�Q�Q���Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q�Q���Q���Q���Q�Q�Q");
		System.out.println("�����Q�Q�Q�Q�Q�Q�Q�����Q�Q���������������������Q����������������������");
		System.out.println("�Q�Q���������������Q�Q�Q�Q���Q���Q�Q���Q�Q���Q�Q�Q�Q�Q���Q���Q���Q�Q�Q");
		System.out.println("�Q�Q���Q�Q�Q�Q�Q���Q�Q�Q�Q���Q���������������Q�Q�Q�Q���Q�Q���Q�Q���Q�Q");
		System.out.println("�Q�Q���������������Q�Q�Q�Q���Q���Q�Q���Q�Q���Q�Q����������������������");
		System.out.println("�Q�Q���Q�Q�Q�Q�Q�Q�Q�Q�Q�Q���Q���������������Q�Q�Q�Q���Q�Q���Q�Q���Q�Q");
		System.out.println("�Q���Q�������������Q�Q�Q�Q���Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q���������������Q�Q");
		System.out.println("���Q�Q���Q�Q�Q�Q���Q�Q�Q���Q�������������������Q�Q�Q���Q�Q���Q�Q���Q�Q");
		System.out.println("�Q�Q�Q�������������Q�Q�Q���Q�Q�Q�Q�Q���Q�Q�Q�Q�Q�Q�Q���������������Q�Q");
		System.out.println();
		
		System.out.println("Please Enter Key... Game Start");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}
	
	/**
	 * �Q�[���̃��C���ƂȂ郋�[�v
	 */
	public void start() {
		printTitle();
		while(!mapManager.isClear()) {//�N���A�����𖞂����܂�
			showGameScreen();
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			gameMove(input);
		}
		showClearScreen();
	}
	
	/**
	 * �Q�[���̊�{��ʂ�\�����郁�\�b�h.
	 * �}�b�v�A�ړ��񐔁A���j���[��\��
	 */
	private void showGameScreen() {
		mapManager.printMap();
		System.out.println("�ړ���: " + characterController.moveCount);
		System.out.println();
		System.out.println("�ړ�: (��->w, ��->a, ��->s, �E->d) + Enter");
		System.out.println("�߂�->u, ���Z�b�g->@");
	}

	/**
	 * �N���A��ʂ�\�����郁�\�b�h
	 */
	private void showClearScreen() {
		mapManager.printMap();
		System.out.println(characterController.moveCount + "��ڂŃN���A�ł��B���߂łƂ��������܂�");
	}
	
	/**
	 * �}�b�v�̓��e���R�s�[���郁�\�b�h
	 * 
	 * @param �R�s�[��
	 * @param �R�s�[��
	 */
	public void copyMap(int[][] map1, int[][] map2) {
		for(int i = 0; i < map1.length; i++) { 
      		for(int j = 0; j < map1[i].length; j++) {
        		map2[i][j] = map1[i][j];
      		}
    	}
	}
	
	/**
	 * ���͂ɍ��킹��playerMove���\�b�h���Ăяo�����\�b�h
	 * 
	 * @param ���͒l
	 */
	private void gameMove(String input) {
		switch(input) {
		case "w":
			storeMap();
			characterController.playerMoveUp();
			break;
		case "a":
			storeMap();
			characterController.playerMoveLeft();
			break;
		case "s":
			storeMap();
			characterController.playerMoveDown();
			break;
		case "d":
			storeMap();
			characterController.playerMoveRight();
			break;
		case "u":
			undoGame();
			break;
		case "@":
			resetGame();
			break;
		default:
			break;
		}
	}
	
	/**
	 * undo�p���\�b�h
	 * 
	 * @param ���͒l
	 */
	private void undoGame() {
		if(!undo.empty()) {
			copyMap(undo.pop(), mapMaker.map);
			characterController.moveCount -= 1;
		}
	}
	
	/**
	 * undo�p���\�b�h
	 * 
	 * @param ���͒l
	 */
	private void storeMap() {
		int[][] undoMap = new int[mapMaker.y][mapMaker.x];
		copyMap(mapMaker.map, undoMap);
		undo.push(undoMap);
	}
	
	/**
	 * ���Z�b�g�p���\�b�h
	 * 
	 * @param ���͒l
	 */
	private void resetGame() {
		copyMap(initialMap, mapMaker.map);
		characterController.moveCount = 0;
	}
	
}
