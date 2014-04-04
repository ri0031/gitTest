import java.io.*;
import java.util.Random;

/**
  * 内定者アルバイトJava課題１ 「ヒットアンドブロー」.
  *
  * @version 1.0, 26 March, 2014
  * @author 池 僚太
  */

public class MyHitAndBlow{
	public static void main(String[] args){
		String number;								//答え
		int turn = 1;								//ターン数
		String s = null;							//プレイヤーの入力値の保管
		boolean clear = false;						//クリア判定 trueならクリア
		
		number = generateNumber();
		MyHitAndBlow hab = new MyHitAndBlow();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(!clear){
			System.out.println("4桁の数値を入力してください。(" + turn + "回目)");
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
		
		System.out.println((turn-1)+"回目でクリア！");
		System.out.println("おめでとうございます！");
	}
	
	/**
	 * ランダムな数字を生成するメソッド。
	 * 各桁の数字が重複しない0123～9876までの４桁の整数をランダムに生成し、
	 * 文字列で返す。
	 * 
	 * @param  なし
	 * @return 生成した文字列
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
	 * 文字列がゲームのルールに沿っているかチェックするメソッド。
	 * 正しい場合はtrueを返し、
	 * 沿っていない場合はそれぞれに対応したエラーメッセージを表示した後falseを返す。
	 * 
	 * @param s チェックしたい文字列
	 * @return チェックの結果 
	 */
	public boolean checkError(String s){
			if(s.length() != 4){
				System.out.println("入力が４桁ではありません");
				return false;
			}
			else if(!isInt(s)){
				System.out.println("入力は数値のみです");	
				return false;
			}
			else if(Integer.parseInt(s) > 9876 || Integer.parseInt(s) < 123){
				System.out.println("入力は0123～9876のみです");	
				return false;
			}
			else if(!isDup(s)){
				System.out.println("入力が重複しています");	
				return false;
			}
		return true;
	}
	
	/**
	 * 文字列が整数かどうかを判定するメソッド
	 * 整数の場合は正しい場合はtrueを返し、
	 * それ以外はfalseを返す。
	 * 
	 * @param s 判定したい文字列
	 * @return  判定の結果 
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
	 * 文字列内に重複している文字があるかどうか判定するメソッド
	 * 重複がない時にtrueを返し、
	 * 重複があるときはfalseを返す。
	 * 
	 * @param s 判定したい文字列
	 * @return  判定の結果 
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
	 * ヒットとブローの数を表示し、クリア判定するメソッド
	 * ヒットの数が4ならtrue、
	 * それ意外ならfalseを返す
	 * 
	 * @param s   判定したい文字列
	 * @param num 問題
	 * @return    クリア判定 
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
		System.out.println("ヒット：:"+hit+", ブロウ："+blow);
		if(hit==4)
			return true;
		else
			return false;
	}
	
	
}