package chapter7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test3_7_2_3 {

	/**
	 * @param args
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader a = new BufferedReader(new InputStreamReader(System.in));

		try {
			int count = 0;
			int x = (int) (Math.random() * 101);
			System.out.println(x);
			String b = a.readLine();
			int num = 0;
			while ((num = Integer.parseInt(b)) != x) {
				if (count == 9) {
					break;

				} else if (num > x) {
					System.out.println("xよりも大きい数字です");
				} else if (num < x) {
					System.out.println("xよりも小さい数字です");
				}
				count++;
				b = a.readLine();
			}
			if (num == x) {
				System.out.println("正解");
				System.out.println("正解するまでに" + (count + 1) + "回かかりました");
			} else {
				System.out.println("10回失敗");
			}
		} catch (NumberFormatException o) {
			System.out.println("数字以外が入力されています。");
		}

	}
}
