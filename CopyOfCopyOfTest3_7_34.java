package chapter7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CopyOfCopyOfTest3_7_34 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		BufferedReader read = new BufferedReader(new InputStreamReader(
				System.in));
		int num = 5;
		int count = 0;
		int[] user = new int[num];
		int[] answer = getRandomNum(num);

		print(answer);

		System.out.println("5桁の数字を入力してください");

		String str = "";
		retry: while (count < 11) {// なにかあったら戻る
			if (count < 10) {
				str = read.readLine();
			}
			count++;

			gameOver(count);
			{
				if (count == 11) {
					break;
				}
			}
			// 文字列が入力されていないか調べる
			if (determineIfNumber(str)) {
				continue retry;
			}

			if (isKeta(str, num)) {
				continue retry;
			}

			System.out.print("user:");

			print(user = conversion(str, user));

			if (isOverlap(user)) {
				System.out.println("0～9までの数値を5桁で重複しないように入力してください。");
				continue retry;

			} // ヒットの判定
			int hit = 0;
			hit = hit(user, answer, hit);
			// ブロウ判定
			int blow = 0;
			blow = blow(user, answer, blow);

			// 結果
			result(hit, blow, answer, count);
			{
				if (hit == answer.length && count <= 10) {
					break;
				}
			}

		}
	}

	// 文字列が入力されていないか調べる
	public static boolean determineIfNumber(String str) {
		boolean flag = false;
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("文字列が入力されています");
			flag = true;
		}
		return flag;

	}

	// 配列の要素をすべて出力する
	public static void print(int[] array) {
		for (int i : array) {
			System.out.print(i);
		}
		System.out.println();
	}

	// 答えの生成１(0~9までの数値をシャッフル)
	public static int[] getShuffleNum() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// arrayをシャッフル
		for (int i = 0; i < array.length; i++) {
			// 0～9までの乱数
			int ran = (int) (Math.random() * 10);
			int a = array[i];
			array[i] = array[ran];
			array[ran] = a;
		}
		return array;

	}

	// 答えの生成２(num桁の数値にする)
	public static int[] getRandomNum(int keta) {
		int[] answer = new int[keta];
		int[] array = getShuffleNum();

		// 上でシャッフルした値の0～num番目の値を取得する
		for (int i = 0; i < answer.length; i++) {
			answer[i] = array[i];
		}
		// もし頭が0だった場合は一番お尻の値を入れる
		if (answer[0] == 0) {
			answer[0] = array[keta];
		}
		return answer;

	}

	/**
	 * 入力された数値の桁の正否を判断するメソッドです
	 *
	 * @param str
	 *            入力された文字列
	 * @param num
	 *            正しい桁数
	 * @return 入力した数値の桁が正しいとtrue、違うとfalseを返します
	 */
	public static boolean isKeta(String str, int num) {
		if (str.length() > num) {
			System.out.println("桁が多すぎます。");
			return true;

		} else if (str.length() < num) {
			System.out.println("桁が少なすぎます。");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 入力された数値に重複がないかのチェックをする
	 *
	 * @param user入力された数値の配列
	 * @return　重複があればtrue、なければfalseを返します
	 */
	// 入力した数値に重複がないかのチェック
	public static boolean isOverlap(int[] user) {
		for (int i = 0; i < user.length; i++) {
			for (int j = i + 1; j < user.length; j++) {
				if (user[i] == user[j]) {
					return true;
				}
			}
		}
		return false;
	}

	// １０回失敗するとゲームオーバーと出力
	public static void gameOver(int count) {
		if (count == 11) {
			System.out.println();
			System.out.println(count - 1 + "回失敗！ゲームオーバー");
		}
	}

	// 入力されたString型の数値をint型に変換する
	public static int[] conversion(String str, int[] user) {
		for (int j = 0; j < str.length(); j++) {
			char ch = str.charAt(j);
			user[j] = Character.getNumericValue(ch);
		}
		return user;
	}

	// hitの判定
	public static int hit(int[] user, int[] answer, int hit) {
		for (int i = 0; i < user.length; i++) {
			if (user[i] == answer[i]) {
				hit++;
			}
		}
		return hit;
	}

	// blowの判定
	public static int blow(int[] user, int[] answer, int blow) {
		for (int i = 0; i < user.length; i++) {
			for (int j = 0; j < answer.length; j++) {
				if (user[i] == answer[j]) {
					blow++;
				}
			}
		}
		return blow;
	}

	// 結果
	public static void result(int hit, int blow, int[] answer, int count) {
		if (hit == answer.length && count <= 10) {
			System.out.println();
			System.out.println("正解!! ");
			System.out.println("正解するまでに" + count + "回かかりました");
			System.out.println(" ");

		} else if (count <= 10) {
			System.out.println();
			System.out.println("HIT " + hit + "個、BLOW " + (blow - hit) + "個");
		}
	}
}