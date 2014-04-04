import java.util.Scanner;
import java.util.Stack;

/**
 * ƒQ[ƒ€‚Ì“®ì‚ÉŠÖ‚·‚éƒƒ\ƒbƒh‚ğ‚Ü‚Æ‚ß‚½ƒNƒ‰ƒX
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
	
	//‰Šúƒ}ƒbƒv•ÛŠÇ—p
	private static int[][] initialMap;
	//undo—p‚Ìƒ}ƒbƒv‚ğ•ÛŠÇ‚·‚éƒXƒ^ƒbƒN
	private static Stack<int[][]> undo = new Stack<int[][]>();
	
	private Game(){}
	private Game(String mapFileAddress) {
		mapMaker = MapMaker.getInstance(mapFileAddress);
		mapManager = MapManager.getInstance(mapFileAddress);
		characterController = CharacterController.getInstance(mapFileAddress);
		//‰Šúƒ}ƒbƒv‚Ì•ÛŠÇ
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
     * ƒ^ƒCƒgƒ‹‚Ì•\¦
	 */
	private void printTitle() {
		System.out.println("QQQQ¡¡¡QQQQQQQQQQQ¡QQQQQQQQQQQQ¡¡QQ");
		System.out.println("QQQ¡QQQ¡QQQQQ¡¡¡¡¡¡¡¡¡¡QQQ¡¡¡¡¡Q¡QQ");
		System.out.println("QQ¡Q¡¡¡Q¡QQQQ¡QQQQ¡QQQQQQQQ¡Q¡Q¡QQQ");
		System.out.println("¡¡QQQQQQQ¡¡QQ¡¡¡¡¡¡¡¡¡¡Q¡¡¡¡¡¡¡¡¡¡¡");
		System.out.println("QQ¡¡¡¡¡¡¡QQQQ¡Q¡QQ¡QQ¡QQQQQ¡Q¡Q¡QQQ");
		System.out.println("QQ¡QQQQQ¡QQQQ¡Q¡¡¡¡¡¡¡QQQQ¡QQ¡QQ¡QQ");
		System.out.println("QQ¡¡¡¡¡¡¡QQQQ¡Q¡QQ¡QQ¡QQ¡¡¡¡¡¡¡¡¡¡¡");
		System.out.println("QQ¡QQQQQQQQQQ¡Q¡¡¡¡¡¡¡QQQQ¡QQ¡QQ¡QQ");
		System.out.println("Q¡Q¡¡¡¡¡¡QQQQ¡QQQQ¡QQQQQQQ¡¡¡¡¡¡¡QQ");
		System.out.println("¡QQ¡QQQQ¡QQQ¡Q¡¡¡¡¡¡¡¡¡QQQ¡QQ¡QQ¡QQ");
		System.out.println("QQQ¡¡¡¡¡¡QQQ¡QQQQQ¡QQQQQQQ¡¡¡¡¡¡¡QQ");
		System.out.println();
		
		System.out.println("Please Enter Key... Game Start");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}
	
	/**
	 * ƒQ[ƒ€‚ÌƒƒCƒ“‚Æ‚È‚éƒ‹[ƒv
	 */
	public void start() {
		printTitle();
		while(!mapManager.isClear()) {//ƒNƒŠƒAğŒ‚ğ–‚½‚·‚Ü‚Å
			showGameScreen();
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			gameMove(input);
		}
		showClearScreen();
	}
	
	/**
	 * ƒQ[ƒ€‚ÌŠî–{‰æ–Ê‚ğ•\¦‚·‚éƒƒ\ƒbƒh.
	 * ƒ}ƒbƒvAˆÚ“®‰ñ”Aƒƒjƒ…[‚ğ•\¦
	 */
	private void showGameScreen() {
		mapManager.printMap();
		System.out.println("ˆÚ“®‰ñ”: " + characterController.moveCount);
		System.out.println();
		System.out.println("ˆÚ“®: (ã->w, ¶->a, ‰º->s, ‰E->d) + Enter");
		System.out.println("–ß‚·->u, ƒŠƒZƒbƒg->@");
	}

	/**
	 * ƒNƒŠƒA‰æ–Ê‚ğ•\¦‚·‚éƒƒ\ƒbƒh
	 */
	private void showClearScreen() {
		mapManager.printMap();
		System.out.println(characterController.moveCount + "‰ñ–Ú‚ÅƒNƒŠƒA‚Å‚·B‚¨‚ß‚Å‚Æ‚¤‚²‚´‚¢‚Ü‚·");
	}
	
	/**
	 * ƒ}ƒbƒv‚Ì“à—e‚ğƒRƒs[‚·‚éƒƒ\ƒbƒh
	 * 
	 * @param ƒRƒs[Œ³
	 * @param ƒRƒs[æ
	 */
	public void copyMap(int[][] map1, int[][] map2) {
		for(int i = 0; i < map1.length; i++) { 
      		for(int j = 0; j < map1[i].length; j++) {
        		map2[i][j] = map1[i][j];
      		}
    	}
	}
	
	/**
	 * “ü—Í‚É‡‚í‚¹‚½playerMoveƒƒ\ƒbƒh‚ğŒÄ‚Ño‚·ƒƒ\ƒbƒh
	 * 
	 * @param “ü—Í’l
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
	 * undo—pƒƒ\ƒbƒh
	 * 
	 * @param “ü—Í’l
	 */
	private void undoGame() {
		if(!undo.empty()) {
			copyMap(undo.pop(), mapMaker.map);
			characterController.moveCount -= 1;
		}
	}
	
	/**
	 * undo—pƒƒ\ƒbƒh
	 * 
	 * @param “ü—Í’l
	 */
	private void storeMap() {
		int[][] undoMap = new int[mapMaker.y][mapMaker.x];
		copyMap(mapMaker.map, undoMap);
		undo.push(undoMap);
	}
	
	/**
	 * ƒŠƒZƒbƒg—pƒƒ\ƒbƒh
	 * 
	 * @param “ü—Í’l
	 */
	private void resetGame() {
		copyMap(initialMap, mapMaker.map);
		characterController.moveCount = 0;
	}
	
}
