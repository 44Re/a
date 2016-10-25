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
					System.out.println("x‚æ‚è‚à‘å‚«‚¢”š‚Å‚·");
				} else if (num < x) {
					System.out.println("x‚æ‚è‚à¬‚³‚¢”š‚Å‚·");
				}
				count++;
				b = a.readLine();
			}
			if (num == x) {
				System.out.println("³‰ğ");
				System.out.println("³‰ğ‚·‚é‚Ü‚Å‚É" + (count + 1) + "‰ñ‚©‚©‚è‚Ü‚µ‚½");
			} else {
				System.out.println("10‰ñ¸”s");
			}
		} catch (NumberFormatException o) {
			System.out.println("”šˆÈŠO‚ª“ü—Í‚³‚ê‚Ä‚¢‚Ü‚·B");
		}

	}
}
