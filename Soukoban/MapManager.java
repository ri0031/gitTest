/**
 * マップを管理するクラス
 *
 * @author Ryota Ike
 */
public class MapManager{
	
	private MapMaker mapMaker;
	private static MapManager instance;
	
	/**
	 * コンストラクタ
	 */
	private MapManager(){};
	private MapManager(String mapFileAddress) {
		this.mapMaker = MapMaker.getInstance(mapFileAddress);
	}
	
	/**
	 * インスタンスを呼び出すメソッド
	 */
	public static MapManager getInstance(String mapFileAddress) {
		if(instance == null) {
			instance = new MapManager(mapFileAddress);
		}
		return instance;
	}
	
	/**
	 * マップ上の特定の場所を' 'に書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setBlank(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert(' ');
	}
	
	/**
	 * マップ上の特定の場所を'#'に書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setWall(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('#');
	}
	
	/**
	 * マップ上の特定の場所を'o'に書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setBoxOffGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('o');
	}
	
	/**
	 * マップ上の特定の場所を'0'に書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setBoxOnGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('0');
	}
	
	/**
	 * マップ上の特定の場所を'.'に書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('.');
	}
	
	/**
	 * マップ上の特定の場所を'p'に書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setPlayerOffGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('p');
	}
	
	/**
	 * マップ上の特定の場所を'P'に書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setPlayerOnGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('P');
	}
	
	/**
	 * マップ上の特定の場所を適切な形のプレイヤー('p'or'P')で書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setPlayer(int x, int y) {
		if(isGoal(x,y)) {
			setPlayerOnGoal(x,y);
		} else {
			setPlayerOffGoal(x,y);
		}
	}
	
	/**
	 * マップ上の特定の場所を適切な形の箱('o'or'0')で書き換えるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void setBox(int x, int y) {
		if(isGoal(x,y)) {
			setBoxOnGoal(x,y);
		} else {
			setBoxOffGoal(x,y);
		}
	}
	
	
	/**
	 * マップ上の特定の場所が'#'かどうかを調べるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 * @return 判定結果
	 */
	public boolean isWall(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('#'))
			return true;
		return false;
	}
	
	/**
	 * マップ上の特定の場所が'o'かどうかを調べるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 * @return 判定結果
	 */
	public boolean isBoxOffGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('o'))
			return true;
		return false;
	}
	
	/**
	 * マップ上の特定の場所が'0'かどうかを調べるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 * @return 判定結果
	 */
	public boolean isBoxOnGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('0'))
			return true;
		return false;
	}
	
	/**
	 * マップ上の特定の場所が'.'かどうかを調べるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 * @return 判定結果
	 */
	public boolean isGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('.'))
			return true;
		return false;
	}
	
	/**
	 * マップ上の特定の場所が'p'かどうかを調べるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 * @return 判定結果
	 */
	public boolean isPlayerOffGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('p'))
			return true;
		return false;
	}
	
	/**
	 * マップ上の特定の場所が'P'かどうかを調べるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 * @return 判定結果
	 */
	public boolean isPlayerOnGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('P'))
			return true;
		return false;
	}
	
	/**
	 * マップ上の特定の場所が'0'もしくは'o'かどうかを調べるメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 * @return 判定結果
	 */
	public boolean isBox(int x, int y) {
		if(isBoxOffGoal(x, y) || isBoxOnGoal(x, y))
			return true;
		return false;
	}
	
	/**
	 * マップ上の特定の場所からプレイヤーを消すメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void erasePlayer(int x, int y) {
		if(isPlayerOnGoal(x,y)) {
			setGoal(x,y);
		} else {
			setBlank(x,y);
		}
	}
	
	/**
	 * マップ上の特定の場所から箱を消すメソッド
	 * 
	 * @param X座標
	 * @param Y座標
	 */
	public void eraseBox(int x, int y) {
		if(isBoxOnGoal(x,y)){
			setGoal(x,y);
		} else {
			setBlank(x,y);
		}
	}
	
	
	/**
	 * マップを表示するメソッド
	 */
	public void printMap() {
		System.out.println();
		for(int i=0; i < mapMaker.map.length; i++) { 
      		for(int j=0; j < mapMaker.map[i].length; j++) {
        		System.out.print(mapMaker.convert(mapMaker.map[i][j]));
      		}
        	System.out.println();
    	}
	}
	
	/**
	 * クリア条件を満たしているかどうかを判別するメソッド.
     * ゴール上にないボックス('o')がなければtrue, あったらfalseを返す
	 * 
	 * @return クリアしてるかどうか
	 */
	public boolean isClear() {
		for(int i=0; i < mapMaker.map.length; i++) { 
      		for(int j=0; j < mapMaker.map[i].length; j++) {
      			if(isBoxOffGoal(j, i))
      				return false;
      		}
    	}
		return true;
	}
}