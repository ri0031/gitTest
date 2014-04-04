
/**
 * プレイヤーの動きを制御するクラス
 */
public class CharacterController {

	private static int playerX;		//プレイヤーのX座標
	private static int playerY;		//プレイヤーのY座標
	public static int moveCount;   //移動回数
	
	private MapMaker mapMaker;
	private MapManager mapManager;
	private static CharacterController instance;
	
	/**
	 * コンストラクタ
	 */
	private CharacterController(){}
	private CharacterController(String mapFileAddress){
		this.mapMaker = MapMaker.getInstance(mapFileAddress);
		this.mapManager = MapManager.getInstance(mapFileAddress);
		loadPlayerPosition();
		moveCount = 0;
	}
	
	/**
	 * インスタンスを呼び出すメソッド
	 */
	public static CharacterController getInstance(String mapFileAddress){
		if(instance == null) {
			instance = new CharacterController(mapFileAddress);
		}
		return instance;
	}

	/**
     * プレイヤーを右へ動かすメソッド
	 */
	public void playerMoveRight() {
		playerMove(1,0);
	}
	
	/**
     * プレイヤーを左へ動かすメソッド
	 */
	public void playerMoveLeft() {
		playerMove(-1,0);
	}
	
	/**
     * プレイヤーを上へ動かすメソッド
	 */
	public void playerMoveUp() {
		playerMove(0,-1);
	}
	
	/**
     * プレイヤーを下へ動かすメソッド
	 */
	public void playerMoveDown() {
		playerMove(0,1);
	}
	
	/**
     * プレイヤーを動かすメソッド
	 * 
	 * @param X座標の移動量
	 * @param Y座標の移動量
	 */
	private void playerMove(int dx, int dy) {
		loadPlayerPosition();
		
		int newPlayerX = playerX + dx; //移動先のX座標
		int newPlayerY = playerY + dy; //移動先のY座標
		
		int newBoxX = newPlayerX + dx; //boxの移動先のX座標
		int newBoxY = newPlayerY + dy; //boxの移動先のY座標
		
		if(mapManager.isWall(newPlayerX, newPlayerY)){ 			//移動先が壁のとき
			//何もしない
		} else if(mapManager.isBox(newPlayerX, newPlayerY)) { 	//ボックスのとき
			if(  mapManager.isBox(newBoxX, newBoxY) 
			  || mapManager.isWall(newBoxX, newBoxY)) { 		//ボックスが動けないとき
				//何もしない
			} else { 											//ボックスが動けるとき
				mapManager.eraseBox(newPlayerX, newPlayerY);
				mapManager.setBox(newBoxX,newBoxY);
				playerMove(dx, dy);
			}
		} else { 												//それ以外
			mapManager.erasePlayer(playerX, playerY);
			mapManager.setPlayer(newPlayerX, newPlayerY);
			setPlayerPosition(newPlayerX, newPlayerY);
			moveCount++;
		} 
		
	}
	
	/**
     * マップからプレイヤーの場所を取得するメソッド
	 */
	public void loadPlayerPosition() {
		for(int i=0; i < mapMaker.map.length; i++) { 
      		for(int j=0; j < mapMaker.map[i].length; j++) {
      			if(mapManager.isPlayerOnGoal(j,i)||mapManager.isPlayerOffGoal(j,i)){
      				setPlayerPosition(j, i);
      			}
      		}
		}
	}
	
	/**
     * プレイヤーのポジションをセットするメソッド
	 *
	 * @param X座標
	 * @param Y座標
	 */
	public void setPlayerPosition(int x, int y) {
		playerX = x;
		playerY = y;
	}
	
}
