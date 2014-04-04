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
 * �}�b�v�f�[�^�������ꂽ�e�L�X�g�t�@�C����ǂݍ����
 * int[][]�^�ɕϊ������}�b�v���쐬����N���X
 *
 * @author Ryota Ike
 */
public class MapMaker {
	private static HashMap<Integer, Character> mapSymbol = new HashMap<Integer, Character>();
	private static HashMap<Character, Integer> mapNumber = new HashMap<Character, Integer>();
	
	/**
	 * �����ɕϊ����ꂽ�}�b�v
	 */
	public static int[][] map;
	public static int x;
	public static int y;
	private static MapMaker instance;
	
	/**
	 * �R���X�g���N�^
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
	 * �R���X�g���N�^
	 */
	public static MapMaker getInstance(String mapFileAddress) {
		if(instance == null) {
			instance = new MapMaker(mapFileAddress);
		}
		return instance;
	}
	
	/**
	 * ������̃}�b�v�f�[�^����
	 * int[][]�^�̃}�b�v���쐬���郁�\�b�h
     *
	 * @param ������̃}�b�v�f�[�^
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
	 * �}�b�v�t�@�C����ǂ�ArrayList�ɕۊǂ��郁�\�b�h�A
	 *
	 * @param �}�b�v�f�[�^�����[����ArrayList
	 * @param �}�b�v�t�@�C���̃A�h���X
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
			//�����ŃG���[���o���Ƃ��ǂ����邩�����
		} catch(IOException e) {
			System.out.println(e);
			e.printStackTrace();
			//������������
		}
		return mapData;
	}
	
	/**
	 * �}�b�v�f�[�^����Œ��̕������T���A
	 * ����length��Ԃ����\�b�h
	 *
	 * @param �}�b�v�f�[�^
	 * @return �Œ��̕������length
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
	 * �}�b�v�f�[�^�̗v�f����Ԃ����\�b�h
	 *
	 * @param �}�b�v�f�[�^
	 * @return �v�f��
	 */
	public int getMapSizeY(ArrayList<String> MapData) {
		return MapData.size();
	}
	
	/**
	 * ��������Ή����������ւ̕ϊ��p���\�b�h
	 * 
	 * @param �ϊ�����������
	 * @return �ϊ���̕���
	 */
	public char convert(int i) {
		return mapSymbol.get(i);
	}
	
	/**
	 * �������琔���ւ̕ϊ��p���\�b�h
	 * 
	 * @param �ϊ�����������
	 * @return �ϊ���̐���
	 */
	public int convert(char symbol) {
		return mapNumber.get(symbol);
	}
}
