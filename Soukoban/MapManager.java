/**
 * �}�b�v���Ǘ�����N���X
 *
 * @author Ryota Ike
 */
public class MapManager{
	
	private MapMaker mapMaker;
	private static MapManager instance;
	
	/**
	 * �R���X�g���N�^
	 */
	private MapManager(){};
	private MapManager(String mapFileAddress) {
		this.mapMaker = MapMaker.getInstance(mapFileAddress);
	}
	
	/**
	 * �C���X�^���X���Ăяo�����\�b�h
	 */
	public static MapManager getInstance(String mapFileAddress) {
		if(instance == null) {
			instance = new MapManager(mapFileAddress);
		}
		return instance;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��' '�ɏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setBlank(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert(' ');
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'#'�ɏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setWall(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('#');
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'o'�ɏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setBoxOffGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('o');
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'0'�ɏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setBoxOnGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('0');
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'.'�ɏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('.');
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'p'�ɏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setPlayerOffGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('p');
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'P'�ɏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setPlayerOnGoal(int x, int y) {
		mapMaker.map[y][x] = mapMaker.convert('P');
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��K�؂Ȍ`�̃v���C���[('p'or'P')�ŏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setPlayer(int x, int y) {
		if(isGoal(x,y)) {
			setPlayerOnGoal(x,y);
		} else {
			setPlayerOffGoal(x,y);
		}
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��K�؂Ȍ`�̔�('o'or'0')�ŏ��������郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void setBox(int x, int y) {
		if(isGoal(x,y)) {
			setBoxOnGoal(x,y);
		} else {
			setBoxOffGoal(x,y);
		}
	}
	
	
	/**
	 * �}�b�v��̓���̏ꏊ��'#'���ǂ����𒲂ׂ郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 * @return ���茋��
	 */
	public boolean isWall(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('#'))
			return true;
		return false;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'o'���ǂ����𒲂ׂ郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 * @return ���茋��
	 */
	public boolean isBoxOffGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('o'))
			return true;
		return false;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'0'���ǂ����𒲂ׂ郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 * @return ���茋��
	 */
	public boolean isBoxOnGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('0'))
			return true;
		return false;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'.'���ǂ����𒲂ׂ郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 * @return ���茋��
	 */
	public boolean isGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('.'))
			return true;
		return false;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'p'���ǂ����𒲂ׂ郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 * @return ���茋��
	 */
	public boolean isPlayerOffGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('p'))
			return true;
		return false;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'P'���ǂ����𒲂ׂ郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 * @return ���茋��
	 */
	public boolean isPlayerOnGoal(int x, int y) {
		if(mapMaker.map[y][x] == mapMaker.convert('P'))
			return true;
		return false;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ��'0'��������'o'���ǂ����𒲂ׂ郁�\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 * @return ���茋��
	 */
	public boolean isBox(int x, int y) {
		if(isBoxOffGoal(x, y) || isBoxOnGoal(x, y))
			return true;
		return false;
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ����v���C���[���������\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void erasePlayer(int x, int y) {
		if(isPlayerOnGoal(x,y)) {
			setGoal(x,y);
		} else {
			setBlank(x,y);
		}
	}
	
	/**
	 * �}�b�v��̓���̏ꏊ���甠���������\�b�h
	 * 
	 * @param X���W
	 * @param Y���W
	 */
	public void eraseBox(int x, int y) {
		if(isBoxOnGoal(x,y)){
			setGoal(x,y);
		} else {
			setBlank(x,y);
		}
	}
	
	
	/**
	 * �}�b�v��\�����郁�\�b�h
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
	 * �N���A�����𖞂����Ă��邩�ǂ����𔻕ʂ��郁�\�b�h.
     * �S�[����ɂȂ��{�b�N�X('o')���Ȃ����true, ��������false��Ԃ�
	 * 
	 * @return �N���A���Ă邩�ǂ���
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