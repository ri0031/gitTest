import java.util.Scanner;
import java.util.Stack;

/**
 * ゲームの動作に関するメソッドをまとめたクラス
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
	
	//初期マップ保管用
	private static int[][] initialMap;
	//undo用のマップを保管するスタック
	private static Stack<int[][]> undo = new Stack<int[][]>();
	
	private Game(){}
	private Game(String mapFileAddress) {
		mapMaker = MapMaker.getInstance(mapFileAddress);
		mapManager = MapManager.getInstance(mapFileAddress);
		characterController = CharacterController.getInstance(mapFileAddress);
		//初期マップの保管
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
     * タイトルの表示
	 */
	private void printTitle() {
		System.out.println("＿＿＿＿■■■＿＿＿＿＿＿＿＿＿＿＿■＿＿＿＿＿＿＿＿＿＿＿＿■■＿＿");
		System.out.println("＿＿＿■＿＿＿■＿＿＿＿＿■■■■■■■■■■＿＿＿■■■■■＿■＿＿");
		System.out.println("＿＿■＿■■■＿■＿＿＿＿■＿＿＿＿■＿＿＿＿＿＿＿＿■＿■＿■＿＿＿");
		System.out.println("■■＿＿＿＿＿＿＿■■＿＿■■■■■■■■■■＿■■■■■■■■■■■");
		System.out.println("＿＿■■■■■■■＿＿＿＿■＿■＿＿■＿＿■＿＿＿＿＿■＿■＿■＿＿＿");
		System.out.println("＿＿■＿＿＿＿＿■＿＿＿＿■＿■■■■■■■＿＿＿＿■＿＿■＿＿■＿＿");
		System.out.println("＿＿■■■■■■■＿＿＿＿■＿■＿＿■＿＿■＿＿■■■■■■■■■■■");
		System.out.println("＿＿■＿＿＿＿＿＿＿＿＿＿■＿■■■■■■■＿＿＿＿■＿＿■＿＿■＿＿");
		System.out.println("＿■＿■■■■■■＿＿＿＿■＿＿＿＿■＿＿＿＿＿＿＿■■■■■■■＿＿");
		System.out.println("■＿＿■＿＿＿＿■＿＿＿■＿■■■■■■■■■＿＿＿■＿＿■＿＿■＿＿");
		System.out.println("＿＿＿■■■■■■＿＿＿■＿＿＿＿＿■＿＿＿＿＿＿＿■■■■■■■＿＿");
		System.out.println();
		
		System.out.println("Please Enter Key... Game Start");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}
	
	/**
	 * ゲームのメインとなるループ
	 */
	public void start() {
		printTitle();
		while(!mapManager.isClear()) {//クリア条件を満たすまで
			showGameScreen();
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			gameMove(input);
		}
		showClearScreen();
	}
	
	/**
	 * ゲームの基本画面を表示するメソッド.
	 * マップ、移動回数、メニューを表示
	 */
	private void showGameScreen() {
		mapManager.printMap();
		System.out.println("移動回数: " + characterController.moveCount);
		System.out.println();
		System.out.println("移動: (上->w, 左->a, 下->s, 右->d) + Enter");
		System.out.println("戻す->u, リセット->@");
	}

	/**
	 * クリア画面を表示するメソッド
	 */
	private void showClearScreen() {
		mapManager.printMap();
		System.out.println(characterController.moveCount + "回目でクリアです。おめでとうございます");
	}
	
	/**
	 * マップの内容をコピーするメソッド
	 * 
	 * @param コピー元
	 * @param コピー先
	 */
	public void copyMap(int[][] map1, int[][] map2) {
		for(int i = 0; i < map1.length; i++) { 
      		for(int j = 0; j < map1[i].length; j++) {
        		map2[i][j] = map1[i][j];
      		}
    	}
	}
	
	/**
	 * 入力に合わせたplayerMoveメソッドを呼び出すメソッド
	 * 
	 * @param 入力値
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
	 * undo用メソッド
	 * 
	 * @param 入力値
	 */
	private void undoGame() {
		if(!undo.empty()) {
			copyMap(undo.pop(), mapMaker.map);
			characterController.moveCount -= 1;
		}
	}
	
	/**
	 * undo用メソッド
	 * 
	 * @param 入力値
	 */
	private void storeMap() {
		int[][] undoMap = new int[mapMaker.y][mapMaker.x];
		copyMap(mapMaker.map, undoMap);
		undo.push(undoMap);
	}
	
	/**
	 * リセット用メソッド
	 * 
	 * @param 入力値
	 */
	private void resetGame() {
		copyMap(initialMap, mapMaker.map);
		characterController.moveCount = 0;
	}
	
}
