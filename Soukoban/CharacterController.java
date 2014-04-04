
/**
 * �v���C���[�̓����𐧌䂷��N���X
 */
public class CharacterController {

	private static int playerX;		//�v���C���[��X���W
	private static int playerY;		//�v���C���[��Y���W
	public static int moveCount;   //�ړ���
	
	private MapMaker mapMaker;
	private MapManager mapManager;
	private static CharacterController instance;
	
	/**
	 * �R���X�g���N�^
	 */
	private CharacterController(){}
	private CharacterController(String mapFileAddress){
		this.mapMaker = MapMaker.getInstance(mapFileAddress);
		this.mapManager = MapManager.getInstance(mapFileAddress);
		loadPlayerPosition();
		moveCount = 0;
	}
	
	/**
	 * �C���X�^���X���Ăяo�����\�b�h
	 */
	public static CharacterController getInstance(String mapFileAddress){
		if(instance == null) {
			instance = new CharacterController(mapFileAddress);
		}
		return instance;
	}

	/**
     * �v���C���[���E�֓��������\�b�h
	 */
	public void playerMoveRight() {
		playerMove(1,0);
	}
	
	/**
     * �v���C���[�����֓��������\�b�h
	 */
	public void playerMoveLeft() {
		playerMove(-1,0);
	}
	
	/**
     * �v���C���[����֓��������\�b�h
	 */
	public void playerMoveUp() {
		playerMove(0,-1);
	}
	
	/**
     * �v���C���[�����֓��������\�b�h
	 */
	public void playerMoveDown() {
		playerMove(0,1);
	}
	
	/**
     * �v���C���[�𓮂������\�b�h
	 * 
	 * @param X���W�̈ړ���
	 * @param Y���W�̈ړ���
	 */
	private void playerMove(int dx, int dy) {
		loadPlayerPosition();
		
		int newPlayerX = playerX + dx; //�ړ����X���W
		int newPlayerY = playerY + dy; //�ړ����Y���W
		
		int newBoxX = newPlayerX + dx; //box�̈ړ����X���W
		int newBoxY = newPlayerY + dy; //box�̈ړ����Y���W
		
		if(mapManager.isWall(newPlayerX, newPlayerY)){ 			//�ړ��悪�ǂ̂Ƃ�
			//�������Ȃ�
		} else if(mapManager.isBox(newPlayerX, newPlayerY)) { 	//�{�b�N�X�̂Ƃ�
			if(  mapManager.isBox(newBoxX, newBoxY) 
			  || mapManager.isWall(newBoxX, newBoxY)) { 		//�{�b�N�X�������Ȃ��Ƃ�
				//�������Ȃ�
			} else { 											//�{�b�N�X��������Ƃ�
				mapManager.eraseBox(newPlayerX, newPlayerY);
				mapManager.setBox(newBoxX,newBoxY);
				playerMove(dx, dy);
			}
		} else { 												//����ȊO
			mapManager.erasePlayer(playerX, playerY);
			mapManager.setPlayer(newPlayerX, newPlayerY);
			setPlayerPosition(newPlayerX, newPlayerY);
			moveCount++;
		} 
		
	}
	
	/**
     * �}�b�v����v���C���[�̏ꏊ���擾���郁�\�b�h
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
     * �v���C���[�̃|�W�V�������Z�b�g���郁�\�b�h
	 *
	 * @param X���W
	 * @param Y���W
	 */
	public void setPlayerPosition(int x, int y) {
		playerX = x;
		playerY = y;
	}
	
}
