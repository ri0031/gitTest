import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;

/**
 * マップデータが書かれたテキストファイルを読み込んで
 * int[][]型に変換したマップを作成するクラス
 *
 * @author Ryota Ike
 */
public class MapMaker {
	private static HashMap<Integer, Character> mapSymbol = new HashMap<Integer, Character>();
	private static HashMap<Character, Integer> mapNumber = new HashMap<Character, Integer>();
	
	/**
	 * 数字に変換されたマップ
	 */
	public static int[][] map;
	public static int x;
	public static int y;
	private static MapMaker instance;
	
	/**
	 * コンストラクタ
	 */
	private MapMaker(){}
	private MapMaker(String mapFileAddress) {
		mapSymbol.put(0, ' ');
		mapSymbol.put(1, '#');
		mapSymbol.put(2, 'o');
		mapSymbol.put(3, '0');
		mapSymbol.put(4, '.');
		mapSymbol.put(5, 'p');
		mapSymbol.put(6, 'P');
		
		mapNumber.put(' ', 0);
		mapNumber.put('#', 1);
		mapNumber.put('o', 2);
		mapNumber.put('0', 3);
		mapNumber.put('.', 4);
		mapNumber.put('p', 5);
		mapNumber.put('P', 6);	
		
		map = makeMap(mapFileAddress);
	}
	
	/**
	 * コンストラクタ
	 */
	public static MapMaker getInstance(String mapFileAddress) {
		if(instance == null) {
			instance = new MapMaker(mapFileAddress);
		}
		return instance;
	}
	
	/**
	 * 文字列のマップデータから
	 * int[][]型のマップを作成するメソッド
     *
	 * @param 文字列のマップデータ
	 * @return 
	 */
	public int[][] makeMap(String mapFileAddress) {
		ArrayList<String> mapData = readMapFile(mapFileAddress);
		x = getMapSizeX(mapData);
		y = getMapSizeY(mapData);
		int[][] map = new int[y][x];
		
		for(int i = 0; i < mapData.size(); i++) {
			for(int j = 0; j < mapData.get(i).length(); j++) {
				map[i][j] = convert(mapData.get(i).charAt(j));
			}
		}
		
		return map;
	}
	
	/**
	 * マップファイルを読みArrayListに保管するメソッド、
	 *
	 * @param マップデータを収納するArrayList
	 * @param マップファイルのアドレス
	 *
	 */
	public ArrayList<String> readMapFile(String mapFileAddress) {
		ArrayList<String> mapData = new ArrayList<String>();
		try {
			File file = new File(mapFileAddress);
			BufferedReader reader = new BufferedReader(new FileReader(file));
		
			String mapLine;
			while((mapLine = reader.readLine()) != null) {
				mapData.add(mapLine);
			}
			reader.close();
		} catch(FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
			//ここでエラーが出たときどうするかだよね
		} catch(IOException e) {
			System.out.println(e);
			e.printStackTrace();
			//ここも同じく
		}
		return mapData;
	}
	
	/**
	 * マップデータから最長の文字列を探し、
	 * そのlengthを返すメソッド
	 *
	 * @param マップデータ
	 * @return 最長の文字列のlength
	 */
	public int getMapSizeX(ArrayList<String> MapData) {
		int x = 0;
		for(int i = 0; i < MapData.size(); i++) {
			if(x < MapData.get(i).length())
				x = MapData.get(i).length();
		}
		return x;
	}
	
	/**
	 * マップデータの要素数を返すメソッド
	 *
	 * @param マップデータ
	 * @return 要素数
	 */
	public int getMapSizeY(ArrayList<String> MapData) {
		return MapData.size();
	}
	
	/**
	 * 数字から対応した文字への変換用メソッド
	 * 
	 * @param 変換したい数字
	 * @return 変換後の文字
	 */
	public char convert(int i) {
		return mapSymbol.get(i);
	}
	
	/**
	 * 文字から数字への変換用メソッド
	 * 
	 * @param 変換したい文字
	 * @return 変換後の数字
	 */
	public int convert(char symbol) {
		return mapNumber.get(symbol);
	}
}
