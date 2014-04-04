import java.io.*;
import java.util.Random;

/**
  * ����҃A���o�C�gJava�ۑ�P �u�q�b�g�A���h�u���[�v.
  *
  * @version 1.0, 26 March, 2014
  * @author �r ����
  */

public class MyHitAndBlow{
	public static void main(String[] args){
		String number;								//����
		int turn = 1;								//�^�[����
		String s = null;							//�v���C���[�̓��͒l�̕ۊ�
		boolean clear = false;						//�N���A���� true�Ȃ�N���A
		
		number = generateNumber();
		MyHitAndBlow hab = new MyHitAndBlow();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(!clear){
			System.out.println("4���̐��l����͂��Ă��������B(" + turn + "���)");
			try{
				s = reader.readLine();
			}catch(IOException e){
				System.out.println(e);	
			}
			if(hab.checkError(s)){
				clear = hab.checkHitBlow(s,number);
				turn++;
			}
		}
		
		System.out.println((turn-1)+"��ڂŃN���A�I");
		System.out.println("���߂łƂ��������܂��I");
	}
	
	/**
	 * �����_���Ȑ����𐶐����郁�\�b�h�B
	 * �e���̐������d�����Ȃ�0123�`9876�܂ł̂S���̐����������_���ɐ������A
	 * ������ŕԂ��B
	 * 
	 * @param  �Ȃ�
	 * @return ��������������
	 */
	public static String generateNumber(){
		String num;
		int[] numbers = {0,1,2,3,4,5,6,7,8,9};
		Random rdm = new Random();
		
		for(int i=0; i<10; i++){
			int x = rdm.nextInt(10);
			int temp = numbers[i];
			numbers[i] = numbers[x];
			numbers[x] = temp;
		}
		num = Integer.toString(numbers[0])+Integer.toString(numbers[1])+Integer.toString(numbers[2])+Integer.toString(numbers[3]);
		return num;
	}
	
	/**
	 * �����񂪃Q�[���̃��[���ɉ����Ă��邩�`�F�b�N���郁�\�b�h�B
	 * �������ꍇ��true��Ԃ��A
	 * �����Ă��Ȃ��ꍇ�͂��ꂼ��ɑΉ������G���[���b�Z�[�W��\��������false��Ԃ��B
	 * 
	 * @param s �`�F�b�N������������
	 * @return �`�F�b�N�̌��� 
	 */
	public boolean checkError(String s){
			if(s.length() != 4){
				System.out.println("���͂��S���ł͂���܂���");
				return false;
			}
			else if(!isInt(s)){
				System.out.println("���͂͐��l�݂̂ł�");	
				return false;
			}
			else if(Integer.parseInt(s) > 9876 || Integer.parseInt(s) < 123){
				System.out.println("���͂�0123�`9876�݂̂ł�");	
				return false;
			}
			else if(!isDup(s)){
				System.out.println("���͂��d�����Ă��܂�");	
				return false;
			}
		return true;
	}
	
	/**
	 * �����񂪐������ǂ����𔻒肷�郁�\�b�h
	 * �����̏ꍇ�͐������ꍇ��true��Ԃ��A
	 * ����ȊO��false��Ԃ��B
	 * 
	 * @param s ���肵����������
	 * @return  ����̌��� 
	 */
	public boolean isInt(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * ��������ɏd�����Ă��镶�������邩�ǂ������肷�郁�\�b�h
	 * �d�����Ȃ�����true��Ԃ��A
	 * �d��������Ƃ���false��Ԃ��B
	 * 
	 * @param s ���肵����������
	 * @return  ����̌��� 
	 */
	public boolean isDup(String s){
		for(int i=0; i<s.length()-1; i++){
			for(int j=i+1; j<s.length(); j++){
				if(s.charAt(i) == s.charAt(j))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * �q�b�g�ƃu���[�̐���\�����A�N���A���肷�郁�\�b�h
	 * �q�b�g�̐���4�Ȃ�true�A
	 * ����ӊO�Ȃ�false��Ԃ�
	 * 
	 * @param s   ���肵����������
	 * @param num ���
	 * @return    �N���A���� 
	 */
	public boolean checkHitBlow(String s, String num){
		int hit = 0;
		int blow = 0;
		for(int i=0; i<s.length() ; i++){
			char temp = s.charAt(i);
			if(num.indexOf(temp) == i){
				hit++;
			}else if(num.indexOf(temp) >= 0){
				blow++;
			}
		}
		System.out.println("�q�b�g�F:"+hit+", �u���E�F"+blow);
		if(hit==4)
			return true;
		else
			return false;
	}
	
	
}